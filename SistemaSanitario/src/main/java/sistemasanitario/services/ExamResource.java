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
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sistemasanitario.entities.EsamePrescrivibile;
import sistemasanitario.entities.Medicina;

/**
 * REST Web Service
 *
 * @author omar
 */
@Path("exam")
public class ExamResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ExamResource
     */
    public ExamResource() {
    }
    
    @Context
    private HttpServletRequest request;
    
    private Dao<EsamePrescrivibile, Integer> esameDao;

    @Context
    public void setServletContext(ServletContext servletContext) {
        if (servletContext != null) {
           esameDao =  (Dao<EsamePrescrivibile, Integer>) servletContext.getAttribute("esameDao");
        }
    }

    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getExamById(@PathParam("id") Integer esameId) {
        
        Response.ResponseBuilder response;
       
        EsamePrescrivibile esame;

        if (esameId == null) {
            // EsameId is missing
            response = Response.status(Response.Status.BAD_REQUEST);
        } else {
            try {
                if ((esame = esameDao.queryForId(esameId)) == null) {
                    
                    response = Response.status(Response.Status.BAD_REQUEST);
                } else {
         
                    response = Response.ok(esame);
                }
            } catch (SQLException ex) {
                response = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
            }
        }
        return response.build();
    }
}
