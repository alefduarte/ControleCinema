package Class;

public class Filme {

    private String nome;
    private int duracao; // minutos
    private String genero;

    public Filme(String nome, int duracao, String genero) {
        this.nome = nome;
        this.duracao = duracao;
        this.genero = genero;
    }

    public String getNome() {
        return nome;
    }

    public int getDuracao() {
        return duracao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

}
