package br.com.stefanini.cepapi.consulta.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.stefanini.cepapi.consulta.client.ConsultaCepClient;
import br.com.stefanini.cepapi.consulta.model.DadosCepOutput;
import br.com.stefanini.cepapi.consulta.model.LogsConsultas;
import br.com.stefanini.cepapi.consulta.repository.LogsConsultasRepository;

@Service
public class ConsultaCepService {
	
	@Autowired
	private ConsultaCepClient client;
	@Autowired
	private LogsConsultasRepository logsRepository;
	private ObjectMapper mapper = new ObjectMapper();
	
	
	public DadosCepOutput consultarDadosCep(String cep) throws JsonProcessingException {
		DadosCepOutput dadosCep = client.obterDadosJson(cep);
		gravarLog(dadosCep, LocalDateTime.now());
		return dadosCep;
	}

	private void gravarLog(DadosCepOutput dadosCep, LocalDateTime dataHoraEvento) throws JsonProcessingException {
		String json = mapper.writeValueAsString(dadosCep);
		logsRepository.save(new LogsConsultas(null, json, dataHoraEvento));
	}
}
