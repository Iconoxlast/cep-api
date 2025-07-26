package br.com.stefanini.cepapi.consulta.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.stefanini.cepapi.consulta.dto.DadosCepOutput;
import br.com.stefanini.cepapi.consulta.persistence.LogsConsultas;
import br.com.stefanini.cepapi.consulta.persistence.LogsConsultasRepository;

@Service
public class ConsultaCepService {
	
	@Autowired
	private ConsumoApi consumoApi;
	@Autowired
	private LogsConsultasRepository logsRepository;
	private ObjectMapper mapper = new ObjectMapper();
	
	@Value("${api.url}")
	private String endereco;
	
	public DadosCepOutput consultarDadosCep(String cep) throws JsonProcessingException {
		DadosCepOutput dadosCep = consumoApi.obterDadosJson(getUrl(cep), DadosCepOutput.class);
		gravarLog(dadosCep, LocalDateTime.now());
		return dadosCep;
	}
	
	private String getUrl(String cep) {
		return String.format("%s%s/json/", endereco, cep.replace("-", ""));
	}

	private void gravarLog(DadosCepOutput dadosCep, LocalDateTime dataHoraEvento) throws JsonProcessingException {
		String json = mapper.writeValueAsString(dadosCep);
		logsRepository.save(new LogsConsultas(null, json, dataHoraEvento));
	}
}
