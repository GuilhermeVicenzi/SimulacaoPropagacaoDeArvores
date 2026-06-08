package simulacao.Testes;

import simulacao.ambiente.Ambiente;
import simulacao.modelo.Arvore;
import simulacao.modelo.Semente;
import simulacao.simulador.Simulacao;
import simulacao.simulador.Simulador;

import java.util.ArrayList;
import java.util.List;

public class TesteCruz {
    public static void main(String[] args) {
        List<Semente> sementes = new ArrayList<>();

        double espacamento = 15;

        double[][] posicoes = {
                {0, 0},
                {espacamento, 0},
                {-espacamento, 0},
                {0, espacamento},
                {0, -espacamento},
                {0, 2*espacamento}
        };

        for (double[] p : posicoes) {
            Arvore a = new Arvore(0, false, true, 50,
                    10, 0.2, 1.1, 50.5, p);

            Semente s = new Semente(0.5F, 5, a);
            s.setPosicaoQueda(p);

            sementes.add(s);
        }
        Simulacao simulacao = new Simulacao(20.0, 0.5, 35, 1234);

        Ambiente ambiente = new Ambiente(new ArrayList<Arvore>(), sementes, 40, 9999, simulacao);
        Simulador simulador = new Simulador(ambiente, simulacao);
        simulador.executar(100);
    }
}
