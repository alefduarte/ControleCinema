package Class;

public class Sessao {

    private Ingresso[] ingressos;
    private DadosSessao dados;

    public Sessao(DadosSessao dados) {
        this.dados = dados;
    }

    public DadosSessao getDados() {
        return dados;
    }

    public void setDados(DadosSessao dados) {
        this.dados = dados;
    }

    

}
