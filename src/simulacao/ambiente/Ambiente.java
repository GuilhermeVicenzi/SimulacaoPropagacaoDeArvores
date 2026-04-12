package simulacao.ambiente;

import simulacao.modelo.Arvore;
import simulacao.modelo.Semente;

import java.util.ArrayList;
import java.util.List;

public class Ambiente {
    List<Arvore> arvores;
    List<Semente> sementes;

    int area;
    float recurso;

    public Ambiente(List<Arvore> arvores, List<Semente> sementes, int tamanho, float recurso) {
        this.arvores = arvores;
        this.sementes = sementes;
        this.area = tamanho;
        this.recurso = recurso;
    }

    public void simularPassos() {
        for (Arvore arvore: arvores) {
            float necessario = arvore.getEnergiaNecessaria();
            if (recurso >= necessario && arvore.isViva()) {
                recurso -= necessario;
                arvore.recebeRecurso(necessario); // Passa o recurso pra arvore
                System.out.println("Recursos restantes: " + recurso);
            }
            arvore.crescer();
            System.out.println(arvore);
            System.out.println("RECURSOS RESTANTES: " + recurso);
        }

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

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        if (area < 0) {
            System.out.println("Area negativa, não modificado");
        }
        this.area = area;
    }

    public float getRecurso() {
        return recurso;
    }

    public void setRecurso(float recurso) {
        this.recurso = recurso;
    }
}
