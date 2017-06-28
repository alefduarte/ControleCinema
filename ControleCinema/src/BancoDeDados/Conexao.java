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
    public String error;

    // construtor padrao
    public Conexao() {
        error = "";
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
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conexao = DriverManager.getConnection(url, "gabriel", senha);
                sentenca = conexao.createStatement();
            } catch (ClassNotFoundException ez) {
                System.out.println("Driver não encontrado! " + ez.getMessage());
            } catch (SQLException ez) {
            }
        }
    }

    // editavel
    public Conexao(String url, String user, String senha) {
        error = "";
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
            error = ("Erro de conexão com banco de dados " + ex.getMessage());
        }
    }

    public Connection getConexao() {
        return conexao;
    }

    public void setConexao(Connection conexao) {
        this.conexao = conexao;
    }

    public Statement getSentenca() {
        return sentenca;
    }

    public void setSentenca(Statement sentenca) {
        this.sentenca = sentenca;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
