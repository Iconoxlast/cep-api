package br.com.stefanini.cepapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import br.com.stefanini.cepapi.consulta.model.DadosCepOutput;

@SpringBootTest
class CepApiApplicationTests {

	private static WireMockServer wireMockServer;

	@BeforeAll
	public static void setupServer() {
		wireMockServer = new WireMockServer(8082);
		wireMockServer.start();
		WireMock.configureFor(8082);
	}

	@Test
	void retornarCep09080001() {
		wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo("/cep/09080001")).willReturn(WireMock.aResponse().withHeader("Content-Type", "application/json").withBody("""
		{
			"cep": "09080-001",
			"logradouro": "Avenida Dom Pedro II",
			"complemento": "de 1482 ao fim - lado par",
			"unidade": "",
			"bairro": "Campestre",
			"localidade": "Santo André",
			"uf": "SP",
			"estado": "São Paulo",
			"regiao": "Sudeste",
			"ibge": "3547809",
			"gia": "6269",
			"ddd": "11",
			"siafi": "7057"
		}
				""")));
		DadosCepOutput resposta = restTemplate().getForObject("http://localhost:8082/cep/09080001", DadosCepOutput.class);
		assertEquals("09080-001", resposta.cep());
		assertEquals("Avenida Dom Pedro II", resposta.logradouro());
	}
	
	@AfterAll
	public static void stopServer() {
		wireMockServer.stop();
	}

	@Bean
	public TestRestTemplate restTemplate() {
		return new TestRestTemplate();
	}

}
