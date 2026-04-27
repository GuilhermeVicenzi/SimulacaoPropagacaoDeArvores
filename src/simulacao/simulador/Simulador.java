package simulacao.simulador;

import javax.swing.Timer;
import simulacao.Visuais.Janela;
import simulacao.ambiente.Ambiente;

public class Simulador {
    Ambiente ambiente;
    Janela janela;

    private int passoAtual = 0;
    private int maxPassos;

    public Simulador(Ambiente ambiente) {
        this.ambiente = ambiente;
        this.janela = new Janela(ambiente);
    }

    public void executar(int passos) {
        this.maxPassos = passos;

        Timer timer = new Timer(500, e -> {

            if (passoAtual >= maxPassos) {
                ((Timer)e.getSource()).stop();
                return;
            }

            System.out.println("Passo " + (passoAtual + 1));

            ambiente.simularPassos();

            janela.atualizar();

            passoAtual++;
        });

        timer.start();

    }
}
