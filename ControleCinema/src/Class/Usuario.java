package Class;

public class Usuario {

    private final String nome;

    private final int id;

    private String senha;

    public Usuario(String nome, int id, String senha) {
        this.nome = nome;
        this.id = id;
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
