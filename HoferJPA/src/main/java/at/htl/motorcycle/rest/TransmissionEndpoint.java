package at.htl.motorcycle.rest;

import at.htl.motorcycle.model.Transmission;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("transmission")
public class TransmissionEndpoint {

    @PersistenceContext
    EntityManager em;

    @POST
    @Path("/insertTransmission")
    @Transactional
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertTransmission(Transmission transmission) {
        em.persist(transmission);
        return Response.ok().entity(transmission).build();
    }

    @GET
    @Path("/getTransmissions")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Transmission> getTransmissions() {
        TypedQuery<Transmission> query = em.createNamedQuery("Transmission.findAll",Transmission.class);
        return query.getResultList();
    }

    @GET
    @Path("/getTransmission/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Transmission getTransmissionById(@PathParam("id") long id) {
        return em.find(Transmission.class, id);
    }

    @PUT
    @Path("/updateTransmission/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTransmission(@PathParam("id") long id, Transmission t) {
        if(t == null || em.find(Transmission.class, id) == null) {
            return Response.serverError().build();
        }
        t.setId(id);
        em.merge(t);
        return Response.ok().entity(em.find(Transmission.class, id)).build();
    }

    @DELETE
    @Transactional
    @Path("/deleteTransmission/{id}")
    public void deleteTransmission(@PathParam("id") long id) {
        Transmission t = em.find(Transmission.class, id);
        if(t != null) {
            em.remove(em.contains(t) ? t : em.merge(t));
        }
    }
}
