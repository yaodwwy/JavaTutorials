package ejb.core.remoteEjb.jndiClientTest;

import org.jboss.ejb.client.EJBClientInterceptor;
import org.jboss.ejb.client.EJBClientInvocationContext;

public class ClientInterceptor implements EJBClientInterceptor {
    public void handleInvocation(EJBClientInvocationContext context) throws Exception {
        System.out.println("############################ Client Interceptor: handleInvocation");
        context.sendRequest();
    }

    public Object handleInvocationResult(EJBClientInvocationContext context) throws Exception {
        System.out.println("############################ Client Interceptor: handleInvocationResult");
        return context.getResult();
    }
}