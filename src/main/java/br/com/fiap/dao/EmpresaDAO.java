package br.com.fiap.dao;

import br.com.fiap.beans.Empresa;
import br.com.fiap.conexoes.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDAO {

    //Insert
    public String inserir(Empresa empresa) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO T_CZN_EMPRESA (ID_EMPRESA, NM_EMPRESA, NR_CNPJ, SETOR)"+
                "VALUES (SEQ_CZN_EMPRESA.NEXTVAL, ?, ?, ? )";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, empresa.getNm_empresa());
            stmt.setString(2, empresa.getNr_cnpj());
            stmt.setString(3, empresa.getSetor());

            stmt.execute();
        }
        return "Empresa registrada com sucesso!";
    }

    //Delete
    public String deletar(int id_empresa) throws SQLException, ClassNotFoundException {
        String sqlCheckins = "DELETE FROM T_CZN_CHECKIN WHERE ID_FUNCIONARIO IN " +
                "(SELECT ID_FUNCIONARIO FROM T_CZN_FUNCIONARIO WHERE ID_EMPRESA = ?)";

        String sqlF = "DELETE FROM T_CZN_FUNCIONARIO WHERE ID_EMPRESA = ?";

        // 3. Deletar os "filhos" (Programas)
        String sqlP = "DELETE FROM T_CZN_PROGRAMA WHERE ID_EMPRESA = ?";

        // 4. Deletar o "pai" (Empresa)
        String sqlE = "DELETE FROM T_CZN_EMPRESA WHERE ID_EMPRESA = ?";

        try (Connection conexao = new ConexaoFactory().conexao()) {
            conexao.setAutoCommit(false);

            try (PreparedStatement stmt = conexao.prepareStatement(sqlCheckins)) {
                stmt.setInt(1, id_empresa);
                stmt.executeUpdate();
            }

            try (PreparedStatement stmt = conexao.prepareStatement(sqlF)) {
                stmt.setInt(1, id_empresa);
                stmt.executeUpdate();
            }

            try (PreparedStatement stmt = conexao.prepareStatement(sqlP)) {
                stmt.setInt(1, id_empresa);
                stmt.executeUpdate();
            }

            try (PreparedStatement stmt = conexao.prepareStatement(sqlE)) {
                stmt.setInt(1, id_empresa);
                stmt.executeUpdate();
            }
            conexao.commit();
            return "Empresa e todos os seus dados (funcionários, programas, checkins) foram removidos!";

        } catch (SQLException e) {
            throw new SQLException("Erro na transação de delete da empresa: " + e.getMessage());
        }
    }
    //UpDate
    public String atualizar(Empresa empresa) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE T_CZN_EMPRESA SET NM_EMPRESA = ?, NR_CNPJ = ?, SETOR = ?" +
                "WHERE ID_EMPRESA = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, empresa.getNm_empresa());
            stmt.setString(2, empresa.getNr_cnpj());
            stmt.setString(3, empresa.getSetor());
            stmt.setInt(4, empresa.getId_empresa());

            stmt.execute();
        }
        return "Empresa atualizada com sucesso!";
    }

    //Select
    public List<Empresa> selecionar() throws SQLException, ClassNotFoundException {
        List<Empresa> listEmpresas = new ArrayList<>();

        String sql = "SELECT * FROM T_CZN_EMPRESA";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()){
                Empresa objEmpresa = new Empresa();
                objEmpresa.setId_empresa(rs.getInt("ID_EMPRESA"));
                objEmpresa.setNm_empresa(rs.getString("NM_EMPRESA"));
                objEmpresa.setNr_cnpj(rs.getString("NR_CNPJ"));
                objEmpresa.setSetor(rs.getString("SETOR"));
                listEmpresas.add(objEmpresa);
            }
        }
        return listEmpresas;
    }
}
