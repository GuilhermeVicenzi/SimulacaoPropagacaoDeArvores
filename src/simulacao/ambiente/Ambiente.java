package simulacao.ambiente;

import simulacao.modelo.Arvore;
import simulacao.modelo.Semente;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ambiente {
    List<Arvore> arvores;
    List<Semente> sementes;

    double raio;
    float recurso;

    public Ambiente(List<Arvore> arvores, List<Semente> sementes, double raio, float recurso) {
        this.arvores = arvores;
        this.sementes = sementes;
        this.raio = raio;
        this.recurso = recurso;
    }

    public void simularPassos() {
        List<Arvore> decompostas = new ArrayList<>();
        for (Arvore arvore: arvores) {
            float necessario = arvore.getEnergiaNecessaria();
            if (recurso >= necessario && arvore.isViva()) {
                recurso -= necessario;
                arvore.recebeRecurso(necessario); // Passa o recurso pra arvore
            }
            arvore.envelhecer();

            if (arvore.decomposta()) {
                decompostas.add(arvore);
                continue;
            }

            if (!arvore.isViva()) {
                arvore.decompor();
                arvore.liberaRecurso(this);
                continue;
            }

            double menorEspaco = Double.MAX_VALUE;

            for (Arvore b : arvores) {
                if (arvore == b) continue;

                double dx = arvore.getCentro()[0] - b.getCentro()[0];
                double dy = arvore.getCentro()[1] - b.getCentro()[1];
                double dist = Math.sqrt(dx*dx + dy*dy);

                double raioA = arvore.getDiametro() / 2;
                double raioB = b.getDiametro() / 2;

                double espaco = dist - raioA - raioB;
                menorEspaco = Math.min(espaco, menorEspaco);
            }

            double espacoBorda = calcularEspacoBorda(arvore);
            menorEspaco = Math.min(menorEspaco, espacoBorda);

            if (menorEspaco > 0) {
                arvore.crescer(menorEspaco);
            }

            Random r = new Random();
            if (arvore.getIdade() >= 5 && r.nextDouble() < 0.3) {
                tentarDispersarSemente(arvore);
            }

            System.out.println(arvore);
            System.out.println("----");
            System.out.println("Arvore: " + arvore.hashCode());
            System.out.println("Centro: (" + arvore.getCentro()[0] + ", " + arvore.getCentro()[1] + ")");
            System.out.println("Raio e Diametro: " + (arvore.getDiametro() /2 ) + " " + arvore.getDiametro());
            System.out.println("Menor espaço: " + menorEspaco);
            System.out.println("----");
        }

        arvores.removeAll(decompostas);

        System.out.println("Recursos restantes: " + recurso);

        List<Arvore> novas = new ArrayList<>();
        List<Semente> remover = new ArrayList<>();

        for (Semente semente : sementes) {

            double[] posicao = semente.getPosicaoQueda();
            double raioNovaArvore = 0.3;

            if (!posicaoValida(posicao, raioNovaArvore)) {
                remover.add(semente);
                recurso += 2;
                System.out.println("Posição ficou inválida devido ao crescimento de outras árvores, semente absorvida");
                continue;
            }


            if (semente.tentarGerminar()) {
                novas.add(semente.germinar());
                remover.add(semente);
                System.out.println("Uma semente germinou, uma nova arvore comecará a crescer.");
            }
        }

        sementes.removeAll(remover);
        arvores.addAll(novas);
    }

    public double calcularEspacoBorda(Arvore a) {
        double dx = - a.getCentro()[0];
        double dy = - a.getCentro()[1];

        double distCentro = Math.sqrt(dx*dx + dy*dy);
        return raio - (distCentro + (a.getDiametro() / 2));
    }

    public boolean posicaoValida(double[] posicao, double raioNovaArvore) {

        double x = posicao[0];
        double y = posicao[1];


        double distanciaCentro = Math.sqrt(x*x + y*y);
        if (distanciaCentro + raioNovaArvore > raio) {
            return false;
        }

        for (Arvore arvore : arvores) {

            double dx = x - arvore.getCentro()[0];
            double dy = y - arvore.getCentro()[1];

            double distancia = Math.sqrt(dx * dx + dy * dy);

            if (distancia < (arvore.getDiametro() / 2) + raioNovaArvore) {
                return false;
            }
        }

        return true;
    }

    public void tentarDispersarSemente(Arvore mae) {

        Random r = new Random();
        int maxTentativas = 5;
        double alcanceMaximo = 25;

        for (int i = 0; i < maxTentativas; i++) {
            double angulo = r.nextDouble() * Math.PI * 2;
            double distancia = r.nextDouble() * r.nextDouble() * alcanceMaximo;
            double x = mae.getCentro()[0] + Math.cos(angulo) * distancia;
            double y = mae.getCentro()[1] + Math.sin(angulo) * distancia;
            double[] posicao = {x, y};
            double raioNovaArvore = 0.1;

            if (posicaoValida(posicao, raioNovaArvore)) {
                Semente s = new Semente(0.5F, 5, mae);
                s.setPosicaoQueda(posicao);

                sementes.add(s);
                return;
            }
        }
    }

    public void adicionarRecurso(float adicionar) {
        this.recurso += adicionar;
    }

    public double getArea() {
        return raio * raio * 3.14;
    }

    public double getAreaUsada() {
        double total = 0;
        for (Arvore a : arvores) {
            total += a.getAreaOcupada();
        }
        return total;
    }

    public double getAreaLivre() {
        double total = getArea();

        for (Arvore a : arvores) {
            total -= a.getAreaOcupada();
        }
        return total;
    }

    public List<Arvore> getArvores() {
        return arvores;
    }

    public void setArvores(List<Arvore> arvores) {
        this.arvores = arvores;
    }

    public List<Semente> getSementes() {
        return sementes;
    }

    public void setSementes(List<Semente> sementes) {
        this.sementes = sementes;
    }

    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) {
        if (raio < 0) {
            System.out.println("Raio negativo, não modificado");
        }
        this.raio = raio;
    }

    public float getRecurso() {
        return recurso;
    }

    public void setRecurso(float recurso) {
        this.recurso = recurso;
    }
}
