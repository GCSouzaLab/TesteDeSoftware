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

    @Test
    public void cadastroChavePixCNPJInvalido(){}

    @Test
    public void tentativaCadastroEntradaMaliciosa(){
        String cpfMalicioso = "'' OR '1'='1'";

        ValidadorChavePIX validador = new ValidadorChavePIX(cpfMalicioso);
        Exception exception = Assert.assertThrows(IllegalArgumentException.class, validador::validarCPF);

        String mensagemEsperada = "O chave pix está inválida!";
        String mensagemAtual = exception.getMessage();
        Assert.assertEquals(mensagemEsperada, mensagemAtual);
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
