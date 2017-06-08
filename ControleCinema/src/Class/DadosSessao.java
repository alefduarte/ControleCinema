package Class;

public class DadosSessao {

    private Filme filme;

    private double preco;

    private Sala sala;

    /**
     * 2D ou 3D
     */
    private int tipo;

    private Tempo horarioInicio;

    private Tempo horarioFim;

    public DadosSessao(Filme filme, double preco, Sala sala, int tipo, Tempo horarioInicio, Tempo horarioFim) {
        this.filme = filme;
        this.preco = preco;
        this.sala = sala;
        this.tipo = tipo;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Tempo getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(Tempo horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public Tempo getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(Tempo horarioFim) {
        this.horarioFim = horarioFim;
    }

}
