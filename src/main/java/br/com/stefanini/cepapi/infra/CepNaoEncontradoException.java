package br.com.stefanini.cepapi.infra;

public class CepNaoEncontradoException extends RuntimeException {
	private static final long serialVersionUID = 8586547165388320458L;
	
	public CepNaoEncontradoException(String message) {
		super(message);
	}

}
