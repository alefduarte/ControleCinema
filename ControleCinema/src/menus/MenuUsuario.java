/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import BancoDeDados.Conexao;
import Ingressos.IngressoViewer;
import Ingressos.SalvaIngresso;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.util.ArrayList;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 *
 * @author gabriel
 */
public class MenuUsuario extends javax.swing.JFrame {

    // variaveis globais, para nao ser preciso declarar toda ver que usá-la
    public static String sql = null;
    public static Conexao con = new Conexao();
    public static String dirImagem = System.getProperty("user.dir") + "/src/Filmes/";

    public void inserirImagensDir() {
        String CaminhoPacote;
        int j = 0;
        File folder = new File(dirImagem);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                j++;
                CaminhoPacote = listOfFiles[i].getAbsolutePath();
                JLabel filme = new JLabel("Label n" + j);
                filme.setBackground(jbSair1.getBackground());
                filme.setOpaque(true);
                String text = jComboBoxNomeFilme.getSelectedItem().toString()
                        + "\nHorario de Inicio: " + jtxHoraInicio.getText() + ":" + jtxMinutosInicio.getText();
                Border bord = new TitledBorder(jbSair1.getBorder(), text, 0, ICONIFIED, jbSair1.getFont(), Color.WHITE);
                filme.setBorder(bord);
                filme.setText("");
                Icon icon = new ImageIcon(CaminhoPacote);
                filme.setIcon(icon);
                jPanel3.add(filme);
                jPanel3.setName(sql);
            }
        }
    }

    public void inserirImagensBanco() {
        int j = 0;
        String CaminhoPacote;
        sql = "SELECT  * FROM filmes";
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            while (retorno.next()) {
                nome = retorno.getString("nome");
                codFilme = retorno.getString("codigo");

                // busca cada horario do filme
                Conexao innerCon = new Conexao();
                sql = "SELECT * FROM sessoes";
                try {
                    ResultSet retorno2 = innerCon.sentenca.executeQuery(sql);
                    while (retorno2.next()) {
                        if (retorno2.getString("codFilme").equals(codFilme)) {
                            j++;
                            CaminhoPacote = dirImagem + retorno.getString("imagem");
                            JLabel filme = new JLabel("Label n" + j);
                            filme.setBackground(jbSair1.getBackground());
                            filme.setOpaque(true);
                            String text = nome + "\nHorario de Inicio: " + retorno2.getTime("horario").toString();
                            Border bord = new TitledBorder(jbSair1.getBorder(), text, 0, ICONIFIED, jbSair1.getFont(), Color.WHITE);
                            filme.setBorder(bord);
                            filme.setText("");
                            Icon icon = new ImageIcon(CaminhoPacote);
                            filme.setIcon(icon);
                            jPanel3.add(filme);
                            jPanel3.setName(nome);
                        }
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao buscar lista de filmes\n" + ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar lista de filmes\n" + ex.getMessage());
        }
    }

    public BufferedReader pegarImagemPacote(String fileNameWithExtension, String sourcePackage) {
        try {
            String file = "/" + sourcePackage + "/" + fileNameWithExtension;
            BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(file)));
            return br;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }

    public static void Copiar2(File source, File dest) throws InterruptedException, IOException {
        //copy file conventional way using Stream
        Copiar(source, dest);
    }

    private static void Copiar(File source, File dest) throws IOException {
        Path copy = Files.copy(source.toPath(), dest.toPath());
        /*Writer output = null;
        output = new BufferedWriter(new FileWriter(dest));
        return (BufferedWriter) output;*/
    }

    public void resetaPoltrona() {
        jButtonA1.setEnabled(true);
        jButtonB1.setEnabled(true);
        jButtonC1.setEnabled(true);
        jButtonD1.setEnabled(true);
        jButtonE1.setEnabled(true);
        jButtonF1.setEnabled(true);
        jButtonG1.setEnabled(true);
        jButtonA2.setEnabled(true);
        jButtonB2.setEnabled(true);
        jButtonC2.setEnabled(true);
        jButtonD2.setEnabled(true);
        jButtonE2.setEnabled(true);
        jButtonF2.setEnabled(true);
        jButtonG2.setEnabled(true);
        jButtonA3.setEnabled(true);
        jButtonB3.setEnabled(true);
        jButtonC3.setEnabled(true);
        jButtonD3.setEnabled(true);
        jButtonE3.setEnabled(true);
        jButtonF3.setEnabled(true);
        jButtonG3.setEnabled(true);
        jButtonA4.setEnabled(true);
        jButtonB4.setEnabled(true);
        jButtonC4.setEnabled(true);
        jButtonD4.setEnabled(true);
        jButtonE4.setEnabled(true);
        jButtonF4.setEnabled(true);
        jButtonG4.setEnabled(true);
        jButtonA5.setEnabled(true);
        jButtonB5.setEnabled(true);
        jButtonC5.setEnabled(true);
        jButtonD5.setEnabled(true);
        jButtonE5.setEnabled(true);
        jButtonF5.setEnabled(true);
        jButtonG5.setEnabled(true);
    }

    public static File resize(File source) throws IOException {
        String inputImagePath = source.getAbsolutePath();
        String outputImagePath = source.getAbsolutePath();
        outputImagePath += "novo." + outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1); //pega o tipo da imagem
        int scaledWidth = 300; //alterei pra 300 como padrao
        int scaledHeight = 300;
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);

        //Define tamanho de largura 
        float o = inputImage.getHeight();
        float u = inputImage.getWidth();
        float porc = o / u;
        // scaledWidth = (int) (scaledHeight * porc); tava deixando a imagem gorda

        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());

        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);

        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
        File temp = new File(outputImagePath);
        return temp;
    }

    /**
     * Creates new form Login_2
     *
     * @param ver
     */
    public MenuUsuario(boolean ver) {
        initComponentsAlterado(ver);
        //initComponents();
    }

    public void recebePoltrona(String pol) {
        jLabel21.setText("Poltrona " + pol);
    }

    public void voltarPedido() {
        jpPedido.setVisible(true);
        jpCancelamento.setVisible(false);
        jpFechamento.setVisible(false);
        jpSuperUser.setVisible(false);
        jLabel2.setVisible(false);
        jpMostrador2.setVisible(false);
        jpPoltrona.setVisible(false);
        jpCadastrarSessao.setVisible(false);
        jpRemoverSessao.setVisible(false);
        jpCadastrarFilme.setVisible(false);
        jpCadastrarSala.setVisible(false);
        jpCadastrarUsuario.setVisible(false);
        jpRemoverUsuario.setVisible(false);
        jpRemoverSala.setVisible(false);
        jpRemoverFilme.setVisible(false);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponentsAlterado(boolean superdefinidor) {
        initComponents();
        inserirImagensBanco();
        jbSuperUser.setVisible(superdefinidor);

        jpPedido.setVisible(false);
        jpCancelamento.setVisible(false);
        jpFechamento.setVisible(false);
        jpSuperUser.setVisible(false);
        jLabel2.setVisible(true);
        jpMostrador2.setVisible(true);
        jpPoltrona.setVisible(false);
        jpCadastrarSessao.setVisible(false);
        jpRemoverSessao.setVisible(false);
        jpCadastrarFilme.setVisible(false);
        jpCadastrarSala.setVisible(false);
        jpCadastrarUsuario.setVisible(false);
        jpRemoverUsuario.setVisible(false);
        jpRemoverSala.setVisible(false);
        jpRemoverFilme.setVisible(false);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jpDevCodesSite = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jpDevCodes = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jpSeletorOpc = new javax.swing.JPanel();
        jbSair = new javax.swing.JButton();
        jlNomeUsuario = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jbCancelamento = new javax.swing.JButton();
        jbFechamento = new javax.swing.JButton();
        jbSuperUser = new javax.swing.JButton();
        jbHome = new javax.swing.JButton();
        jbPedido = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jpMostrador1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jpMostrador2 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jpCancelamento = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jbCancelarIngresso = new javax.swing.JButton();
        jbCancelarIngresso1 = new javax.swing.JButton();
        jpPedido = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jbPoltrona = new javax.swing.JButton();
        jbCompra = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jpPoltrona = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jlLinha5 = new javax.swing.JLabel();
        jlLinha1 = new javax.swing.JLabel();
        jlLinha2 = new javax.swing.JLabel();
        jlLinha3 = new javax.swing.JLabel();
        jlLinha4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButtonA1 = new javax.swing.JButton();
        jButtonB1 = new javax.swing.JButton();
        jButtonC1 = new javax.swing.JButton();
        jButtonD1 = new javax.swing.JButton();
        jButtonE1 = new javax.swing.JButton();
        jButtonF1 = new javax.swing.JButton();
        jButtonG1 = new javax.swing.JButton();
        jButtonA2 = new javax.swing.JButton();
        jButtonA3 = new javax.swing.JButton();
        jButtonA4 = new javax.swing.JButton();
        jButtonA5 = new javax.swing.JButton();
        jButtonB2 = new javax.swing.JButton();
        jButtonB3 = new javax.swing.JButton();
        jButtonB4 = new javax.swing.JButton();
        jButtonB5 = new javax.swing.JButton();
        jButtonC2 = new javax.swing.JButton();
        jButtonC3 = new javax.swing.JButton();
        jButtonC4 = new javax.swing.JButton();
        jButtonC5 = new javax.swing.JButton();
        jButtonD2 = new javax.swing.JButton();
        jButtonD3 = new javax.swing.JButton();
        jButtonD4 = new javax.swing.JButton();
        jButtonD5 = new javax.swing.JButton();
        jButtonE2 = new javax.swing.JButton();
        jButtonE3 = new javax.swing.JButton();
        jButtonE4 = new javax.swing.JButton();
        jButtonE5 = new javax.swing.JButton();
        jButtonF2 = new javax.swing.JButton();
        jButtonF3 = new javax.swing.JButton();
        jButtonF4 = new javax.swing.JButton();
        jButtonF5 = new javax.swing.JButton();
        jButtonG2 = new javax.swing.JButton();
        jButtonG3 = new javax.swing.JButton();
        jButtonG4 = new javax.swing.JButton();
        jButtonG5 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jpFechamento = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jpSuperUser = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jbSair2 = new javax.swing.JButton();
        jbSair3 = new javax.swing.JButton();
        jbSair4 = new javax.swing.JButton();
        jbRemoverSessao = new javax.swing.JButton();
        jbSair6 = new javax.swing.JButton();
        jbSair7 = new javax.swing.JButton();
        jSeparator19 = new javax.swing.JSeparator();
        jbSair10 = new javax.swing.JButton();
        jbSair5 = new javax.swing.JButton();
        jpCadastrarSessao = new javax.swing.JPanel();
        jbSair1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jtxMinutosInicio = new javax.swing.JTextField();
        jtxHoraInicio = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jComboBoxNomeFilme = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel64 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jTextField11 = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        jpRemoverSessao = new javax.swing.JPanel();
        jbBuscarSessao = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jlTipoS = new javax.swing.JLabel();
        jlPrecoS = new javax.swing.JLabel();
        jlCodigoSalaS = new javax.swing.JLabel();
        jlGeneroFilmeS = new javax.swing.JLabel();
        jlNomeFilmeS = new javax.swing.JLabel();
        jlDuracaoFilmeS = new javax.swing.JLabel();
        jlHorarioIniS = new javax.swing.JLabel();
        jtxBuscarCodigoS = new javax.swing.JTextField();
        jLabel88 = new javax.swing.JLabel();
        jlCodigoFilmeS = new javax.swing.JLabel();
        jbSair11 = new javax.swing.JButton();
        jLabel90 = new javax.swing.JLabel();
        jlAudioS = new javax.swing.JLabel();
        jpCadastrarFilme = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jbSair8 = new javax.swing.JButton();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jbCadastrarFilme = new javax.swing.JButton();
        jtxCodigoC = new javax.swing.JTextField();
        jtxDuracacaoC = new javax.swing.JTextField();
        jtxGeneroC = new javax.swing.JTextField();
        jtxNomeFilmeC = new javax.swing.JTextField();
        jpCadastrarSala = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        jtxCapacidadeSala = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        jtxCodigoSala = new javax.swing.JTextField();
        jbCadastrarS = new javax.swing.JButton();
        jpCadastrarUsuario = new javax.swing.JPanel();
        jLabel71 = new javax.swing.JLabel();
        jtxNomeU = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        jtxId = new javax.swing.JTextField();
        jbCadastrarU = new javax.swing.JButton();
        jLabel73 = new javax.swing.JLabel();
        jtxSenha = new javax.swing.JPasswordField();
        jtxSenhaComfirmacao = new javax.swing.JPasswordField();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jcbTipoUsuario = new javax.swing.JComboBox<>();
        jpRemoverUsuario = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        jtxIdBusca = new javax.swing.JTextField();
        jbBuscarUsuario = new javax.swing.JButton();
        jLabel78 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jlSenhaU = new javax.swing.JLabel();
        jlTipoUsuario = new javax.swing.JLabel();
        jlNomeU = new javax.swing.JLabel();
        jbRemoverU = new javax.swing.JButton();
        jpRemoverSala = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jtxBuscarSala = new javax.swing.JTextField();
        jbBusacarSala = new javax.swing.JButton();
        jlCapacidade = new javax.swing.JLabel();
        jbCadastrarS2 = new javax.swing.JButton();
        jpRemoverFilme = new javax.swing.JPanel();
        jLabel82 = new javax.swing.JLabel();
        jlGenero = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jbBuscarFilme = new javax.swing.JButton();
        jtxBuscaCodigoF = new javax.swing.JTextField();
        jlCapa = new javax.swing.JTextField();
        jlNomeFilme = new javax.swing.JTextField();
        jlDuracao = new javax.swing.JTextField();
        jbRemoverFilme1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpDevCodesSite.setBackground(new java.awt.Color(36, 47, 65));

        jLabel6.setFont(new java.awt.Font("Bebas", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("www.devcode.com.br");
        jpDevCodesSite.add(jLabel6);

        jPanel1.add(jpDevCodesSite, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 570, 560, 30));

        jpDevCodes.setBackground(new java.awt.Color(36, 47, 65));

        jLabel5.setFont(new java.awt.Font("Bebas", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("DevCode");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jpDevCodes.add(jLabel5);

        jPanel1.add(jpDevCodes, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 560, 30));

        jpSeletorOpc.setBackground(new java.awt.Color(36, 47, 65));
        jpSeletorOpc.setForeground(new java.awt.Color(36, 47, 65));
        jpSeletorOpc.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbSair.setBackground(new java.awt.Color(229, 91, 0));
        jbSair.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jbSair.setForeground(new java.awt.Color(255, 255, 255));
        jbSair.setText("Sair");
        jbSair.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbSairMouseClicked(evt);
            }
        });
        jbSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSairActionPerformed(evt);
            }
        });
        jpSeletorOpc.add(jbSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 80, 20));

        jlNomeUsuario.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jlNomeUsuario.setForeground(new java.awt.Color(255, 255, 255));
        jlNomeUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/user-icon.png"))); // NOI18N
        jlNomeUsuario.setText("    Usuario");
        jpSeletorOpc.add(jlNomeUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 160, 70));

        jPanel2.setBackground(new java.awt.Color(229, 91, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(229, 91, 0), 4));
        jPanel2.setForeground(new java.awt.Color(238, 238, 238));
        jPanel2.setToolTipText("");
        jpSeletorOpc.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 30, 70, 70));

        jbCancelamento.setBackground(new java.awt.Color(229, 91, 0));
        jbCancelamento.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jbCancelamento.setForeground(new java.awt.Color(255, 255, 255));
        jbCancelamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/cancel.png"))); // NOI18N
        jbCancelamento.setText("Cancelamento");
        jbCancelamento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbCancelamento.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbCancelamento.setIconTextGap(30);
        jbCancelamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbCancelamentoMouseClicked(evt);
            }
        });
        jbCancelamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelamentoActionPerformed(evt);
            }
        });
        jpSeletorOpc.add(jbCancelamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 230, 40));

        jbFechamento.setBackground(new java.awt.Color(229, 91, 0));
        jbFechamento.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jbFechamento.setForeground(new java.awt.Color(255, 255, 255));
        jbFechamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/dollar-symbol.png"))); // NOI18N
        jbFechamento.setText("Fechamento de Caixa");
        jbFechamento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbFechamento.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbFechamento.setIconTextGap(7);
        jbFechamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbFechamentoMouseClicked(evt);
            }
        });
        jbFechamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbFechamentoActionPerformed(evt);
            }
        });
        jpSeletorOpc.add(jbFechamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 230, 40));

        jbSuperUser.setBackground(new java.awt.Color(229, 91, 0));
        jbSuperUser.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jbSuperUser.setForeground(new java.awt.Color(255, 255, 255));
        jbSuperUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/file.png"))); // NOI18N
        jbSuperUser.setText("Super User");
        jbSuperUser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbSuperUser.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbSuperUser.setIconTextGap(45);
        jbSuperUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbSuperUserMouseClicked(evt);
            }
        });
        jbSuperUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSuperUserActionPerformed(evt);
            }
        });
        jpSeletorOpc.add(jbSuperUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 230, 40));

        jbHome.setBackground(new java.awt.Color(229, 91, 0));
        jbHome.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jbHome.setForeground(new java.awt.Color(255, 255, 255));
        jbHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/house.png"))); // NOI18N
        jbHome.setText("Home");
        jbHome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbHome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbHome.setIconTextGap(70);
        jbHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbHomeMouseClicked(evt);
            }
        });
        jbHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbHomeActionPerformed(evt);
            }
        });
        jpSeletorOpc.add(jbHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 230, 40));

        jbPedido.setBackground(new java.awt.Color(229, 91, 0));
        jbPedido.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jbPedido.setForeground(new java.awt.Color(255, 255, 255));
        jbPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/checked.png"))); // NOI18N
        jbPedido.setText("Pedido");
        jbPedido.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbPedido.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbPedido.setIconTextGap(65);
        jbPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbPedidoMouseClicked(evt);
            }
        });
        jbPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPedidoActionPerformed(evt);
            }
        });
        jpSeletorOpc.add(jbPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 230, 40));
        jpSeletorOpc.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 230, -1));
        jpSeletorOpc.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 230, -1));
        jpSeletorOpc.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 230, -1));
        jpSeletorOpc.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 230, -1));

        jPanel1.add(jpSeletorOpc, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 600));

        jpPedido.setVisible(false);
        jpCancelamento.setVisible(false);
        jpFechamento.setVisible(false);
        jpSuperUser.setVisible(false);
        jLabel2.setVisible(true);
        jLabel3.setVisible(true);
        jpMostrador1.setVisible(true);
        jpMostrador2.setVisible(true);
        jpPoltrona.setVisible(false);
        jpCadastrarSessao.setVisible(false);
        jpPedido.setVisible(true);
        jpCancelamento.setVisible(false);
        jpFechamento.setVisible(false);
        jpSuperUser.setVisible(false);
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        jpMostrador1.setVisible(false);
        jpMostrador2.setVisible(false);
        jpPoltrona.setVisible(false);
        jpCadastrarSessao.setVisible(false);
        jpRemoverSessao.setVisible(false);
        jLayeredPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Bebas", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(36, 47, 65));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Filmes em Cartaz");
        jLayeredPane1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 120));

        jLabel3.setFont(new java.awt.Font("Bebas", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(36, 47, 65));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLayeredPane1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 536, 550, 0));

        jpMostrador1.setBackground(new java.awt.Color(36, 47, 65));
        jpMostrador1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        jPanel4.setBackground(new java.awt.Color(36, 47, 65));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jpMostrador1.setViewportView(jPanel4);

        jLayeredPane1.add(jpMostrador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 492, 490, 0));

        jpMostrador2.setBackground(new java.awt.Color(36, 47, 65));
        jpMostrador2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        jPanel3.setBackground(new java.awt.Color(36, 47, 65));
        jpMostrador2.setViewportView(jPanel3);

        jLayeredPane1.add(jpMostrador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 490, 370));

        jpCancelamento.setBackground(new java.awt.Color(204, 204, 204));
        jpCancelamento.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jpCancelamento.setMaximumSize(new java.awt.Dimension(540, 540));
        jpCancelamento.setMinimumSize(new java.awt.Dimension(540, 540));
        jpCancelamento.setPreferredSize(new java.awt.Dimension(540, 540));

        jLabel10.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Codigo do Ingresso");

        jTextField2.setFont(new java.awt.Font("Alice", 0, 18)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("000000");
        jTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jTextField2.setPreferredSize(new java.awt.Dimension(122, 30));
        jTextField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField2MouseClicked(evt);
            }
        });
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Tipo de Ingresso");

        jLabel9.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Filme");

        jLabel11.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Horario de Inicio");

        jLabel1.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Meia/Inteira");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));

        jLabel23.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));

        jLabel24.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("00:00");
        jLabel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));

        jbCancelarIngresso.setBackground(new java.awt.Color(229, 91, 0));
        jbCancelarIngresso.setFont(new java.awt.Font("Alice", 1, 14)); // NOI18N
        jbCancelarIngresso.setForeground(new java.awt.Color(255, 255, 255));
        jbCancelarIngresso.setText("BUSCA");
        jbCancelarIngresso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbCancelarIngresso.setMaximumSize(new java.awt.Dimension(238, 38));
        jbCancelarIngresso.setMinimumSize(new java.awt.Dimension(238, 38));
        jbCancelarIngresso.setPreferredSize(new java.awt.Dimension(238, 38));
        jbCancelarIngresso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbCancelarIngressoMouseClicked(evt);
            }
        });
        jbCancelarIngresso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarIngressoActionPerformed(evt);
            }
        });

        jbCancelarIngresso1.setBackground(new java.awt.Color(229, 91, 0));
        jbCancelarIngresso1.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jbCancelarIngresso1.setForeground(new java.awt.Color(255, 255, 255));
        jbCancelarIngresso1.setText("Cancelar Ingresso");
        jbCancelarIngresso1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbCancelarIngresso1.setMaximumSize(new java.awt.Dimension(238, 38));
        jbCancelarIngresso1.setMinimumSize(new java.awt.Dimension(238, 38));
        jbCancelarIngresso1.setPreferredSize(new java.awt.Dimension(238, 38));
        jbCancelarIngresso1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbCancelarIngresso1MouseClicked(evt);
            }
        });
        jbCancelarIngresso1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarIngresso1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpCancelamentoLayout = new javax.swing.GroupLayout(jpCancelamento);
        jpCancelamento.setLayout(jpCancelamentoLayout);
        jpCancelamentoLayout.setHorizontalGroup(
            jpCancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCancelamentoLayout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(jpCancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpCancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpCancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpCancelamentoLayout.createSequentialGroup()
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbCancelarIngresso, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(91, 91, 91))
            .addGroup(jpCancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpCancelamentoLayout.createSequentialGroup()
                    .addContainerGap(173, Short.MAX_VALUE)
                    .addComponent(jbCancelarIngresso1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(139, 139, 139)))
        );
        jpCancelamentoLayout.setVerticalGroup(
            jpCancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCancelamentoLayout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(jpCancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbCancelarIngresso, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(42, 42, 42)
                .addGroup(jpCancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(41, 41, 41)
                .addGroup(jpCancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jpCancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel24))
                .addContainerGap(203, Short.MAX_VALUE))
            .addGroup(jpCancelamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpCancelamentoLayout.createSequentialGroup()
                    .addContainerGap(448, Short.MAX_VALUE)
                    .addComponent(jbCancelarIngresso1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(54, 54, 54)))
        );

        jLayeredPane1.add(jpCancelamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 540));

        jpPedido.setBackground(new java.awt.Color(204, 204, 204));
        jpPedido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jpPedido.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 15, 169, -1));

        jLabel13.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Filme");
        jpPedido.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 18, 150, 24));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jpPedido.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 71, 169, -1));

        jLabel14.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Sala");
        jpPedido.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 75, 150, -1));

        jLabel15.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Capacidade :");
        jpPedido.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 119, 173, -1));

        jSeparator15.setForeground(new java.awt.Color(36, 47, 65));
        jpPedido.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 395, 543, 10));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        jpPedido.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 161, 169, -1));

        jLabel16.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Formato");
        jpPedido.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 209, 150, -1));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Inteira", "Meia" }));
        jComboBox4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });
        jpPedido.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 292, 169, -1));

        jLabel17.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Tipo de Ingresso");
        jpPedido.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 302, -1, -1));

        jLabel18.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel18.setText("Preço do Ingresso");
        jpPedido.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 427, -1, -1));

        jLabel19.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Posição Poltrona");
        jpPedido.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 346, -1, -1));

        jbPoltrona.setBackground(new java.awt.Color(229, 91, 0));
        jbPoltrona.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jbPoltrona.setForeground(new java.awt.Color(255, 255, 255));
        jbPoltrona.setText("Poltrona");
        jbPoltrona.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbPoltrona.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbPoltronaMouseClicked(evt);
            }
        });
        jbPoltrona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPoltronaActionPerformed(evt);
            }
        });
        jpPedido.add(jbPoltrona, new org.netbeans.lib.awtextra.AbsoluteConstraints(183, 342, 167, -1));

        jbCompra.setBackground(new java.awt.Color(229, 91, 0));
        jbCompra.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jbCompra.setForeground(new java.awt.Color(255, 255, 255));
        jbCompra.setText("Confirmar Compra/Imprimir Ingresso");
        jbCompra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbCompraMouseClicked(evt);
            }
        });
        jbCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCompraActionPerformed(evt);
            }
        });
        jpPedido.add(jbCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 482, -1, -1));

        jLabel20.setFont(new java.awt.Font("Alice", 0, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("R$ 12.50");
        jLabel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpPedido.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(268, 423, 174, -1));

        jLabel21.setFont(new java.awt.Font("Alice", 0, 18)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Poltrona     ");
        jLabel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpPedido.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 340, 150, -1));

        jLabel22.setFont(new java.awt.Font("Alice", 0, 18)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("2D/3D");
        jLabel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpPedido.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 205, 170, -1));

        jLabel52.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("Horario");
        jpPedido.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 165, 150, -1));

        jLabel48.setFont(new java.awt.Font("Alice", 0, 18)); // NOI18N
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("000");
        jLabel48.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpPedido.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 115, 150, -1));

        jLabel62.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel62.setText("Áudio");
        jpPedido.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 257, 87, -1));

        jLabel63.setFont(new java.awt.Font("Alice", 0, 18)); // NOI18N
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel63.setText("LEG/DUB");
        jLabel63.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpPedido.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 170, -1));

        jLayeredPane1.add(jpPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 540, 540));

        jpPoltrona.setBackground(new java.awt.Color(204, 204, 204));
        jpPoltrona.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Alice", 1, 24)); // NOI18N
        jLabel4.setText("   A         B         C         D         E         F         G");
        jpPoltrona.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 480, 30));

        jlLinha5.setFont(new java.awt.Font("Alice", 1, 24)); // NOI18N
        jlLinha5.setText("5");
        jpPoltrona.add(jlLinha5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, -1, -1));

        jlLinha1.setFont(new java.awt.Font("Alice", 1, 24)); // NOI18N
        jlLinha1.setText("1");
        jpPoltrona.add(jlLinha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jlLinha2.setFont(new java.awt.Font("Alice", 1, 24)); // NOI18N
        jlLinha2.setText("2");
        jpPoltrona.add(jlLinha2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jlLinha3.setFont(new java.awt.Font("Alice", 1, 24)); // NOI18N
        jlLinha3.setText("3");
        jpPoltrona.add(jlLinha3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        jlLinha4.setFont(new java.awt.Font("Alice", 1, 24)); // NOI18N
        jlLinha4.setText("4");
        jpPoltrona.add(jlLinha4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, -1));

        jLabel12.setFont(new java.awt.Font("Alice", 1, 36)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Poltronas Sala");
        jpPoltrona.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 0, 540, 70));

        jButtonA1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonA1.setToolTipText("");
        jButtonA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonA1ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 55, 60));

        jButtonB1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonB1ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonB1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 55, 60));

        jButtonC1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonC1ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonC1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 55, 60));

        jButtonD1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonD1ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonD1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, 55, 60));

        jButtonE1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonE1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonE1ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonE1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, 55, 60));

        jButtonF1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonF1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonF1ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 110, 55, 60));

        jButtonG1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonG1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonG1ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonG1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, 55, 60));

        jButtonA2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonA2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonA2ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 55, 60));

        jButtonA3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonA3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonA3ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonA3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 55, 60));

        jButtonA4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonA4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonA4ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonA4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 55, 60));

        jButtonA5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonA5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonA5ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonA5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 55, 60));

        jButtonB2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonB2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonB2ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonB2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 55, 60));

        jButtonB3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonB3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonB3ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonB3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, 55, 60));

        jButtonB4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonB4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonB4ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonB4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, 55, 60));

        jButtonB5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonB5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonB5ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonB5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 430, 55, 60));

        jButtonC2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonC2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonC2ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonC2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 55, 60));

        jButtonC3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonC3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonC3ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonC3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 55, 60));

        jButtonC4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonC4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonC4ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonC4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 350, 55, 60));

        jButtonC5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonC5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonC5ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonC5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 430, 55, 60));

        jButtonD2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonD2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonD2ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonD2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 55, 60));

        jButtonD3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonD3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonD3ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonD3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, 55, 60));

        jButtonD4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonD4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonD4ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonD4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 350, 55, 60));

        jButtonD5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonD5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonD5ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonD5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 430, 55, 60));

        jButtonE2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonE2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonE2ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonE2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, 55, 60));

        jButtonE3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonE3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonE3ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonE3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 270, 55, 60));

        jButtonE4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonE4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonE4ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonE4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 350, 55, 60));

        jButtonE5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonE5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonE5ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonE5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 430, 55, 60));

        jButtonF2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonF2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonF2ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonF2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 190, 55, 60));

        jButtonF3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonF3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonF3ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonF3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 270, 55, 60));

        jButtonF4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonF4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonF4ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonF4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 350, 55, 60));

        jButtonF5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonF5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonF5ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonF5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 430, 55, 60));

        jButtonG2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonG2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonG2ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonG2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, 55, 60));

        jButtonG3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonG3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonG3ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonG3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 270, 55, 60));

        jButtonG4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonG4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonG4ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonG4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 350, 55, 60));

        jButtonG5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/poltrona.png"))); // NOI18N
        jButtonG5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonG5ActionPerformed(evt);
            }
        });
        jpPoltrona.add(jButtonG5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 430, 55, 60));
        jpPoltrona.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 512, 10));
        jpPoltrona.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 512, -1));
        jpPoltrona.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 510, 10));
        jpPoltrona.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 510, -1));

        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jpPoltrona.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, 10, 430));

        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jpPoltrona.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 10, 430));

        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jpPoltrona.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 10, 430));

        jSeparator12.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jpPoltrona.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 10, 430));

        jSeparator13.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jpPoltrona.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 10, 430));

        jSeparator14.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jpPoltrona.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 10, 430));

        jLayeredPane1.add(jpPoltrona, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 540, 540));

        jpFechamento.setBackground(new java.awt.Color(204, 204, 204));
        jpFechamento.setMaximumSize(new java.awt.Dimension(540, 540));
        jpFechamento.setMinimumSize(new java.awt.Dimension(540, 540));
        jpFechamento.setPreferredSize(new java.awt.Dimension(540, 540));

        jLabel25.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Finalizar Dia");

        jLabel26.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Quantidade de Ingressos Vendidos");

        jLabel27.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Valor Final do Caixa");

        jLabel28.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Quantidade de Meias");

        jLabel29.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Quantidade de Inteiras");

        jLabel30.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("000000");
        jLabel30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));

        jLabel31.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("000,00");
        jLabel31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));

        jLabel32.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("00000");
        jLabel32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));

        jLabel33.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("00000");
        jLabel33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));

        jButton1.setBackground(new java.awt.Color(229, 91, 0));
        jButton1.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Fechar Caixa");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpFechamentoLayout = new javax.swing.GroupLayout(jpFechamento);
        jpFechamento.setLayout(jpFechamentoLayout);
        jpFechamentoLayout.setHorizontalGroup(
            jpFechamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator16)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpFechamentoLayout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addGroup(jpFechamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpFechamentoLayout.createSequentialGroup()
                        .addGroup(jpFechamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(113, 113, 113))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpFechamentoLayout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpFechamentoLayout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(139, 139, 139))))
        );
        jpFechamentoLayout.setVerticalGroup(
            jpFechamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpFechamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel26)
                .addGap(18, 18, 18)
                .addComponent(jLabel30)
                .addGap(18, 18, 18)
                .addComponent(jLabel27)
                .addGap(18, 18, 18)
                .addComponent(jLabel31)
                .addGap(18, 18, 18)
                .addComponent(jLabel28)
                .addGap(30, 30, 30)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29)
                .addGap(18, 18, 18)
                .addComponent(jLabel32)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jLayeredPane1.add(jpFechamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, -1));

        jpSuperUser.setBackground(new java.awt.Color(204, 204, 204));
        jpSuperUser.setMaximumSize(new java.awt.Dimension(540, 540));
        jpSuperUser.setMinimumSize(new java.awt.Dimension(540, 540));
        jpSuperUser.setPreferredSize(new java.awt.Dimension(540, 540));

        jPanel6.setBackground(new java.awt.Color(229, 91, 0));

        jLabel34.setBackground(new java.awt.Color(229, 91, 0));
        jLabel34.setFont(new java.awt.Font("Alice", 1, 24)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("SubMenu SuperUser");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jbSair2.setBackground(new java.awt.Color(229, 91, 0));
        jbSair2.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jbSair2.setForeground(new java.awt.Color(255, 255, 255));
        jbSair2.setText("Cadastrar Sessão");
        jbSair2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbSair2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbSair2MouseClicked(evt);
            }
        });
        jbSair2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSair2ActionPerformed(evt);
            }
        });

        jbSair3.setBackground(new java.awt.Color(229, 91, 0));
        jbSair3.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jbSair3.setForeground(new java.awt.Color(255, 255, 255));
        jbSair3.setText("Cadastrar Usuario");
        jbSair3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbSair3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbSair3MouseClicked(evt);
            }
        });
        jbSair3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSair3ActionPerformed(evt);
            }
        });

        jbSair4.setBackground(new java.awt.Color(229, 91, 0));
        jbSair4.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jbSair4.setForeground(new java.awt.Color(255, 255, 255));
        jbSair4.setText("Cadastrar Sala");
        jbSair4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbSair4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbSair4MouseClicked(evt);
            }
        });
        jbSair4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSair4ActionPerformed(evt);
            }
        });

        jbRemoverSessao.setBackground(new java.awt.Color(229, 91, 0));
        jbRemoverSessao.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jbRemoverSessao.setForeground(new java.awt.Color(255, 255, 255));
        jbRemoverSessao.setText("Remover Sessão");
        jbRemoverSessao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbRemoverSessao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbRemoverSessaoMouseClicked(evt);
            }
        });
        jbRemoverSessao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRemoverSessaoActionPerformed(evt);
            }
        });

        jbSair6.setBackground(new java.awt.Color(229, 91, 0));
        jbSair6.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jbSair6.setForeground(new java.awt.Color(255, 255, 255));
        jbSair6.setText("Remover Usuario");
        jbSair6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbSair6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbSair6MouseClicked(evt);
            }
        });
        jbSair6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSair6ActionPerformed(evt);
            }
        });

        jbSair7.setBackground(new java.awt.Color(229, 91, 0));
        jbSair7.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jbSair7.setForeground(new java.awt.Color(255, 255, 255));
        jbSair7.setText("Remover Sala");
        jbSair7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbSair7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbSair7MouseClicked(evt);
            }
        });
        jbSair7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSair7ActionPerformed(evt);
            }
        });

        jSeparator19.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jbSair10.setBackground(new java.awt.Color(229, 91, 0));
        jbSair10.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jbSair10.setForeground(new java.awt.Color(255, 255, 255));
        jbSair10.setText("Remover Filme");
        jbSair10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbSair10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbSair10MouseClicked(evt);
            }
        });
        jbSair10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSair10ActionPerformed(evt);
            }
        });

        jbSair5.setBackground(new java.awt.Color(229, 91, 0));
        jbSair5.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jbSair5.setForeground(new java.awt.Color(255, 255, 255));
        jbSair5.setText("Cadastrar Filme");
        jbSair5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbSair5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbSair5MouseClicked(evt);
            }
        });
        jbSair5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSair5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpSuperUserLayout = new javax.swing.GroupLayout(jpSuperUser);
        jpSuperUser.setLayout(jpSuperUserLayout);
        jpSuperUserLayout.setHorizontalGroup(
            jpSuperUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpSuperUserLayout.createSequentialGroup()
                .addContainerGap(69, Short.MAX_VALUE)
                .addGroup(jpSuperUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpSuperUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jbSair2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbSair3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jbSair4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jbSair5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator19, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jpSuperUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jbSair10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbSair7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbRemoverSessao, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                    .addComponent(jbSair6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
                .addGap(59, 59, 59))
        );
        jpSuperUserLayout.setVerticalGroup(
            jpSuperUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSuperUserLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jpSuperUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpSuperUserLayout.createSequentialGroup()
                        .addGroup(jpSuperUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpSuperUserLayout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addComponent(jbSair2)
                                .addGap(40, 40, 40)
                                .addComponent(jbSair3)
                                .addGap(44, 44, 44)
                                .addComponent(jbSair4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jpSuperUserLayout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addComponent(jbRemoverSessao)
                                .addGap(41, 41, 41)
                                .addComponent(jbSair6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                                .addComponent(jbSair7)
                                .addGap(46, 46, 46)))
                        .addGroup(jpSuperUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbSair10)
                            .addComponent(jbSair5))
                        .addGap(84, 84, 84))
                    .addGroup(jpSuperUserLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jSeparator19, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jLayeredPane1.add(jpSuperUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 540));

        jpCadastrarSessao.setBackground(new java.awt.Color(204, 204, 204));
        jpCadastrarSessao.setMaximumSize(new java.awt.Dimension(540, 540));
        jpCadastrarSessao.setMinimumSize(new java.awt.Dimension(540, 540));
        jpCadastrarSessao.setPreferredSize(new java.awt.Dimension(540, 540));
        jpCadastrarSessao.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbSair1.setBackground(new java.awt.Color(229, 91, 0));
        jbSair1.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jbSair1.setForeground(new java.awt.Color(255, 255, 255));
        jbSair1.setText("Cadastrar");
        jbSair1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbSair1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbSair1MouseClicked(evt);
            }
        });
        jbSair1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSair1ActionPerformed(evt);
            }
        });
        jpCadastrarSessao.add(jbSair1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 500, 150, 30));

        jLabel7.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Nome do Filme");
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpCadastrarSessao.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 190, -1));

        jLabel35.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Codigo do Filme");
        jLabel35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpCadastrarSessao.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 190, -1));

        jLabel36.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Duração");
        jLabel36.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpCadastrarSessao.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 190, -1));

        jLabel37.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Genero");
        jLabel37.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpCadastrarSessao.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 190, -1));

        jLabel38.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Codigo da Sala");
        jLabel38.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpCadastrarSessao.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 190, -1));

        jLabel40.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("Audio");
        jLabel40.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpCadastrarSessao.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 190, -1));

        jLabel41.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("Horario de Inicio");
        jLabel41.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpCadastrarSessao.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 190, -1));

        jTextField5.setEditable(false);
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setText("00");
        jTextField5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpCadastrarSessao.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 220, 30));

        jTextField6.setEditable(false);
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField6.setText("0000");
        jTextField6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpCadastrarSessao.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, 220, 30));

        jTextField8.setEditable(false);
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpCadastrarSessao.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, 220, 30));

        jTextField10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField10.setText("00.00");
        jTextField10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jTextField10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField10MouseClicked(evt);
            }
        });
        jpCadastrarSessao.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, 220, 30));

        jtxMinutosInicio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxMinutosInicio.setText("00");
        jtxMinutosInicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jtxMinutosInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtxMinutosInicioMouseClicked(evt);
            }
        });
        jtxMinutosInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxMinutosInicioActionPerformed(evt);
            }
        });
        jpCadastrarSessao.add(jtxMinutosInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 290, 100, 30));

        jtxHoraInicio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxHoraInicio.setText("00");
        jtxHoraInicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jtxHoraInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtxHoraInicioMouseClicked(evt);
            }
        });
        jtxHoraInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxHoraInicioActionPerformed(evt);
            }
        });
        jpCadastrarSessao.add(jtxHoraInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, 100, 30));

        jLabel42.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("Preço");
        jLabel42.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpCadastrarSessao.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 190, -1));

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2D", "3D" }));
        jComboBox5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpCadastrarSessao.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, 220, 30));

        jComboBoxNomeFilme.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxNomeFilme.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jComboBoxNomeFilme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxNomeFilmeActionPerformed(evt);
            }
        });
        jpCadastrarSessao.add(jComboBoxNomeFilme, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 220, 30));

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });
        jpCadastrarSessao.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, 220, 30));

        jLabel64.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setText("3D/2D");
        jLabel64.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpCadastrarSessao.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 190, -1));

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Legendado", "Dublado" }));
        jComboBox7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpCadastrarSessao.add(jComboBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 370, 220, 30));

        jTextField11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField11.setText("00000");
        jTextField11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jTextField11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField11MouseClicked(evt);
            }
        });
        jpCadastrarSessao.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 410, 220, 30));

        jLabel87.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel87.setText("Codigo Sessão");
        jLabel87.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpCadastrarSessao.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, 190, -1));

        jLayeredPane1.add(jpCadastrarSessao, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jpRemoverSessao.setBackground(new java.awt.Color(204, 204, 204));
        jpRemoverSessao.setMaximumSize(new java.awt.Dimension(540, 540));
        jpRemoverSessao.setMinimumSize(new java.awt.Dimension(540, 540));
        jpRemoverSessao.setPreferredSize(new java.awt.Dimension(540, 540));
        jpRemoverSessao.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbBuscarSessao.setBackground(new java.awt.Color(229, 91, 0));
        jbBuscarSessao.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jbBuscarSessao.setForeground(new java.awt.Color(255, 255, 255));
        jbBuscarSessao.setText("Buscar");
        jbBuscarSessao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbBuscarSessao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbBuscarSessaoMouseClicked(evt);
            }
        });
        jbBuscarSessao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuscarSessaoActionPerformed(evt);
            }
        });
        jpRemoverSessao.add(jbBuscarSessao, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, 100, 30));

        jLabel43.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("Nome do Filme");
        jLabel43.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverSessao.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 180, -1));

        jLabel44.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("Codigo do Filme");
        jLabel44.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverSessao.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 180, -1));

        jLabel45.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("Duração");
        jLabel45.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverSessao.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 180, -1));

        jLabel46.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("Genero");
        jLabel46.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverSessao.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 180, -1));

        jLabel47.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("Codigo da Sala");
        jLabel47.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverSessao.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 180, -1));

        jLabel49.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("3D/2D");
        jLabel49.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverSessao.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, 180, -1));

        jLabel50.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setText("Horario de Inicio");
        jLabel50.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverSessao.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 180, -1));

        jLabel51.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("Preço");
        jLabel51.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverSessao.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 180, -1));

        jlTipoS.setFont(new java.awt.Font("Alice", 0, 18)); // NOI18N
        jlTipoS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTipoS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverSessao.add(jlTipoS, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 360, 220, 30));

        jlPrecoS.setFont(new java.awt.Font("Alice", 0, 18)); // NOI18N
        jlPrecoS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlPrecoS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverSessao.add(jlPrecoS, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, 220, 30));

        jlCodigoSalaS.setFont(new java.awt.Font("Alice", 0, 18)); // NOI18N
        jlCodigoSalaS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlCodigoSalaS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverSessao.add(jlCodigoSalaS, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 240, 220, 30));

        jlGeneroFilmeS.setFont(new java.awt.Font("Alice", 0, 18)); // NOI18N
        jlGeneroFilmeS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlGeneroFilmeS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverSessao.add(jlGeneroFilmeS, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 220, 30));

        jlNomeFilmeS.setFont(new java.awt.Font("Alice", 0, 18)); // NOI18N
        jlNomeFilmeS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlNomeFilmeS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverSessao.add(jlNomeFilmeS, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 220, 30));

        jlDuracaoFilmeS.setFont(new java.awt.Font("Alice", 0, 18)); // NOI18N
        jlDuracaoFilmeS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlDuracaoFilmeS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverSessao.add(jlDuracaoFilmeS, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 220, 30));

        jlHorarioIniS.setFont(new java.awt.Font("Alice", 0, 18)); // NOI18N
        jlHorarioIniS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlHorarioIniS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverSessao.add(jlHorarioIniS, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 320, 220, 30));

        jtxBuscarCodigoS.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxBuscarCodigoS.setText("00000");
        jtxBuscarCodigoS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jtxBuscarCodigoS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtxBuscarCodigoSMouseClicked(evt);
            }
        });
        jpRemoverSessao.add(jtxBuscarCodigoS, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 100, 30));

        jLabel88.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel88.setText("Codigo Sessão");
        jLabel88.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverSessao.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 180, -1));

        jlCodigoFilmeS.setFont(new java.awt.Font("Alice", 0, 18)); // NOI18N
        jlCodigoFilmeS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlCodigoFilmeS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverSessao.add(jlCodigoFilmeS, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 220, 30));

        jbSair11.setBackground(new java.awt.Color(229, 91, 0));
        jbSair11.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jbSair11.setForeground(new java.awt.Color(255, 255, 255));
        jbSair11.setText("Remover");
        jbSair11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbSair11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbSair11MouseClicked(evt);
            }
        });
        jbSair11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSair11ActionPerformed(evt);
            }
        });
        jpRemoverSessao.add(jbSair11, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 480, 150, 30));

        jLabel90.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel90.setText("Audio");
        jLabel90.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverSessao.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, 180, -1));

        jlAudioS.setFont(new java.awt.Font("Alice", 0, 18)); // NOI18N
        jlAudioS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlAudioS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverSessao.add(jlAudioS, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 400, 220, 30));

        jLayeredPane1.add(jpRemoverSessao, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jpCadastrarFilme.setBackground(new java.awt.Color(204, 204, 204));
        jpCadastrarFilme.setMaximumSize(new java.awt.Dimension(550, 540));
        jpCadastrarFilme.setMinimumSize(new java.awt.Dimension(550, 540));
        jpCadastrarFilme.setPreferredSize(new java.awt.Dimension(550, 540));
        jpCadastrarFilme.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel39.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("Capa do Filme");
        jLabel39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpCadastrarFilme.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 170, -1));

        jTextField4.setEditable(false);
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jpCadastrarFilme.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 370, 150, 30));

        jbSair8.setBackground(new java.awt.Color(229, 91, 0));
        jbSair8.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jbSair8.setForeground(new java.awt.Color(255, 255, 255));
        jbSair8.setText("....");
        jbSair8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbSair8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbSair8MouseClicked(evt);
            }
        });
        jbSair8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSair8ActionPerformed(evt);
            }
        });
        jpCadastrarFilme.add(jbSair8, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 370, 50, 30));

        jLabel65.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel65.setText("Nome do Filme");
        jLabel65.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpCadastrarFilme.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 170, -1));

        jLabel66.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel66.setText("Codigo do Filme");
        jLabel66.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpCadastrarFilme.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 170, -1));

        jLabel67.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel67.setText("Duração");
        jLabel67.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpCadastrarFilme.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 170, -1));

        jLabel68.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel68.setText("Genero");
        jLabel68.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpCadastrarFilme.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 170, -1));

        jbCadastrarFilme.setBackground(new java.awt.Color(229, 91, 0));
        jbCadastrarFilme.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jbCadastrarFilme.setForeground(new java.awt.Color(255, 255, 255));
        jbCadastrarFilme.setText("Cadastrar");
        jbCadastrarFilme.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbCadastrarFilme.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbCadastrarFilmeMouseClicked(evt);
            }
        });
        jbCadastrarFilme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCadastrarFilmeActionPerformed(evt);
            }
        });
        jpCadastrarFilme.add(jbCadastrarFilme, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 480, 150, 30));

        jtxCodigoC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxCodigoC.setText("0000");
        jtxCodigoC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jtxCodigoC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtxCodigoCMouseClicked(evt);
            }
        });
        jpCadastrarFilme.add(jtxCodigoC, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 220, 30));

        jtxDuracacaoC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxDuracacaoC.setText("00:00");
        jtxDuracacaoC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jtxDuracacaoC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtxDuracacaoCMouseClicked(evt);
            }
        });
        jpCadastrarFilme.add(jtxDuracacaoC, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 220, 30));

        jtxGeneroC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxGeneroC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jtxGeneroC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtxGeneroCMouseClicked(evt);
            }
        });
        jpCadastrarFilme.add(jtxGeneroC, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 300, 220, 30));

        jtxNomeFilmeC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxNomeFilmeC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jtxNomeFilmeC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtxNomeFilmeCMouseClicked(evt);
            }
        });
        jpCadastrarFilme.add(jtxNomeFilmeC, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, 220, 30));

        jLayeredPane1.add(jpCadastrarFilme, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jpCadastrarSala.setBackground(new java.awt.Color(204, 204, 204));
        jpCadastrarSala.setMaximumSize(new java.awt.Dimension(550, 540));
        jpCadastrarSala.setMinimumSize(new java.awt.Dimension(550, 540));
        jpCadastrarSala.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel69.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel69.setText("Capacidade:");
        jLabel69.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpCadastrarSala.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 170, -1));

        jtxCapacidadeSala.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxCapacidadeSala.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jtxCapacidadeSala.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtxCapacidadeSalaMouseClicked(evt);
            }
        });
        jpCadastrarSala.add(jtxCapacidadeSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 240, 220, 30));

        jLabel70.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel70.setText("Código da Sala:");
        jLabel70.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpCadastrarSala.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 170, -1));

        jtxCodigoSala.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxCodigoSala.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jtxCodigoSala.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtxCodigoSalaMouseClicked(evt);
            }
        });
        jpCadastrarSala.add(jtxCodigoSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 220, 30));

        jbCadastrarS.setBackground(new java.awt.Color(229, 91, 0));
        jbCadastrarS.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jbCadastrarS.setForeground(new java.awt.Color(255, 255, 255));
        jbCadastrarS.setText("Cadastrar");
        jbCadastrarS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbCadastrarS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbCadastrarSMouseClicked(evt);
            }
        });
        jbCadastrarS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCadastrarSActionPerformed(evt);
            }
        });
        jpCadastrarSala.add(jbCadastrarS, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 460, 150, 30));

        jLayeredPane1.add(jpCadastrarSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 540));

        jpCadastrarUsuario.setBackground(new java.awt.Color(204, 204, 204));
        jpCadastrarUsuario.setMaximumSize(new java.awt.Dimension(550, 540));
        jpCadastrarUsuario.setMinimumSize(new java.awt.Dimension(550, 540));
        jpCadastrarUsuario.setPreferredSize(new java.awt.Dimension(550, 540));
        jpCadastrarUsuario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel71.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel71.setText("Nome");
        jLabel71.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpCadastrarUsuario.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 200, -1));

        jtxNomeU.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxNomeU.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jtxNomeU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtxNomeUMouseClicked(evt);
            }
        });
        jpCadastrarUsuario.add(jtxNomeU, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 220, 30));

        jLabel72.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel72.setText("Tipo de Usuario");
        jLabel72.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpCadastrarUsuario.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 200, -1));

        jtxId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxId.setText("000000");
        jtxId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jtxId.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtxIdMouseClicked(evt);
            }
        });
        jpCadastrarUsuario.add(jtxId, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, 220, 30));

        jbCadastrarU.setBackground(new java.awt.Color(229, 91, 0));
        jbCadastrarU.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jbCadastrarU.setForeground(new java.awt.Color(255, 255, 255));
        jbCadastrarU.setText("Cadastrar");
        jbCadastrarU.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbCadastrarU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbCadastrarUMouseClicked(evt);
            }
        });
        jbCadastrarU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCadastrarUActionPerformed(evt);
            }
        });
        jpCadastrarUsuario.add(jbCadastrarU, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 480, 150, 30));

        jLabel73.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel73.setText("Senha");
        jLabel73.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpCadastrarUsuario.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 200, -1));

        jtxSenha.setText("jPasswordField1");
        jtxSenha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jtxSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtxSenhaMouseClicked(evt);
            }
        });
        jpCadastrarUsuario.add(jtxSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, 220, 30));

        jtxSenhaComfirmacao.setText("jPasswordField1");
        jtxSenhaComfirmacao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jtxSenhaComfirmacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtxSenhaComfirmacaoMouseClicked(evt);
            }
        });
        jpCadastrarUsuario.add(jtxSenhaComfirmacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, 220, 30));

        jLabel74.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel74.setText("Comfirmar Senha");
        jLabel74.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpCadastrarUsuario.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 200, -1));

        jLabel75.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel75.setText("ID Usuário");
        jLabel75.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpCadastrarUsuario.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 200, -1));

        jcbTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Usuário Comum", "Administrador" }));
        jcbTipoUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpCadastrarUsuario.add(jcbTipoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, 220, 30));

        jLayeredPane1.add(jpCadastrarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 540));

        jpRemoverUsuario.setBackground(new java.awt.Color(204, 204, 204));
        jpRemoverUsuario.setMaximumSize(new java.awt.Dimension(550, 540));
        jpRemoverUsuario.setMinimumSize(new java.awt.Dimension(550, 540));
        jpRemoverUsuario.setPreferredSize(new java.awt.Dimension(550, 540));
        jpRemoverUsuario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel76.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel76.setText("Nome");
        jLabel76.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverUsuario.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 200, -1));

        jtxIdBusca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxIdBusca.setText("000000");
        jtxIdBusca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jtxIdBusca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtxIdBuscaMouseClicked(evt);
            }
        });
        jpRemoverUsuario.add(jtxIdBusca, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, 110, 30));

        jbBuscarUsuario.setBackground(new java.awt.Color(229, 91, 0));
        jbBuscarUsuario.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jbBuscarUsuario.setForeground(new java.awt.Color(255, 255, 255));
        jbBuscarUsuario.setText("Buscar");
        jbBuscarUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbBuscarUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbBuscarUsuarioMouseClicked(evt);
            }
        });
        jbBuscarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuscarUsuarioActionPerformed(evt);
            }
        });
        jpRemoverUsuario.add(jbBuscarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 90, 30));

        jLabel78.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel78.setText("Senha");
        jLabel78.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverUsuario.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 200, -1));

        jLabel80.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel80.setText("ID Usuário");
        jLabel80.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverUsuario.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 200, -1));

        jLabel81.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel81.setText("Tipo de Usuario");
        jLabel81.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverUsuario.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 200, -1));

        jlSenhaU.setFont(new java.awt.Font("Alice", 0, 18)); // NOI18N
        jlSenhaU.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlSenhaU.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverUsuario.add(jlSenhaU, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 220, 30));

        jlTipoUsuario.setFont(new java.awt.Font("Alice", 0, 18)); // NOI18N
        jlTipoUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTipoUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverUsuario.add(jlTipoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 300, 220, 30));

        jlNomeU.setFont(new java.awt.Font("Alice", 0, 18)); // NOI18N
        jlNomeU.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlNomeU.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverUsuario.add(jlNomeU, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 220, 30));

        jbRemoverU.setBackground(new java.awt.Color(229, 91, 0));
        jbRemoverU.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jbRemoverU.setForeground(new java.awt.Color(255, 255, 255));
        jbRemoverU.setText("Remover");
        jbRemoverU.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbRemoverU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbRemoverUMouseClicked(evt);
            }
        });
        jbRemoverU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRemoverUActionPerformed(evt);
            }
        });
        jpRemoverUsuario.add(jbRemoverU, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 480, 150, 30));

        jLayeredPane1.add(jpRemoverUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 540));

        jpRemoverSala.setBackground(new java.awt.Color(204, 204, 204));
        jpRemoverSala.setMaximumSize(new java.awt.Dimension(550, 540));
        jpRemoverSala.setMinimumSize(new java.awt.Dimension(550, 540));
        jpRemoverSala.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel77.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel77.setText("Capacidade:");
        jLabel77.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverSala.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 170, -1));

        jLabel79.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel79.setText("Código da Sala:");
        jLabel79.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverSala.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 170, -1));

        jtxBuscarSala.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxBuscarSala.setText("00000");
        jtxBuscarSala.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jtxBuscarSala.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtxBuscarSalaMouseClicked(evt);
            }
        });
        jtxBuscarSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxBuscarSalaActionPerformed(evt);
            }
        });
        jpRemoverSala.add(jtxBuscarSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 110, 30));

        jbBusacarSala.setBackground(new java.awt.Color(229, 91, 0));
        jbBusacarSala.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jbBusacarSala.setForeground(new java.awt.Color(255, 255, 255));
        jbBusacarSala.setText("Buscar");
        jbBusacarSala.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbBusacarSala.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbBusacarSalaMouseClicked(evt);
            }
        });
        jbBusacarSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBusacarSalaActionPerformed(evt);
            }
        });
        jpRemoverSala.add(jbBusacarSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, 100, 30));

        jlCapacidade.setFont(new java.awt.Font("Alice", 0, 18)); // NOI18N
        jlCapacidade.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlCapacidade.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverSala.add(jlCapacidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 240, 220, 30));

        jbCadastrarS2.setBackground(new java.awt.Color(229, 91, 0));
        jbCadastrarS2.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jbCadastrarS2.setForeground(new java.awt.Color(255, 255, 255));
        jbCadastrarS2.setText("Remover");
        jbCadastrarS2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbCadastrarS2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbCadastrarS2MouseClicked(evt);
            }
        });
        jbCadastrarS2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCadastrarS2ActionPerformed(evt);
            }
        });
        jpRemoverSala.add(jbCadastrarS2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 460, 150, 30));

        jLayeredPane1.add(jpRemoverSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 540));

        jpRemoverFilme.setBackground(new java.awt.Color(204, 204, 204));
        jpRemoverFilme.setMaximumSize(new java.awt.Dimension(550, 540));
        jpRemoverFilme.setMinimumSize(new java.awt.Dimension(550, 540));
        jpRemoverFilme.setPreferredSize(new java.awt.Dimension(550, 540));
        jpRemoverFilme.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel82.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel82.setText("Capa do Filme");
        jLabel82.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverFilme.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 170, -1));

        jlGenero.setEditable(false);
        jlGenero.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jlGenero.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jlGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlGeneroActionPerformed(evt);
            }
        });
        jpRemoverFilme.add(jlGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 300, 220, 30));

        jLabel83.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel83.setText("Nome do Filme");
        jLabel83.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverFilme.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 170, -1));

        jLabel84.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel84.setText("Codigo do Filme");
        jLabel84.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverFilme.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 170, -1));

        jLabel85.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel85.setText("Duração");
        jLabel85.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverFilme.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 170, -1));

        jLabel86.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel86.setText("Genero");
        jLabel86.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jpRemoverFilme.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 170, -1));

        jbBuscarFilme.setBackground(new java.awt.Color(229, 91, 0));
        jbBuscarFilme.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jbBuscarFilme.setForeground(new java.awt.Color(255, 255, 255));
        jbBuscarFilme.setText("Buscar");
        jbBuscarFilme.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbBuscarFilme.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbBuscarFilmeMouseClicked(evt);
            }
        });
        jbBuscarFilme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBuscarFilmeActionPerformed(evt);
            }
        });
        jpRemoverFilme.add(jbBuscarFilme, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 90, 30));

        jtxBuscaCodigoF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxBuscaCodigoF.setText("0000");
        jtxBuscaCodigoF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jtxBuscaCodigoF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtxBuscaCodigoFMouseClicked(evt);
            }
        });
        jpRemoverFilme.add(jtxBuscaCodigoF, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, 110, 30));

        jlCapa.setEditable(false);
        jlCapa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jlCapa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jlCapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlCapaActionPerformed(evt);
            }
        });
        jpRemoverFilme.add(jlCapa, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 370, 220, 30));

        jlNomeFilme.setEditable(false);
        jlNomeFilme.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jlNomeFilme.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jlNomeFilme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlNomeFilmeActionPerformed(evt);
            }
        });
        jpRemoverFilme.add(jlNomeFilme, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 220, 30));

        jlDuracao.setEditable(false);
        jlDuracao.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jlDuracao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 47, 65), 4));
        jlDuracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlDuracaoActionPerformed(evt);
            }
        });
        jpRemoverFilme.add(jlDuracao, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 220, 30));

        jbRemoverFilme1.setBackground(new java.awt.Color(229, 91, 0));
        jbRemoverFilme1.setFont(new java.awt.Font("Alice", 1, 18)); // NOI18N
        jbRemoverFilme1.setForeground(new java.awt.Color(255, 255, 255));
        jbRemoverFilme1.setText("Remover");
        jbRemoverFilme1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 114, 0), 4));
        jbRemoverFilme1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbRemoverFilme1MouseClicked(evt);
            }
        });
        jbRemoverFilme1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRemoverFilme1ActionPerformed(evt);
            }
        });
        jpRemoverFilme.add(jbRemoverFilme1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 480, 150, 30));

        jLayeredPane1.add(jpRemoverFilme, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1.add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 550, 540));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbSairMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbSairMouseClicked

    private void jbSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSairActionPerformed
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        Login log = new Login();
        log.setVisible(true);
        dispose();
    }//GEN-LAST:event_jbSairActionPerformed

    private void jbCancelamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbCancelamentoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbCancelamentoMouseClicked

    private void jbCancelamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelamentoActionPerformed
        jpPedido.setVisible(false);
        jpCancelamento.setVisible(true);
        jpFechamento.setVisible(false);
        jpSuperUser.setVisible(false);
        jLabel2.setVisible(false);
        jpMostrador2.setVisible(false);
        jpPoltrona.setVisible(false);
        jpCadastrarSessao.setVisible(false);
        jpRemoverSessao.setVisible(false);
        jpCadastrarFilme.setVisible(false);
        jpCadastrarSala.setVisible(false);
        jpCadastrarUsuario.setVisible(false);
        jpRemoverUsuario.setVisible(false);
        jpRemoverSala.setVisible(false);
        jpRemoverFilme.setVisible(false);
        
    }//GEN-LAST:event_jbCancelamentoActionPerformed

    private void jbFechamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbFechamentoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbFechamentoMouseClicked

    private void jbFechamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbFechamentoActionPerformed
        jpPedido.setVisible(false);
        jpCancelamento.setVisible(false);
        jpFechamento.setVisible(true);
        jpSuperUser.setVisible(false);
        jLabel2.setVisible(false);
        jpMostrador2.setVisible(false);
        jpPoltrona.setVisible(false);
        jpCadastrarSessao.setVisible(false);
        jpRemoverSessao.setVisible(false);
        jpCadastrarFilme.setVisible(false);
        jpCadastrarSala.setVisible(false);
        jpCadastrarUsuario.setVisible(false);
        jpRemoverUsuario.setVisible(false);
        jpRemoverSala.setVisible(false);
        jpRemoverFilme.setVisible(false);
        
    }//GEN-LAST:event_jbFechamentoActionPerformed

    private void jbSuperUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbSuperUserMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbSuperUserMouseClicked

    private void jbSuperUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSuperUserActionPerformed
        jpPedido.setVisible(false);
        jpCancelamento.setVisible(false);
        jpFechamento.setVisible(false);
        jpSuperUser.setVisible(true);
        jLabel2.setVisible(false);
        jpMostrador2.setVisible(false);
        jpPoltrona.setVisible(false);
        jpCadastrarSessao.setVisible(false);
        jpRemoverSessao.setVisible(false);
        jpCadastrarFilme.setVisible(false);
        jpCadastrarSala.setVisible(false);
        jpCadastrarUsuario.setVisible(false);
        jpRemoverUsuario.setVisible(false);
        jpRemoverSala.setVisible(false);
        jpRemoverFilme.setVisible(false);
        
    }//GEN-LAST:event_jbSuperUserActionPerformed

    private void jbHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbHomeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbHomeMouseClicked

    private void jbHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbHomeActionPerformed
        jpPedido.setVisible(false);
        jpCancelamento.setVisible(false);
        jpFechamento.setVisible(false);
        jpSuperUser.setVisible(false);
        jLabel2.setVisible(true);
        jpMostrador2.setVisible(true);
        jpPoltrona.setVisible(false);
        jpCadastrarSessao.setVisible(false);
        jpRemoverSessao.setVisible(false);
        jpCadastrarFilme.setVisible(false);
        jpCadastrarSala.setVisible(false);
        jpCadastrarUsuario.setVisible(false);
        jpRemoverUsuario.setVisible(false);
        jpRemoverSala.setVisible(false);
        jpRemoverFilme.setVisible(false);
        
    }//GEN-LAST:event_jbHomeActionPerformed

    private void jbPedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbPedidoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbPedidoMouseClicked
    public static String codFilme = null;
    public static String codSessao = null;
    private void jbPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPedidoActionPerformed
        jpPedido.setVisible(true);
        jpCancelamento.setVisible(false);
        jpFechamento.setVisible(false);
        jpSuperUser.setVisible(false);
        jLabel2.setVisible(false);
        jpMostrador2.setVisible(false);
        jpPoltrona.setVisible(false);
        jpCadastrarSessao.setVisible(false);
        jpRemoverSessao.setVisible(false);
        jpCadastrarFilme.setVisible(false);
        jpCadastrarSala.setVisible(false);
        jpCadastrarUsuario.setVisible(false);
        jpRemoverUsuario.setVisible(false);
        jpRemoverSala.setVisible(false);
        jpRemoverFilme.setVisible(false);
        

        // lista de filmes
        ArrayList<String> filmes = new ArrayList<>();
        sql = "SELECT  * FROM filmes";
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            while (retorno.next()) {
                filmes.add(retorno.getString("nome"));
            }
            jComboBox1.setModel(new DefaultComboBoxModel(filmes.toArray()));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar lista de filmes\n" + ex.getMessage());
        }

        //codigo do filme
        codFilme = null;
        sql = "SELECT * FROM filmes";
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            while (retorno.next()) {
                if (retorno.getString("nome").equals(jComboBox1.getSelectedItem().toString())) {
                    codFilme = retorno.getString("codigo");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não há sessões com o filme selecionado \n" + ex.getMessage());
        }

        //lista sessoes
        ArrayList<String> salas = new ArrayList<>();
        sql = "SELECT * FROM sessoes";
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            while (retorno.next()) {
                if (retorno.getString("codFilme").equals(codFilme)) {
                    salas.add(retorno.getString("codSala"));
                }
            }
            jComboBox2.setModel(new DefaultComboBoxModel(salas.toArray()));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar lista de filmes\n" + ex.getMessage());
        }
        // pega capacidade

        String capacidade = null;
        sql = "SELECT * FROM salas";
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            while (retorno.next()) {
                if (retorno.getString("codSala").equals(jComboBox2.getSelectedItem().toString())) {
                    capacidade = retorno.getString("capacidade");
                }
            }
            jLabel48.setText(capacidade);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar lista de filmes\n" + ex.getMessage());
        }

        //pegar horarios
        ArrayList<String> horario = new ArrayList<>();
        sql = "SELECT * FROM sessoes";
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            while (retorno.next()) {
                if (retorno.getString("codFilme").equals(codFilme) && retorno.getString("codSala").equals(jComboBox2.getSelectedItem().toString())) {
                    horario.add(retorno.getTime("horario").toString());
                }
            }
            jComboBox3.setModel(new DefaultComboBoxModel(horario.toArray()));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar lista de filmes\n" + ex.getMessage());
        }

        // pegar preço
        float preco = 0;
        sql = "SELECT * FROM sessoes";
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            while (retorno.next()) {
                if (retorno.getString("codFilme").equals(codFilme)
                        && retorno.getString("codSala").equals(jComboBox2.getSelectedItem().toString())
                        && retorno.getTime("horario").toString().equals(jComboBox3.getSelectedItem().toString())) {
                    preco = retorno.getFloat("preco");
                    codSessao = retorno.getString("codigo");
                    jLabel22.setText(retorno.getString("formato")); //pega formato
                    jLabel63.setText(retorno.getString("audio")); //pega audio
                }
            }
            if (jComboBox4.getSelectedItem().toString().equals("Meia")) {
                preco /= 2;
            }
            jLabel20.setText("R$ " + String.valueOf(preco));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar lista de filmes\n" + ex.getMessage());
        }
    }//GEN-LAST:event_jbPedidoActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        //codigo do filme
        codFilme = null;
        sql = "SELECT * FROM filmes";
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            while (retorno.next()) {
                if (retorno.getString("nome").equals(jComboBox1.getSelectedItem().toString())) {
                    codFilme = retorno.getString("codigo");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não há sessões com o filme selecionado \n" + ex.getMessage());
        }

        //lista sessoes
        ArrayList<String> salas = new ArrayList<>();
        sql = "SELECT * FROM sessoes";
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            while (retorno.next()) {
                if (retorno.getString("codFilme").equals(codFilme)) {
                    salas.add(retorno.getString("codSala"));
                }
            }
            jComboBox2.setModel(new DefaultComboBoxModel(salas.toArray()));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar lista de filmes\n" + ex.getMessage());
        }
        // pega capacidade

        String capacidade = null;
        sql = "SELECT * FROM salas";
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            while (retorno.next()) {
                if (retorno.getString("codSala").equals(jComboBox2.getSelectedItem().toString())) {
                    capacidade = retorno.getString("capacidade");
                }
            }
            jLabel48.setText(capacidade);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar lista de filmes\n" + ex.getMessage());
        }

        //pegar horarios
        ArrayList<String> horario = new ArrayList<>();
        sql = "SELECT * FROM sessoes";
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            while (retorno.next()) {
                if (retorno.getString("codFilme").equals(codFilme) && retorno.getString("codSala").equals(jComboBox2.getSelectedItem().toString())) {
                    horario.add(retorno.getTime("horario").toString());
                }
            }
            jComboBox3.setModel(new DefaultComboBoxModel(horario.toArray()));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar lista de filmes\n" + ex.getMessage());
        }
        // pegar preço
        float preco = 0;
        sql = "SELECT * FROM sessoes";
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            while (retorno.next()) {
                if (retorno.getString("codFilme").equals(codFilme)
                        && retorno.getString("codSala").equals(jComboBox2.getSelectedItem().toString())
                        && retorno.getTime("horario").toString().equals(jComboBox3.getSelectedItem().toString())) {
                    preco = retorno.getFloat("preco");
                    codSessao = retorno.getString("codigo");
                    jLabel22.setText(retorno.getString("formato")); //pega formato
                    jLabel63.setText(retorno.getString("audio")); //pega audio
                }
            }
            if (jComboBox4.getSelectedItem().toString().equals("Meia")) {
                preco /= 2;
            }
            jLabel20.setText("R$ " + String.valueOf(preco));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar lista de filmes\n" + ex.getMessage());
        }

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jbPoltronaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbPoltronaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbPoltronaMouseClicked

    private void jbPoltronaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPoltronaActionPerformed
        jpPedido.setVisible(false);
        jpCancelamento.setVisible(false);
        jpFechamento.setVisible(false);
        jpSuperUser.setVisible(false);
        jLabel2.setVisible(false);
        jpMostrador2.setVisible(false);
        jpPoltrona.setVisible(true);
        jpCadastrarSessao.setVisible(false);
        jpRemoverSessao.setVisible(false);
        jpCadastrarFilme.setVisible(false);
        jpCadastrarSala.setVisible(false);
        jpCadastrarUsuario.setVisible(false);
        jpRemoverUsuario.setVisible(false);
        jpRemoverSala.setVisible(false);
        jpRemoverFilme.setVisible(false);
        

        resetaPoltrona();
        String codPoltrona = jLabel21.getText().replaceAll("Poltrona ", "");

        // pega capacidade
        if (jLabel48.getText().equals("30")) {
            jButtonG1.setEnabled(false);
            jButtonG2.setEnabled(false);
            jButtonG3.setEnabled(false);
            jButtonG4.setEnabled(false);
            jButtonG5.setEnabled(false);
        } else if (jLabel48.getText().equals("28")) {
            jButtonA5.setEnabled(false);
            jButtonB5.setEnabled(false);
            jButtonC5.setEnabled(false);
            jButtonD5.setEnabled(false);
            jButtonE5.setEnabled(false);
            jButtonF5.setEnabled(false);
            jButtonG5.setEnabled(false);
        } else if (jLabel48.getText().equals("25")) {
            jButtonG1.setEnabled(false);
            jButtonG2.setEnabled(false);
            jButtonG3.setEnabled(false);
            jButtonG4.setEnabled(false);
            jButtonG5.setEnabled(false);
            jButtonF1.setEnabled(false);
            jButtonF2.setEnabled(false);
            jButtonF3.setEnabled(false);
            jButtonF4.setEnabled(false);
            jButtonF5.setEnabled(false);
        } else if (jLabel48.getText().equals("24")) {
            jButtonA5.setEnabled(false);
            jButtonB5.setEnabled(false);
            jButtonC5.setEnabled(false);
            jButtonD5.setEnabled(false);
            jButtonE5.setEnabled(false);
            jButtonF5.setEnabled(false);
            jButtonG5.setEnabled(false);
            jButtonG4.setEnabled(false);
            jButtonG3.setEnabled(false);
            jButtonG2.setEnabled(false);
            jButtonG1.setEnabled(false);
        } else if (jLabel48.getText().equals("21")) {
            jButtonA5.setEnabled(false);
            jButtonB5.setEnabled(false);
            jButtonC5.setEnabled(false);
            jButtonD5.setEnabled(false);
            jButtonE5.setEnabled(false);
            jButtonF5.setEnabled(false);
            jButtonG5.setEnabled(false);
            jButtonA4.setEnabled(false);
            jButtonB4.setEnabled(false);
            jButtonC4.setEnabled(false);
            jButtonD4.setEnabled(false);
            jButtonE4.setEnabled(false);
            jButtonF4.setEnabled(false);
            jButtonG4.setEnabled(false);
        }

        // procura pontronas exisentes
        sql = "SELECT * FROM ingressos";
        //String sql2 = "SELECT * FROM sessoes WHERE codigo=";
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            while (retorno.next()) {
                if (retorno.getString("codSessao").equals(codSessao)) {
                    //<editor-fold defaultstate="collapsed" desc=" desativas assentos usados ">
                    if (retorno.getString("assento").equals("A1")) {
                        jButtonA1.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("B1")) {
                        jButtonB1.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("C1")) {
                        jButtonC1.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("D1")) {
                        jButtonD1.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("E1")) {
                        jButtonE1.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("F1")) {
                        jButtonF1.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("G1")) {
                        jButtonG1.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("A2")) {
                        jButtonA2.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("B2")) {
                        jButtonB2.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("C2")) {
                        jButtonC2.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("D2")) {
                        jButtonD2.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("E2")) {
                        jButtonE2.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("F2")) {
                        jButtonF2.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("G2")) {
                        jButtonG2.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("A3")) {
                        jButtonA3.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("B3")) {
                        jButtonB3.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("C3")) {
                        jButtonC3.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("D3")) {
                        jButtonD3.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("E3")) {
                        jButtonE3.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("F3")) {
                        jButtonF3.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("G3")) {
                        jButtonG3.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("A4")) {
                        jButtonA4.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("B4")) {
                        jButtonB4.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("C4")) {
                        jButtonC4.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("D4")) {
                        jButtonD4.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("E4")) {
                        jButtonE4.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("F4")) {
                        jButtonF4.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("G4")) {
                        jButtonG4.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("A5")) {
                        jButtonA5.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("B5")) {
                        jButtonB5.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("C5")) {
                        jButtonC5.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("D5")) {
                        jButtonD5.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("E5")) {
                        jButtonE5.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("F5")) {
                        jButtonF5.setEnabled(false);
                    }
                    if (retorno.getString("assento").equals("G5")) {
                        jButtonG5.setEnabled(false);
                    }
                    //</editor-fold>
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar lista de codigo de sessoes\n" + ex.getMessage());
        }
    }//GEN-LAST:event_jbPoltronaActionPerformed

    private void jbCompraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbCompraMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbCompraMouseClicked

    private void jbCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCompraActionPerformed
        String tipoIngresso = jComboBox4.getSelectedItem().toString();
        String codPoltrona = jLabel21.getText().replaceAll("Poltrona ", "");
        float preco = Float.parseFloat(jLabel20.getText().replaceAll("[^\\d.]", "")); // pega apenas numeros, nao apagar
        int codigo = (int) (Math.random() * 9999 + 1111); // gera um random
        boolean exit = false;

        //Procura se o codigo ja existe
        // procuura se assento ja foi adicioando, evita clicar duas vezes
        boolean assento = false;
        sql = "SELECT * FROM ingressos";
        if (codPoltrona.length() == 2) {
            while (exit == false) {
                try {
                    ResultSet retorno = con.sentenca.executeQuery(sql);
                    while (retorno.next()) {
                        if (retorno.getString("codigo").equals(codigo)) {
                            codigo = (int) (Math.random() * 999999 + 111111);
                        } else {
                            if (retorno.getString("assento").equals(codPoltrona)
                                    && retorno.getString("codSessao").equals(codSessao)) {
                                assento = true;
                            }
                            exit = true;
                        }
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao buscar lista de codigo de sessoes\n" + ex.getMessage());
                }
            }
            if (assento == false) {
                //Insere no banco de dados
                sql = "INSERT INTO ingressos VALUES (" + codigo + "," + codSessao + ",'"
                        + codPoltrona + "','" + tipoIngresso + "')";
                try {
                    con.sentenca.execute(sql);
                    nome = jComboBox1.getSelectedItem().toString() + " " + jLabel22.getText()
                            + " " + jLabel63.getText();
                    String codSala = jComboBox2.getSelectedItem().toString();
                    String horario = jComboBox3.getSelectedItem().toString();
                    String codIng = String.valueOf(codigo);
                    SalvaIngresso.writePng(codSala, nome, horario, String.valueOf(preco), codIng, codPoltrona);
                    IngressoViewer tela = new IngressoViewer();
                    tela.zerar();
                    tela.viewer();
                    tela.setVisible(true);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro de sintaxe " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Assento Indisponível");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione o assento");
        }


    }//GEN-LAST:event_jbCompraActionPerformed

    //<editor-fold defaultstate="collapsed" desc=" Recebe valor do botao poltrona ">
    private void jButtonA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonA1ActionPerformed
        recebePoltrona("A1");
        voltarPedido();
    }//GEN-LAST:event_jButtonA1ActionPerformed

    private void jButtonB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonB1ActionPerformed
        recebePoltrona("B1");
        voltarPedido();
    }//GEN-LAST:event_jButtonB1ActionPerformed

    private void jButtonA2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonA2ActionPerformed
        recebePoltrona("A2");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonA2ActionPerformed

    private void jButtonA3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonA3ActionPerformed
        recebePoltrona("A3");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonA3ActionPerformed

    private void jButtonA4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonA4ActionPerformed
        recebePoltrona("A4");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonA4ActionPerformed

    private void jButtonA5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonA5ActionPerformed
        recebePoltrona("A5");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonA5ActionPerformed

    private void jButtonB2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonB2ActionPerformed
        recebePoltrona("B2");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonB2ActionPerformed

    private void jButtonB3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonB3ActionPerformed
        recebePoltrona("B3");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonB3ActionPerformed

    private void jButtonB4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonB4ActionPerformed
        recebePoltrona("B4");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonB4ActionPerformed

    private void jButtonB5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonB5ActionPerformed
        recebePoltrona("B5");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonB5ActionPerformed

    private void jButtonC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonC1ActionPerformed
        recebePoltrona("C1");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonC1ActionPerformed

    private void jButtonC2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonC2ActionPerformed
        recebePoltrona("C2");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonC2ActionPerformed

    private void jButtonC3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonC3ActionPerformed
        recebePoltrona("C3");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonC3ActionPerformed

    private void jButtonC4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonC4ActionPerformed
        recebePoltrona("C4");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonC4ActionPerformed

    private void jButtonC5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonC5ActionPerformed
        recebePoltrona("C5");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonC5ActionPerformed

    private void jButtonD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonD1ActionPerformed
        recebePoltrona("D1");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonD1ActionPerformed

    private void jButtonD2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonD2ActionPerformed
        recebePoltrona("D2");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonD2ActionPerformed

    private void jButtonD3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonD3ActionPerformed
        recebePoltrona("D3");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonD3ActionPerformed

    private void jButtonD4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonD4ActionPerformed
        recebePoltrona("D4");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonD4ActionPerformed

    private void jButtonD5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonD5ActionPerformed
        recebePoltrona("D5");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonD5ActionPerformed

    private void jButtonE1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonE1ActionPerformed
        recebePoltrona("E1");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonE1ActionPerformed

    private void jButtonE2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonE2ActionPerformed
        recebePoltrona("E2");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonE2ActionPerformed

    private void jButtonE3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonE3ActionPerformed
        recebePoltrona("E3");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonE3ActionPerformed

    private void jButtonE4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonE4ActionPerformed
        recebePoltrona("E4");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonE4ActionPerformed

    private void jButtonE5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonE5ActionPerformed
        recebePoltrona("E5");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonE5ActionPerformed

    private void jButtonF1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonF1ActionPerformed
        recebePoltrona("F1");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonF1ActionPerformed

    private void jButtonF2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonF2ActionPerformed
        recebePoltrona("F2");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonF2ActionPerformed

    private void jButtonF3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonF3ActionPerformed
        recebePoltrona("F3");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonF3ActionPerformed

    private void jButtonF4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonF4ActionPerformed
        recebePoltrona("F4");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonF4ActionPerformed

    private void jButtonF5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonF5ActionPerformed
        recebePoltrona("F5");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonF5ActionPerformed

    private void jButtonG1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonG1ActionPerformed
        recebePoltrona("G1");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonG1ActionPerformed

    private void jButtonG2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonG2ActionPerformed
        recebePoltrona("G2");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonG2ActionPerformed

    private void jButtonG3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonG3ActionPerformed
        recebePoltrona("G3");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonG3ActionPerformed

    private void jButtonG4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonG4ActionPerformed
        recebePoltrona("G4");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonG4ActionPerformed

    private void jButtonG5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonG5ActionPerformed
        recebePoltrona("G5");
        voltarPedido();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonG5ActionPerformed
    //</editor-fold>

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        Conexao innerCon = new Conexao(); // um resultset dentro de outro resultset nao executa
        float valorTotal = 0;               // ao menos que haja duas conexões
        int quantIngressos = 0;
        float valor = 0;
        int quantMeia = 0;
        int quantInteira = 0;
        sql = "SELECT * FROM ingressos";
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            while (retorno.next()) {
                quantIngressos += 1;
                String tipo = retorno.getString("tipo");
                sql = "SELECT * FROM sessoes WHERE codigo=" + retorno.getInt("codSessao");
                try {
                    ResultSet retorno2 = innerCon.sentenca.executeQuery(sql);
                    retorno2.first();
                    valor = retorno2.getFloat("preco");
                    if (tipo.equals("Meia")) {
                        valor /= 2;
                        quantMeia += 1;
                    } else {
                        quantInteira += 1;
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao buscar valor de sessao\n" + ex.getMessage());
                }
                valorTotal += valor;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar lista de codigo de ingressos\n" + ex.getMessage());
        }
        jLabel30.setText(String.valueOf(quantIngressos));
        jLabel31.setText(String.valueOf(valorTotal));
        jLabel33.setText(String.valueOf(quantMeia));
        jLabel32.setText(String.valueOf(quantInteira));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jbSair2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSair2ActionPerformed
        jpPedido.setVisible(false);
        jpCancelamento.setVisible(false);
        jpFechamento.setVisible(false);
        jpSuperUser.setVisible(false);
        jLabel2.setVisible(false);
        jpPoltrona.setVisible(false);
        jpCadastrarSessao.setVisible(true);
        jpRemoverSessao.setVisible(false);
        jpCadastrarFilme.setVisible(false);
        jpCadastrarSala.setVisible(false);
        jpCadastrarUsuario.setVisible(false);
        jpRemoverUsuario.setVisible(false);
        jpRemoverSala.setVisible(false);
        jpRemoverFilme.setVisible(false);
        

        // lista de filmes
        ArrayList<String> filmes = new ArrayList<>();
        sql = "SELECT  * FROM filmes";
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            while (retorno.next()) {
                filmes.add(retorno.getString("nome"));
            }
            jComboBoxNomeFilme.setModel(new DefaultComboBoxModel(filmes.toArray()));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar lista de filmes\n" + ex.getMessage());
        }

        //codigo do filme
        sql = "SELECT * FROM filmes";
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            while (retorno.next()) {
                if (retorno.getString("nome").equals(jComboBoxNomeFilme.getSelectedItem().toString())) {
                    jTextField6.setText(retorno.getString("codigo"));
                    jTextField5.setText(retorno.getString("duracao") + " minutos");
                    jTextField8.setText(retorno.getString("genero"));
                    jTextField4.setText(retorno.getString("imagem"));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não há sessões com o filme selecionado \n" + ex.getMessage());
        }
        //lista sessoes
        ArrayList<String> salas = new ArrayList<>();
        sql = "SELECT * FROM salas";
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            while (retorno.next()) {
                salas.add(retorno.getString("codSala"));
            }
            jComboBox6.setModel(new DefaultComboBoxModel(salas.toArray()));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar lista de filmes\n" + ex.getMessage());
        }

        jComboBox7.setModel(new DefaultComboBoxModel(new String[]{"DUB", "LEG"}));
    }//GEN-LAST:event_jbSair2ActionPerformed

    private void jbSair2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbSair2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbSair2MouseClicked

    private void jbSair3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbSair3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbSair3MouseClicked

    private void jbSair3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSair3ActionPerformed
        jpPedido.setVisible(false);
        jpCancelamento.setVisible(false);
        jpFechamento.setVisible(false);
        jpSuperUser.setVisible(false);
        jLabel2.setVisible(false);
        jpMostrador2.setVisible(false);
        jpPoltrona.setVisible(false);
        jpCadastrarSessao.setVisible(false);
        jpRemoverSessao.setVisible(false);
        jpCadastrarFilme.setVisible(false);
        jpCadastrarSala.setVisible(false);
        jpCadastrarUsuario.setVisible(true);
        jpRemoverUsuario.setVisible(false);
        jpRemoverSala.setVisible(false);
        jpRemoverFilme.setVisible(false);
        
    }//GEN-LAST:event_jbSair3ActionPerformed

    private void jbSair4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbSair4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbSair4MouseClicked

    private void jbSair4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSair4ActionPerformed
        // TODO add your handling code here:
        jpPedido.setVisible(false);
        jpCancelamento.setVisible(false);
        jpFechamento.setVisible(false);
        jpSuperUser.setVisible(false);
        jLabel2.setVisible(false);
        jpMostrador2.setVisible(false);
        jpPoltrona.setVisible(false);
        jpCadastrarSessao.setVisible(false);
        jpRemoverSessao.setVisible(false);
        jpCadastrarFilme.setVisible(false);
        jpCadastrarSala.setVisible(true);
        jtxCodigoSala.setVisible(true);
        jtxCapacidadeSala.setVisible(true);
        jpCadastrarUsuario.setVisible(false);
        jpRemoverUsuario.setVisible(false);
        jpRemoverSala.setVisible(false);
        jpRemoverFilme.setVisible(false);
        
    }//GEN-LAST:event_jbSair4ActionPerformed

    private void jbRemoverSessaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbRemoverSessaoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbRemoverSessaoMouseClicked

    private void jbRemoverSessaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRemoverSessaoActionPerformed
        jpPedido.setVisible(false);
        jpCancelamento.setVisible(false);
        jpFechamento.setVisible(false);
        jpSuperUser.setVisible(false);
        jLabel2.setVisible(false);
        jpPoltrona.setVisible(false);
        jpCadastrarSessao.setVisible(false);
        jpRemoverSessao.setVisible(true);
        jpCadastrarFilme.setVisible(false);
        jpCadastrarSala.setVisible(false);
        jpCadastrarUsuario.setVisible(false);
        jpRemoverUsuario.setVisible(false);
        jpRemoverSala.setVisible(false);
        jpRemoverFilme.setVisible(false);
        
    }//GEN-LAST:event_jbRemoverSessaoActionPerformed

    private void jbSair6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbSair6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbSair6MouseClicked

    private void jbSair6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSair6ActionPerformed
        jpPedido.setVisible(false);
        jpCancelamento.setVisible(false);
        jpFechamento.setVisible(false);
        jpSuperUser.setVisible(false);
        jLabel2.setVisible(false);
        jpMostrador2.setVisible(false);
        jpPoltrona.setVisible(false);
        jpCadastrarSessao.setVisible(false);
        jpRemoverSessao.setVisible(false);
        jpCadastrarFilme.setVisible(false);
        jpCadastrarSala.setVisible(false);
        jpCadastrarUsuario.setVisible(false);
        jpRemoverUsuario.setVisible(true);
        jpRemoverSala.setVisible(false);
        jpRemoverFilme.setVisible(false);
        
    }//GEN-LAST:event_jbSair6ActionPerformed

    private void jbSair7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbSair7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbSair7MouseClicked

    private void jbSair7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSair7ActionPerformed
        jpPedido.setVisible(false);
        jpCancelamento.setVisible(false);
        jpFechamento.setVisible(false);
        jpSuperUser.setVisible(false);
        jLabel2.setVisible(false);
        jpMostrador2.setVisible(false);
        jpPoltrona.setVisible(false);
        jpCadastrarSessao.setVisible(false);
        jpRemoverSessao.setVisible(false);
        jpCadastrarFilme.setVisible(false);
        jpCadastrarSala.setVisible(false);
        jpCadastrarUsuario.setVisible(false);
        jpRemoverUsuario.setVisible(false);
        jpRemoverSala.setVisible(true);
        jpRemoverFilme.setVisible(false);
        
    }//GEN-LAST:event_jbSair7ActionPerformed

    private void jbCancelarIngressoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbCancelarIngressoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbCancelarIngressoMouseClicked

    private void jbCancelarIngressoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarIngressoActionPerformed
        //pegar dado ingresso
        sql = "SELECT * FROM ingressos WHERE codigo=" + jTextField2.getText();
        int codSessao = 0;
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            retorno.first();
            jLabel1.setText(retorno.getString("tipo"));
            codSessao = retorno.getInt("codSessao");
        } catch (SQLException ex) {
            jLabel23.setText("Código inválido");
        }

        //procura sessao
        sql = "SELECT * FROM sessoes WHERE codigo=" + codSessao;
        int codFilme = 0;
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            retorno.first();
            jLabel24.setText(retorno.getTime("horario").toString());
            codFilme = retorno.getInt("codFilme");
        } catch (SQLException ex) {
            jLabel23.setText("Código inválido");
        }

        //procura filme
        sql = "SELECT * FROM filmes WHERE codigo=" + codFilme;
        // jLabel23 - filme
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            retorno.first();
            jLabel23.setText(retorno.getString("nome"));
        } catch (SQLException ex) {
            jLabel23.setText("Código inválido");
        }

    }//GEN-LAST:event_jbCancelarIngressoActionPerformed

    private void jbSair1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbSair1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbSair1MouseClicked

    String nome = "";
    String caminho = "";
    int i = 0;
    private void jbSair1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSair1ActionPerformed
        i++;
        String CaminhoPacote = "";
        if (caminho.contains("/src/")) {
            CaminhoPacote = caminho;
        } else {
            CaminhoPacote = dirImagem + jTextField4.getText();
        }

        //BufferedReader img = pegarImagemPacote(NomeImagem,CaminhoPacote);
        JLabel filme = new JLabel("Label n" + i);
        filme.setBackground(jbSair1.getBackground());
        filme.setOpaque(true);
        String text = jComboBoxNomeFilme.getSelectedItem().toString()
                + "\nHorario de Inicio: " + jtxHoraInicio.getText() + ":" + jtxMinutosInicio.getText();
        Border bord = new TitledBorder(jbSair1.getBorder(), text, 0, ICONIFIED, jbSair1.getFont(), Color.WHITE);
        filme.setBorder(bord);
        filme.setText("");
        Icon icon = new ImageIcon(CaminhoPacote);
        filme.setIcon(icon);
        jPanel3.add(filme);
        jPanel3.setName(sql);

        // criar codigo
        nome = jComboBoxNomeFilme.getSelectedItem().toString();
        int random = (int) (Math.random() * 9999 + 1111);
        sql = "SELECT * FROM sessoes";
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            while (retorno.next()) {
                if (retorno.getString("codigo").equals(random)) {
                    random = (int) (Math.random() * 9999 + 1111);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar lista de codigo de sessoes\n" + ex.getMessage());
        }

        // adiciona no banco
        codFilme = jTextField6.getText();
        int codSala = Integer.parseInt(jComboBox6.getSelectedItem().toString());
        float preco = Float.parseFloat(jTextField10.getText());
        String horario = jtxHoraInicio.getText() + ":" + jtxMinutosInicio.getText();
        String formato = jComboBox5.getSelectedItem().toString();
        String audio = jComboBox7.getSelectedItem().toString();
        String sql = "INSERT INTO sessoes VALUES (" + random + "," + codFilme + "," + preco
                + "," + codSala + ",'" + horario + "','" + formato + "','" + audio + "')";
        Conexao con2 = new Conexao();
        try {
            con2.sentenca.execute(sql);
            JOptionPane.showMessageDialog(this, "Inserido com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro de sintaxe " + ex.getMessage());
        }
        jpCadastrarSessao.setVisible(false);
        jpSuperUser.setVisible(true);
    }//GEN-LAST:event_jbSair1ActionPerformed

    private void jbSair8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbSair8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbSair8MouseClicked

    private void jbSair8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSair8ActionPerformed
        File source;
        File dest = null;
        int retorno = 0;

        JFileChooser poster = new JFileChooser();
        poster.setDialogTitle("Buscador de Imagem");
        poster.setFileFilter(new FileNameExtensionFilter("Image files", "bmp", "png", "jpg"));
        poster.setFileSelectionMode(JFileChooser.FILES_ONLY);
        poster.showOpenDialog(this);

        if (retorno == JFileChooser.APPROVE_OPTION) {

            caminho = poster.getSelectedFile().getAbsolutePath();
            source = poster.getSelectedFile();

            jTextField4.setText(caminho);

            JOptionPane.showMessageDialog(null, poster.getSelectedFile().getName());
            nome = poster.getSelectedFile().getName();
            dest = new File("src" + File.separator + "Filmes" + File.separator + poster.getSelectedFile().getName());
            caminho = dest.getAbsolutePath();

            /////// 
            try {
                ///////
                source = resize(source);

            } catch (IOException ex) {
                Logger.getLogger(MenuUsuario.class
                        .getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error resizing" + ex.getMessage());
            }
            ////
            try {
                Copiar2(source, dest);
                source.delete();

            } catch (InterruptedException | IOException ex) {
                Logger.getLogger(MenuUsuario.class
                        .getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error coping" + ex.getMessage());
                jtxCodigoC.setText("");
                jtxNomeFilmeC.setText("");
                jtxDuracacaoC.setText("");
                jtxGeneroC.setText("");
                jTextField4.setText("");
            }

        } else {
            //dispose();
        }
    }//GEN-LAST:event_jbSair8ActionPerformed

    private void jtxHoraInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxHoraInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxHoraInicioActionPerformed

    private void jtxMinutosInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxMinutosInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxMinutosInicioActionPerformed

    private void jbBuscarSessaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbBuscarSessaoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbBuscarSessaoMouseClicked

    private void jbBuscarSessaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscarSessaoActionPerformed
        String CodigoSala = "";
        String preco = "";
        String codigoFilme = "";
        String horario = "";
        String tipo = "";
        String audio = "";

        String conferir = jtxBuscarCodigoS.getText();
        sql = "SELECT * FROM sessoes";
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            while (retorno.next()) {
                if (retorno.getString("codigo").equals(conferir)) {
                    codigoFilme = retorno.getString("codFilme");
                    preco = retorno.getString("preco");
                    CodigoSala = retorno.getString("codSala");
                    horario = retorno.getTime("horario").toString();
                    tipo = retorno.getString("formato");
                    audio = retorno.getString("audio");
                    String sql2 = "SELECT * FROM filmes";
                    Conexao con2 = new Conexao();
                    try {
                        ResultSet retorno2 = con2.sentenca.executeQuery(sql2);
                        while (retorno2.next()) {
                            if (retorno2.getString("codigo").equals(codigoFilme)) {
                                jlNomeFilmeS.setText(retorno2.getString("nome"));
                                jlDuracaoFilmeS.setText(retorno2.getString("duracao") + " minutos");
                                jlGeneroFilmeS.setText(retorno2.getString("genero"));
                                jlCodigoFilmeS.setText(codigoFilme);
                                jlCodigoSalaS.setText(CodigoSala);
                                jlPrecoS.setText(preco);
                                jlHorarioIniS.setText(horario);
                                jlTipoS.setText(tipo);
                                jlAudioS.setText(audio);
                            }
                        }

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao buscar lista de codigo de sessões.\n" + ex.getMessage());
                    }
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar lista de codigo de sessões.\n" + ex.getMessage());
        }
    }//GEN-LAST:event_jbBuscarSessaoActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // pegar preço
        float preco = 0;
        sql = "SELECT * FROM sessoes";
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            while (retorno.next()) {
                if (retorno.getString("codFilme").equals(codFilme)
                        && retorno.getString("codSala").equals(jComboBox2.getSelectedItem().toString())
                        && retorno.getTime("horario").toString().equals(jComboBox3.getSelectedItem().toString())) {
                    preco = retorno.getFloat("preco");
                    codSessao = String.valueOf(retorno.getFloat("codigo"));
                }
            }

            if (jComboBox4.getSelectedItem().toString().equals("Meia")) {
                preco /= 2;
            }
            jLabel20.setText("R$ " + String.valueOf(preco));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar lista de filmes\n" + ex.getMessage());
        }
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // pegar preço
        float preco = 0;
        sql = "SELECT * FROM sessoes";
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            while (retorno.next()) {
                if (retorno.getString("codFilme").equals(codFilme)
                        && retorno.getString("codSala").equals(jComboBox2.getSelectedItem().toString())
                        && retorno.getTime("horario").toString().equals(jComboBox3.getSelectedItem().toString())) {
                    preco = retorno.getFloat("preco");
                    codSessao = String.valueOf(retorno.getFloat("codigo"));
                }
            }

            if (jComboBox4.getSelectedItem().toString().equals("Meia")) {
                preco /= 2;
            }
            jLabel20.setText("R$ " + String.valueOf(preco));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar lista de filmes\n" + ex.getMessage());
        }
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed

    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jbCancelarIngresso1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbCancelarIngresso1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbCancelarIngresso1MouseClicked

    private void jbCancelarIngresso1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarIngresso1ActionPerformed
        //apagar ingresso
        sql = "DELETE FROM ingressos WHERE codigo=" + jTextField2.getText();
        try {
            con.sentenca.executeUpdate(sql);
            JOptionPane.showMessageDialog(this, "Ingresso Cancelado");
            jTextField2.setText("");
            jLabel1.setText("Meia/Inteira");
            jLabel24.setText("00:00");
            jLabel23.setText("");
        } catch (SQLException ex) {
            jLabel23.setText("Código inválido");
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

    }//GEN-LAST:event_jbCancelarIngresso1ActionPerformed

    private void jbSair10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbSair10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbSair10MouseClicked

    private void jbSair10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSair10ActionPerformed
        jpPedido.setVisible(false);
        jpCancelamento.setVisible(false);
        jpFechamento.setVisible(false);
        jpSuperUser.setVisible(false);
        jLabel2.setVisible(false);
        jpMostrador2.setVisible(false);
        jpPoltrona.setVisible(false);
        jpCadastrarSessao.setVisible(false);
        jpRemoverSessao.setVisible(false);
        jpCadastrarFilme.setVisible(false);
        jpCadastrarSala.setVisible(false);
        jpCadastrarUsuario.setVisible(false);
        jpRemoverUsuario.setVisible(false);
        jpRemoverSala.setVisible(false);
        jpRemoverFilme.setVisible(true);
        

    }//GEN-LAST:event_jbSair10ActionPerformed

    private void jbSair5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbSair5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbSair5MouseClicked

    private void jbSair5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSair5ActionPerformed
        jpPedido.setVisible(false);
        jpCancelamento.setVisible(false);
        jpFechamento.setVisible(false);
        jpSuperUser.setVisible(false);
        jLabel2.setVisible(false);
        jpMostrador2.setVisible(false);
        jpPoltrona.setVisible(false);
        jpCadastrarSessao.setVisible(false);
        jpRemoverSessao.setVisible(false);
        jpCadastrarFilme.setVisible(true);
        jpCadastrarSala.setVisible(false);
        jpCadastrarUsuario.setVisible(false);
        jpRemoverUsuario.setVisible(false);
        jpRemoverSala.setVisible(false);
        jpRemoverFilme.setVisible(false);
        
    }//GEN-LAST:event_jbSair5ActionPerformed

    private void jTextField2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MouseClicked
        jTextField2.setText("");
    }//GEN-LAST:event_jTextField2MouseClicked

    private void jComboBoxNomeFilmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxNomeFilmeActionPerformed
        //codigo do filme
        sql = "SELECT * FROM filmes";
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            while (retorno.next()) {
                if (retorno.getString("nome").equals(jComboBoxNomeFilme.getSelectedItem().toString())) {
                    jTextField6.setText(retorno.getString("codigo"));
                    jTextField5.setText(retorno.getString("duracao") + " minutos");
                    jTextField8.setText(retorno.getString("genero"));
                    jTextField4.setText(retorno.getString("imagem"));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não há sessões com o filme selecionado \n" + ex.getMessage());
        }

    }//GEN-LAST:event_jComboBoxNomeFilmeActionPerformed

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed

    }//GEN-LAST:event_jComboBox6ActionPerformed

    private void jTextField10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField10MouseClicked
        jTextField10.setText("");
    }//GEN-LAST:event_jTextField10MouseClicked

    private void jtxHoraInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxHoraInicioMouseClicked
        jtxHoraInicio.setText("");
    }//GEN-LAST:event_jtxHoraInicioMouseClicked

    private void jtxMinutosInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxMinutosInicioMouseClicked
        jtxMinutosInicio.setText("");
    }//GEN-LAST:event_jtxMinutosInicioMouseClicked

    private void jbCadastrarFilmeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbCadastrarFilmeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbCadastrarFilmeMouseClicked

    private void jbCadastrarFilmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCadastrarFilmeActionPerformed
        i++;
        int erro = 0;
        String CaminhoPacote = "";
        if (caminho.contains("/src/")) {
            CaminhoPacote = caminho;
        } else {
            CaminhoPacote = dirImagem + jTextField4.getText();
        }

        // criar codigo
        nome = jComboBoxNomeFilme.getSelectedItem().toString();

        String conferir = jtxCodigoC.getText();
        sql = "SELECT * FROM filmes";
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            while (retorno.next()) {
                if (retorno.getString("codigo").equals(conferir)) {
                    erro = 1;
                    JOptionPane.showMessageDialog(null, "Erro de cadastro: Filme não Cadastrado. \nCodigo de Filme Existente. \nDigite outro Codigo.");

                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar lista de codigo de sessoes\n" + ex.getMessage());
        }
        if (erro == 0) {
            String s = jTextField4.getText();
            String imagem = s.substring(s.lastIndexOf("/") + 1, s.length());

            // adiciona no banco
            sql = "INSERT INTO filmes VALUES (" + conferir + ",'" + jtxNomeFilmeC.getText() + "'," + Integer.parseInt(jtxDuracacaoC.getText()) + ",'" + jtxGeneroC.getText() + "','" + imagem + "')"; //ver como enviar horario
            Conexao con2 = new Conexao();
            try {
                con2.sentenca.execute(sql);
                JOptionPane.showMessageDialog(this, "Inserido com sucesso!");
            } catch (SQLException ex) {

                System.out.println("Erro de sintaxe " + ex.getMessage());
            }
            jpCadastrarFilme.setVisible(false);
            jpSuperUser.setVisible(true);
        } else {
            jtxCodigoC.setText("");
            jtxNomeFilmeC.setText("");
            jtxDuracacaoC.setText("");
            jtxGeneroC.setText("");
            jTextField4.setText("");
        }
    }//GEN-LAST:event_jbCadastrarFilmeActionPerformed

    private void jtxCodigoCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxCodigoCMouseClicked
        jtxCodigoC.setText("");
    }//GEN-LAST:event_jtxCodigoCMouseClicked

    private void jtxDuracacaoCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxDuracacaoCMouseClicked
        jtxDuracacaoC.setText("");
    }//GEN-LAST:event_jtxDuracacaoCMouseClicked

    private void jtxGeneroCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxGeneroCMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxGeneroCMouseClicked

    private void jtxNomeFilmeCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxNomeFilmeCMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxNomeFilmeCMouseClicked

    private void jtxCapacidadeSalaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxCapacidadeSalaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxCapacidadeSalaMouseClicked

    private void jtxCodigoSalaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxCodigoSalaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxCodigoSalaMouseClicked

    private void jbCadastrarSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbCadastrarSMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbCadastrarSMouseClicked

    private void jbCadastrarSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCadastrarSActionPerformed
        // TODO add your handling code here:
        int capacidade = Integer.parseInt(jtxCapacidadeSala.getText());
        int codigo = Integer.parseInt(jtxCodigoSala.getText());
        String conferir = jtxCodigoSala.getText();
        int erro = 0;
        sql = "SELECT * FROM salas";
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);

            while (retorno.next()) {

                if (retorno.getString("codSala").equals(conferir)) {
                    erro = 1;

                    JOptionPane.showMessageDialog(null, "Erro de cadastro: Sala não cadastrada. \nCodigo de sala existente. \nDigite outro Codigo.");

                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar lista de codigo de sala\n" + ex.getMessage());
        }
        if (erro == 0) {
            sql = "INSERT INTO salas VALUES (" + conferir + "," + capacidade + ")";
            Conexao con3 = new Conexao();
            try {
                con3.sentenca.execute(sql);
                JOptionPane.showMessageDialog(this, "Inserido com sucesso!");
            } catch (SQLException ex) {
                System.out.println("Erro de sintaxe " + ex.getMessage());
            }
            jtxCapacidadeSala.setText("");
            jtxCodigoSala.setText("");
            jpCadastrarFilme.setVisible(false);
            jpSuperUser.setVisible(true);
            jtxCapacidadeSala.setVisible(false);
            jtxCodigoSala.setVisible(false);
        } else {
            jtxCapacidadeSala.setText("");
            jtxCodigoSala.setText("");
        }

    }//GEN-LAST:event_jbCadastrarSActionPerformed

    private void jtxNomeUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxNomeUMouseClicked
        jtxNomeU.setText("");
    }//GEN-LAST:event_jtxNomeUMouseClicked

    private void jtxIdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxIdMouseClicked
        jtxId.setText("");
    }//GEN-LAST:event_jtxIdMouseClicked

    private void jbCadastrarUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbCadastrarUMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbCadastrarUMouseClicked

    private void jbCadastrarUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCadastrarUActionPerformed
        if (jtxSenha.getText().equals(jtxSenhaComfirmacao.getText())) {
            int id = Integer.parseInt(jtxId.getText());
            String nome = jtxNomeU.getText();
            String senha = jtxSenha.getText();
            int tipoUsuario = 0;
            if (jcbTipoUsuario.getSelectedItem().equals("Administrador")) {
                tipoUsuario = 1;
            } else {
                tipoUsuario = 0;
            }

            String conferir = jtxId.getText();
            int erro = 0;
            sql = "SELECT * FROM usuarios";
            try {
                ResultSet retorno = con.sentenca.executeQuery(sql);
                while (retorno.next()) {
                    if (retorno.getString("id").equals(conferir)) {
                        erro = 1;
                        JOptionPane.showMessageDialog(null, "Erro de cadastro: Usuario não cadastrada. \nID do Usuario existente. \nDigite outro ID.");
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar lista de ID de usuarios\n" + ex.getMessage());
            }

            if (erro == 0) {
                sql = "INSERT INTO usuarios VALUES (" + id + ",'" + senha + "','" + nome + "'," + tipoUsuario + ")";
                Conexao con3 = new Conexao();
                try {
                    con3.sentenca.execute(sql);
                    JOptionPane.showMessageDialog(this, "Inserido com sucesso!");
                } catch (SQLException ex) {
                    System.out.println("Erro de sintaxe " + ex.getMessage());
                }
                jtxSenha.setText("");
                jtxSenhaComfirmacao.setText("");
                jtxId.setText("");
                jtxNomeU.setText("");
                jpCadastrarUsuario.setVisible(false);
                jpSuperUser.setVisible(true);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Erro no cadastro. \nAs senhas nao sao iguais. \nDigite novamente");
            jtxSenha.setText("");
            jtxSenhaComfirmacao.setText("");
        }
    }//GEN-LAST:event_jbCadastrarUActionPerformed


    private void jbBuscarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscarUsuarioActionPerformed
        int tipoUsuario = 1;
        String nomeU = "";
        String senhaU = "";
        String conferir = jtxIdBusca.getText();
        int erro = 0;
        sql = "SELECT * FROM usuarios";
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            while (retorno.next()) {
                if (retorno.getString("id").equals(conferir)) {
                    erro = 0;
                    senhaU = retorno.getString("senha");
                    nomeU = retorno.getString("usuario");
                    tipoUsuario = retorno.getInt("super");
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar lista de ID de usuarios\n" + ex.getMessage());
        }
        if (erro == 0) {
            jlNomeU.setText(nomeU);
            jlSenhaU.setText(senhaU);
            if (tipoUsuario == 1) {

                jlTipoUsuario.setText("Administrador");
            } else {
                jlTipoUsuario.setText("Usuario Comum");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Erro de busca: Usuario Inexistente. \nDigite outro ID.");
            jtxIdBusca.setText("000000");
        }

    }//GEN-LAST:event_jbBuscarUsuarioActionPerformed

    private void jbBuscarUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbBuscarUsuarioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbBuscarUsuarioMouseClicked

    private void jtxIdBuscaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxIdBuscaMouseClicked
        jtxIdBusca.setText("");
    }//GEN-LAST:event_jtxIdBuscaMouseClicked

    private void jbRemoverUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbRemoverUMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbRemoverUMouseClicked

    private void jbRemoverUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRemoverUActionPerformed

        sql = "DELETE FROM usuarios WHERE id=" + jtxIdBusca.getText();
        try {
            con.sentenca.executeUpdate(sql);
            JOptionPane.showMessageDialog(this, "Usuario deletado");
            jlNomeU.setText("");
            jlTipoUsuario.setText("");
            jlSenhaU.setText("");
            jpRemoverUsuario.setVisible(false);
            jpSuperUser.setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

    }//GEN-LAST:event_jbRemoverUActionPerformed

    private void jtxBuscarSalaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxBuscarSalaMouseClicked
        jtxBuscarSala.setText("");
    }//GEN-LAST:event_jtxBuscarSalaMouseClicked

    private void jbBusacarSalaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbBusacarSalaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbBusacarSalaMouseClicked

    private void jbBusacarSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBusacarSalaActionPerformed
        String capacidade = "";
        String conferir = jtxBuscarSala.getText();
        int erro = 0;
        sql = "SELECT * FROM salas";
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            while (retorno.next()) {
                if (retorno.getString("codSala").equals(conferir)) {
                    erro = 0;
                    capacidade = retorno.getString("capacidade");
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar lista de codigos das salas\n" + ex.getMessage());
        }
        if (erro == 0) {
            jlCapacidade.setText(capacidade);
        } else {
            JOptionPane.showMessageDialog(null, "Erro de busca: Sala Inexistente. \nDigite outro codigo.");
            jlCapacidade.setText("00000");
        }

    }//GEN-LAST:event_jbBusacarSalaActionPerformed

    private void jtxBuscarSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxBuscarSalaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxBuscarSalaActionPerformed

    private void jbCadastrarS2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbCadastrarS2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbCadastrarS2MouseClicked

    private void jbCadastrarS2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCadastrarS2ActionPerformed
        sql = "DELETE FROM salas WHERE codSala=" + jtxBuscarSala.getText();
        try {
            con.sentenca.executeUpdate(sql);
            JOptionPane.showMessageDialog(this, "Sala deletada");
            jlCapacidade.setText("");
            jpRemoverSala.setVisible(false);
            jpSuperUser.setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

    }//GEN-LAST:event_jbCadastrarS2ActionPerformed

    private void jlGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlGeneroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jlGeneroActionPerformed

    private void jbBuscarFilmeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbBuscarFilmeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbBuscarFilmeMouseClicked

    private void jbBuscarFilmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBuscarFilmeActionPerformed
        String nome = "";
        String duracao = "";
        String genero = "";
        String capa = "";
        String conferir = jtxBuscaCodigoF.getText();
        int erro = 0;
        sql = "SELECT * FROM filmes";
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            while (retorno.next()) {
                if (retorno.getString("codigo").equals(conferir)) {
                    erro = 0;
                    nome = retorno.getString("nome");
                    duracao = retorno.getString("duracao");
                    genero = retorno.getString("genero");
                    capa = retorno.getString("imagem");
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar lista de codigos dos filmes\n" + ex.getMessage());
        }
        if (erro == 0) {
            jlNomeFilme.setText(nome);
            jlDuracao.setText(duracao);
            jlGenero.setText(genero);
            jlCapa.setText(capa);
        } else {
            JOptionPane.showMessageDialog(null, "Erro de busca: Filme Inexistente. \nDigite outro codigo.");
            jtxBuscaCodigoF.setText("00000");
        }
    }//GEN-LAST:event_jbBuscarFilmeActionPerformed

    private void jtxBuscaCodigoFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxBuscaCodigoFMouseClicked
        jtxBuscaCodigoF.setText("");
    }//GEN-LAST:event_jtxBuscaCodigoFMouseClicked

    private void jlCapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlCapaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jlCapaActionPerformed

    private void jlNomeFilmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlNomeFilmeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jlNomeFilmeActionPerformed

    private void jlDuracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlDuracaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jlDuracaoActionPerformed

    private void jbRemoverFilme1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbRemoverFilme1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbRemoverFilme1MouseClicked

    private void jbRemoverFilme1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRemoverFilme1ActionPerformed
        sql = "DELETE FROM filmes WHERE codigo=" + jtxBuscaCodigoF.getText();
        try {
            con.sentenca.executeUpdate(sql);
            JOptionPane.showMessageDialog(this, "Filme deletado");
            jlCapacidade.setText("");
            jpRemoverFilme.setVisible(false);
            jpSuperUser.setVisible(true);
            jlNomeFilme.setText("");
            jlDuracao.setText("");
            jlGenero.setText("");
            jlCapa.setText("");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_jbRemoverFilme1ActionPerformed

    private void jTextField11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField11MouseClicked
        jTextField11.setText("");
    }//GEN-LAST:event_jTextField11MouseClicked

    private void jtxBuscarCodigoSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxBuscarCodigoSMouseClicked
        jtxBuscarCodigoS.setText("");
    }//GEN-LAST:event_jtxBuscarCodigoSMouseClicked

    private void jbSair11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbSair11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbSair11MouseClicked

    private void jbSair11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSair11ActionPerformed

        sql = "DELETE FROM sessoes WHERE codigo=" + jtxBuscarCodigoS.getText();
        try {
            con.sentenca.executeUpdate(sql);
            JOptionPane.showMessageDialog(this, "Usuario deletado");
            jtxBuscarCodigoS.setText("000000");
            jlNomeFilmeS.setText("");
            jlDuracaoFilmeS.setText("");
            jlGeneroFilmeS.setText("");
            jlCodigoFilmeS.setText("");
            jlCodigoSalaS.setText("");
            jlPrecoS.setText("");
            jlHorarioIniS.setText("");
            jlTipoS.setText("");
            jlAudioS.setText("");
            jpRemoverSessao.setVisible(false);
            jpSuperUser.setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

    }//GEN-LAST:event_jbSair11ActionPerformed

    private void jtxSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxSenhaMouseClicked
        jtxSenha.setText("");
    }//GEN-LAST:event_jtxSenhaMouseClicked

    private void jtxSenhaComfirmacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxSenhaComfirmacaoMouseClicked
        jtxSenhaComfirmacao.setText("");
    }//GEN-LAST:event_jtxSenhaComfirmacaoMouseClicked

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // pega capacidade

        String capacidade = null;
        sql = "SELECT * FROM salas";
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            while (retorno.next()) {
                if (retorno.getString("codSala").equals(jComboBox2.getSelectedItem().toString())) {
                    capacidade = retorno.getString("capacidade");
                }
            }
            jLabel48.setText(capacidade);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar lista de filmes\n" + ex.getMessage());
        }

        //pegar horarios
        ArrayList<String> horario = new ArrayList<>();
        sql = "SELECT * FROM sessoes";
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            while (retorno.next()) {
                if (retorno.getString("codFilme").equals(codFilme) && retorno.getString("codSala").equals(jComboBox2.getSelectedItem().toString())) {
                    horario.add(retorno.getTime("horario").toString());
                }
            }
            jComboBox3.setModel(new DefaultComboBoxModel(horario.toArray()));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar lista de filmes\n" + ex.getMessage());
        }
        // pegar preço
        float preco = 0;
        sql = "SELECT * FROM sessoes";
        try {
            ResultSet retorno = con.sentenca.executeQuery(sql);
            while (retorno.next()) {
                if (retorno.getString("codFilme").equals(codFilme)
                        && retorno.getString("codSala").equals(jComboBox2.getSelectedItem().toString())
                        && retorno.getTime("horario").toString().equals(jComboBox3.getSelectedItem().toString())) {
                    preco = retorno.getFloat("preco");
                    codSessao = retorno.getString("codigo");
                    jLabel22.setText(retorno.getString("formato")); //pega formato
                    jLabel63.setText(retorno.getString("audio")); //pega audio
                }
            }
            if (jComboBox4.getSelectedItem().toString().equals("Meia")) {
                preco /= 2;
            }
            jLabel20.setText("R$ " + String.valueOf(preco));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar lista de filmes\n" + ex.getMessage());
        }

    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        Sobre sobre = new Sobre();
        sobre.setVisible(true);
    }//GEN-LAST:event_jLabel5MouseClicked

    public void alterarNomeUsuario(String nome) {
        jlNomeUsuario.setText(nome);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MenuUsuario(false).setVisible(true);
        });

    }
    //<editor-fold defaultstate="collapsed" desc=" Variables declaration ">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonA1;
    private javax.swing.JButton jButtonA2;
    private javax.swing.JButton jButtonA3;
    private javax.swing.JButton jButtonA4;
    private javax.swing.JButton jButtonA5;
    private javax.swing.JButton jButtonB1;
    private javax.swing.JButton jButtonB2;
    private javax.swing.JButton jButtonB3;
    private javax.swing.JButton jButtonB4;
    private javax.swing.JButton jButtonB5;
    private javax.swing.JButton jButtonC1;
    private javax.swing.JButton jButtonC2;
    private javax.swing.JButton jButtonC3;
    private javax.swing.JButton jButtonC4;
    private javax.swing.JButton jButtonC5;
    private javax.swing.JButton jButtonD1;
    private javax.swing.JButton jButtonD2;
    private javax.swing.JButton jButtonD3;
    private javax.swing.JButton jButtonD4;
    private javax.swing.JButton jButtonD5;
    private javax.swing.JButton jButtonE1;
    private javax.swing.JButton jButtonE2;
    private javax.swing.JButton jButtonE3;
    private javax.swing.JButton jButtonE4;
    private javax.swing.JButton jButtonE5;
    private javax.swing.JButton jButtonF1;
    private javax.swing.JButton jButtonF2;
    private javax.swing.JButton jButtonF3;
    private javax.swing.JButton jButtonF4;
    private javax.swing.JButton jButtonF5;
    private javax.swing.JButton jButtonG1;
    private javax.swing.JButton jButtonG2;
    private javax.swing.JButton jButtonG3;
    private javax.swing.JButton jButtonG4;
    private javax.swing.JButton jButtonG5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBoxNomeFilme;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JButton jbBusacarSala;
    private javax.swing.JButton jbBuscarFilme;
    private javax.swing.JButton jbBuscarSessao;
    private javax.swing.JButton jbBuscarUsuario;
    private javax.swing.JButton jbCadastrarFilme;
    private javax.swing.JButton jbCadastrarS;
    private javax.swing.JButton jbCadastrarS2;
    private javax.swing.JButton jbCadastrarU;
    private javax.swing.JButton jbCancelamento;
    private javax.swing.JButton jbCancelarIngresso;
    private javax.swing.JButton jbCancelarIngresso1;
    private javax.swing.JButton jbCompra;
    private javax.swing.JButton jbFechamento;
    private javax.swing.JButton jbHome;
    private javax.swing.JButton jbPedido;
    private javax.swing.JButton jbPoltrona;
    private javax.swing.JButton jbRemoverFilme1;
    private javax.swing.JButton jbRemoverSessao;
    private javax.swing.JButton jbRemoverU;
    private javax.swing.JButton jbSair;
    private javax.swing.JButton jbSair1;
    private javax.swing.JButton jbSair10;
    private javax.swing.JButton jbSair11;
    private javax.swing.JButton jbSair2;
    private javax.swing.JButton jbSair3;
    private javax.swing.JButton jbSair4;
    private javax.swing.JButton jbSair5;
    private javax.swing.JButton jbSair6;
    private javax.swing.JButton jbSair7;
    private javax.swing.JButton jbSair8;
    private javax.swing.JButton jbSuperUser;
    private javax.swing.JComboBox<String> jcbTipoUsuario;
    private javax.swing.JLabel jlAudioS;
    private javax.swing.JTextField jlCapa;
    private javax.swing.JLabel jlCapacidade;
    private javax.swing.JLabel jlCodigoFilmeS;
    private javax.swing.JLabel jlCodigoSalaS;
    private javax.swing.JTextField jlDuracao;
    private javax.swing.JLabel jlDuracaoFilmeS;
    private javax.swing.JTextField jlGenero;
    private javax.swing.JLabel jlGeneroFilmeS;
    private javax.swing.JLabel jlHorarioIniS;
    private javax.swing.JLabel jlLinha1;
    private javax.swing.JLabel jlLinha2;
    private javax.swing.JLabel jlLinha3;
    private javax.swing.JLabel jlLinha4;
    private javax.swing.JLabel jlLinha5;
    private javax.swing.JTextField jlNomeFilme;
    private javax.swing.JLabel jlNomeFilmeS;
    private javax.swing.JLabel jlNomeU;
    private javax.swing.JLabel jlNomeUsuario;
    private javax.swing.JLabel jlPrecoS;
    private javax.swing.JLabel jlSenhaU;
    private javax.swing.JLabel jlTipoS;
    private javax.swing.JLabel jlTipoUsuario;
    private javax.swing.JPanel jpCadastrarFilme;
    private javax.swing.JPanel jpCadastrarSala;
    private javax.swing.JPanel jpCadastrarSessao;
    private javax.swing.JPanel jpCadastrarUsuario;
    private javax.swing.JPanel jpCancelamento;
    private javax.swing.JPanel jpDevCodes;
    private javax.swing.JPanel jpDevCodesSite;
    private javax.swing.JPanel jpFechamento;
    private javax.swing.JScrollPane jpMostrador1;
    private javax.swing.JScrollPane jpMostrador2;
    private javax.swing.JPanel jpPedido;
    private javax.swing.JPanel jpPoltrona;
    private javax.swing.JPanel jpRemoverFilme;
    private javax.swing.JPanel jpRemoverSala;
    private javax.swing.JPanel jpRemoverSessao;
    private javax.swing.JPanel jpRemoverUsuario;
    private javax.swing.JPanel jpSeletorOpc;
    private javax.swing.JPanel jpSuperUser;
    private javax.swing.JTextField jtxBuscaCodigoF;
    private javax.swing.JTextField jtxBuscarCodigoS;
    private javax.swing.JTextField jtxBuscarSala;
    private javax.swing.JTextField jtxCapacidadeSala;
    private javax.swing.JTextField jtxCodigoC;
    private javax.swing.JTextField jtxCodigoSala;
    private javax.swing.JTextField jtxDuracacaoC;
    private javax.swing.JTextField jtxGeneroC;
    private javax.swing.JTextField jtxHoraInicio;
    private javax.swing.JTextField jtxId;
    private javax.swing.JTextField jtxIdBusca;
    private javax.swing.JTextField jtxMinutosInicio;
    private javax.swing.JTextField jtxNomeFilmeC;
    private javax.swing.JTextField jtxNomeU;
    private javax.swing.JPasswordField jtxSenha;
    private javax.swing.JPasswordField jtxSenhaComfirmacao;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
