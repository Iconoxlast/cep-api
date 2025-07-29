package br.com.stefanini.cepapi.consulta.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.stefanini.cepapi.consulta.model.DadosCepOutput;

public interface ConsultaCepService {
	DadosCepOutput consultarDadosCep(String cep) throws JsonProcessingException;
}
