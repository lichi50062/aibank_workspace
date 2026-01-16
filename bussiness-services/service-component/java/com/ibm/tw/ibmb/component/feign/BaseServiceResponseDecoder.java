package com.ibm.tw.ibmb.component.feign;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.ibmb.base.model.BaseServiceResponse;
import com.ibm.tw.ibmb.util.ExceptionUtils;

import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;

public class BaseServiceResponseDecoder implements Decoder {

    private static IBLog logger = IBLog.getLog(BaseServiceResponseDecoder.class);

    private final Decoder delegate;
    private Map<Type, Type> parameterizedTypeMap = new HashMap<>();

    public BaseServiceResponseDecoder(Decoder delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        // fortify: Code Correctness: Class Does Not Implement Equivalence Method
        if (type instanceof ParameterizedType && ((ParameterizedType) type).getRawType() == BaseServiceResponse.class) {
            return delegate.decode(response, type);
        }
        Response clonedResponse = response;
        try (InputStream is = response.body().asInputStream()) {
            byte[] responseBody = is.readAllBytes();
            //@formatter:off
            clonedResponse = Response.builder().body(responseBody)
                    .headers(response.headers())
                    .protocolVersion(response.protocolVersion())
                    .reason(response.reason())
                    .request(response.request())
                    .status(response.status())
                    .build();
            //@formatter:on

            Type wrappedType = this.parameterizedTypeMap.get(type);
            if (wrappedType == null) {
                wrappedType = createParameterizedType(BaseServiceResponse.class, type);
                this.parameterizedTypeMap.put(type, wrappedType);
            }
            BaseServiceResponse<?> res = (BaseServiceResponse<?>) delegate.decode(clonedResponse, wrappedType);
            if (res == null) {
                return null;
            }
            if (res.getStatus().isError()) {
                throw new RuntimeException(ExceptionUtils.getActionException(res.getStatus()));
            }
            return res.getData();
        }
        catch (IOException | DecodeException e) {
            // error decoding as BaseServiceResponse, try with original type
            logger.debug("error decoding as BaseServiceResponse, try with original type");
            if (logger.isTraceEnabled()) {
                logger.trace("error decoding as BaseServiceResponse", e);
            }
        }
        return delegate.decode(clonedResponse, type);
    }

    private Type createParameterizedType(Class<?> rawType, Type type) {
        return new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[] { type };
            }

            @Override
            public Type getRawType() {
                return rawType;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
    }

}
