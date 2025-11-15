package br.com.fiap.bo;

import br.com.fiap.beans.Empresa;
import br.com.fiap.dao.EmpresaDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmpresaBO {
    EmpresaDAO empresaDAO;

    //Selecionar
    public ArrayList<Empresa> selecionarBo() throws SQLException, ClassNotFoundException {
        empresaDAO = new EmpresaDAO();
        return (ArrayList<Empresa>) empresaDAO.selecionar();
    }

    //Inserir
    public void inserirBo(Empresa empresa) throws SQLException, ClassNotFoundException {
        EmpresaDAO empresaDao = new EmpresaDAO();
        empresaDao.inserir(empresa);
    }

    //Atualizar
    public void atualizarBo(Empresa empresa) throws SQLException, ClassNotFoundException {
        EmpresaDAO empresaDao = new EmpresaDAO();
        empresaDao.atualizar(empresa);
    }

    //Deletar
    public void deletarBo(int id_empresa) throws SQLException, ClassNotFoundException {
        EmpresaDAO empresaDao = new EmpresaDAO();
        empresaDao.deletar(id_empresa);
    }
}
