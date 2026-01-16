/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.ibm.tw.ibmb.component.log;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.net.URLEncodedUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ibm.tw.commons.gson.GsonHelper;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.base.model.RqData;
import com.ibm.tw.ibmb.base.model.RsData;
import com.ibm.tw.ibmb.component.context.ExecutionContext;
import com.ibm.tw.ibmb.component.context.MDCKey;

import io.micrometer.core.instrument.util.StringEscapeUtils;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

/**
 * Async log processor
 * 
 * @author horance
 *
 */
@SuppressWarnings("deprecation")
@Component
public class AsyncLogProcessor implements InitializingBean {
    protected static final IBLog APILogger = IBLog.getLog("API_LOGGER");
    protected static final IBLog logger = IBLog.getLog(AsyncLogProcessor.class);
    protected static final IBLog traceLogDataLogger = IBLog.getLog("ESB_TRACE_DATA_LOG");
    protected static final IBLog traceLogLogger = IBLog.getLog("ESB_TRACE_LOG");
    protected static final IBLog chlRQLogger = IBLog.getLog("CHL_RQRS_LOGGER");
    protected static final IBLog B2CAccessLogger = IBLog.getLog("B2C_ACCESS_LOG");

    private String[] fieldsToHide;
    @Autowired
    private Environment environment;

    private Gson gson = null;

    @Value("${aibank.log.replacement-for-hidden-field:XXXX}")
    private String replaceCode;

    /**
     * 處理 API logging 紀錄
     * 
     * @param context
     * @param execRequest
     * @param execResponse
     * @throws IOException
     */
    public void doApiLogging(ExecutionContext context, ServletRequest execRequest, ServletResponse execResponse, long startTime, long endTime) throws IOException {
        // process request logging
        Map<String, String> requestLog = formatRequestLog(context, execRequest, startTime);
        // process response logging
        Map<String, String> responseLog = formatResponseLog(context, execResponse, endTime);
        // process data log & hidden
        processHiddenFieldsAndWriteApiLog(requestLog, responseLog);
    }

    @Async
    protected void processHiddenFieldsAndWriteApiLog(Map<String, String> requestLog, Map<String, String> responseLog) {
        String body = processDataMasking(requestLog.get("contentType"), requestLog.get("body"));
        requestLog.put("body", body);
        body = processHiddenFieldsForJSON(responseLog.get("body"));
        responseLog.put("body", body);
        APILogger.debugNoEncode(gson.toJson(requestLog));
        APILogger.debugNoEncode(gson.toJson(responseLog));
    }

    /**
     * 將 response 資訊轉換為log string
     * 
     * @param context
     * @param execRequest
     * @param startTime
     * @return
     * @throws IOException
     */
    private Map<String, String> formatResponseLog(ExecutionContext context, ServletResponse execResponse, long startTime) throws IOException {
        if (!(execResponse instanceof ContentCachingResponseWrapper)) {
            // not logging if context not cached
            return null;
        }
        ContentCachingResponseWrapper response = (ContentCachingResponseWrapper) execResponse;

        int httpStatus = response.getStatus();
        context.put(MDCKey.httpStatus.name(), String.valueOf(httpStatus));

        Map<String, String> parts = prepareCommonParts(context, startTime);

        String body = "";
        try {
            body = readResponseContent(response);
        }
        catch (UnsupportedEncodingException e) {
            body = "errro reading request body:" + e.getMessage();
        }
        parts.put("body", body);
        // flush result before return
        response.copyBodyToResponse();
        return parts;
    }

    private String readResponseContent(ContentCachingResponseWrapper response) throws UnsupportedEncodingException {
        return new String(response.getContentAsByteArray(), "UTF-8");
    }

    /**
     * 將 request 資訊轉換為log string
     * 
     * @param context
     * @param execRequest
     * @param startTime
     * @return
     */
    private Map<String, String> formatRequestLog(ExecutionContext context, ServletRequest execRequest, long startTime) {
        if (!(execRequest instanceof ContentCachingRequestWrapper)) {
            // not logging if context not cached
            return null;
        }
        ContentCachingRequestWrapper request = (ContentCachingRequestWrapper) execRequest;
        // [yyyyMMddHHmmss.SSS][httpMethod][httpStatus][path][txnNo][corpIdNum][userId][authorityCode][sessionid][funcCode]{input}

        String methodStr = request.getMethod();
        StringBuffer requestURLBuffer = request.getRequestURL();
        String queryString = request.getQueryString();
        if (StringUtils.isNotBlank(queryString)) {
            requestURLBuffer.append("?").append(queryString);
        }
        String requestURI = requestURLBuffer.toString();
        int httpStatus = -1;
        context.put(MDCKey.httpMethod.name(), methodStr);
        context.put(MDCKey.requestURI.name(), requestURI);
        context.put(MDCKey.httpStatus.name(), String.valueOf(httpStatus));

        String contentType = request.getContentType();

        Map<String, String> parts = prepareCommonParts(context, startTime);
        parts.put("contentType", contentType);

        parts.put("body", readRequestContent(request));
        return parts;
    }

    private String processDataMasking(String contentType, String content) {
        if (MediaType.APPLICATION_JSON_VALUE.equals(contentType)) {
            return processHiddenFieldsForJSON(content);
        }
        else if (MediaType.APPLICATION_FORM_URLENCODED_VALUE.equals(contentType)) {
            return processHiddenFieldsForQueryString(content);
        }
        return content;
    }

    private String processHiddenFieldsForQueryString(String content) {
        List<NameValuePair> paramList = URLEncodedUtils.parse(content, StandardCharsets.UTF_8);
        List<String> processedString = new ArrayList<>(paramList.size());
        paramList.forEach(p -> {
            if (isNeedHide(p.getName())) {
                processedString.add(p.getName() + "=" + replaceCode);
            }
            else {
                processedString.add(p.getName() + "=" + p.getValue());
            }
        });
        return StringUtils.join(processedString, "&");
    }

    @SuppressWarnings("unchecked")
    private String processHiddenFieldsForJSON(String content) {
        if (StringUtils.isBlank(content)) {
            return content;
        }
        ObjectMapper mapper = new ObjectMapper().enable(Feature.ALLOW_COMMENTS);
        Map<String, Object> body = null;
        try {
            body = mapper.readValue(content, HashMap.class);
            hideJsonElement(body);
            return mapper.writeValueAsString(body);
        }
        catch (JsonProcessingException e) {
            logger.warn("error process hidden fields", e);
        }
        // hidden field failed, return original body
        return content;
    }

    @SuppressWarnings("unchecked")
    private String processHiddenFieldsForJSON(Object content) {
        ObjectMapper mapper = new ObjectMapper().enable(Feature.ALLOW_COMMENTS);
        Map<String, Object> body = null;
        try {
            body = mapper.convertValue(content, HashMap.class);
            hideJsonElement(body);
            return mapper.writeValueAsString(body);
        }
        catch (JsonProcessingException e) {
            logger.warn("error process hidden fields", e);
        }
        // hidden field failed, return original body
        return gson.toJson(content);
    }

    private void hideJsonElement(Map<String, Object> element) {
        for (Entry<String, Object> entry : element.entrySet()) {
            String name = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                if (isNeedHide(name)) {
                    entry.setValue(replaceCode);
                }
            }
            else {
                processFieldValue(name, value);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void processFieldValue(String name, Object value) {
        if (value == null) {
            return;
        }
        if (value instanceof Map) {
            hideJsonElement((Map<String, Object>) value);
        }
        else if (value instanceof Collection) {
            Collection<Object> list = (Collection<Object>) value;
            for (Object e : list) {
                processFieldValue(name, e);
            }
        }
        else if (value.getClass().isArray()) {
            int length = Array.getLength(value);
            for (int i = 0; i < length; i++) {
                processFieldValue(name, Array.get(value, i));
            }
        }
    }

    private boolean isNeedHide(String name) {
        return StringUtils.equalsAnyIgnoreCase(name, fieldsToHide);
    }

    private final XMLInputFactory createXMLInputFactory() {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false); // 禁用外部實體
        factory.setProperty(XMLInputFactory.SUPPORT_DTD, false); // 禁用 DTD
        return factory;
    }

    private String processHiddenFieldsForXML(String xml) {
        XMLEventReader eventReader = null;
        XMLEventWriter eventWriter = null;
        try (StringWriter stringWriter = new StringWriter();) {
            XMLInputFactory inputFactory = createXMLInputFactory();
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            eventReader = inputFactory.createXMLEventReader(new StringReader(xml));
            eventWriter = outputFactory.createXMLEventWriter(stringWriter);
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    eventWriter.add(event);
                    StartElement startElement = event.asStartElement();
                    String elementName = startElement.getName().getLocalPart();

                    if (isNeedHide(elementName)) {
                        event = eventReader.nextEvent(); // Move to the next event (characters)

                        if (event instanceof Characters) {
                            eventWriter.add(eventFactory.createCharacters(replaceCode));
                        }
                    }
                }
                else {
                    eventWriter.add(event);

                }
            }

            eventWriter.close();
            eventReader.close();

            String modifiedXml = stringWriter.toString();
            return modifiedXml;
        }
        catch (XMLStreamException | NoSuchElementException | IOException e) {
            logger.warn("error processing hiding xml data, output unprocessed value", e);
        }
        finally {
            if (eventReader != null) {
                try {
                    eventReader.close();
                }
                catch (XMLStreamException e) {
                    logger.warn("error closing xml reader", e);
                }
            }
            if (eventWriter != null) {
                try {
                    eventWriter.close();
                }
                catch (XMLStreamException e) {
                    logger.warn("error closing xml writer", e);
                }
            }
        }
        return xml;
    }

    private Map<String, String> prepareCommonParts(ExecutionContext context, long ts) {
        Map<String, String> parts = new HashMap<>();
        for (MDCKey key : MDCKey.values()) {
            String value = context.get(key.name(), "-");
            if (key.isNeedMask()) {
                switch (key) {
                case custid:
                    value = DataMaskUtil.maskIdNo(value);
                    break;
                case userid:
                    value = DataMaskUtil.maskExt(value);
                    break;
                default:
                    value = "***";
                    break;
                }
            }
            parts.put(key.name(), value);
        }
        return parts;
    }

    /**
     * 取得 request content GET/HEAD/DELETE 沒有 body
     * 
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    private String readRequestContent(ContentCachingRequestWrapper request) {
        HttpMethod method = HttpMethod.valueOf(request.getMethod());
        String body = "";
        // GET/HEAD/DELETE 沒有 body
        switch (method.name()) {
        case "GET":
        case "DELETE":
        case "HEAD":
            body = "";
            break;
        default:
            body = new String(request.getContentAsByteArray(), StandardCharsets.UTF_8);
            break;
        }
        return body;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String setting = environment.getProperty("aibank.log.fields-to-hide");
        fieldsToHide = StringUtils.isBlank(setting) ? new String[0] : StringUtils.split(setting, ",");
        gson = GsonHelper.newInstance().newBuilder().disableHtmlEscaping().create();
    }

    @Async
    public void doTraceLog(ITraceLogDataEntity<?, ?> traceLog) {
        String traceLogData = traceLog.getTraceLogData();
        String formattedData = processHiddenFieldsForXML(traceLogData);
        traceLog.setTraceLogData(StringEscapeUtils.escapeJson(formattedData));
        traceLogDataLogger.infoNoEncode(gson.toJson(traceLog));
    }

    @Async
    public void doRQLog(String loggerFormat, String taskId, String method, RqData rqBody) {
        if (chlRQLogger.isDebugEnabled()) {
            String rqBodyStr = processHiddenFieldsForJSON(rqBody);
            chlRQLogger.debug(loggerFormat, taskId, method, rqBodyStr);
        }
    }

    @Async
    public void doRSLog(String loggerFormat, String taskId, String method, RsData rsBody) {
        if (chlRQLogger.isDebugEnabled()) {
            String rsBodyStr = processHiddenFieldsForJSON(rsBody);
            chlRQLogger.debug(loggerFormat, taskId, method, rsBodyStr);
        }
    }

    @Async
    public void doTraceLogMetaData(ITraceLogEntity<String> traceLog) {
        traceLogLogger.errorNoEncode(gson.toJson(traceLog));
    }

    @Async
    public void doB2CAccessLog(String accessLogData) {
        B2CAccessLogger.infoNoEncode(accessLogData);
    }

}
