package br.com.fiap.resources;

import br.com.fiap.beans.Funcionario;
import br.com.fiap.bo.FuncionarioBO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.Provider;

import java.sql.SQLException;
import java.util.ArrayList;

@Provider
@Path("/funcionario")
public class FuncionarioResource {

    public static class LoginSolicitado{
        public String email;
        public String senha;
    }


    public static class ErrorResponse {
        public String message;
        public ErrorResponse(String message) {
            this.message = message;
        }
    }

    private FuncionarioBO funcionarioBO = new FuncionarioBO();

    // Selecionar
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Funcionario> selecionarRs() throws SQLException, ClassNotFoundException {
        return (ArrayList<Funcionario>) funcionarioBO.selecionarBo();
    }

    //Inserir
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirRs(Funcionario funcionario, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        funcionarioBO.inserirBo(funcionario);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(funcionario.getId_funcionario()));
        return Response.created(builder.build()).build();
    }

    // Atualizar
    @PUT
    @Path("/{id_funcionario}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarRs(@PathParam("id_funcionario") int id_funcionarioUrl, Funcionario funcionarioJson) throws SQLException, ClassNotFoundException {
        funcionarioJson.setId_funcionario(id_funcionarioUrl);
        funcionarioBO.atualizarBo(funcionarioJson);
        return Response.ok().build();
    }

    // Deletar
    @DELETE
    @Path("/{id_funcionario}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarRs(@PathParam("id_funcionario") int id_funcionario) throws SQLException, ClassNotFoundException {
        funcionarioBO.deletarBo(id_funcionario);
        return Response.ok().build();
    }

    //Login
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginRs(LoginSolicitado loginSolicitado) throws SQLException, ClassNotFoundException {
        Funcionario funcionario = funcionarioBO.loginBo(loginSolicitado.email, loginSolicitado.senha);

        if (funcionario == null){
            ErrorResponse erro = new ErrorResponse("Email ou senha inválidos.");
            return Response.status(Response.Status.UNAUTHORIZED).entity(erro).build();
        }

        // Sucesso
        return Response.ok(funcionario).build();
    }
}