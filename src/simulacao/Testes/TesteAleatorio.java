package simulacao.Testes;

import simulacao.ambiente.Ambiente;
import simulacao.modelo.Arvore;
import simulacao.modelo.Semente;
import simulacao.simulador.Simulacao;
import simulacao.simulador.Simulador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TesteAleatorio {
    public static void main(String[] args) {
        List<Semente> sementes = new ArrayList<>();

        Random r = new Random();

        for (int i = 0; i < 20; i++) {

            double angulo = r.nextDouble() * Math.PI * 2;
            double distancia = Math.sqrt(r.nextDouble()) * 38;

            double x = Math.cos(angulo) * distancia;
            double y = Math.sin(angulo) * distancia;

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
