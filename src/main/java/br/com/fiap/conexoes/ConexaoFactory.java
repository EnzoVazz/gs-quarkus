package br.com.fiap.conexoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
    //Metodo de conexao com o banco de dados
    public Connection conexao() throws ClassNotFoundException, SQLException {
        //driver
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //retornar conexao com informações de nosso acesso
        return DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl",
                "rm561702", "071106");
    }
}
