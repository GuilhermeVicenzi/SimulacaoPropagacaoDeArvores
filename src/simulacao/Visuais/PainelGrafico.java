package simulacao.Visuais;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
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

        setTitle("Gráficos de Simulação");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new GridLayout(2, 1));

        add(criarGraficoPopulacao());
        add(criarGraficoEficiencia());

        setLocationRelativeTo(null);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private ChartPanel criarGraficoPopulacao() {
        dataset = new XYSeriesCollection();

        JFreeChart grafico = ChartFactory.createXYLineChart(
                "Crescimento da Floresta",
                "Iteração",
                "População",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        customizarEstiloGrafico(grafico, true);

        return new ChartPanel(grafico);
    }

    private ChartPanel criarGraficoEficiencia() {
        datasetEficiencia = new XYSeriesCollection();

        JFreeChart grafico = ChartFactory.createXYLineChart(
                "Eficiência da Floresta",
                "Iteração",
                "Área por Árvore",
                datasetEficiencia,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        customizarEstiloGrafico(grafico, false);

        return new ChartPanel(grafico);
    }

    private void customizarEstiloGrafico(JFreeChart grafico, boolean isGraficoPopulacao) {
        grafico.setTitle(new org.jfree.chart.title.TextTitle(
                grafico.getTitle().getText(),
                new Font("Segoe UI", Font.BOLD, 16)
        ));

        XYPlot plot = grafico.getXYPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(new Color(230, 230, 230));
        plot.setRangeGridlinePaint(new Color(230, 230, 230));
        plot.setOutlinePaint(null);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        Color[] coresDistribuicao = {
                new Color(27, 117, 89),   // Verde (Círculo)
                new Color(211, 84, 0),    // Laranja Escuro (Grid)
                new Color(41, 128, 185),  // Azul (Espiral)
                new Color(142, 68, 173),  // Roxo (Aleatório)
                new Color(44, 62, 80)     // Cinza Escuro (Clusters)
        };

        Stroke linhaContinua = new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

        Stroke linhaTracejada = new BasicStroke(
                1.5f,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND,
                1.0f,
                new float[]{4.0f, 4.0f},
                0.0f
        );

        for (int i = 0; i < 20; i++) {
            int indiceSimulador = isGraficoPopulacao ? (i / 2) : i;

            Color corDoSimulador = coresDistribuicao[indiceSimulador % coresDistribuicao.length];

            renderer.setSeriesShapesVisible(i, false);
            renderer.setSeriesPaint(i, corDoSimulador);

            if (isGraficoPopulacao) {
                if (i % 2 == 0) {
                    renderer.setSeriesStroke(i, linhaContinua);
                } else {
                    renderer.setSeriesStroke(i, linhaTracejada);
                }
            } else {
                renderer.setSeriesStroke(i, linhaContinua);
            }
        }

        plot.setRenderer(renderer);
    }

    public void atualizarGraficos() {
        atualizarPopulacao();
        atualizarEficiencia();
    }

    private void atualizarPopulacao() {
        dataset.setNotify(false);

        dataset.removeAllSeries();

        for (int i = 0; i < simuladores.size(); i++) {
            Simulador simulador = simuladores.get(i);
            XYSeries serieArvores = new XYSeries(simulador.getNome() + " - Árvores");
            XYSeries serieSementes = new XYSeries(simulador.getNome() + " - Sementes");

            for (EstatisticasIteracao e : simulador.getHistorico()) {
                serieArvores.add(e.getIteracao(), e.getQuantidadeArvores());
                serieSementes.add(e.getIteracao(), e.getQuantidadeSementes());
            }

            dataset.addSeries(serieArvores);
            dataset.addSeries(serieSementes);
        }

        dataset.setNotify(true);
    }

    private void atualizarEficiencia() {
        datasetEficiencia.setNotify(false);

        datasetEficiencia.removeAllSeries();

        for (int i = 0; i < simuladores.size(); i++) {
            Simulador simulador = simuladores.get(i);
            XYSeries serieEficiencia = new XYSeries(simulador.getNome());

            for (EstatisticasIteracao e : simulador.getHistorico()) {
                if (e.getQuantidadeArvores() > 0) {
                    double eficiencia = e.getAreaOcupada() / e.getQuantidadeArvores();
                    serieEficiencia.add(e.getIteracao(), eficiencia);
                }
            }
            datasetEficiencia.addSeries(serieEficiencia);
        }

        datasetEficiencia.setNotify(true);
    }
}