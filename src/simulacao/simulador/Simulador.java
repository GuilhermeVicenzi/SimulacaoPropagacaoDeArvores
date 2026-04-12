package simulacao.simulador;

import simulacao.ambiente.Ambiente;

public class Simulador {
    Ambiente ambiente;

    public Simulador(Ambiente ambiente) {
        this.ambiente = ambiente;
    }

    public void executar(int passos) {
        for (int i = 0; i < passos; i++) {
            System.out.println("=======================================================");
            System.out.println("Execucao de passo: " + (i + 1));
            System.out.println("=======================================================");
            ambiente.simularPassos();
        }
    }
}
