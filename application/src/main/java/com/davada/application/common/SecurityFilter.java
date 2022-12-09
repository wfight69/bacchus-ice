package com.davada.application.common;

//@Provider
//@Priority(Interceptor.Priority.APPLICATION)
public class SecurityFilter {
//        implements ContainerRequestFilter {
//    private static final String HEADER_EMAIL = "HD-Email";
//    @Inject
//    ErpRequestContextImpl erpRequestContext;
//    @Context
//    SecurityContext securityContext;
//    //    @Context
//    //    UriInfo info;
//    @Context
//    HttpServerRequest request;
//
//    @Override
//    public void filter(ContainerRequestContext requestContext) throws IOException {
//        // TESTING C/U/D API에 대해서만 아래 코드 수행
//        String method = requestContext.getMethod();
//        if (method.equals("POST") || method.equals("PUT") || method.equals("DELETE")) {
//            EntityAudit auditInfo = new EntityAudit(new Date().getTime(), Id.newId().toString(), request.remoteAddress().toString());
//            erpRequestContext.setEntityAudit(auditInfo);
//        }
//
//    }
}