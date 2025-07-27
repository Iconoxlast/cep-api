package br.com.stefanini.cepapi.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestControllerAdvice
public class ExceptionHandlers {

	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<String> tratarErro400(HttpClientErrorException e) {
		return ResponseEntity.status(e.getStatusCode()).body("""
				{
					"erro": "CEP inválido"
				}
				""");
	}
	
	@ExceptionHandler(JsonProcessingException.class)
	public ResponseEntity<String> tratarExcecaoProcessamentoJson(JsonProcessingException e) {
		return ResponseEntity.internalServerError().body(e.getMessage());
	}
}
