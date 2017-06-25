package BancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/* Written by alefduarte */
public class Conexao {

    public Connection conexao;
    public Statement sentenca;
    private String url;
    private String user;
    private String senha;

    public Conexao() {
        this.user = "root";
        this.senha = "12345";
        this.url = "jdbc:mysql://localhost:3306/ControleCinema";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection(url, user, senha);
            sentenca = conexao.createStatement();
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver não encontrado! " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Erro de conexão com banco de dados " + ex.getMessage());
        }
    }

    public Conexao(String user, String senha) {
        this.user = user;
        this.senha = senha;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/ControleCinema", user, senha);
            sentenca = conexao.createStatement();
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver não encontrado! " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Erro de conexão com banco de dados " + ex.getMessage());
        }
    }

    public Conexao(String url, String user, String senha) {
        this.url = url;
        this.user = user;
        this.senha = senha;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection(url, user, senha);
            sentenca = conexao.createStatement();
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver não encontrado! " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Erro de conexão com banco de dados " + ex.getMessage());
        }
    }

}
