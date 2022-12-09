package com.davada.application.common;

import javax.interceptor.InvocationContext;

//@Interceptor
//@EnableAuditLog
//@Priority(Interceptor.Priority.APPLICATION)
public class EnableAuditLogInterceptor {
    //    @Inject
    ErpRequestContext erpRequestContext;

    //    @AroundInvoke
    public Object auditCommand(InvocationContext context) throws Exception {
        System.out.println("AroundInvoke method called");
        Object[] parameters = context.getParameters();

//        if (context.getMethod().isAnnotationPresent(EnableAuditLog.class)) {
//            EnableAuditLog annotation = context.getMethod().getAnnotation(EnableAuditLog.class);
//            for (Object parameter : parameters) {
//                if (parameter instanceof Auditable) {
//                    if (annotation.value() == EnableAuditLog.CommandType.UPDATE) {
//                        ((Auditable) parameter).setAuditLog(AuditLog.getInstance(erpRequestContext.getEntityAudit()));
//                    } else if (annotation.value() == EnableAuditLog.CommandType.CREATE) {
//                        ((Auditable) parameter).setAuditLog(AuditLog.getInstance().applyUpdateAuditLog(erpRequestContext.getEntityAudit()));
//                    } else if (annotation.value() == EnableAuditLog.CommandType.REMOVE) {
//                        ((Auditable) parameter).setAuditLog(AuditLog.getInstance().applyDeleteAuditLog(erpRequestContext.getEntityAudit()));
//                    }
//                }
//            }
//        }

        return context.proceed();
    }
}
