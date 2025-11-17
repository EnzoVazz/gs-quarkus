package br.com.fiap.dao;

import br.com.fiap.beans.Empresa;
import br.com.fiap.beans.Funcionario;
import br.com.fiap.conexoes.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    //Insert
    public String inserir(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        String sql = "Insert into T_CZN_FUNCIONARIO (ID_FUNCIONARIO, NM_FUNCIONARIO, EMAIL, SENHA, CARGO, ID_EMPRESA)" +
                "VALUES (SEQ_CZN_FUNCIONARIO.NEXTVAL, ?, ?, ?, ?, ?)";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setString(1, funcionario.getNm_funcionario());
            stmt.setString(2, funcionario.getEmail());
            stmt.setString(3, funcionario.getSenha());
            stmt.setString(4, funcionario.getCargo());
            stmt.setInt(5, funcionario.getEmpresa().getId_empresa());

            stmt.execute();
        }
        return "Funcionário cadastrado com sucesso!";
    }

    //Delete
    public String deletar(int id_funcionario) throws SQLException, ClassNotFoundException {
        String sqlC = "DELETE FROM T_CZN_CHECKIN WHERE ID_FUNCIONARIO = ?";
        String sqlF = "DELETE FROM T_CZN_FUNCIONARIO WHERE ID_FUNCIONARIO = ?";

        try (Connection conexao = new ConexaoFactory().conexao()) {
            conexao.setAutoCommit(false);

            try (PreparedStatement stmtC = conexao.prepareStatement(sqlC)) {
                stmtC.setInt(1, id_funcionario);
                stmtC.executeUpdate();
            }

            try (PreparedStatement stmtF = conexao.prepareStatement(sqlF)) {
                stmtF.setInt(1, id_funcionario);
                stmtF.executeUpdate();
            }
            conexao.commit();
            return "Funcionário e seus check-ins foram deletados com sucesso!";
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar: " + e.getMessage());
        }
    }

    //Update
    public String atualizar (Funcionario funcionario) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE T_CZN_FUNCIONARIO SET NM_FUNCIONARIO = ?, EMAIL = ?, SENHA = ?, CARGO = ?, ID_EMPRESA = ? WHERE ID_FUNCIONARIO = ?";


        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setString(1, funcionario.getNm_funcionario());
            stmt.setString(2, funcionario.getEmail());
            stmt.setString(3, funcionario.getSenha());
            stmt.setString(4, funcionario.getCargo());
            stmt.setInt(5, funcionario.getEmpresa().getId_empresa());
            stmt.setInt(6, funcionario.getId_funcionario());

            stmt.execute();
        }
        return "Funcionário atualizado com sucesso!";
    }

    //Select
    public List<Funcionario> selecionar() throws SQLException, ClassNotFoundException {
        ArrayList<Funcionario> listFuncionarios = new ArrayList<>();

        String sql = "SELECT F.*, E.NM_EMPRESA " +
                "FROM T_CZN_FUNCIONARIO F " +
                "JOIN T_CZN_EMPRESA E ON F.ID_EMPRESA = E.ID_EMPRESA";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while(rs.next()){
                Funcionario objFuncionario = new Funcionario();

                objFuncionario.setId_funcionario(rs.getInt("ID_FUNCIONARIO"));
                objFuncionario.setNm_funcionario(rs.getString("NM_FUNCIONARIO"));
                objFuncionario.setEmail(rs.getString("EMAIL"));
                objFuncionario.setSenha(rs.getString("SENHA"));
                objFuncionario.setCargo(rs.getString("CARGO"));

                Empresa objEmpresa = new Empresa();
                objEmpresa.setId_empresa(rs.getInt("ID_EMPRESA"));
                objEmpresa.setNm_empresa(rs.getString("NM_EMPRESA"));

                objFuncionario.setEmpresa(objEmpresa);
                listFuncionarios.add(objFuncionario);
            }
        }
        return listFuncionarios;
    }

    public Funcionario buscarEmailESenha(String email, String senha) throws SQLException, ClassNotFoundException {
        String sql = "SELECT F.*, E.NM_EMPRESA " +
                "FROM T_CZN_FUNCIONARIO F " +
                "JOIN T_CZN_EMPRESA E ON F.ID_EMPRESA = E.ID_EMPRESA " +
                "WHERE F.EMAIL = ? AND F.SENHA = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    Funcionario objFuncionario = new Funcionario();
                    objFuncionario.setId_funcionario(rs.getInt("ID_FUNCIONARIO"));
                    objFuncionario.setNm_funcionario(rs.getString("NM_FUNCIONARIO"));
                    objFuncionario.setEmail(rs.getString("EMAIL"));
                    objFuncionario.setCargo(rs.getString("CARGO"));

                    Empresa objEmpresa = new Empresa();
                    objEmpresa.setId_empresa(rs.getInt("ID_EMPRESA"));
                    objEmpresa.setNm_empresa(rs.getString("NM_EMPRESA"));

                    objFuncionario.setEmpresa(objEmpresa);

                    return objFuncionario;
                }
            }
        }
        return null;
    }
}
