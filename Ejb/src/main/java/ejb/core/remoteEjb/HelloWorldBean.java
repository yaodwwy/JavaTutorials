package ejb.core.remoteEjb;

/**
 * Created by adam on 16-11-15.
 */

import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Remote(HelloWorld.class)
@Stateless
public class HelloWorldBean implements HelloWorld {
    public String sayHello(){
        return "Hello World!";
    }
}