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
    Simulacao simulacao;

    private int passoAtual = 0;
    private int maxPassos;
    private String nome;

    private List<EstatisticasIteracao> historico;

    public Simulador(Ambiente ambiente, Simulacao simulacao, String nome) {
        this.ambiente = ambiente;
        this.janela = new Janela(ambiente);
        this.historico = new ArrayList<EstatisticasIteracao>();
        this.painelGrafico = null;
        this.simulacao = simulacao;
        this.nome = nome;
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

            if (painelGrafico != null) {
                painelGrafico.atualizarGraficos();
            }
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

    public PainelGrafico getPainelGrafico() {
        return painelGrafico;
    }

    public void setPainelGrafico(PainelGrafico painelGrafico) {
        this.painelGrafico = painelGrafico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
