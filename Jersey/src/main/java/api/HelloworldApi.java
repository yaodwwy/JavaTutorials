package api;

import ejb.core.entity.UserEntity;
import ejb.core.service.FirstEjb;
import ejb.core.service.SecondEjb;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by yaoo on 2016/9/13.
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HelloworldApi {

    @Inject
    FirstEjb ejb;

    @Inject
    SecondEjb secondEjb;

    public HelloworldApi() {
    }

    @GET
    @Path("/named")
    public List<UserEntity> users() {
        return ejb.getNamedQueryList();
    }

    @GET
    @Path("/add")
    public String add() {
        return ejb.add();
    }


    @GET
    @Path("/users")
    public List<UserEntity> list() {
        return ejb.getList();
    }

}
