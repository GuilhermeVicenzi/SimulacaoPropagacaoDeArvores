package simulacao.modelo;

import java.util.Random;

public class Semente {
    private float chanceGerminar;
    private int tempoGerminar;
    private Arvore arvorePai;
    private double[] posicaoQueda;

    public Semente(float chanceGerminar, int tempoGerminar, Arvore arvore) {
        this.chanceGerminar = chanceGerminar;
        this.tempoGerminar = tempoGerminar;
        this.arvorePai = arvore;
        posicaoQueda = calcularQueda();
    }

    public boolean tentarGerminar(Random random) {
        tempoGerminar--;
        if (tempoGerminar <= 0) {
            return random.nextDouble() < chanceGerminar;
        }
        return false;
    }

    public Arvore germinar() {

        return new Arvore(
                0,
                false,
                true,
                10, // energia inicial
                arvorePai.getEnergiaNecessaria(),
                0.2,
                arvorePai.getTaxaCrescimento(),
                arvorePai.getDiametroMAX(),
                posicaoQueda
        );
    }

    public double[] calcularQueda() {
        double[] centro = arvorePai.getCentro();
        double maxX = centro[0] + (arvorePai.getDiametro() / 2);
        double minX = centro[0] - (arvorePai.getDiametro() / 2);

        double maxY = centro[1] + (arvorePai.getDiametro() / 2);
        double minY = centro[1] - (arvorePai.getDiametro() / 2);

        double x = (Math.random() * (maxX - minX + 1)) + (int)minX;
        double y = (Math.random() * (maxY - minY + 1)) + (int)minY;
        double[] novoCentro = {x, y};

        return novoCentro;
    }

    public float getChanceGerminar() {
        return chanceGerminar;
    }

    public void setChanceGerminar(float chanceGerminar) {
        this.chanceGerminar = chanceGerminar;
    }

    public int getTempoGerminar() {
        return tempoGerminar;
    }

    public void setTempoGerminar(int tempoGerminar) {
        this.tempoGerminar = tempoGerminar;
    }

    public Arvore getArvorePai() {
        return arvorePai;
    }

    public void setArvorePai(Arvore arvorePai) {
        this.arvorePai = arvorePai;
    }

    public double[] getPosicaoQueda() {
        return posicaoQueda;
    }

    public void setPosicaoQueda(double[] posicaoQueda) {
        this.posicaoQueda = posicaoQueda;
    }
}
