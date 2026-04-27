package simulacao.Visuais;

import simulacao.ambiente.Ambiente;
import simulacao.modelo.Arvore;
import simulacao.modelo.Semente;

import javax.swing.*;
import java.awt.*;

public class PainelFloresta extends JPanel{
    private Ambiente ambiente;

    public PainelFloresta(Ambiente ambiente) {
        this.ambiente = ambiente;
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics g2 = (Graphics2D) g;

        int largura = getWidth();
        int altura = getHeight();

        int centroTelaX = largura / 2;
        int centroTelaY = altura / 2;

        double escala = 8.0;

        int raioTela = (int)(ambiente.getRaio() * escala);

        g2.setColor(Color.BLACK);
        g2.drawOval(
                centroTelaX - raioTela,
                centroTelaY - raioTela,
                raioTela * 2,
                raioTela * 2
        );

        for (Arvore a : ambiente.getArvores()) {
            int x = centroTelaX + (int)(a.getCentro()[0] * escala);
            int y = centroTelaY + (int)(a.getCentro()[1] * escala);

            int r = (int)((a.getDiametro()/2) * escala);

            if (a.isViva()) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.gray);
            }

            g.fillOval(x - r, y - r, r * 2, r * 2);

            int tamanho = r / 2;
            g.setColor(new Color(139,69,19));
            g.fillOval(x - tamanho / 2, y - tamanho /2 , tamanho, tamanho);
        }

        g.setColor(Color.ORANGE);

        for (Semente s : ambiente.getSementes()) {

            int x = centroTelaX + (int)(s.getPosicaoQueda()[0] * escala);
            int y = centroTelaY - (int)(s.getPosicaoQueda()[1] * escala);


            int tamanho = (int)escala / 2;
            g.fillOval(x - tamanho / 2, y - tamanho / 2, tamanho, tamanho);
        }
    }

}
