package simulacao.entrada;

import simulacao.simulador.Simulacao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LeitorEntrada {

    public static List<Simulacao> lerSimulacoes(String caminho)
            throws IOException {

        List<Simulacao> simulacoes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(
                new FileReader(caminho))) {

            String linha;

            // pula o cabeçalho
            br.readLine();

            while ((linha = br.readLine()) != null) {

                if (linha.isBlank()) {
                    continue;
                }

                String[] dados = linha.split(",");

                double diametroMax =
                        Double.parseDouble(dados[0]);

                double distanciaMin =
                        Double.parseDouble(dados[1]);

                int idadeMax =
                        Integer.parseInt(dados[2]);

                int seed =
                        Integer.parseInt(dados[3]);

                float recursoInicial =
                        Float.parseFloat(dados[4]);

                int validadeSemente =
                        Integer.parseInt(dados[5]);

                Simulacao simulacao = new Simulacao(
                        diametroMax,
                        distanciaMin,
                        idadeMax,
                        seed,
                        recursoInicial,
                        validadeSemente
                );

                simulacoes.add(simulacao);
            }
        }

        return simulacoes;
    }
}
