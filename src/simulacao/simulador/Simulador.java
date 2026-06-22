package simulacao.simulador;

import javax.swing.Timer;

import jakarta.persistence.*;
import simulacao.Visuais.Janela;
import simulacao.Visuais.PainelGrafico;
import simulacao.ambiente.Ambiente;
import simulacao.persistencia.SimuladorRepository;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "simulacao")
public class Simulador {
    @Transient
    private Ambiente ambiente;
    @Transient
    private Janela janela;
    @Transient
    private PainelGrafico painelGrafico;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "simulacao_id")
    private Simulacao simulacao;

    private int passoAtual = 0;
    private int maxPassos;

    @Column(nullable = false)
    private String nome;

    @OneToMany(
            mappedBy = "simulador",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<EstatisticasIteracao> historico;

    @Transient
    private SimuladorRepository repository =
            new SimuladorRepository();

    public Simulador() {};

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
//                mostrarEstatisticas();
                repository.salvar(this);
                return;
            }

//            System.out.println("Passo " + (passoAtual + 1));

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
                        ambiente.getSementes().size(),
                        this
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
