package BancoDeDados;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/* Written by alefduarte */
public class DBConnection {
    private Connection DBConnection;
    public Connection connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Conexão concluída");
        }catch(ClassNotFoundException cnfe){
            System.out.println("Coneção falhou " + cnfe);
        }
        String url = "jdbc:mysql://localhost:3306/ControleCinema";
        try {
            DBConnection = (Connection) DriverManager.getConnection(url, "root", "12345");
            System.out.println("Banco de dados conectado");
        } catch(SQLException se){
            System.out.println("Banco de dados nao encontrado " + se);
        }
        return DBConnection;
    }
}
