package br.com.fiap.bo;

import br.com.fiap.beans.Programa;
import br.com.fiap.dao.ProgramaDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProgramaBO {
    ProgramaDAO programaDAO;

    //Selecionar
    public ArrayList<Programa> selecionarBo() throws SQLException, ClassNotFoundException {
        programaDAO = new ProgramaDAO();
        return (ArrayList<Programa>) programaDAO.selecionar();
    }

    //Inserir
    public void inserirBo(Programa programa) throws SQLException, ClassNotFoundException {
        ProgramaDAO programaDao = new ProgramaDAO();
        programaDao.inserir(programa);
    }

    //Atualizar
    public void atualizarBo(Programa programa) throws SQLException, ClassNotFoundException {
        ProgramaDAO programaDao = new ProgramaDAO();
        programaDao.atualizar(programa);
    }

    //Deletar
    public void deletarBo(int id_programa) throws SQLException, ClassNotFoundException {
        ProgramaDAO programaDao = new ProgramaDAO();
        programaDao.deletar(id_programa);
    }
}
