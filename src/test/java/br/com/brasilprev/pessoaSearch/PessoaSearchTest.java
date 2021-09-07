package br.com.brasilprev.pessoaSearch;

import br.com.brasilprev.config.Configuracoes;
import br.com.brasilprev.factory.PessoaDataFactory;
import br.com.brasilprev.factory.TelefoneDataFactory;
import br.com.brasilprev.pojo.Pessoa;
import br.com.brasilprev.pojo.Telefone;
import io.restassured.http.ContentType;
import org.aeonbits.owner.ConfigFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;


public class PessoaSearchTest {

    @Before
    public void setUp() {
        Configuracoes configuracoes = ConfigFactory.create(Configuracoes.class);
        baseURI = configuracoes.baseURI();
        port = configuracoes.port();
    }
	
    @Test
    public void deve_retornar_uma_mensagem_para_busca_de_telefone_inexistente() {

        Telefone telefone = TelefoneDataFactory.criarTelefoneComNumeroRandomico();

        given()
            .contentType(ContentType.JSON)
        .when()
            .get("pessoas/{ddd}/{numero}", telefone.getDdd(), telefone.getNumero())
        .then()
            .assertThat()
            .log().all()
            .statusCode(404)
            .body("erro", equalTo(String.format("Não existe pessoa com o telefone (%s)%s", telefone.getDdd(),telefone.getNumero())));
    }


    @Test
    public void deve_ser_possivel_buscar_um_usuario_pelo_telefone() throws Exception {
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

        given()
            .contentType(ContentType.JSON)
        .when()
            .get("pessoas/{ddd}/{numero}", telefone.get(0).getDdd(), telefone.get(0).getNumero())
        .then()
            .assertThat()
            .log().all()
            .statusCode(200)
            .body("cpf", equalTo(pessoa.getCpf()));
    }

}
