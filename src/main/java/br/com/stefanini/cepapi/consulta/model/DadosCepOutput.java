package br.com.stefanini.cepapi.consulta.model;

public record DadosCepOutput(String cep, String logradouro, String complemento, String unidade, String bairro,
		String localidade, String uf, String estado, String regiao, String ibge, String gia, String ddd, String siafi) {

}
