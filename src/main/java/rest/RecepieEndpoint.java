package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dto.RecipeDTO;
import errorhandling.GenericExceptionMapper;
import facades.RecepieFacade;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import utils.EMF_Creator;

/**
 *
 * @author rando
 */
@Path("recepies")
public class RecepieEndpoint {

    private static EntityManagerFactory EMF
            = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static final RecepieFacade FACADE = RecepieFacade.getRecepieFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final GenericExceptionMapper GENERIC_EXCEPTION_MAPPER
            = new GenericExceptionMapper();

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response restTest() throws IOException, InterruptedException {
        //CompleteDTO cDTO = facade.runParalel();
        return Response.ok("{\"msg\":\"Test Went Through\"}").build();
    }

    @GET
    @Path("/all")
    //@RolesAllowed("user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListOfRecepies() throws IOException, InterruptedException {
        try {
            List<RecipeDTO> recepieList = FACADE.getAllRecepies();
            return Response.ok(recepieList).build();
        } catch (IOException ex) {
            return GENERIC_EXCEPTION_MAPPER.toResponse(ex);
        }
    }

    @POST
    @Path("/id")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //@RolesAllowed({"user", "admin"})
    public Response getSpecificRecepieDetails(String json) throws InterruptedException {
        Type listType = new TypeToken<List<Long>>() {
        }.getType();

        List<Long> idListOfLong = new Gson().fromJson(json, listType);

//        try {
        List<RecipeDTO> rDTO = FACADE.getSpecificRecepieInfo(idListOfLong);
        return Response.ok(rDTO).build();
//        } 
//        catch (InterruptedException ex) {
//            return 
//        }
    }
}
