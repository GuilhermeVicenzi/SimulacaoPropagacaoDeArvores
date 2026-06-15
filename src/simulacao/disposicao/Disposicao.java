package simulacao.disposicao;

import simulacao.modelo.Arvore;
import simulacao.modelo.Semente;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Disposicao {
    private int quantidade;
    private double espacamento;
    private double tamanho;
    private Random random;
    private Arvore arvore;

    public Disposicao(int quantidade, double espacamento, double tamanho, Random random, Arvore arvore) {
        this.quantidade = quantidade;
        this.espacamento = espacamento;
        this.tamanho = tamanho;
        this.random = random;
        this.arvore = arvore;
    }

    public List<Semente> disporCirculo(double raio) {
        if (raio > tamanho) {
            throw new IllegalArgumentException(
                    "O círculo de disposição excede os limites do ambiente."
            );
        }

        List<Semente> sementes = new ArrayList<>();

        for (int i = 0; i < quantidade; i++) {

            double angulo = (2 * Math.PI * i) / quantidade;

            double x = Math.cos(angulo) * raio * 3;
            double y = Math.sin(angulo) * raio * 3;

            if ((x * x) + (y * y) <= (tamanho * tamanho)) {

                Semente s = new Semente(0.5F, arvore);
                s.setPosicaoQueda(new double[]{x, y});

                sementes.add(s);
            };
        }

        return sementes;
    }

    public List<Semente> disporGrid(int linhas, int colunas) {
        List<Semente> sementes = new ArrayList<>();

        double offsetX = (colunas - 1) * espacamento / 2.0;
        double offsetY = (linhas - 1) * espacamento / 2.0;

        for (int x = 0; x < colunas; x++) {
            for (int y = 0; y < linhas; y++) {

                double px = x * espacamento - offsetX;
                double py = y * espacamento - offsetY;

                if ((px * px) + (py * py) <= (tamanho * tamanho)) {

                    Semente s = new Semente(0.5F, arvore);
                    s.setPosicaoQueda(new double[]{px, py});

                    sementes.add(s);
                }
            }
        }

        return sementes;
    }

    public List<Semente> disporEspiral() {
        List<Semente> sementes = new ArrayList<>();

        for (int i = 0; i < quantidade; i++) {

            double angulo = i * 0.5;
            double raio = i * espacamento / 10;

            if (raio > tamanho) {
                break;
            }

            double px = Math.cos(angulo) * raio;
            double py = Math.sin(angulo) * raio;

            Semente s = new Semente(0.5F, arvore);
            s.setPosicaoQueda(new double[]{px, py});

            sementes.add(s);
        }

        return sementes;
    }

    public List<Semente> disporAleatorio() {
        List<Semente> sementes = new ArrayList<>();

        Random r = new Random();

        for (int i = 0; i < quantidade; i++) {

            double angulo = r.nextDouble() * Math.PI * 2;
            double distancia = Math.sqrt(r.nextDouble()) * tamanho;

            double px = Math.cos(angulo) * distancia;
            double py = Math.sin(angulo) * distancia;

            Semente s = new Semente(0.5F, arvore);
            s.setPosicaoQueda(new double[]{px, py});

            sementes.add(s);
        }

        return sementes;
    }

    public List<Semente> disporClusters(int quantidadeClusters) {
        List<Semente> sementes = new ArrayList<>();

        Random r = new Random();

        List<double[]> centros = new ArrayList<>();

        for (int i = 0; i < quantidadeClusters; i++) {

            double angulo = r.nextDouble() * Math.PI * 2;
            double distancia = Math.sqrt(r.nextDouble()) * tamanho * 0.7;

            double cx = Math.cos(angulo) * distancia;
            double cy = Math.sin(angulo) * distancia;

            centros.add(new double[]{cx, cy});
        }

        for (int i = 0; i < quantidade; i++) {

            double[] centro = centros.get(r.nextInt(centros.size()));

            double angulo = r.nextDouble() * Math.PI * 2;
            double distancia = Math.sqrt(r.nextDouble()) * espacamento;

            double px = centro[0] + Math.cos(angulo) * distancia;
            double py = centro[1] + Math.sin(angulo) * distancia;

            if ((px * px) + (py * py) <= (tamanho * tamanho)) {

                Semente s = new Semente(0.5F, arvore);
                s.setPosicaoQueda(new double[]{px, py});

                sementes.add(s);
            }
        }

        return sementes;
    }
}
