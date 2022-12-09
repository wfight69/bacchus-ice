package com.davada.application.common;

import com.davada.domain.common.exception.ErpRuntimeException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ErpRuntimeExceptionMapper implements ExceptionMapper<ErpRuntimeException> {
    @Override
    public Response toResponse(ErpRuntimeException e) {
        return Response.status(Response.Status.OK).
                entity(CommonResponse.fail(e.getErrorCode(), e.getMessage())).build();
    }
}
