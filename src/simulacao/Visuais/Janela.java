package simulacao.Visuais;

import simulacao.ambiente.Ambiente;

import javax.swing.*;

public class Janela extends JFrame{
    PainelFloresta painel;

    public Janela (Ambiente ambiente) {

        setTitle("Floresta");
        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        painel = new PainelFloresta(ambiente);

        add(painel);
        setVisible(true);
    }

    public void atualizar() {
        painel.repaint();
    }
}
