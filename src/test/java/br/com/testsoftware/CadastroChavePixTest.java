package br.com.testsoftware;

import org.junit.Assert;
import org.junit.Test;

public class CadastroChavePixTest {

    @Test
    public void cadastroChavePixCPFValido(){
        String cpfValido = "97777857550";

        ValidadorChavePIX validador = new ValidadorChavePIX(cpfValido);
        boolean isCPFValido = validador.validarCPF();

        Assert.assertTrue(isCPFValido);
    }

    @Test()
    public void cadastroChavePixCNPJInvalido() {
        ValidadorChavePIX validador = new ValidadorChavePIX("00.000.0001/0000-00");

        boolean isCnpjValido = validador.validarCNPJ();

        Assert.assertFalse(isCnpjValido);
    }

    @Test(expected = SecurityException.class)
    public void tentativaCadastroEntradaMaliciosa(){
        String cpfMalicioso = "'' OR '1'='1'";

        ValidadorChavePIX validador = new ValidadorChavePIX(cpfMalicioso);
        validador.validarCNPJ();
    }

    @Test
    public void cadastroChavePixCNPJValido(){
        String chavePix = "34.577.439/0001-01";
        ValidadorChavePIX validador = new ValidadorChavePIX(chavePix);

        Assert.assertTrue(validador.validarCNPJ());
    }

    @Test
    public void cadastroChavePixCPFInvalido(){
        String cpfInvalido = "111.111.111-11";

        ValidadorChavePIX validador = new ValidadorChavePIX(cpfInvalido);
        Exception exception = Assert.assertThrows(IllegalArgumentException.class, validador::validarCPF);

        String mensagemEsperada = "O CPF está inválido! Informe um novo CPF.";
        String mensagemAtual = exception.getMessage();
        Assert.assertEquals(mensagemEsperada, mensagemAtual);
    }

}
