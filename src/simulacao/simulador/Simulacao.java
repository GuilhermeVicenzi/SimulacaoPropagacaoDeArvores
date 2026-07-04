package simulacao.simulador;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "parametros")
public class Simulacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double diametroMax;
    private double distanciaMin;
    private int idadeMax;
    private int seed;
    private float recursoInicial;
    private int validadeSemente;

    @Transient
    private Random random;

    @OneToMany(mappedBy = "simulacao")
    private List<Simulador> simuladores = new ArrayList<>();

    public Simulacao() {}

    public Simulacao(double diametroMax, double distanciaMin, int idadeMax, int seed,
                     float recursoInicial, int validadeSemente) {
        this.diametroMax = diametroMax;
        this.distanciaMin = distanciaMin;
        this.idadeMax = idadeMax;
        this.seed = seed;
        this.random = new Random(seed);
        this.recursoInicial = recursoInicial;
        this.validadeSemente = validadeSemente;
    }

    @PostLoad
    private void inicializarRandom() {
        random = new Random(seed);
    }

    public void adicionarSimulador(Simulador simulador) {
        simuladores.add(simulador);
    }

    public double getDiametroMax() {
        return diametroMax;
    }

    public double getDistanciaMin() {
        return distanciaMin;
    }

    public int getIdadeMax() {
        return idadeMax;
    }

    public int getSeed() {
        return seed;
    }

    public Random getRandom() {
        if (random == null) {
            random = new Random(seed);
        }
        return random;
    }

    public float getRecursoInicial() {
        return recursoInicial;
    }

    public int getValidadeSemente() {
        return validadeSemente;
    }
}