package simulacao.modelo;

import java.util.List;

public class Arvore {
    private int idade;
    private boolean madura;
    private boolean viva;
    private float energia;
    private float energiaNecessaria;
    private float diametro;
    private float crescimento;

    public Arvore(int idade, boolean madura, boolean viva, float energia,
                  float energiaNecessaria, float diametro, float crescimento) {
        this.idade = idade;
        this.madura = madura;
        this.viva = viva;
        this.energia = energia;
        this.energiaNecessaria = energiaNecessaria;
        this.diametro = diametro;
        this.crescimento = crescimento;
    }

    public void crescer() {
        if (!viva) {
            return;
        }

        idade++;
        energia -= energiaNecessaria;
        if (energia <= 0) {
            viva = false;
        }
        diametro += crescimento;
        if (!madura && idade >= 10) {
            madura = true;
        }
    }

    public void recebeRecurso(float recurso) {
        energia += recurso;
    }

    @Override
    public String toString() {
        String string = "";
        if (isViva()) {
            string = "Arvore com " + this.idade + " anos. Tendo armazenado: " + this.energia + " de energia. "
                    + "Com um diametro de " + this.diametro + ".";
        } else {
            string = "Arvore morta. Ela tinha " + this.idade + " anos e um diametro de " + this.diametro;
        }

        return string;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public boolean isMadura() {
        return madura;
    }

    public void setMadura(boolean madura) {
        this.madura = madura;
    }

    public boolean isViva() {
        return viva;
    }

    public void setViva(boolean viva) {
        this.viva = viva;
    }

    public float getEnergia() {
        return energia;
    }

    public void setEnergia(float energia) {
        this.energia = energia;
    }

    public float getEnergiaNecessaria() {
        return energiaNecessaria;
    }

    public void setEnergiaNecessaria(float energiaNecessaria) {
        this.energiaNecessaria = energiaNecessaria;
    }

    public float getDiametro() {
        return diametro;
    }

    public void setDiametro(float diametro) {
        this.diametro = diametro;
    }

    public float getCrescimento() {
        return crescimento;
    }

    public void setCrescimento(float crescimento) {
        this.crescimento = crescimento;
    }
}
