package simulacao.ambiente;

import simulacao.modelo.Arvore;
import simulacao.modelo.Semente;

import java.util.ArrayList;
import java.util.List;

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
        for (Arvore arvore: arvores) {
            float necessario = arvore.getEnergiaNecessaria();
            if (recurso >= necessario && arvore.isViva()) {
                recurso -= necessario;
                arvore.recebeRecurso(necessario); // Passa o recurso pra arvore
            }
            arvore.envelhecer();

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

            System.out.println(arvore);
            System.out.println("----");
            System.out.println("Arvore: " + arvore.hashCode());
            System.out.println("Centro: (" + arvore.getCentro()[0] + ", " + arvore.getCentro()[1] + ")");
            System.out.println("Raio e Diametro: " + (arvore.getDiametro() /2 ) + " " + arvore.getDiametro());
            System.out.println("Menor espaço: " + menorEspaco);
            System.out.println("----");
        }


        System.out.println("Recursos restantes: " + recurso);

        List<Arvore> novas = new ArrayList<>();
        List<Semente> remover = new ArrayList<>();

        for (Semente semente : sementes) {
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
