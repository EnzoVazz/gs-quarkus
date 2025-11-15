package br.com.fiap.resources;

import br.com.fiap.beans.Empresa;
import br.com.fiap.bo.EmpresaBO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.Provider;

import java.sql.SQLException;
import java.util.ArrayList;

@Provider
@Path("/empresa")
public class EmpresaResource {
    private EmpresaBO empresaBO = new EmpresaBO();

    //Selecionar
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Empresa>selecionarRs() throws SQLException, ClassNotFoundException {
        return (ArrayList<Empresa>) empresaBO.selecionarBo();
    }

    //Inserir
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirRs(Empresa empresa, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        empresaBO.inserirBo(empresa);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(empresa.getId_empresa()));
        return Response.created(builder.build()).build();
    }

    //Atualizar
    @PUT
    @Path("/{id_empresa}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarRs(@PathParam("id_empresa")int id_empresaUrl, Empresa empresaJson) throws SQLException, ClassNotFoundException {
        empresaJson.setId_empresa(id_empresaUrl);
        empresaBO.atualizarBo(empresaJson);
        return Response.ok().build();
    }

    //Deletar
    @DELETE
    @Path("/{id_empresa}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarRs(@PathParam("id_empresa")int id_empresa) throws SQLException, ClassNotFoundException {
        empresaBO.deletarBo(id_empresa);
        return Response.ok().build();
    }
}
