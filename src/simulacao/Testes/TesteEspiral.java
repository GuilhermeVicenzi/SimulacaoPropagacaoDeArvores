package simulacao.Testes;

import simulacao.ambiente.Ambiente;
import simulacao.modelo.Arvore;
import simulacao.modelo.Semente;
import simulacao.simulador.Simulacao;
import simulacao.simulador.Simulador;

import java.util.ArrayList;
import java.util.List;

public class TesteEspiral {
    public static void main(String[] args) {
        List<Semente> sementes = new ArrayList<>();

        int quantidade = 20;

        for (int i = 0; i < quantidade; i++) {

            double angulo = i * 0.5;
            double raio = i * 2;

            double x = Math.cos(angulo) * raio;
            double y = Math.sin(angulo) * raio;

            if (raio > 40) {
                break;
            }
            Arvore a = new Arvore(0, false, true, 50,
                    10, 0.2, 1.1, 50.5, new double[]{x, y});

            Semente s = new Semente(0.5F, 5, a);
            s.setPosicaoQueda(new double[]{x, y});

            sementes.add(s);
        }
        Simulacao simulacao = new Simulacao(20.0, 0.5, 35, 1234);

        Ambiente ambiente = new Ambiente(new ArrayList<Arvore>(), sementes, 40, 9999, simulacao);
        Simulador simulador = new Simulador(ambiente, simulacao);
        simulador.executar(100);
    }
}
