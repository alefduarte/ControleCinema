package Ingressos;

import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JEditorPane;

/* Written by alefduarte */
public class SalvaIngresso {

    public static String dirIngresso = System.getProperty("user.dir") + "/src/Ingressos/";

    public static void writePng(String codSala, String nomeFilme, String horario,
            String preco, String codIng, String assento) {
        
        File file = new File(System.getProperty("user.dir") + "/src/Ingressos/Ingresso.png");
        file.delete();
        int width = 1000, height = 500;
        // Create a `BufferedImage` and create the its `Graphics`
        BufferedImage image = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice().getDefaultConfiguration()
                .createCompatibleImage(width, height);
        Graphics graphics = image.createGraphics();
        String conteudo = "<!DOCTYPE html>\n<html>\n<body>\n\n<p style=\"font-size:16px\">Cinema Cineland LTDA</p>"
                + "\n<table>\n  <tr>\n   <td style=\"font-size:14px\">CNPJ 064829375/0004-54&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
                + "\n    <td style=\"font-size:14px\">IE 74339218&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\n"
                + "    <td style=\"font-size:14px\">IM 74328932</td>\n  </tr>\n</table>\n"
                + "<p style=\"font-size:14px\">R. Paraná, 3001 - Jardim Belvedere, Divinópolis - MG, 35501-170 </p>"
                + "\n<p style=\"font-size:36px;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        conteudo += "SALA - " +codSala + "</p>\n";
        conteudo += "<p style=\"font-size:30px\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        conteudo += nomeFilme + "</p>\n\n";
        conteudo += "<table>\n  <tr>\n    <td style=\"font-size:18px\">Data/Hora&nbsp;&nbsp;"
                + "&nbsp;&nbsp;&nbsp;</td>\n    <td style=\"font-size:18px\">Valor Ingresso&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
                + "\n    <td style=\"font-size:18px\">Cod. Ingresso&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
                + "\n    <td style=\"font-size:18px\">Assento</td>\n  </tr>\n  <tr>"
                + "\n    <td style=\"font-size:28px\">28/06/17 " + horario + "&nbsp;&nbsp;&nbsp;&nbsp;</td>"
                + "\n    <td style=\"font-size:28px\">R$ " + preco + "&nbsp;&nbsp;&nbsp;&nbsp;</td>"
                + "\n    <td style=\"font-size:28px\">" + codIng + "&nbsp;&nbsp;&nbsp;&nbsp;</td>"
                + "\n     <td style=\"font-size:28px\">" + assento + "</td>\n  </tr>\n</table>";
        conteudo += "\n\n<hr>\n<table>\n  <tr>\n    <td style=\"font-size:12px\">ISS: 3,0000%&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
                + "\n    <td style=\"font-size:12px\">COFINS: 0.0760%&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>"
                + "\n    <td style=\"font-size:12px\">ALVARA: 145673</td>\n  </tr>\n  <tr>\n"
                + "    <td style=\"font-size:12px\">PIS: 0,0165%&nbsp;&nbsp;&nbsp;&nbsp;</td>\n"
                + "    <td style=\"font-size:12px\">Cod. Fiscal: 04192834&nbsp;&nbsp;&nbsp;&nbsp;</td>\n"
                + "    <td style=\"font-size:12px\">AVCB: 1221/15 - 28/12/17</td>\n  </tr>\n</table>\n\n"
                + "<hr>\n\n<p style=\"font-size:12px\">  </p>\n<p style=\"font-size:12px\">  </p>\n\n</body>\n</html>";

        // Create an `JEditorPane` and invoke `print(Graphics)`
        JEditorPane jep = new JEditorPane("text/html", conteudo);
        jep.setSize(width, height);
        jep.print(graphics);
        // Output the `BufferedImage` via `ImageIO`
        try {
            ImageIO.write(image, "png", new File(dirIngresso + "Ingresso.png"));
        } catch (IOException e) {
        }
    }

}
