package com.davada.application.common;


import org.jboss.logging.Logger;

//@Provider
public class LoggingFilter {
//        implements ContainerRequestFilter {

    private static final Logger LOG = Logger.getLogger(LoggingFilter.class);
//
//    @Context
//    UriInfo info;
//
//    @Context
//    HttpServerRequest request;
//
//    @Override
//    public void filter(ContainerRequestContext context) {
//
//        final String method = context.getMethod();
//        final String path = info.getPath();
//        final String address = request.remoteAddress().toString();
//
//        Principal userPrincipal = context.getSecurityContext().getUserPrincipal();
//
//        LOG.infof("Request %s %s from IP %s", method, path, address);
//    }
}