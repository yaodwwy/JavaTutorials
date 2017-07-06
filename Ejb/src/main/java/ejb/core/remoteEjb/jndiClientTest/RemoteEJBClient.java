package ejb.core.remoteEjb.jndiClientTest;
import ejb.core.remoteEjb.HelloWorld;
import org.jboss.ejb.client.EJBClientConfiguration;
import org.jboss.ejb.client.EJBClientContext;
import org.jboss.ejb.client.PropertiesBasedEJBClientConfiguration;
import org.jboss.ejb.client.remoting.ConfigBasedEJBClientContextSelector;

import javax.naming.*;
import java.util.Hashtable;
import java.util.Properties;


public class RemoteEJBClient {

    public static void main(String[] args) throws Exception {
        invokeStatelessBean();
    }

    private static void invokeStatelessBean() throws NamingException {

//        java:global/ear/ejb/HelloWorldBean!ejb.core.remoteEjb.HelloWorld
//        java:app/ejb/HelloWorldBean!ejb.core.remoteEjb.HelloWorld
//        java:module/HelloWorldBean!ejb.core.remoteEjb.HelloWorld
//        java:jboss/exported/ear/ejb/HelloWorldBean!ejb.core.remoteEjb.HelloWorld
//        java:global/ear/ejb/HelloWorldBean
//        java:app/ejb/HelloWorldBean
//        java:module/HelloWorldBean

        /////////////////////////////
        // The "standard" JNDI lookup
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        //jndiProperties.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");
        // needed for a login module that requires the password in plaintext
        jndiProperties.put(Context.PROVIDER_URL, "remote://10.0.3.33:8080");
        jndiProperties.put(Context.SECURITY_PRINCIPAL, "admin");
        jndiProperties.put(Context.SECURITY_CREDENTIALS, "123456");
        jndiProperties.put("jboss.naming.client.ejb.context", true);
        final Context context = new InitialContext(jndiProperties);

        traverseJndiNode("/", context);
        final HelloWorld helloWorld = (HelloWorld) context.lookup("ear/ejb/HelloWorldBean!"+ HelloWorld.class.getName());
        System.out.println(helloWorld.sayHello());
        context.close();

        /////////////////////////////////////////////
        // Using the proprietary JBoss EJB Client API
        final Properties ejbProperties = new Properties();
        ejbProperties.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", "false");
        ejbProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        ejbProperties.put("remote.connections", "1");
        ejbProperties.put("remote.connection.1.host", "localhost");
        ejbProperties.put("remote.connection.1.port", 8080);
        //ejbProperties.put("remote.connection.1.connect.options.org.xnio.Options.SASL_DISALLOWED_MECHANISMS", "JBOSS-LOCAL-USER"); // needed for forcing authentication over remoting (i.e. if you have a custom login module)
        //ejbProperties.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false"); // needed for a login module that requires the password in plaintext
        ejbProperties.put("remote.connection.1.username", "admin");
        ejbProperties.put("remote.connection.1.password", "123456");
        ejbProperties.put("org.jboss.ejb.client.scoped.context", "true"); // Not needed when EJBClientContext.setSelector is called programatically. ATTENTION: Client-Interceptor registration below does not work with this property! BUG?

        final EJBClientConfiguration ejbClientConfiguration = new PropertiesBasedEJBClientConfiguration(ejbProperties);
        final ConfigBasedEJBClientContextSelector selector = new ConfigBasedEJBClientContextSelector(ejbClientConfiguration);
        EJBClientContext.setSelector(selector);
        EJBClientContext.getCurrent().registerInterceptor(0, new ClientInterceptor());

        final Context ejbContext = new InitialContext(ejbProperties);
        final HelloWorld ejbHelloWorld = (HelloWorld) ejbContext.lookup("ejb:ear/ejb/HelloWorldBean!"+ HelloWorld.class.getName());
        System.out.println(ejbHelloWorld.sayHello());

    }

    private static void traverseJndiNode(String nodeName, Context context)  {
        try {
            NamingEnumeration<NameClassPair> list = context.list(nodeName);
            while (list.hasMore()){
                String childName = nodeName + "/" + list.next().getName();
                System.out.println(childName);
                traverseJndiNode(childName, context);
            }
        } catch (NamingException ex) {
            // We reached a leaf
        }
    }
}