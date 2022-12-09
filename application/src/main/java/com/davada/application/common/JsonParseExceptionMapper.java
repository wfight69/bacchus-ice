package com.davada.application.common;

import com.google.gson.JsonParseException;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Slf4j
@Provider
public class JsonParseExceptionMapper implements ExceptionMapper<JsonParseException> {
    @Override
    public Response toResponse(JsonParseException e) {

        Throwable rootCause = e.getCause();
        while (true) {
            if (rootCause.getCause() == null)
                break;
            rootCause = rootCause.getCause();
        }

        log.error("", e);

        return Response.status(Response.Status.OK).
                entity(CommonResponse.fail("SYSTEM_ERROR", rootCause.getMessage())).build();
    }
}
