package simulacao.Visuais;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import simulacao.simulador.EstatisticasIteracao;
import simulacao.simulador.Simulador;

import javax.swing.*;
import java.awt.*;

public class PainelGrafico extends JFrame {

    private Simulador simulador;

    private XYSeries serieArvores;
    private XYSeries serieSementes;
    private XYSeriesCollection dataset;

    private XYSeries serieEficiencia;
    private XYSeriesCollection datasetEficiencia;

    public PainelGrafico(Simulador simulador) {

        this.simulador = simulador;

        setTitle("Gráficos");
        setSize(1000, 800);

        setLayout(new GridLayout(2, 1));

        add(criarGraficoPopulacao());
        add(criarGraficoEficiencia());


        setVisible(true);
    }

    private ChartPanel criarGraficoPopulacao() {

        serieArvores = new XYSeries("Árvores");
        serieSementes = new XYSeries("Sementes");

        dataset = new XYSeriesCollection();
        dataset.addSeries(serieArvores);
        dataset.addSeries(serieSementes);

        JFreeChart grafico = ChartFactory.createXYLineChart(
                "Crescimento da Floresta",
                "Iteração",
                "População",
                dataset
        );

        return new ChartPanel(grafico);
    }

    private ChartPanel criarGraficoEficiencia() {

        serieEficiencia = new XYSeries("Eficiência Espacial");

        datasetEficiencia = new XYSeriesCollection();
        datasetEficiencia.addSeries(serieEficiencia);

        JFreeChart grafico = ChartFactory.createXYLineChart(
                "Eficiência da Floresta",
                "Iteração",
                "Área por Árvore",
                datasetEficiencia
        );

        return new ChartPanel(grafico);
    }

    public void atualizarGraficos() {

        serieArvores.clear();
        serieSementes.clear();

        for (EstatisticasIteracao e : simulador.getHistorico()) {

            serieArvores.add(
                    e.getIteracao(),
                    e.getQuantidadeArvores()
            );

            serieSementes.add(
                    e.getIteracao(),
                    e.getQuantidadeSementes()
            );
        }

        serieEficiencia.clear();

        for (EstatisticasIteracao e : simulador.getHistorico()) {

            if (e.getQuantidadeArvores() > 0) {

                double eficiencia =
                        e.getAreaOcupada() /
                                e.getQuantidadeArvores();

                serieEficiencia.add(
                        e.getIteracao(),
                        eficiencia
                );
            }
        }

    }
}