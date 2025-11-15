package br.com.fiap.dao;

import br.com.fiap.beans.Checkin;
import br.com.fiap.beans.Funcionario;
import br.com.fiap.conexoes.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CheckinDAO {

    //Insert
    public String inserir(Checkin checkin) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO T_CZN_CHECKIN (ID_CHECKIN, DT_CHECKIN, NR_HUMOR, NR_ESTRESSE, ID_FUNCIONARIO)" +
                "VALUES (SEQ_CZN_CHECKIN.NEXTVAL, ?, ?, ?, ?)";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setDate(1, Date.valueOf(checkin.getDt_checkin()));
            stmt.setInt(2, checkin.getNr_humor());
            stmt.setInt(3, checkin.getNr_estresse());
            stmt.setInt(4, checkin.getFuncionario().getId_funcionario());

            stmt.execute();
        }
        return "Checkin realizado com sucesso!";
    }

    //Delete
    public String deletar(int id_checkin) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM T_CZN_CHECKIN WHERE ID_CHECKIN = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setInt(1, id_checkin);
            stmt.execute();
        }
        return "Checkin excluído com sucesso!";
    }

    //Atualizar
    public String atualizar(Checkin checkin) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE T_CZN_CHECKIN SET DT_CHECKIN = ?, NR_HUMOR = ?, NR_ESTRESSE = ?, ID_FUNCIONARIO = ? WHERE ID_CHECKIN = ?";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setDate(1, Date.valueOf(checkin.getDt_checkin()));
            stmt.setInt(2, checkin.getNr_humor());
            stmt.setInt(3, checkin.getNr_estresse());
            stmt.setInt(4,checkin.getFuncionario().getId_funcionario());
            stmt.setInt(5, checkin.getId_checkin());

            stmt.execute();
        }
        return "Checkin atualizado com sucesso!";
    }

    //Select
    public List<Checkin> selecionar() throws SQLException, ClassNotFoundException {
        List<Checkin> listCheckins = new ArrayList<>();

        String sql = "SELECT C.*, F.NM_FUNCIONARIO "+
                "FROM T_CZN_CHECKIN C " +
                "JOIN T_CZN_FUNCIONARIO F ON C.ID_FUNCIONARIO = F.ID_FUNCIONARIO " +
                "ORDER BY C.DT_CHECKIN";

        try (Connection conexao = new ConexaoFactory().conexao();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()){
                Checkin objCheckin = new Checkin();
                objCheckin.setId_checkin(rs.getInt("ID_CHECKIN"));
                objCheckin.setDt_checkin(rs.getDate("DT_CHECKIN").toLocalDate());
                objCheckin.setNr_humor(rs.getInt("NR_HUMOR"));
                objCheckin.setNr_estresse(rs.getInt("NR_ESTRESSE"));

                Funcionario objFuncionario = new Funcionario();
                objFuncionario.setId_funcionario(rs.getInt("ID_FUNCIONARIO"));
                objFuncionario.setNm_funcionario(rs.getString("NM_FUNCIONARIO"));

                objCheckin.setFuncionario(objFuncionario);
                listCheckins.add(objCheckin);

            }
        }
        return listCheckins;
    }
}
