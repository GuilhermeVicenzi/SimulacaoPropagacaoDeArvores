package simulacao.Visuais;

import simulacao.ambiente.Ambiente;

import javax.swing.*;

public class Janela extends JFrame{
    private PainelFloresta painel;

    public Janela (Ambiente ambiente) {

        setTitle("Floresta");
        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        painel = new PainelFloresta(ambiente);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);

        add(painel);
        setVisible(true);
    }

    public void atualizar() {
        painel.repaint();
    }
}
