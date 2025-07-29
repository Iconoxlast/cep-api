package br.com.stefanini.cepapi.consulta.client;

import br.com.stefanini.cepapi.consulta.model.DadosCepOutput;

public interface ConsultaCepClient {
	DadosCepOutput obterDadosJson(String cep);
}
