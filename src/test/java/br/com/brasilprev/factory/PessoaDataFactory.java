package br.com.brasilprev.factory;

import br.com.brasilprev.pojo.Endereco;
import br.com.brasilprev.pojo.Pessoa;
import br.com.brasilprev.pojo.Telefone;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;


public class PessoaDataFactory {

    private static Pessoa returnPessoaComNomeECpf(String nome, String cpf) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(RandomStringUtils.randomAlphabetic(4, 30));
        pessoa.setCpf(RandomStringUtils.randomNumeric(11));

        return pessoa;
    }

    public static Pessoa criarPessoaTest() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Pessoa pessoa = objectMapper.readValue(new FileInputStream("src/test/resources/requestBody/postV1PessoaTest.json"), Pessoa.class);

        return pessoa;
    }

    public static Pessoa criarPessoa() throws NullPointerException, IOException {
        Pessoa pessoa = returnPessoaComNomeECpf(RandomStringUtils.randomAlphabetic(4, 30), RandomStringUtils.randomNumeric(11));

        Telefone telefone = TelefoneDataFactory.criarTelefoneComNumeroExpecifico(RandomStringUtils.randomNumeric(2), RandomStringUtils.randomNumeric(9));

        List<Endereco> enderecos = EnderecoDataFactory.criarEnderecos();
        pessoa.setTelefones(telefone);
        pessoa.setEnderecos(enderecos);

        return pessoa;
    }

    public static Pessoa criarPessoaComDadosEmBranco() throws IOException {
        Pessoa pessoa = returnPessoaComNomeECpf("", "");

        Telefone telefone = TelefoneDataFactory.criarTelefoneComNumeroExpecifico("", "");

        Endereco endereco = EnderecoDataFactory.criarEnderecoSemDados();

        List<Endereco> enderecos = new ArrayList<Endereco>();
        enderecos.add(endereco);

        pessoa.setTelefones(telefone);
        pessoa.setEnderecos(enderecos);

        return pessoa;
    }

    public static Pessoa criarPessoaComTelefoneEspecifico(String ddd, String numero) throws IOException {
        Pessoa pessoa = returnPessoaComNomeECpf(RandomStringUtils.randomAlphabetic(4, 30), RandomStringUtils.randomNumeric(11));

        Telefone telefone = TelefoneDataFactory.criarTelefoneComNumeroExpecifico(ddd, numero);

        List<Endereco> enderecos = EnderecoDataFactory.criarEnderecos();

        pessoa.setTelefones(telefone);
        pessoa.setEnderecos(enderecos);

        return pessoa;
    }

    public static Pessoa criarPessoaComODddDoTelefoneEspecifico(String ddd) throws IOException {
        Pessoa pessoa = returnPessoaComNomeECpf(RandomStringUtils.randomAlphabetic(4, 30), RandomStringUtils.randomNumeric(11));

        Telefone telefone = TelefoneDataFactory.criarTelefoneComNumeroExpecifico(ddd, RandomStringUtils.randomNumeric(9));

        List<Endereco> enderecos = EnderecoDataFactory.criarEnderecos();

        pessoa.setTelefones(telefone);
        pessoa.setEnderecos(enderecos);

        return pessoa;
    }

    public static Pessoa criarPessoaComNumeroDoTelefoneEspecifico(String numero) throws IOException {
        Pessoa pessoa = returnPessoaComNomeECpf(RandomStringUtils.randomAlphabetic(4, 30), RandomStringUtils.randomNumeric(11));

        Telefone telefone = TelefoneDataFactory.criarTelefoneComNumeroExpecifico(RandomStringUtils.randomNumeric(2), numero);

        List<Endereco> enderecos = EnderecoDataFactory.criarEnderecos();

        pessoa.setTelefones(telefone);
        pessoa.setEnderecos(enderecos);

        return pessoa;
    }

    public static Pessoa criarPessoaComCpfEspecifico(String cpf) throws IOException {
        Pessoa pessoa = returnPessoaComNomeECpf(RandomStringUtils.randomAlphabetic(4, 30), cpf);

        Telefone telefone = TelefoneDataFactory.criarTelefoneComNumeroExpecifico(RandomStringUtils.randomNumeric(2), RandomStringUtils.randomNumeric(11));

        List<Endereco> enderecos = EnderecoDataFactory.criarEnderecos();

        pessoa.setTelefones(telefone);
        pessoa.setEnderecos(enderecos);

        return pessoa;
    }

}
