package br.com.testsoftware;

public class ValidadorChavePIX {

    String chavePix;

    private static final String CNPJ_SEGURO_PATTERN = "(^\\d{14}$)|(^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$)";

    public ValidadorChavePIX(String chavePix) {
        this.chavePix = chavePix;
    }

    public boolean validarCPF() {
        if (chavePix == null) throw new IllegalArgumentException("Mensagem...");
        return true;
    }


    public boolean validarCNPJ() {
        if (chavePix == null || !chavePix.matches(CNPJ_SEGURO_PATTERN))
            throw new SecurityException();

        String chaveNormalizada = normalizarChave(this.chavePix);



        return true;
    }

    private String normalizarChave(String cnpjComMascara) {
        return cnpjComMascara.replaceAll("\\D", "");
    }
}
