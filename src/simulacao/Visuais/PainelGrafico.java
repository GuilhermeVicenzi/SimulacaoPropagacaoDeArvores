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
import java.util.List;

public class PainelGrafico extends JFrame {

    private List<Simulador> simuladores;

    private XYSeriesCollection dataset;
    private XYSeriesCollection datasetEficiencia;

    public PainelGrafico(List<Simulador> simuladores) {

        this.simuladores = simuladores;

        setTitle("Gráficos");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new GridLayout(2, 1));

        add(criarGraficoPopulacao());
        add(criarGraficoEficiencia());

        setVisible(true);
    }

    private ChartPanel criarGraficoPopulacao() {

        dataset = new XYSeriesCollection();

        JFreeChart grafico = ChartFactory.createXYLineChart(
                "Crescimento da Floresta",
                "Iteração",
                "População",
                dataset
        );

        return new ChartPanel(grafico);
    }

    private ChartPanel criarGraficoEficiencia() {

        datasetEficiencia = new XYSeriesCollection();

        JFreeChart grafico = ChartFactory.createXYLineChart(
                "Eficiência da Floresta",
                "Iteração",
                "Área por Árvore",
                datasetEficiencia
        );

        return new ChartPanel(grafico);
    }

    public void atualizarGraficos() {

        atualizarPopulacao();
        atualizarEficiencia();
    }

    private void atualizarPopulacao() {

        dataset.removeAllSeries();

        for (int i = 0; i < simuladores.size(); i++) {

            Simulador simulador = simuladores.get(i);

            XYSeries serieArvores =
                    new XYSeries(
                            simulador.getNome() + " - Árvores");

            XYSeries serieSementes =
                    new XYSeries(
                            simulador.getNome() + " - Sementes");

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

            dataset.addSeries(serieArvores);
            dataset.addSeries(serieSementes);
        }
    }

    private void atualizarEficiencia() {

        datasetEficiencia.removeAllSeries();

        for (int i = 0; i < simuladores.size(); i++) {

            Simulador simulador = simuladores.get(i);

            XYSeries serieEficiencia =
                    new XYSeries(
                            simulador.getNome());
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

            datasetEficiencia.addSeries(serieEficiencia);
        }
    }
}