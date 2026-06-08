package simulacao.simulador;

import java.util.Random;

public class Simulacao {
    private final double diametroMax;
    private final double distanciaMin;
    private final int idadeMax;
    private final Random seed;

    public Simulacao(double diametroMax, double distanciaMin, int idadeMax, int seed) {
        this.diametroMax = diametroMax;
        this.distanciaMin = distanciaMin;
        this.idadeMax = idadeMax;
        this.seed = new Random(seed);
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
}
