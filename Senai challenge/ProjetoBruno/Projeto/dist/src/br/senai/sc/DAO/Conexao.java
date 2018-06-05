package br.senai.sc.DAO;
import java.sql.*;

public class Conexao {
    public static Connection getConnection() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        
        Connection connect = DriverManager.
                getConnection("jdbc:mysql://localhost/Projeto?user=root&password=");         
      
        return connect;
    }
}
