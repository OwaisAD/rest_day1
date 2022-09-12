package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.EmployeeFacade;
import facades.PersonFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("employee")
public class EmployeeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final EmployeeFacade FACADE =  EmployeeFacade.getEmployeeFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    // http://localhost:8080/rest_day1_war_exploded/api/employee/all
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllEmployees() {
        return Response.ok().entity(GSON.toJson(FACADE.getAllEmployees())).build(); // ok() så den får response 200
    }

    // http://localhost:8080/rest_day1_war_exploded/api/employee/3
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUser(@PathParam("id") long id) {
        return Response.ok().entity(GSON.toJson(FACADE.getEmployeeById(id))).build();
    }

    @GET
    @Path("highestpaid")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getHighestPaid() {
        return Response.ok().entity(GSON.toJson(FACADE.getEmployeesWithHighestSalary())).build();
    }

    // 404 MEANS RESSOURCE ERROR - NOT FOUND

    // http://localhost:8080/rest_day1_war_exploded/api/employee/name/Elon%20Musk
    @GET
    @Path("name/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUser(@PathParam("name") String name) {
        return Response.ok().entity(GSON.toJson(FACADE.getEmployeeByName(name))).build();
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
