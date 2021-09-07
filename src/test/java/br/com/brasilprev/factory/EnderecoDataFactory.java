package br.com.brasilprev.factory;

import br.com.brasilprev.pojo.Endereco;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDataFactory {

    public static  Endereco criarEnderecoDataBind() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Endereco endereco = objectMapper.readValue(new FileInputStream("src/test/resources/requestBody/postV1Endereco.json"), Endereco.class);

        return endereco;
    }

    public static Endereco criarEndereco() throws IOException {
        Endereco endereco = criarEnderecoDataBind();

        return endereco;
    }

    public static List<Endereco> criarEnderecos() throws IOException {
        Endereco endereco = EnderecoDataFactory.criarEndereco();

        List<Endereco> enderecos = new ArrayList<Endereco>();
        enderecos.add(endereco);

        return enderecos;
    }

    public static Endereco criarEnderecoSemDados() throws IOException {
        Endereco endereco = criarEnderecoDataBind();
        endereco.setLogradouro("");
        endereco.setBairro("");
        endereco.setCidade("");
        endereco.setEstado("");
        endereco.setNumero("");
        endereco.setComplemento("");

        return endereco;
    }

    public static Endereco criarEnderecoSemLogradouro() throws IOException {
        Endereco endereco = criarEnderecoDataBind();
        endereco.setLogradouro("");

        return endereco;
    }


}
