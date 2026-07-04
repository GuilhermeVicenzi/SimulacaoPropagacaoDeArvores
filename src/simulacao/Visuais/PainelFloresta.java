package simulacao.Visuais;

import simulacao.ambiente.Ambiente;
import simulacao.modelo.Arvore;
import simulacao.modelo.Semente;

import javax.swing.*;
import java.awt.*;

public class PainelFloresta extends JPanel {
    private Ambiente ambiente;

    private final Color COR_GRAMA = new Color(225, 235, 210);
    private final Color COR_LIMITE = new Color(140, 150, 130);
    private final Color COR_TRONCO = new Color(115, 75, 45);

    private final Color COR_ARVORE_VIVA = new Color(46, 139, 87, 180);
    private final Color COR_ARVORE_MORTA = new Color(120, 130, 120, 160);
    private final Color COR_SEMENTE = new Color(210, 140, 60);

    public PainelFloresta(Ambiente ambiente) {
        this.ambiente = ambiente;
        setBackground(COR_GRAMA);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        int largura = getWidth();
        int altura = getHeight();

        int centroTelaX = largura / 2;
        int centroTelaY = altura / 2;

        double escala = 8.0;

        int raioTela = (int)(ambiente.getRaio() * escala);

        g2.setColor(COR_LIMITE);
        g2.setStroke(new BasicStroke(2.0f));
        g2.drawOval(
                centroTelaX - raioTela,
                centroTelaY - raioTela,
                raioTela * 2,
                raioTela * 2
        );

        for (Arvore a : ambiente.getArvores()) {
            int x = centroTelaX + (int)(a.getCentro()[0] * escala);
            int y = centroTelaY + (int)(a.getCentro()[1] * escala);
            int r = (int)((a.getDiametro() / 2) * escala);

            int tamanhoTronco = Math.max(4, r / 3);

            g2.setColor(COR_TRONCO);
            g2.fillOval(x - tamanhoTronco / 2, y - tamanhoTronco / 2, tamanhoTronco, tamanhoTronco);
        }

        for (Arvore a : ambiente.getArvores()) {
            int x = centroTelaX + (int)(a.getCentro()[0] * escala);
            int y = centroTelaY + (int)(a.getCentro()[1] * escala);
            int r = (int)((a.getDiametro() / 2) * escala);

            if (a.isViva()) {
                g2.setColor(COR_ARVORE_VIVA);
            } else {
                g2.setColor(COR_ARVORE_MORTA);
            }

            g2.fillOval(x - r, y - r, r * 2, r * 2);

            if (a.isViva()) {
                g2.setColor(new Color(34, 100, 60, 200));
            } else {
                g2.setColor(new Color(90, 100, 90, 180));
            }
            g2.setStroke(new BasicStroke(1.0f));
            g2.drawOval(x - r, y - r, r * 2, r * 2);
        }

        g2.setColor(COR_SEMENTE);
        for (Semente s : ambiente.getSementes()) {
            int x = centroTelaX + (int)(s.getPosicaoQueda()[0] * escala);
            int y = centroTelaY + (int)(s.getPosicaoQueda()[1] * escala);

            int tamanho = Math.max(3, (int)escala / 2);

            g2.fillOval(x - tamanho / 2, y - tamanho / 2, tamanho, (int)(tamanho * 1.2));
        }
    }
}