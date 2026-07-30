package br.com.testsoftware;

public class ValidadorChavePIX {

    String chavePix;

    public ValidadorChavePIX(String chavePix) {
        this.chavePix = chavePix;
    }

    public boolean validarCPF() {
        if (chavePix == null) throw new IllegalArgumentException("Mensagem...");
        return true;
    }


    public boolean validarCNPJ() {
        if (chavePix == null) throw new IllegalArgumentException("Mensagem...");
        return true;
    }
}
