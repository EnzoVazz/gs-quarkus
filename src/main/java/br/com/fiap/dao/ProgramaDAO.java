package br.com.fiap.dao;

import br.com.fiap.beans.Empresa;
import br.com.fiap.beans.Programa;
import br.com.fiap.conexoes.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramaDAO {

    //Insert
    public String inserir(Programa programa) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO T_CZN_PROGRAMA (ID_PROGRAMA, NM_PROGRAMA, DT_INICIO_PROGRAMA, DT_FIM_PROGRAMA, DESCRICAO_PROGRAMA, ID_EMPRESA)" +
                "VALUES (SEQ_CZN_PROGRAMA.NEXTVAL, ?, ?, ?, ?, ?)";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setString(1, programa.getNm_programa());
            stmt.setDate(2, Date.valueOf(programa.getDt_inicio_programa()));

            if (programa.getDt_fim_programa() != null) {
                stmt.setDate(3, Date.valueOf(programa.getDt_fim_programa()));
            } else {
                stmt.setNull(3, java.sql.Types.DATE);
            }

            stmt.setString(4, programa.getDescricao_programa());
            stmt.setInt(5, programa.getEmpresa().getId_empresa());

            stmt.execute();
        }
        return "Programa cadastrado com sucesso";
    }

    //Delete
    public String deletar(int id_programa) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM T_CZN_PROGRAMA WHERE ID_PROGRAMA = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setInt(1, id_programa);

            stmt.execute();
        }
        return "Programa excluído com sucesso!";
    }

    //Atualizar
    public String atualizar (Programa programa) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE T_CZN_PROGRAMA SET NM_PROGRAMA = ?, DT_INICIO_PROGRAMA = ?, DT_FIM_PROGRAMA = ?, DESCRICAO_PROGRAMA = ?, ID_EMPRESA = ? WHERE ID_PROGRAMA = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setString(1, programa.getNm_programa());
            stmt.setDate(2, Date.valueOf(programa.getDt_inicio_programa()));

            if (programa.getDt_fim_programa() != null) {
                stmt.setDate(3, Date.valueOf(programa.getDt_fim_programa()));
            } else {
                stmt.setNull(3, java.sql.Types.DATE);
            }

            stmt.setString(4, programa.getDescricao_programa());
            stmt.setInt(5, programa.getEmpresa().getId_empresa());
            stmt.setInt(6, programa.getId_programa());

            stmt.execute();
        }
        return "Programa atualizado com sucesso!";
    }

    //Select
    public List<Programa> selecionar() throws SQLException, ClassNotFoundException {
        List<Programa> listProgramas = new ArrayList<>();

        String sql = "SELECT P.*, E.NM_EMPRESA " +
                "FROM T_CZN_PROGRAMA P " +
                "JOIN T_CZN_EMPRESA E ON P.ID_EMPRESA = E.ID_EMPRESA " +
                "ORDER BY P.DT_INICIO_PROGRAMA";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()){
                Programa objPrograma = new Programa();
                objPrograma.setId_programa(rs.getInt("ID_PROGRAMA"));
                objPrograma.setNm_programa(rs.getString("NM_PROGRAMA"));
                objPrograma.setDt_inicio_programa(rs.getDate("DT_INICIO_PROGRAMA").toLocalDate());
                if (rs.getDate("DT_FIM_PROGRAMA") != null) {
                    objPrograma.setDt_fim_programa(rs.getDate("DT_FIM_PROGRAMA").toLocalDate());
                }
                objPrograma.setDescricao_programa(rs.getString("DESCRICAO_PROGRAMA"));

                Empresa objEmpresa = new Empresa();
                objEmpresa.setId_empresa(rs.getInt("ID_EMPRESA"));
                objEmpresa.setNm_empresa(rs.getString("NM_EMPRESA"));

                objPrograma.setEmpresa(objEmpresa);
                listProgramas.add(objPrograma);
            }
        }
        return listProgramas;
    }

}
