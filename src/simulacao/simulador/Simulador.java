package simulacao.simulador;

import javax.swing.Timer;
import simulacao.Visuais.Janela;
import simulacao.Visuais.PainelGrafico;
import simulacao.ambiente.Ambiente;

import java.util.ArrayList;
import java.util.List;

public class Simulador {
    Ambiente ambiente;
    Janela janela;
    PainelGrafico painelGrafico;

    private int passoAtual = 0;
    private int maxPassos;

    private List<EstatisticasIteracao> historico;

    public Simulador(Ambiente ambiente) {
        this.ambiente = ambiente;
        this.janela = new Janela(ambiente);
        this.historico = new ArrayList<EstatisticasIteracao>();
        this.painelGrafico = new PainelGrafico(this);
    }

    public void executar(int passos) {
        this.maxPassos = passos;

        Timer timer = new Timer(500, e -> {

            if (passoAtual >= maxPassos) {
                ((Timer)e.getSource()).stop();
                mostrarEstatisticas();
                return;
            }

            System.out.println("Passo " + (passoAtual + 1));

            ambiente.simularPassos();

            painelGrafico.atualizarGraficos();
            janela.atualizar();

            registrarEstatisticas();

            passoAtual++;
        });

        timer.start();

    }

    public void mostrarEstatisticas() {
        for (EstatisticasIteracao estatistica: historico) {
            System.out.println(estatistica);
        }
    }

    public void registrarEstatisticas() {
        historico.add(
                new EstatisticasIteracao(
                        passoAtual,
                        ambiente.getArea(),
                        ambiente.getAreaUsada(),
                        ambiente.getAreaLivre(),
                        ambiente.getArvores().size(),
                        ambiente.getSementes().size()
                )
        );
    }

    public List<EstatisticasIteracao> getHistorico() {
        return historico;
    }
}
