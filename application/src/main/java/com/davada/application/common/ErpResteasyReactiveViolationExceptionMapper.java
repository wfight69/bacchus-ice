package com.davada.application.common;

import io.quarkus.hibernate.validator.runtime.jaxrs.ResteasyReactiveViolationException;
import io.quarkus.hibernate.validator.runtime.jaxrs.ValidatorMediaTypeUtil;
import io.quarkus.hibernate.validator.runtime.jaxrs.ViolationReport;

import javax.validation.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.*;

@Provider
public class ErpResteasyReactiveViolationExceptionMapper implements ExceptionMapper<ValidationException> {
    @Context
    HttpHeaders headers;

    public ErpResteasyReactiveViolationExceptionMapper() {
    }

    public Response toResponse(ValidationException exception) {
        if (!(exception instanceof ResteasyReactiveViolationException)) {
            throw exception;
        } else {
            ResteasyReactiveViolationException resteasyViolationException = (ResteasyReactiveViolationException) exception;
            if (this.hasReturnValueViolation(resteasyViolationException.getConstraintViolations())) {
                throw resteasyViolationException;
            } else {
                return this.buildViolationReportResponse(resteasyViolationException);
            }
        }
    }

    private boolean hasReturnValueViolation(Set<ConstraintViolation<?>> violations) {
        if (violations != null) {
            Iterator var2 = violations.iterator();

            while (var2.hasNext()) {
                ConstraintViolation<?> violation = (ConstraintViolation) var2.next();
                if (this.isReturnValueViolation(violation)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isReturnValueViolation(ConstraintViolation<?> violation) {
        Iterator<Path.Node> nodes = violation.getPropertyPath().iterator();
        Path.Node firstNode = (Path.Node) nodes.next();
        if (firstNode.getKind() != ElementKind.METHOD) {
            return false;
        } else {
            Path.Node secondNode = (Path.Node) nodes.next();
            return secondNode.getKind() == ElementKind.RETURN_VALUE;
        }
    }

    private Response buildViolationReportResponse(ConstraintViolationException cve) {
        Response.ResponseBuilder builder = Response.status(Response.Status.OK);
        builder.header("validation-exception", "true");
        MediaType mediaType = ValidatorMediaTypeUtil.getAcceptMediaType(this.headers.getAcceptableMediaTypes(),
                Arrays.asList(MediaType.APPLICATION_JSON_TYPE));
        if (mediaType == null) {
            mediaType = MediaType.APPLICATION_JSON_TYPE;
        }

        List<ViolationReport.Violation> violationsInReport = new ArrayList(cve.getConstraintViolations().size());
        Iterator var6 = cve.getConstraintViolations().iterator();

        while (var6.hasNext()) {
            ConstraintViolation<?> cv = (ConstraintViolation) var6.next();
            violationsInReport.add(new ViolationReport.Violation(cv.getPropertyPath().toString(), cv.getMessage()));
        }

        CommonResponse commonResponse = CommonResponse.fail("Constraint Violation",
                violationsInReport.toString());
//                new ViolationReport("Constraint Violation", status, violationsInReport).toString());
        builder.entity(commonResponse);
        builder.type(mediaType);
        return builder.build();
    }
}
