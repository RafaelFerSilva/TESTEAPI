package br.com.brasilprev.factory;

import br.com.brasilprev.pojo.Telefone;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TelefoneDataFactory {

    public static Telefone criarTelefoneDataBind() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Telefone telefone = objectMapper.readValue(new FileInputStream("src/test/resources/requestBody/postV1Telefone.json"), Telefone.class);

        return telefone;
    }

    public static Telefone criarTelefoneComNumeroExpecifico(String ddd, String numero) {
        Telefone telefone = new Telefone();
        telefone.setDdd(ddd);
        telefone.setNumero(numero);

        return telefone;
    }

    public static Telefone criarTelefones(String ddd, String numero) {
        Telefone fone = TelefoneDataFactory
                .criarTelefoneComNumeroExpecifico(ddd, numero);

        return fone;
    }

    public static Telefone criarTelefoneComNumeroRandomico() {
        Telefone telefone = new Telefone();
        telefone.setDdd(RandomStringUtils.randomNumeric(2));
        telefone.setNumero(RandomStringUtils.randomNumeric(9));

        return telefone;
    }
}
