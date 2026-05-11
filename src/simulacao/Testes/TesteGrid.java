package simulacao.Testes;

import simulacao.ambiente.Ambiente;
import simulacao.modelo.Arvore;
import simulacao.modelo.Semente;
import simulacao.simulador.Simulador;

import java.util.ArrayList;
import java.util.List;

public class TesteGrid {
    public static void main(String[] args) {
        List<Semente> sementes = new ArrayList<>();

        for (int x = -2; x <= 2; x++) {
            for (int y = -2; y <= 2; y++) {

                double px = x * 15;
                double py = y * 15;

                Arvore a = new Arvore(0, false, true, 50,
                        10, 0.2, 1.1, 50.5, new double[]{px, py});

                Semente s = new Semente(0.5F, 5, a);
                s.setPosicaoQueda(new double[]{px, py});

                sementes.add(s);
            }
        }

        Ambiente ambiente = new Ambiente(new ArrayList<Arvore>(), sementes, 40, 9999);
        Simulador simulador = new Simulador(ambiente);
        simulador.executar(100);

    }
}