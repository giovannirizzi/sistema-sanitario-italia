/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasanitario.services;

import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sistemasanitario.entities.Medicina;

/**
 * REST Web Service
 *
 * @author giovanni
 */
@Path("medicine")
public class MedicineResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Medicine2Resource
     */
    public MedicineResource() {
    }
    
    @Context
    private HttpServletRequest request;
    
    private Dao<Medicina, Integer> medicinaDao;

    @Context
    public void setServletContext(ServletContext servletContext) {
        if (servletContext != null) {
            medicinaDao =  (Dao<Medicina, Integer>) servletContext.getAttribute("medicinaDao");
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getExamById(@PathParam("id") Integer medicineId) {
        
        Response.ResponseBuilder response;
       
        Medicina medicine;

        if (medicineId == null) {
            // Medicine Id is missing
            response = Response.status(Response.Status.BAD_REQUEST);
        } else {
            try {
                if ((medicine = medicinaDao.queryForId(medicineId)) == null) {
                    
                    response = Response.status(Response.Status.BAD_REQUEST);
                } else {
         
                    response = Response.ok(medicine);
                }
            } catch (SQLException ex) {
                response = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
            }
        }
        return response.build();
    }
}
