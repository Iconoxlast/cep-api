package br.com.stefanini.cepapi.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestControllerAdvice
public class ExceptionHandlers {

	@ExceptionHandler(CepNaoEncontradoException.class)
	public ResponseEntity<String> tratarErro400(CepNaoEncontradoException e) {
		return ResponseEntity.badRequest().body(String.format("""
				{
					"erro": "%s"
				}
				""", e.getMessage()));
	}
	
	@ExceptionHandler(JsonProcessingException.class)
	public ResponseEntity<String> tratarExcecaoProcessamentoJson(JsonProcessingException e) {
		return ResponseEntity.internalServerError().body(e.getMessage());
	}
}
