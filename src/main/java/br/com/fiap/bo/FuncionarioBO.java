package br.com.fiap.bo;

import br.com.fiap.beans.Funcionario;
import br.com.fiap.dao.FuncionarioDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class FuncionarioBO {
    FuncionarioDAO funcionarioDAO;

    //Selecionar
    public ArrayList<Funcionario> selecionarBo() throws SQLException, ClassNotFoundException {
        funcionarioDAO = new FuncionarioDAO();
        return (ArrayList<Funcionario>) funcionarioDAO.selecionar();
    }

    //Inserir
    public void inserirBo(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        FuncionarioDAO funcionarioDao = new FuncionarioDAO();
        funcionarioDao.inserir(funcionario);
    }

    //Atualizar
    public void atualizarBo(Funcionario funcionario) throws SQLException, ClassNotFoundException {
        FuncionarioDAO funcionarioDao = new FuncionarioDAO();
        funcionarioDao.atualizar(funcionario);
    }

    //Deletar
    public void deletarBo(int id_funcionario) throws SQLException, ClassNotFoundException {
        FuncionarioDAO funcionarioDao = new FuncionarioDAO();
        funcionarioDao.deletar(id_funcionario);
    }

    //Login
    public Funcionario loginBo(String email, String senha) throws SQLException, ClassNotFoundException {
        FuncionarioDAO funcionarioDao = new FuncionarioDAO();
        Funcionario funcionario = funcionarioDao.buscarEmailESenha(email, senha);
        return funcionario;
    }
}
