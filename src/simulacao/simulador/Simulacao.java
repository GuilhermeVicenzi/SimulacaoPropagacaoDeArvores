package simulacao.simulador;

import java.util.Random;

public class Simulacao {
    private final double diametroMax;
    private final double distanciaMin;
    private final int idadeMax;
    private final Random seed;
    private final float recursoInicial;
    private final int validadeSemente;

    public Simulacao(double diametroMax, double distanciaMin, int idadeMax, int seed, float recursoInicial, int validadeSemente) {
        this.diametroMax = diametroMax;
        this.distanciaMin = distanciaMin;
        this.idadeMax = idadeMax;
        this.seed = new Random(seed);
        this.recursoInicial = recursoInicial;
        this.validadeSemente = validadeSemente;
    }

    public double getDiametroMax() {
        return diametroMax;
    }

    public double getDistanciaMin() {
        return distanciaMin;
    }

    public int getIdadeMax() {
        return idadeMax;
    }

    public Random getSeed() {
        return seed;
    }

    public float getRecursoInicial() {
        return recursoInicial;
    }

    public int getValidadeSemente() {
        return validadeSemente;
    }
}
