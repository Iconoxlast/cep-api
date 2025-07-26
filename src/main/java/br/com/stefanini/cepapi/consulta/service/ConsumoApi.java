package br.com.stefanini.cepapi.consulta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ConsumoApi {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public <T> T obterDadosJson(String url, Class<T> classeResposta) {
		return restTemplate.getForObject(url, classeResposta);
	}
}
