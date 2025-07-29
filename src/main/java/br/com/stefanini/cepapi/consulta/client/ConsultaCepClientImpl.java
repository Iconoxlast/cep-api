package br.com.stefanini.cepapi.consulta.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.stefanini.cepapi.consulta.model.DadosCepOutput;

@Component
public class ConsultaCepClientImpl implements ConsultaCepClient {
	
	@Autowired
	private RestTemplate restTemplate;

	@Value("${api.url}")
	private String endereco;
	
	public DadosCepOutput obterDadosJson(String cep) {
		return restTemplate.getForObject(getUrl(cep), DadosCepOutput.class);
	}
	
	private String getUrl(String cep) {
		return String.format("%s%s", endereco, cep.replace("-", ""));
	}
}
