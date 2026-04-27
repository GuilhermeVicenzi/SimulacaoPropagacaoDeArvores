package simulacao;

import simulacao.ambiente.Ambiente;
import simulacao.modelo.Arvore;
import simulacao.modelo.Semente;
import simulacao.simulador.Simulador;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Arvore a1 = new Arvore(0, false, true, 50,
                10, 0.2, 1.1, 50.5, new double[]{10.0, 0.0});

        Arvore a2 = new Arvore(0, false, true, 50,
                10, 0.2, 1.1, 50.5, new double[]{-10.0, 0.0});

        Arvore a3 = new Arvore(0, false, true, 50,
                10, 0.2, 1.1, 50.5, new double[]{0.0, 10.0});

        Arvore a4 = new Arvore(0, false, true, 50,
                10, 0.2, 1.1, 50.5, new double[]{0.0, -10.0});

        List<Arvore> arvores = new ArrayList<>();
        arvores.add(a1);
        arvores.add(a2);
        arvores.add(a3);
        arvores.add(a4);

        List<Semente> sementes = new ArrayList<>();
        Semente s = new Semente(0.5F, 5, a1);
        s.setPosicaoQueda(new double[]{0, 0});
        sementes.add(s);

        Ambiente ambiente = new Ambiente(arvores, sementes, 20, 10000F);

        Simulador sim = new Simulador(ambiente);
        sim.executar(25); // Os raios devem ficar próximos de 7.1, 7.2
    }
}
