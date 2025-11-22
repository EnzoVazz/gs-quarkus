package br.com.fiap.resources;

import br.com.fiap.beans.Checkin;
import br.com.fiap.bo.CheckinBO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.Provider;

import java.sql.SQLException;
import java.util.ArrayList;

@Provider
@Path("/checkin")
public class CheckinResource {

    private CheckinBO checkinBO = new CheckinBO();

    // Selecionar
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Checkin> selecionarRs() throws SQLException, ClassNotFoundException {
        return (ArrayList<Checkin>) checkinBO.selecionarBo();
    }

    // Inserir
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirRs(Checkin checkin, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        checkinBO.inserirBo(checkin);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(checkin.getId_checkin()));
        return Response.created(builder.build()).build();
    }

    // Atualizar
    @PUT
    @Path("/{id_checkin}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarRs(@PathParam("id_checkin")int id_checkinUrl, Checkin checkinJson) throws SQLException, ClassNotFoundException {
        checkinJson.setId_checkin(id_checkinUrl);
        checkinBO.atualizarBo(checkinJson);
        return Response.ok().build();
    }

    // Deletar
    @DELETE
    @Path("/{id_checkin}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarRs(@PathParam("id_checkin") int id_checkin) throws SQLException, ClassNotFoundException {
        checkinBO.deletarBo(id_checkin);
        return Response.ok().build();
    }
}