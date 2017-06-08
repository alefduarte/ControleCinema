package Class;

public class Filme {

    private final String nome;

    private final Tempo duracao;

    public Filme(String nome, Tempo duracao) {
        this.nome = nome;
        this.duracao = duracao;
    }

    public String getNome() {
        return nome;
    }

    public Tempo getDuracao() {
        return duracao;
    }

}
