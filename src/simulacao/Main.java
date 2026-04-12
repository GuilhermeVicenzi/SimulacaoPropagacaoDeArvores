package simulacao;

import simulacao.ambiente.Ambiente;
import simulacao.modelo.Arvore;
import simulacao.modelo.Semente;
import simulacao.simulador.Simulador;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Arvore arvore = new Arvore(0, false, true, 50,
                10, 0.2F, 1.1F);
        List<Arvore> arvores = new ArrayList<>();
        arvores.add(arvore);

        Semente semente = new Semente(1F, 2, arvore);
        List<Semente> sementes = new ArrayList<>();
//        sementes.add(semente);

        Ambiente ambiente = new Ambiente(arvores, sementes, 100, 1000F);

        Simulador sim = new Simulador(ambiente);
        sim.executar(105);
    }
}
