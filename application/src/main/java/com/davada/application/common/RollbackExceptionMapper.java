package com.davada.application.common;

import lombok.extern.slf4j.Slf4j;

import javax.transaction.RollbackException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Slf4j
@Provider
public class RollbackExceptionMapper implements ExceptionMapper<RollbackException> {
    @Override
    public Response toResponse(RollbackException e) {

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
