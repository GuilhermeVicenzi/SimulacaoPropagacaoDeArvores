package simulacao.simulador;

public class EstatisticasIteracao {

    private int iteracao;

    private double areaTotal;
    private double areaOcupada;
    private double areaLivre;

    private int quantidadeArvores;
    private int quantidadeSementes;

    public EstatisticasIteracao(
            int iteracao,
            double areaTotal,
            double areaOcupada,
            double areaLivre,
            int quantidadeArvores,
            int quantidadeSementes) {

        this.iteracao = iteracao;
        this.areaTotal = areaTotal;
        this.areaOcupada = areaOcupada;
        this.areaLivre = areaLivre;
        this.quantidadeArvores = quantidadeArvores;
        this.quantidadeSementes = quantidadeSementes;
    }

    @Override
    public String toString() {
        return "EstatisticasIteracao{" +
                "iteracao=" + iteracao +
                ", areaTotal=" + areaTotal +
                ", areaOcupada=" + areaOcupada +
                ", areaLivre=" + areaLivre +
                ", quantidadeArvores=" + quantidadeArvores +
                ", quantidadeSementes=" + quantidadeSementes +
                '}';
    }

    public int getIteracao() {
        return iteracao;
    }

    public void setIteracao(int iteracao) {
        this.iteracao = iteracao;
    }

    public double getAreaTotal() {
        return areaTotal;
    }

    public void setAreaTotal(double areaTotal) {
        this.areaTotal = areaTotal;
    }

    public double getAreaOcupada() {
        return areaOcupada;
    }

    public void setAreaOcupada(double areaOcupada) {
        this.areaOcupada = areaOcupada;
    }

    public double getAreaLivre() {
        return areaLivre;
    }

    public void setAreaLivre(double areaLivre) {
        this.areaLivre = areaLivre;
    }

    public int getQuantidadeArvores() {
        return quantidadeArvores;
    }

    public void setQuantidadeArvores(int quantidadeArvores) {
        this.quantidadeArvores = quantidadeArvores;
    }

    public int getQuantidadeSementes() {
        return quantidadeSementes;
    }

    public void setQuantidadeSementes(int quantidadeSementes) {
        this.quantidadeSementes = quantidadeSementes;
    }
}
