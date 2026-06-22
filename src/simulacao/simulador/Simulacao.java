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
    private Random seed;
    private float recursoInicial;
    private int validadeSemente;

    @OneToMany(mappedBy = "simulacao")
    private List<Simulador> simuladores = new ArrayList<Simulador>();

    public Simulacao() {};

    public Simulacao(double diametroMax, double distanciaMin, int idadeMax, int seed, float recursoInicial, int validadeSemente) {
        this.diametroMax = diametroMax;
        this.distanciaMin = distanciaMin;
        this.idadeMax = idadeMax;
        this.seed = new Random(seed);
        this.recursoInicial = recursoInicial;
        this.validadeSemente = validadeSemente;
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

    public Random getSeed() {
        return seed;
    }

    public float getRecursoInicial() {
        return recursoInicial;
    }

    public int getValidadeSemente() {
        return validadeSemente;
    }
}
