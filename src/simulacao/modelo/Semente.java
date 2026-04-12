package simulacao.modelo;

public class Semente {
    float chanceGerminar;
    int tempoGerminar;
    Arvore arvore;

    public Semente(float chanceGerminar, int tempoGerminar, Arvore arvore) {
        this.chanceGerminar = chanceGerminar;
        this.tempoGerminar = tempoGerminar;
        this.arvore = arvore;
    }

    public boolean tentarGerminar() {
        tempoGerminar--;
        if (tempoGerminar <= 0) {
            return Math.random() < chanceGerminar;
        }
        return false;
    }

    public Arvore germinar() {
        return new Arvore(
                0,
                false,
                true,
                10, // energia inicial
                arvore.getEnergiaNecessaria(),
                0.2F,
                arvore.getCrescimento()
        );
    }
}
