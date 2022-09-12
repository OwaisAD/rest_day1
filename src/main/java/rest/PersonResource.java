package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PersonDTO;
import utils.EMF_Creator;
import facades.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final PersonFacade FACADE =  PersonFacade.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello there ! :D\"" + "," + "\"age\":\"20\"" + "," + "\"desc\":\"test\"}";
    }

    @GET
    @Path("allpersons")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllPersons() {
        return Response.ok().entity(GSON.toJson(FACADE.getAll())).build(); // ok() så den får response 200
    }

    @GET
    @Produces("text/plain")
    @Path("/QueryDemo")
    public String getText(@QueryParam("id") int id, @QueryParam("name") String name) {
        return "{\"name\":\""+name+"\"}";
    }

    @GET
    @Path("/{username}")
    @Produces("text/plain")
    public String getUser(@PathParam("username") String userName) {
        return "Hello " + userName;
    }



    /*@POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response postExample(String input){
        PersonDTO rmdto = GSON.fromJson(input, PersonDTO.class);
        System.out.println(rmdto);
        return Response.ok().entity(rmdto).build();
    }*/
}
