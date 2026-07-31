package br.com.testsoftware;

public class ValidadorChavePIX {

    String chavePix;

    private static final String CNPJ_SEGURO_PATTERN = "(^\\d{14}$)|(^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$)";

    public ValidadorChavePIX(String chavePix) {
        this.chavePix = chavePix;
    }

    public boolean validarCPF() {
        String cpf = this.chavePix;

        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");
        if (cpf.length()!=11) {
            throw new IllegalArgumentException("O CPF está inválido! Informe um novo CPF.");
        }
        for (int i = 1; i < 11; i++) {
            if (cpf.charAt(i)!= cpf.charAt(i-1)) {
                break;
            }else if (i==10) {
                throw new IllegalArgumentException("O CPF está inválido! Informe um novo CPF.");
            }
        }
        int soma = 0;
        int Dv1 = 0;
        int Dv2 = 0;
        String Dv = cpf.substring(10);
        for (int i = 0; i < 9; i++) {
            soma += (10-i)*Character.getNumericValue(cpf.charAt(i));
            System.out.println(soma);
        }
        Dv1 = soma%11;
        if (Dv1 < 2) {
            Dv1 = 0;
        } else {
            Dv1 = 11-Dv1;
        }
        System.out.println(Dv1);
        soma=0;
        cpf = cpf.substring(0,9);
        String cpfDv2 =cpf + Dv1;
        for (int i = 0; i < cpfDv2.length(); i++) {
            soma += (11-i)*Character.getNumericValue(cpfDv2.charAt(i));
        }
        Dv2 = soma%11;
        if (Dv2 < 2) {
            Dv2 = 0;
        } else {
            Dv2 = 11-Dv2;
        }
        System.out.println(Dv2);
        cpf += Dv1;
        cpf += Dv2;
        System.out.println(cpf);

        if (cpf.substring(10).equals(Dv)) {
            return true;
        } else {
            throw new IllegalArgumentException("O CPF está inválido! Informe um novo CPF.");
        }
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
