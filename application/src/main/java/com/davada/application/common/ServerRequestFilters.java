package com.davada.application.common;

import com.davada.domain.common.EntityAudit;
import io.vertx.core.http.HttpServerRequest;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import java.util.Date;

@Slf4j
public class ServerRequestFilters {
    @Inject
    ErpRequestContextImpl erpRequestContext;
    @Context
    HttpServerRequest request;

    @ServerRequestFilter(preMatching = true)
    void preMatchingFilter(ContainerRequestContext requestContext) {
//        String method = requestContext.getMethod();
//        if (HttpMethod.POST.equals(method) || HttpMethod.PUT.equals(method) || HttpMethod.DELETE.equals(method)) {
        String upn = "test";
        if (requestContext.getSecurityContext().getUserPrincipal() != null) {
            upn = requestContext.getSecurityContext().getUserPrincipal().getName();
            log.debug("upn : " + upn);
        }

        EntityAudit auditInfo = new EntityAudit(new Date().getTime(), upn, request.remoteAddress().toString());
        erpRequestContext.setEntityAudit(auditInfo);
//        }
    }
}
