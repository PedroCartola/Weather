package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    static String stringconexao = "jdbc:postgresql:/localhost:5432/ApiJava";
    static String usuario = "postgres";
    static String senha = "123";
    
    public Connection getConnection(){
        try{
            return DriverManager.getConnection(stringconexao,usuario, senha);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
