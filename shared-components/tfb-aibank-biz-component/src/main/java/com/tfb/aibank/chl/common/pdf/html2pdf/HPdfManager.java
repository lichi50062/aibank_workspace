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
package com.tfb.aibank.chl.common.pdf.html2pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.lang3.LocaleUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.ToolContext;
import org.apache.velocity.tools.generic.ResourceTool;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.context.MDCKey;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ValidateParamUtils;
import com.pd4ml.Constants;
import com.pd4ml.Dimensions.Units;
import com.pd4ml.PD4ML;
import com.pd4ml.PageMargins;
import com.pd4ml.PageSize;
import com.pd4ml.util.Base;

// @formatter:off
/**
 * @(#)HPdfManager.java
 * 
 * <p>Description:html->pdf 功能管理</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/12, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class HPdfManager extends HPdfContants {

    @Autowired
    protected SystemParamCacheManager systemParamCacheManager;

    @Value("${aibank.common.pd4ml.activation-key:}")
    private String pd4mlLicenseKey;

    /** PDF Permissions */
    private int miPermissions = Constants.AllowPrint | Constants.AllowCopy; // Print + Copy

    /**
     * 產生PDF
     *
     * @param sContent
     * @param sContextPath
     * @param sURI
     * @param os
     * @param exportName
     * @throws IOException
     * @throws SAXException
     * @throws TransformerException
     * @throws ParserConfigurationException
     */
    public void doCreate(String templateName, Object params, String password, String exportName, ByteArrayOutputStream out) throws IOException, SAXException, TransformerException, ParserConfigurationException {
        doCreate(templateName, params, password, exportName, format, out);
    }

    /**
     * 產生PDF
     *
     * @param sContent
     * @param sContextPath
     * @param sURI
     * @param os
     * @param exportName
     * @throws IOException
     * @throws SAXException
     * @throws TransformerException
     * @throws ParserConfigurationException
     */
    public void doCreate(String templateName, Object params, String password, String exportName, PageSize pageSize, ByteArrayOutputStream out) throws IOException {

        PD4ML pd4ml = new PD4ML(this.pd4mlLicenseKey);
        pd4ml.setParam(Constants.PD4ML_DOCUMENT_VIEW_MODE, "OneColumn");

        Map<String, String> m = new HashMap<>();
        m.put(Constants.PD4ML_CACHE_ENABLE, "true");
        pd4ml.setDynamicData(m);

        pd4ml.setPageSize(pageSize);
        pd4ml.useTTF("java:fonts", true);
        pd4ml.setLogLevel(Base.LOG_DEBUG);
        String sContent = getPDFContents(templateName, params);

        sContent = fixTxtBalanceSPAN(sContent);
        sContent = fixPD4MLNonSupported(sContent);
        /*
         * AIX Chinese TureType Fonts Monotype Sans Duospace WT TC TW Monotype Sans WT TC TW Times New Roman WT TC TW
         */

        pd4ml.addStyle(PDF_FONT, true);

        if (UNITS.equals("mm")) {
            PageMargins margins = new PageMargins(LEFT, TOP, RIGHT, BOTTOM, Units.MM);
            pd4ml.setPageMargins(margins);
        }
        else {
            PageMargins margins = new PageMargins(LEFT, TOP, RIGHT, BOTTOM);
            pd4ml.setPageMargins(margins);
        }

        pd4ml.setHtmlWidth(USER_SPACE_WIDTH);

        pd4ml.setDocumentTitle(exportName);
        // Debug
        pd4ml.setLogLevel(Base.LOG_DEBUG);
        // Security
        pd4ml.setPermissions(StringUtils.isEmpty(password) ? NO_PASSWOD : password, miPermissions);
        InputStream inputStream = new ByteArrayInputStream(sContent.getBytes());
        pd4ml.readHTML(inputStream);
        // fix table tit1
        pd4ml.writePDF(out);

    }

    /**
     * 硬修 txt-Balance span to DIV
     * 
     * @param sContent
     * @param sContextPath
     * @return
     */
    private String fixTxtBalanceSPAN(String sContent) {
        String result = StringUtils.trimToEmptyEx(sContent).replaceAll("<span class=[\"']txt-Balance[\"'](.*?)>(.+?)</span>", "<div class=\"txt-Balance\">$2</div>");
        return result;
    }

    /**
     * 修正PD4ML未支援標籤及屬性
     *
     * @param sContent
     * @param sContextPath
     *
     * @return
     */
    public static String fixPD4MLNonSupported(String sContent) {
        return sContent.replaceAll("base.css", "print.css");
    }

    /**
     * 取得 PDF 內容
     *
     * @return
     */
    public String getPDFContents(String templateName, Object params) {

        // template檔案(.vm)所在位置
        String templateFile = String.format("/pdf/templates/%s.vm", templateName);

        // 產生Velocity Context
        VelocityContext context = new VelocityContext();

        if (params != null) {
            // 傳入參數
            context.put("params", ValidateParamUtils.validParamObj(params));
        }

        // 傳入ResourceTool
        context.put("text", createResourceTool());

        VelocityEngine velocityEngine = createVelocityEngine();

        // 讀取模板
        Template template = velocityEngine.getTemplate(templateFile);

        // 合併
        StringWriter writer = new StringWriter();
        template.merge(context, writer);

        return writer.toString();
    }

    /**
     * VelocityEngine 初始化
     * 
     * @return
     */
    private VelocityEngine createVelocityEngine() {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperties(velocityProperties());
        velocityEngine.init();
        return velocityEngine;
    }

    /**
     * Velocity 參數
     * 
     * @return
     */
    private Properties velocityProperties() {
        Properties properties = new Properties();
        properties.setProperty("resource.loader", "class");
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        properties.setProperty("input.encoding", "UTF-8");
        properties.setProperty("output.encoding", "UTF-8");
        return properties;
    }

    private ResourceTool createResourceTool() {
        ResourceTool tool = new ResourceTool();
        Map<String, Object> map = new HashMap<>();
        map.put(ResourceTool.BUNDLES_KEY, new String[] { "messages" });
        map.put(ToolContext.LOCALE_KEY, getLocale());
        tool.configure(map);
        return tool;
    }

    private Locale getLocale() {
        String localeStr = MDC.get(MDCKey.locale.name());
        if (StringUtils.isBlank(localeStr)) {
            return Locale.TAIWAN;
        }
        return LocaleUtils.toLocale(localeStr);
    }

}
