package exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by yaoo on 2016/9/13.
 */
@Provider
public class ExceptionsMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        System.out.println("--------->>> " + exception.getMessage() + " <<<---------");
        if (exception.getCause() != null)
            System.out.println("--------->>> " + exception.getCause().getMessage() + " <<<---------");
        return Response.status(500).build();
    }

}
