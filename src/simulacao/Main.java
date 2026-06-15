package simulacao;

import simulacao.Visuais.PainelGrafico;
import simulacao.ambiente.Ambiente;
import simulacao.disposicao.Disposicao;
import simulacao.modelo.Arvore;
import simulacao.modelo.Semente;
import simulacao.simulador.Simulacao;
import simulacao.simulador.Simulador;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Simulacao simulacao =
                new Simulacao(20.0, 0.5, 35, 1234, 25000, 20);

        Arvore arvoreBase = new Arvore(
                0,
                false,
                true,
                50,
                10,
                0.2,
                1.1,
                50.5,
                new double[]{0.0, 0.0}
        );

        Disposicao disposicao =
                new Disposicao(
                        25,
                        15,
                        50,
                        simulacao.getSeed(),
                        arvoreBase
                );

        List<Semente> sementescCirculo = disposicao.disporCirculo(10);
        List<Semente> sementesGrid = disposicao.disporGrid(5, 5);
        List<Semente> sementesEspiral = disposicao.disporEspiral();
        List<Semente> sementesAleatorio = disposicao.disporAleatorio();
        List<Semente> sementesClusters = disposicao.disporClusters(5);

        Ambiente ambiente1 =
                new Ambiente(
                        new ArrayList<>(),
                        sementescCirculo,
                        50,
                        simulacao
                );


        Ambiente ambiente2 =
                new Ambiente(
                        new ArrayList<>(),
                        sementesGrid,
                        50,
                        simulacao
                );


        Ambiente ambiente3 =
                new Ambiente(
                        new ArrayList<>(),
                        sementesEspiral,
                        50,
                        simulacao
                );


        Ambiente ambiente4 =
                new Ambiente(
                        new ArrayList<>(),
                        sementesAleatorio,
                        50,
                        simulacao
                );

        Ambiente ambiente5 =
                new Ambiente(
                        new ArrayList<>(),
                        sementesClusters,
                        50,
                        simulacao
                );

//
        Simulador simulador1 = new Simulador(ambiente1, simulacao, "Circulo");
        Simulador simulador2 = new Simulador(ambiente2, simulacao, "Grid");
        Simulador simulador3 = new Simulador(ambiente3, simulacao, "Espiral");
        Simulador simulador4 = new Simulador(ambiente4, simulacao, "Aleatorio");
        Simulador simulador5 = new Simulador(ambiente5, simulacao, "Clusters");


        simulador1.executar(100);
        simulador2.executar(100);
        simulador3.executar(100);
        simulador4.executar(100);
        simulador5.executar(100);

        PainelGrafico grafico =
                new PainelGrafico(List.of(simulador1, simulador2, simulador3, simulador4, simulador5));


        simulador1.setPainelGrafico(grafico);
        simulador2.setPainelGrafico(grafico);
        simulador3.setPainelGrafico(grafico);
        simulador4.setPainelGrafico(grafico);
        simulador5.setPainelGrafico(grafico);

    }
}