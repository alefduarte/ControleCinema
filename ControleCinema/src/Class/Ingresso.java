package Class;

public class Ingresso {

    private final int codigo;

    private int assento;

    /**
     * Meia ou Inteira
     *
     */
    private int tipo;

    private DadosSessao dados;

    public Ingresso(int codigo, int assento, int tipo, DadosSessao dados) {
        this.codigo = codigo;
        this.assento = assento;
        this.tipo = tipo;
        this.dados = dados;
    }

    public int getAssento() {
        return assento;
    }

    public void setAssento(int assento) {
        this.assento = assento;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public DadosSessao getDados() {
        return dados;
    }

    public void setDados(DadosSessao dados) {
        this.dados = dados;
    }

    public int getCodigo() {
        return codigo;
    }

}
