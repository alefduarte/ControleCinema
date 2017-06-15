// iprimindo filme nao encontrado em adicionarSessoes mesmo com este encontrado
// imprimindo sala nao encontrada em adicionarSessoes mesmo com ela encontrada
package controlecinema;

import Class.DadosSessao;
import Class.Filme;
import Class.Ingresso;
import Class.Sala;
import Class.Sessao;
import Class.Tempo;
import Class.Usuario;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Principal {

    //Variáveis Globais
    public static String nome = null;
    public static String genero = null;
    public static int duracao = 0;
    public static int codigo = 0;
    public static int tipo = 0;
    public static int capacidade = 0;
    public static String user = null;
    public static Sala sala = null;
    public static Filme filme = null;
    public static Sessao sessao = null;
    public static DadosSessao dados = null;
    public static Ingresso ingresso = null;
    public static Tempo tempo = new Tempo();
    public static ArrayList<Sessao> sessoes = new ArrayList<>();
    public static ArrayList<Usuario> usuarios = new ArrayList<>();
    public static ArrayList<Filme> filmes = new ArrayList<>();
    public static ArrayList<Sala> salas = new ArrayList<>();
    public static Scanner scanner = null;

    // Banco de Dados
    public static String dirUsuarios = System.getProperty("user.dir") + "/src/Dados/usuarios.txt";
    public static String dirFilmes = System.getProperty("user.dir") + "/src/Dados/filmes.txt";
    public static String dirSalas = System.getProperty("user.dir") + "/src/Dados/salas.txt";
    public static String dirSessoes = System.getProperty("user.dir") + "/src/Dados/sessoes.txt";
    public static String dirIngressos = System.getProperty("user.dir") + "/src/Ingressos/";

    public static void main(String[] args) {
        boolean sair = false;
        while (sair == false) {
            int login = Login();
            switch (login) {
                case 1:
                    menuAdmin();
                    sair = true;
                    break;
                case 2:
                    menuFunc();
                    sair = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos");
                    break;
            }
        }
    }

    /*
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *   
    *
    * Métodos
    * 
     */
    public static int Login() {
        // retorna 1 - admin, 2 - funcionario, 0 - falha 
        int login = 0;
        JOptionPane.showMessageDialog(null, "Login");
        nome = JOptionPane.showInputDialog(null, "Nome: ");
        String senha = JOptionPane.showInputDialog(null, "Senha: ");

        try {
            scanner = new Scanner(new FileReader(dirUsuarios))
                    .useDelimiter("\\||\\n");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }
        while (scanner.hasNext()) {
            String nomeArq = scanner.next();
            String senhaArq = scanner.next();
            int idArq = Integer.parseInt(scanner.next());
            if (nomeArq.equals(nome) && senhaArq.equals(senha)) {
                user = nomeArq;
                if (idArq == 1) {
                    login = 1;
                    break;
                } else {
                    login = 2;
                    break;
                }
            } else {
                login = 0;
            }
        }
        return login;
    }

    public static void menuAdmin() {
        JOptionPane.showMessageDialog(null, "Bem vindo " + user);
        while (true) {
            int op = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Escolha uma opção:\n1. Adicionar Sala \n2. Adicionar Filme\n"
                    + "3. Adicionar Sessao\n4. Venda Ingresso\n5. Impressão\n"
                    + "6. Adicionar Usuario\n7. Sair"
                    + "\nOPÇÂO:"));

            switch (op) {
                case 1:
                    adicionarSala();
                    break;
                case 2:
                    adicionarFilme();
                    break;
                case 3:
                    adicionarSessao();
                    break;
                case 4:
                    vendaIngresso();
                    break;
                case 5:
                    while (true) {
                        op = Integer.parseInt(JOptionPane.showInputDialog(null,
                                "Escolha uma opção:\n1. Sala \n2. Filmes\n"
                                + "3. Sessoes\n4. Ingressos\n5. Usuários\n"
                                + "6. Imprimir tudo\n7. Sair"
                                + "\nOPÇÂO:"));

                        switch (op) {
                            case 1:
                                imprimirSalas();
                                break;
                            case 2:
                                imprimirFilmes();
                                break;
                            case 3:
                                imprimirSessoes();
                                break;
                            case 4:
                                imprimirIngressos();
                                break;
                            case 5:
                                imprimirUsuarios();
                                break;
                            case 6:
                                imprimirTudo();
                                imprimirUsuarios();
                                break;
                            case 7:
                                break;
                            default:
                                break;
                        }
                        if (op == 6) {
                            break;
                        } else if (Integer.parseInt(JOptionPane.showInputDialog(null, "Outra impressão? \n 1. Sim 2. Não\nOPÇÂO:")) == 2) {
                            break;
                        }
                    }
                    op = 0;
                    break;
                case 6:
                    adicionarUsuario();
                    break;
                case 7:
                    break;
                default:
                    break;
            }
            if (op == 7) {
                break;
            } else if (Integer.parseInt(JOptionPane.showInputDialog(null, "Outro Comando? \n 1. Sim 2. Não\nOPÇÂO:")) == 2) {
                break;
            }
        }
    }

    public static void menuFunc() {
        JOptionPane.showMessageDialog(null, "Bem vindo " + user);
        while (true) {
            int op = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Escolha uma opção:\n1. Venda Ingresso\n2. Impressão\n3. Sair"
                    + "\nOPÇÂO:"));

            switch (op) {
                case 1:
                    vendaIngresso();
                    break;
                case 2:
                    while (true) {
                        op = Integer.parseInt(JOptionPane.showInputDialog(null,
                                "Escolha uma opção:\n1. Sala \n2. Filmes\n"
                                + "3. Sessoes\n4. Ingressos\n5. Imprimir tudo\n6. Sair"
                                + "\nOPÇÂO:"));

                        switch (op) {
                            case 1:
                                imprimirSalas();
                                break;
                            case 2:
                                imprimirFilmes();
                                break;
                            case 3:
                                imprimirSessoes();
                                break;
                            case 4:
                                imprimirIngressos();
                                break;
                            case 5:
                                imprimirTudo();
                                break;
                            case 6:
                                break;
                            default:
                                break;
                        }
                        if (op == 6) {
                            break;
                        } else if (Integer.parseInt(JOptionPane.showInputDialog(null, "Outra impressão? \n 1. Sim 2. Não\nOPÇÂO:")) == 2) {
                            break;
                        }
                    }
                    op = 0;
                    break;
                case 3:
                    break;
                default:
                    break;
            }
            if (op == 3) {
                break;
            } else if (Integer.parseInt(JOptionPane.showInputDialog(null, "Outro Comando? \n 1. Sim 2. Não\nOPÇÂO:")) == 2) {
                break;
            }
        }
    }

    public static void adicionarSala() {
        codigo = (Integer.parseInt(JOptionPane.showInputDialog(null, "Codigo: ")));
        capacidade = (Integer.parseInt(JOptionPane.showInputDialog(null, "Capacidade: ")));

        String conteudo;
        try {
            FileWriter f = new FileWriter(dirSalas, true);
            conteudo = codigo + "|" + capacidade;
            conteudo += "\n";
            f.write(conteudo);
            f.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }

    public static void adicionarFilme() {
        nome = JOptionPane.showInputDialog(null, "Nome: ");
        duracao = (Integer.parseInt(JOptionPane.showInputDialog(null, "Duração: ")));
        genero = JOptionPane.showInputDialog(null, "Genero: ");

        String conteudo;
        try {
            FileWriter f = new FileWriter(dirFilmes, true);
            conteudo = nome + "|" + duracao + "|" + genero;
            conteudo += "\n";
            f.write(conteudo);
            f.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }

    public static void adicionarSessao() {
        boolean exit = false;

        while (exit == false) {
            if (dirFilmes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não há filmes cadastrados");
                exit = true;
            } else {
                imprimirFilmes();
                nome = JOptionPane.showInputDialog(null, "Nome do filme: ");
                try {
                    scanner = new Scanner(new FileReader(dirFilmes))
                            .useDelimiter("\\||\\n");
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: " + ex);
                }
                while (scanner.hasNext() && exit == false) {
                    String nomeArq = scanner.next();
                    duracao = Integer.parseInt(scanner.next());
                    genero = scanner.next();
                    if (nomeArq.toLowerCase().equals(nome.toLowerCase())) {
                        nome = nomeArq;
                        exit = true;
                        break;
                    } else {
                        if (exit == false) {
                            JOptionPane.showMessageDialog(null, "Filme não encontrado");
                        }
                    }
                }
            }
        }

        exit = false;
        while (exit == false) {
            if (dirSalas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não há salas cadastradas");
                exit = true;
            } else {
                imprimirSalas();
                codigo = Integer.parseInt(JOptionPane.showInputDialog(null, "Codigo da sala: "));
                try {
                    scanner = new Scanner(new FileReader(dirSalas))
                            .useDelimiter("\\||\\n");
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: " + ex);
                }
                while (scanner.hasNext() && exit == false) {
                    int codigoArq = Integer.parseInt(scanner.next());
                    capacidade = Integer.parseInt(scanner.next());
                    if (codigoArq == codigo) {
                        exit = true;
                        break;
                    } else {
                        if (exit == false) {
                            JOptionPane.showMessageDialog(null, "Sala não encontrado");
                        }
                    }

                }
            }
        }
        double preco = (Double.parseDouble(JOptionPane.showInputDialog(null, "Preço: ")));
        tipo = (Integer.parseInt(JOptionPane.showInputDialog(null, "Tipo\n1 - 2D \t 2 - 3D: ")));
        String horario = (JOptionPane.showInputDialog(null, "Horário Inicio\nHora: "));
        horario += ":" + (JOptionPane.showInputDialog(null, "Horário Inicio\nMinutos: "));

        String conteudo;
        try {
            FileWriter f = new FileWriter(dirSessoes, true);
            conteudo = nome + "|" + duracao + "|" + genero + "|" + preco + "|"
                    + codigo + "|" + capacidade + "|" + tipo + "|" + horario;
            conteudo += "\n";
            f.write(conteudo);
            f.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }

    }

    public static void vendaIngresso() {
        if (dirSessoes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há sessões disponíveis");
        } else {
            boolean sair = false;
            while (sair == false) {
                JOptionPane.showMessageDialog(null, "Escolha a sessão");

                imprimirSessoes();

                try {
                    scanner = new Scanner(new FileReader(dirSessoes))
                            .useDelimiter("\\||\\n");
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: " + ex);
                }
                int i = 0;
                int codSessao = Integer.parseInt(JOptionPane.showInputDialog(null, "Número da Sessão: "));
                while (scanner.hasNext()) {
                    i++;
                    String nomeArq = scanner.next();
                    duracao = Integer.parseInt(scanner.next());
                    genero = scanner.next();
                    double preco = Double.parseDouble(scanner.next());
                    codigo = Integer.parseInt(scanner.next());
                    capacidade = Integer.parseInt(scanner.next());
                    tipo = Integer.parseInt(scanner.next());
                    String horario = scanner.next();
                    if (codSessao == i) {
                        int codigoIng = Integer.parseInt(JOptionPane.showInputDialog(null, "Código da Ingresso: ")); //seria gerado aleatoriamente
                        int assento = Integer.parseInt(JOptionPane.showInputDialog(null, "Assento: "));
                        int tipoIng = Integer.parseInt(JOptionPane.showInputDialog(null, "Tipo\n1 - Meia\t 2 - Inteira "));
                        String conteudo;
                        conteudo = codigoIng + "|" + assento + "|" + tipoIng;
                        conteudo += "\n";
                        String arquivo = "Ingressos_Sessao" + i + ".txt";
                        if ((dirIngressos + arquivo).isEmpty()) {
                            gravaTxt(dirIngressos, arquivo, conteudo);
                        } else {
                            try (FileWriter f = new FileWriter((dirIngressos + arquivo), true)) {
                                f.write(conteudo);
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(null, "ERRO" + ex.getMessage());
                            }
                        }
                        sair = true;
                        break;
                    }
                }
                if (sair == false) {
                    JOptionPane.showMessageDialog(null, "Número Incorreto");
                }
            }
        }
    }

    public static void adicionarUsuario() {
        nome = JOptionPane.showInputDialog(null, "Nome: ");
        String senha = JOptionPane.showInputDialog(null, "Senha: ");
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "id: "));

        String conteudo;
        try {
            FileWriter f = new FileWriter(dirUsuarios, true);
            conteudo = nome + "|" + senha + "|" + id;
            conteudo += "\n";
            f.write(conteudo);
            f.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }

    public static void imprimirFilmes() {
        try {
            scanner = new Scanner(new FileReader(dirFilmes))
                    .useDelimiter("\\||\\n");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }
        int i = 0;
        while (scanner.hasNext()) {
            i++;
            nome = scanner.next();
            duracao = Integer.parseInt(scanner.next());
            genero = scanner.next();
            String filmesDisp = "Filme " + (i) + ":\n";
            filmesDisp += "Nome: " + nome + "\n"
                    + "Gênero: " + genero + "\n"
                    + "Duração: " + duracao + "m\n";
            JOptionPane.showMessageDialog(null, filmesDisp);
        }
    }

    public static void imprimirSalas() {
        try {
            scanner = new Scanner(new FileReader(dirSalas))
                    .useDelimiter("\\||\\n");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }
        int i = 0;
        while (scanner.hasNext()) {
            i++;
            codigo = Integer.parseInt(scanner.next());
            capacidade = Integer.parseInt(scanner.next());
            String salasDisp = "Sala " + (i) + ":\n";
            salasDisp += "Codigo: " + codigo + "\n"
                    + "Capacidade: " + capacidade + "\n";
            JOptionPane.showMessageDialog(null, salasDisp);
        }
    }

    public static void imprimirSessoes() {
        try {
            scanner = new Scanner(new FileReader(dirSessoes))
                    .useDelimiter("\\||\\n");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }
        int i = 0;
        while (scanner.hasNext()) {
            i++;
            nome = scanner.next();
            duracao = Integer.parseInt(scanner.next());
            genero = scanner.next();
            double preco = Double.parseDouble(scanner.next());
            codigo = Integer.parseInt(scanner.next());
            capacidade = Integer.parseInt(scanner.next());
            tipo = Integer.parseInt(scanner.next());
            String horario = scanner.next();

            String sessoesDisp = "Sessão " + i + ":\n";
            sessoesDisp += "Filme: " + nome + "\n"
                    + "Duração: " + duracao + "\n"
                    + "Gênero: " + genero + "\n"
                    + "Preço: " + preco + "\n"
                    + "Código da Sala: " + codigo + "\n"
                    + "Capacidade da Sala: " + capacidade + "m\n"
                    + "Tipo: " + tipo + "\n"
                    + "Horário : " + horario + "\n";
            JOptionPane.showMessageDialog(null, sessoesDisp);
        }

    }

    public static void imprimirIngressos() {
        if (dirSessoes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há sessões disponíveis");
        } else {
            boolean sair = false;
            while (sair == false) {
                JOptionPane.showMessageDialog(null, "Escolha a sessão");

                imprimirSessoes();

                try {
                    scanner = new Scanner(new FileReader(dirSessoes))
                            .useDelimiter("\\||\\n");
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: " + ex);
                }
                int i = 0;
                int codSessao = Integer.parseInt(JOptionPane.showInputDialog(null, "Número da Sessão: "));
                while (scanner.hasNext()) {
                    i++;
                    String nomeArq = scanner.next();
                    duracao = Integer.parseInt(scanner.next());
                    genero = scanner.next();
                    double preco = Double.parseDouble(scanner.next());
                    codigo = Integer.parseInt(scanner.next());
                    capacidade = Integer.parseInt(scanner.next());
                    tipo = Integer.parseInt(scanner.next());
                    String horario = scanner.next();

                    if (codSessao == i) {
                        String arquivo = "Ingressos_Sessao" + i + ".txt";
                        if ((dirIngressos + arquivo).isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Não há ingressos para essa sessão");
                        } else {
                            try {
                                scanner = new Scanner(new FileReader(dirIngressos + arquivo))
                                        .useDelimiter("\\||\\n");
                            } catch (FileNotFoundException ex) {
                                JOptionPane.showMessageDialog(null, "Erro: " + ex);
                            }
                            String infSessoes = "Filme: " + nome + "\n"
                                    + "Duração: " + duracao + "\n"
                                    + "Gênero: " + genero + "\n"
                                    + "Preço: " + preco + "\n"
                                    + "Código da Sala: " + codigo + "\n"
                                    + "Capacidade da Sala: " + capacidade + "\n"
                                    + "Tipo: " + tipo + "\n"
                                    + "Horário : " + horario + "\n";
                            i = 0;
                            while (scanner.hasNext()) {
                                i++;
                                codigo = Integer.parseInt(scanner.next());
                                int assento = Integer.parseInt(scanner.next());
                                tipo = Integer.parseInt(scanner.next());

                                String sessoesDisp = "Ingresso " + i + ":\n";
                                sessoesDisp += infSessoes;
                                sessoesDisp += "Código: " + codigo + "\n"
                                        + "Assento: " + assento + "\n"
                                        + "Tipo: " + tipo + "\n";
                                JOptionPane.showMessageDialog(null, sessoesDisp);
                            }
                        }
                        sair = true;
                        break;
                    }
                }
                if (sair == false) {
                    JOptionPane.showMessageDialog(null, "Número Incorreto");
                }
            }
        }
    }

    public static void imprimirUsuarios() {
        try {
            scanner = new Scanner(new FileReader(dirUsuarios))
                    .useDelimiter("\\||\\n");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        }
        int i = 0;
        while (scanner.hasNext()) {
            i++;
            String usuario = scanner.next();
            String senha = scanner.next();
            int id = Integer.parseInt(scanner.next());
            String salasDisp = "Usuário: " + usuario + "\n";
            salasDisp += "Senha: " + senha + "\n"
                    + "ID: " + id + "\n";
            JOptionPane.showMessageDialog(null, salasDisp);
        }
    }

    public static void imprimirTudo() {
        imprimirSalas();
        imprimirFilmes();
        imprimirSessoes();
        imprimirIngressos();

    }

    public static void gravaTxt(String dir, String nomeArq, String conteudo) {

        File file = new File(dir + nomeArq);
        try {
            FileWriter f = new FileWriter(file, true);
            f.write(conteudo);
            f.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }


    /*
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *
    *   
     */
    //<editor-fold defaultstate="collapsed" desc=" Código usado anteriormente, sem o uso de arquivos txt ">

    /*

    public static void adicionarSala() {
        codigo = (Integer.parseInt(JOptionPane.showInputDialog(null, "Codigo: ")));
        int capacidade = (Integer.parseInt(JOptionPane.showInputDialog(null, "Capacidade: ")));

        sala = new Sala(codigo, capacidade);
        salas.add(sala);
    }


    public static void adicionarFilme() {
        nome = JOptionPane.showInputDialog(null, "Nome: ");
        duracao = (Integer.parseInt(JOptionPane.showInputDialog(null, "Duração: ")));
        genero = JOptionPane.showInputDialog(null, "Genero: ");

        filme = new Filme(nome, duracao, genero);
        filmes.add(filme);
    }


    public static void adicionarSessao() {
        int exit = 0;
        while (exit == 0) {
            nome = JOptionPane.showInputDialog(null, "Nome do filme: ");
            if (filmes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não há filmes cadastrados");
            } else {
                for (int i = 0; i < filmes.size(); i++) {
                    if (filmes.get(i).getNome().toLowerCase().equals(nome.toLowerCase())) {
                        filme = filmes.get(i);
                        exit = 1;
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Filme não encontrado");
                    }
                }
            }
        }
        exit = 0;
        while (exit == 0) {
            codigo = Integer.parseInt(JOptionPane.showInputDialog(null, "Codigo da sala: "));
            if (salas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não há salas cadastradas");
            } else {
                for (int i = 0; i < salas.size(); i++) {
                    if (salas.get(i).getCodSala() == codigo) {
                        sala = salas.get(i);
                        exit = 1;
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Sala não encontrado");
                    }
                }
            }
        }
        double preco = (Double.parseDouble(JOptionPane.showInputDialog(null, "Preço: ")));
        tipo = (Integer.parseInt(JOptionPane.showInputDialog(null, "Tipo\n1 - 2D \t 2 - 3D: ")));
        tempo.setHora(Integer.parseInt(JOptionPane.showInputDialog(null, "Horário Inicio\nHora: ")));
        tempo.setMinuto(Integer.parseInt(JOptionPane.showInputDialog(null, "Horário Inicio\nMinutos: ")));
        dados = new DadosSessao(filme, preco, sala, tipo, tempo);
        sessao = new Sessao(dados);

        sessoes.add(sessao);

    }


    public static void vendaIngresso() {
        if (sessoes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há sessões disponíveis");
        } else {
            boolean sair = false;
            while (sair == false) {
                String sessoesDisp = "";
                JOptionPane.showMessageDialog(null, "Escolha a sessão");
                for (int i = 0; i < sessoes.size(); i++) {
                    sessoesDisp = "Filme " + (i + 1) + ":\n";
                    sessoesDisp += sessoes.get(i).getDados().getFilme().getNome() + "\n";
                }
                JOptionPane.showMessageDialog(null, sessoesDisp);
                nome = JOptionPane.showInputDialog(null, "Nome do filme: ");
                for (int i = 0; i < sessoes.size(); i++) {
                    if (sessoes.get(i).getDados().getFilme().getNome().equals(nome)) {
                        codigo = Integer.parseInt(JOptionPane.showInputDialog(null, "Código da Ingresso: ")); //seria gerado aleatoriamente
                        int assento = Integer.parseInt(JOptionPane.showInputDialog(null, "Assento: "));
                        tipo = Integer.parseInt(JOptionPane.showInputDialog(null, "Tipo\n1 - Meia\t 2 - Inteira "));
                        ingresso = new Ingresso(codigo, assento, tipo);
                        sessoes.get(i).adicionarIngresso(ingresso);
                        sair = true;
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Nome incorreto");
                    }
                }
            }
        }
    }

    
    
    public static void imprimirTudo() {
        String salasDisp = "";
        for (int i = 0; i < salas.size(); i++) {
            salasDisp = "Sala " + (i + 1) + ":\n";
            salasDisp += "Código: " + salas.get(i).getCodSala() + "\n"
                    + "Capacidade: " + salas.get(i).getCapacidade();
        }
        JOptionPane.showMessageDialog(null, salasDisp);

        String filmesDisp = "";
        for (int i = 0; i < filmes.size(); i++) {
            filmesDisp = "Filme " + (i + 1) + ":\n";
            filmesDisp += "Nome: " + filmes.get(i).getNome() + "\n"
                    + "Gênero: " + filmes.get(i).getGenero() + "\n"
                    + "Duração: " + filmes.get(i).getDuracao() + "m";
        }
        JOptionPane.showMessageDialog(null, filmesDisp);

        String sessoesDisp = "";
        for (int i = 0; i < sessoes.size(); i++) {
            sessoesDisp = "Sessão " + (i + 1) + ":\n";
            sessoesDisp += "Código da Sala: " + sessoes.get(i).getDados().getSala().getCodSala() + "\n"
                    + "Capacidade da Sala: " + sessoes.get(i).getDados().getSala().getCapacidade() + "\n"
                    + "Filme: " + sessoes.get(i).getDados().getFilme().getNome() + "\n"
                    + "Gênero: " + sessoes.get(i).getDados().getFilme().getGenero() + "\n"
                    + "Duração: " + sessoes.get(i).getDados().getFilme().getDuracao() + "m\n"
                    + "Preço: " + sessoes.get(i).getDados().getPreco() + "\n"
                    + "Horário : " + sessoes.get(i).getDados().getHorarioInicio().getHora() + ":"
                    + sessoes.get(i).getDados().getHorarioInicio().getMinuto() + "\n"
                    + "Tipo: " + sessoes.get(i).getDados().getTipo() + "\n";
        }
        JOptionPane.showMessageDialog(null, sessoesDisp);

        sessoes.stream().forEach((m) -> {
            m.imprimirIngressos();
        });
    }

    
    
    public static void adicionarUsuario() {
        JOptionPane.showMessageDialog(null, "Adicionar Usuario");
        nome = JOptionPane.showInputDialog(null, "Nome: ");
        String senha = JOptionPane.showInputDialog(null, "Senha: ");
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "id: "));
        Usuario usuario = new Usuario(nome, id, senha);
        usuarios.add(usuario);
    }

    public static void adicionarUsuario(String nome, int id, String senha) {
        Usuario usuario = new Usuario(nome, id, senha);
        usuarios.add(usuario);
    }
     */
    //</editor-fold>
}
