package br.com.stefanini.cepapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.stefanini.cepapi.consulta.service.ConsultaCepService;

@RestController
@RequestMapping("/cep")
public class ConsultaCepController {

	@Autowired
	private ConsultaCepService cepService;
	
	@GetMapping("{cep}")
	public ResponseEntity<?> buscarCep(@PathVariable String cep) throws JsonProcessingException {
		try {
			return ResponseEntity.ok(cepService.consultarDadosCep(cep));			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(String.format("Erro ao consultar CEP %s", cep));
		}
	}
}
