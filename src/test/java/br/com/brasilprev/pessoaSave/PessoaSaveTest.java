package br.com.brasilprev.pessoaSave;

import br.com.brasilprev.config.Configuracoes;
import br.com.brasilprev.factory.PessoaDataFactory;
import br.com.brasilprev.factory.TelefoneDataFactory;
import br.com.brasilprev.pojo.Pessoa;
import br.com.brasilprev.pojo.Telefone;
import io.restassured.http.ContentType;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.not;


public class PessoaSaveTest {

    @Before
    public void setUp() {
        Configuracoes configuracoes = ConfigFactory.create(Configuracoes.class);
        baseURI = configuracoes.baseURI();
        port = configuracoes.port();
    }

    @Test
    public void deve_ser_possivel_salvar_uma_pessoa_com_todos_os_dados_corretos() throws Exception {
        Pessoa pessoa = PessoaDataFactory.criarPessoa();

        given()
            .contentType(ContentType.JSON)
            .body(pessoa)
        .when()
            .post("/pessoas")
        .then()
            .assertThat()
                .log().all()
                .statusCode(201);
    }

    @Test
    public void nao_deve_ser_possivel_salvar_uma_pessoa_com_todos_os_dados_em_branco() throws Exception {
        Pessoa pessoa = PessoaDataFactory.criarPessoaComDadosEmBranco();

        given()
            .contentType(ContentType.JSON)
            .body(pessoa)
        .when()
            .post("/pessoas")
        .then()
            .assertThat()
                .log().all()
                .statusCode(not(201));
    }

    @Test
    public void nao_deve_ser_possivel_salvar_uma_pessoa_com_o_telefone_em_branco() throws Exception {
        Pessoa pessoa = PessoaDataFactory.criarPessoaComTelefoneEspecifico("", "");

        given()
            .contentType(ContentType.JSON)
            .body(pessoa)
        .when()
            .post("/pessoas")
        .then()
            .assertThat()
                .log().all()
                .statusCode(not(201))
                .body("erro", not(equalTo(String.format("Já existe pessoa cadastrada com o Telefone (%s)%s",
                        pessoa.getTelefones().get(0).getDdd(),
                        pessoa.getTelefones().get(0).getNumero())
                )));
    }

    @Test
    public void nao_deve_ser_possivel_salvar_uma_pessoa_com_o_ddd_do_telefone_em_branco() throws Exception {
        Pessoa pessoa = PessoaDataFactory.criarPessoaComODddDoTelefoneEspecifico("");

        given()
            .contentType(ContentType.JSON)
            .body(pessoa)
        .when()
            .post("/pessoas")
        .then()
            .assertThat()
                .log().all()
                .statusCode(not(201));
    }


    @Test
    public void nao_deve_ser_possivel_salvar_uma_pessoa_com_o_telefone_invalido() throws Exception {
        Pessoa pessoa = PessoaDataFactory.criarPessoaComTelefoneEspecifico("00", "000000000");

        given()
            .contentType(ContentType.JSON)
            .body(pessoa)
        .when()
            .post("/pessoas")
        .then()
            .assertThat()
                .log().all()
                .statusCode(not(201))
                .body("erro", not(equalTo(String.format("Já existe pessoa cadastrada com o Telefone (%s)%s",
                        pessoa.getTelefones().get(0).getDdd(),
                        pessoa.getTelefones().get(0).getNumero())
                )));
    }

    @Test
    public void nao_deve_ser_possivel_salvar_uma_pessoa_com_o_telefone_que_seja_letras() throws Exception {
        Pessoa pessoa = PessoaDataFactory.criarPessoaComTelefoneEspecifico("00", RandomStringUtils.randomAlphabetic(9));

        given()
            .contentType(ContentType.JSON)
            .body(pessoa)
        .when()
            .post("/pessoas")
        .then()
            .assertThat()
                .log().all()
                .statusCode(not(201))
                .body("erro", not(equalTo(String.format("Já existe pessoa cadastrada com o Telefone (%s)%s",
                        pessoa.getTelefones().get(0).getDdd(),
                        pessoa.getTelefones().get(0).getNumero())
                )));
    }

    @Test
    public void nao_deve_salvar_duas_pessoas_com_o_mesmo_telefone() throws Exception {

        Pessoa pessoa = PessoaDataFactory.criarPessoa();

        given()
            .contentType(ContentType.JSON)
            .body(pessoa)
        .when()
            .post("/pessoas")
        .then()
                .assertThat()
                .log().all()
                .statusCode(201);

        List<Telefone> telefone =  pessoa.getTelefones();

        Pessoa pessoaTelefoneDuplicado = PessoaDataFactory
                .criarPessoaComTelefoneEspecifico(
                        telefone.get(0).getDdd(),
                        telefone.get(0).getNumero()
                );

        given()
            .contentType(ContentType.JSON)
            .body(pessoaTelefoneDuplicado)
        .when()
            .post("/pessoas")
        .then()
            .assertThat()
                .log().all()
                .statusCode(400)
                .body("erro", equalTo(
                                String.format("Já existe pessoa cadastrada com o Telefone (%s)%s",
                                        pessoa.getTelefones().get(0).getDdd(),
                                        pessoa.getTelefones().get(0).getNumero())
                        )
                );
    }

    @Test
    public void nao_deve_salvar_duas_pessoas_com_o_mesmo_cpf() throws Exception {
        Pessoa pessoa = PessoaDataFactory.criarPessoa();

        given()
            .contentType(ContentType.JSON)
            .body(pessoa)
        .when()
            .post("/pessoas")
        .then()
                .assertThat()
                .statusCode(201);

        given()
            .contentType(ContentType.JSON)
            .body(pessoa)
        .when()
            .post("/pessoas")
        .then()
            .assertThat()
                .log().all()
                .statusCode(400)
                .body("erro", equalTo(String.format("Já existe pessoa cadastrada com o CPF %s", pessoa.getCpf())));
    }

    @Test
    public void nao_deve_salvar_uma_pessoa_com_cpf_em_branco() throws Exception {
        Pessoa pessoa = PessoaDataFactory.criarPessoaComCpfEspecifico("");

        given()
            .contentType(ContentType.JSON)
            .body(pessoa)
        .when()
            .post("/pessoas")
        .then()
            .assertThat()
                .log().all()
                .statusCode(not(201))
                .body("erro", not(equalTo(String.format("Já existe pessoa cadastrada com o CPF %s", pessoa.getCpf()))));

    }

    @Test
    public void nao_deve_salvar_uma_pessoa_com_cpf_null() throws Exception {
        Pessoa pessoa = PessoaDataFactory.criarPessoaComCpfEspecifico(null);

        given()
            .contentType(ContentType.JSON)
        .body(pessoa)
            .when()
            .post("/pessoas")
        .then()
            .assertThat()
                .log().all()
                .statusCode(not(201))
                .body("erro", not(equalTo(String.format("Já existe pessoa cadastrada com o CPF %s", pessoa.getCpf()))));

    }

    @Test
    public void nao_deve_salvar_uma_pessoa_com_cpf_de_numero_invalido() throws Exception {
        Pessoa pessoa = PessoaDataFactory.criarPessoaComCpfEspecifico("99999999999");

        given()
            .contentType(ContentType.JSON)
            .body(pessoa)
        .when()
            .post("/pessoas")
        .then()
            .assertThat()
                .log().all()
                .statusCode(not(201))
                .body("erro", not(equalTo(String.format("Já existe pessoa cadastrada com o CPF %s", pessoa.getCpf()))));

    }

    @Test
    public void nao_deve_salvar_uma_pessoa_com_cpf_de_letras() throws Exception {

        Pessoa pessoa = PessoaDataFactory.criarPessoaComCpfEspecifico("ASDCXSDERFG");

        given()
            .contentType(ContentType.JSON)
            .body(pessoa)
        .when()
            .post("/pessoas")
        .then()
            .assertThat()
            .log().all()
            .statusCode(not(201))
            .body("erro", not(equalTo(String.format("Já existe pessoa cadastrada com o CPF %s", pessoa.getCpf()))));

    }

}