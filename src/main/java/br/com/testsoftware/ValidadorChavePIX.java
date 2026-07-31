package br.com.testsoftware;

public class ValidadorChavePIX {

    String chavePix;

    private static final String CNPJ_PATTERN = "[A-Z0-9]{12}[0-9]{2}";
    private static final int[] PESOS_PRIMEIRO_DIGITO = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] PESOS_SEGUNDO_DIGITO = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    public ValidadorChavePIX(String chavePix) {
        this.chavePix = chavePix;
    }

    public boolean validarCPF() {
        String cpf = this.chavePix;

        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");
        if (cpf.length() != 11) {
            throw new IllegalArgumentException("O CPF está inválido! Informe um novo CPF.");
        }
        for (int i = 1; i < 11; i++) {
            if (cpf.charAt(i) != cpf.charAt(i - 1)) {
                break;
            } else if (i == 10) {
                throw new IllegalArgumentException("O CPF está inválido! Informe um novo CPF.");
            }
        }
        int soma = 0;
        int Dv1 = 0;
        int Dv2 = 0;
        String Dv = cpf.substring(10);
        for (int i = 0; i < 9; i++) {
            soma += (10 - i) * Character.getNumericValue(cpf.charAt(i));
            System.out.println(soma);
        }
        Dv1 = soma % 11;
        if (Dv1 < 2) {
            Dv1 = 0;
        } else {
            Dv1 = 11 - Dv1;
        }
        System.out.println(Dv1);
        soma = 0;
        cpf = cpf.substring(0, 9);
        String cpfDv2 = cpf + Dv1;
        for (int i = 0; i < cpfDv2.length(); i++) {
            soma += (11 - i) * Character.getNumericValue(cpfDv2.charAt(i));
        }
        Dv2 = soma % 11;
        if (Dv2 < 2) {
            Dv2 = 0;
        } else {
            Dv2 = 11 - Dv2;
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

        verificarEntradaMaliciosa();

        String cnpj = normalizarChave(chavePix);

        if (!cnpj.matches(CNPJ_PATTERN)) {
            return false;
        }

        String baseCnpj = cnpj.substring(0, 12);
        String digitosInformados = cnpj.substring(12, 14);

        int primeiroDigitoCalculado = calcularDigitoCNPJ(baseCnpj, PESOS_PRIMEIRO_DIGITO);

        int segundoDigitoCalculado = calcularDigitoCNPJ(baseCnpj + primeiroDigitoCalculado, PESOS_SEGUNDO_DIGITO);

        String digitosCalculados = "" + primeiroDigitoCalculado + segundoDigitoCalculado;

        return digitosCalculados.equals(digitosInformados);
    }

    private String normalizarChave(String cnpjComMascara) {
        return cnpjComMascara.trim().toUpperCase().replace(".", "").replace("/", "").replace("-", "");
    }

    private int calcularDigitoCNPJ(String base, int[] pesos) {
        int soma = 0;

        for (int i = 0; i < base.length(); i++) {
            char caractere = base.charAt(i);
            int valor = caractere - '0';

            soma = soma + valor * pesos[i];
        }

        int resto = soma % 11;

        if (resto == 0 || resto == 1) {
            return 0;
        }

        return 11 - resto;
    }

    private void verificarEntradaMaliciosa() {
        if (chavePix == null) {
            return;
        }

        String entrada = chavePix.trim();

        if (!entrada.matches("[A-Za-z0-9./-]+")) {
            throw new SecurityException(
                    "A chave PIX está inválida!"
            );
        }
    }
}
