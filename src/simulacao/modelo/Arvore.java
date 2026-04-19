package simulacao.modelo;

public class Arvore {
    private int idade;
    private boolean madura;
    private boolean viva;
    private float energia;
    private float energiaNecessaria;
    private double diametro;
    private double taxaCrescimento;
    private double diametroMAX;
    private double[] centro;

    public Arvore(int idade, boolean madura, boolean viva, float energia,
                  float energiaNecessaria, double diametro, double crescimento,
                  double diametroMAX, double[] centro) {
        if (centro == null || centro.length != 2) {
            throw new IllegalArgumentException("O vetor da posição deve conter exatamente 2 números.");
        }
        this.idade = idade;
        this.madura = madura;
        this.viva = viva;
        this.energia = energia;
        this.energiaNecessaria = energiaNecessaria;
        this.diametro = diametro;
        this.taxaCrescimento = crescimento;
        this.diametroMAX = diametroMAX;
        this.centro = centro;
    }

    public void envelhecer() {
        energia -= energiaNecessaria;
        if (energia <= 0) {
            viva = false;
            return;
        }

        idade++;
        if (!madura && idade >= 10) {
            madura = true;
        }
    }

    public void crescer(double espacoDisponivel) {
        diametro += (taxaCrescimento * (1 - diametro / diametroMAX));
    }

    public void recebeRecurso(float recurso) {
        energia += recurso;
    }

    @Override
    public String toString() {
        String string = "";
        if (isViva()) {
            string = "Arvore com " + this.idade + " anos. Tendo armazenado: " + this.energia + " de energia. "
                    + "Com um diametro de " + this.diametro + ".";
        } else {
            string = "Arvore morta. Ela tinha " + this.idade + " anos e um diametro de " + this.diametro;
        }

        return string;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public boolean isMadura() {
        return madura;
    }

    public void setMadura(boolean madura) {
        this.madura = madura;
    }

    public boolean isViva() {
        return viva;
    }

    public void setViva(boolean viva) {
        this.viva = viva;
    }

    public float getEnergia() {
        return energia;
    }

    public void setEnergia(float energia) {
        this.energia = energia;
    }

    public float getEnergiaNecessaria() {
        return energiaNecessaria;
    }

    public void setEnergiaNecessaria(float energiaNecessaria) {
        this.energiaNecessaria = energiaNecessaria;
    }

    public double getDiametro() {
        return diametro;
    }

    public void setDiametro(double diametro) {
        this.diametro = diametro;
    }

    public double getTaxaCrescimento() {
        return taxaCrescimento;
    }

    public void setTaxaCrescimento(double taxaCrescimento) {
        this.taxaCrescimento = taxaCrescimento;
    }

    public double getDiametroMAX() {
        return diametroMAX;
    }

    public void setDiametroMAX(double diametroMAX) {
        this.diametroMAX = diametroMAX;
    }

    public double[] getCentro() {
        return centro;
    }

    public void setCentro(double[] centro) {
        this.centro = centro;
    }
}
