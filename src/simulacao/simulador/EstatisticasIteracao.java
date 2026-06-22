package simulacao.simulador;

import jakarta.persistence.*;

@Entity
@Table(name = "estatisticas_iteracao")
public class EstatisticasIteracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int iteracao;

    private double areaTotal;
    private double areaOcupada;
    private double areaLivre;

    private int quantidadeArvores;
    private int quantidadeSementes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "simulador_id")
    private Simulador simulador;

    public EstatisticasIteracao() {
    }

    public EstatisticasIteracao(
            int iteracao,
            double areaTotal,
            double areaOcupada,
            double areaLivre,
            int quantidadeArvores,
            int quantidadeSementes,
            Simulador simulador) {

        this.iteracao = iteracao;
        this.areaTotal = areaTotal;
        this.areaOcupada = areaOcupada;
        this.areaLivre = areaLivre;
        this.quantidadeArvores = quantidadeArvores;
        this.quantidadeSementes = quantidadeSementes;
        this.simulador = simulador;
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
