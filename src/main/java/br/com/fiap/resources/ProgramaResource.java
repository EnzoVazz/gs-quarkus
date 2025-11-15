package br.com.fiap.resources;

import br.com.fiap.beans.Empresa;
import br.com.fiap.beans.Programa;
import br.com.fiap.bo.ProgramaBO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.Provider;

import java.sql.SQLException;
import java.util.ArrayList;

@Provider
@Path("/programa")
public class ProgramaResource {

    private ProgramaBO programaBO = new ProgramaBO();

    // Selecionar
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Programa> selecionarRs() throws SQLException, ClassNotFoundException {
        return (ArrayList<Programa>) programaBO.selecionarBo();
    }

    // Inserir
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirRs(Programa programa, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        programaBO.inserirBo(programa);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(programa.getId_programa()));
        return Response.created(builder.build()).build();
    }

    // Atualizar
    @PUT
    @Path("/{id_programa}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarRs(@PathParam("id_programa")int id_programaUrl, Programa programaJson) throws SQLException, ClassNotFoundException {
        programaJson.setId_programa(id_programaUrl);
        programaBO.atualizarBo(programaJson);
        return Response.ok().build();
    }

    // Deletar
    @DELETE
    @Path("/{id_programa}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarRs(@PathParam("id_programa") int id_programa) throws SQLException, ClassNotFoundException {
        programaBO.deletarBo(id_programa);
        return Response.ok().build();
    }
}