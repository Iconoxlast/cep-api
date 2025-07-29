package br.com.stefanini.cepapi.consulta.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.stefanini.cepapi.consulta.client.ConsultaCepClient;
import br.com.stefanini.cepapi.consulta.model.DadosCepOutput;
import br.com.stefanini.cepapi.consulta.model.LogsConsultas;
import br.com.stefanini.cepapi.consulta.repository.LogsConsultasRepository;
import br.com.stefanini.cepapi.infra.CepNaoEncontradoException;

@Service
public class ConsultaCepServiceImpl implements ConsultaCepService {
	
	@Autowired
	private ConsultaCepClient client;
	@Autowired
	private LogsConsultasRepository logsRepository;
	private ObjectMapper mapper = new ObjectMapper();
	
	
	public DadosCepOutput consultarDadosCep(String cep) throws JsonProcessingException {
		String dados = "";
		try {
			DadosCepOutput dadosCep = client.obterDadosJson(cep);
			dados = mapper.writeValueAsString(dadosCep);
			return dadosCep;
		} catch (HttpClientErrorException e) {
			dados = String.format("Erro ao consultar CEP %s; invalido ou indisponivel", cep);
			throw new CepNaoEncontradoException(dados);
		} finally {
			gravarLog(dados, LocalDateTime.now());			
		}
	}

	private void gravarLog(String dadosRetorno, LocalDateTime dataHoraEvento) throws JsonProcessingException {
		String json = mapper.writeValueAsString(dadosRetorno);
		logsRepository.save(new LogsConsultas(null, json, dataHoraEvento));
	}
}
