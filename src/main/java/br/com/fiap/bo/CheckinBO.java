package br.com.fiap.bo;

import br.com.fiap.beans.Checkin;
import br.com.fiap.dao.CheckinDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CheckinBO {
    CheckinDAO checkinDAO;

    //Selecionar
    public ArrayList<Checkin> selecionarBo() throws SQLException, ClassNotFoundException {
        checkinDAO = new CheckinDAO();
        return (ArrayList<Checkin>) checkinDAO.selecionar();
    }

    //Inserir
    public void inserirBo(Checkin checkin) throws SQLException, ClassNotFoundException {
        CheckinDAO checkinDao = new CheckinDAO();
        checkinDao.inserir(checkin);
    }

    //Atualizar
    public void atualizarBo(Checkin checkin) throws SQLException, ClassNotFoundException {
        CheckinDAO checkinDao = new CheckinDAO();
        checkinDao.atualizar(checkin);
    }

    //Deletar
    public void deletarBo(int id_checkin) throws SQLException, ClassNotFoundException {
        CheckinDAO checkinDao = new CheckinDAO();
        checkinDao.deletar(id_checkin);
    }
}
