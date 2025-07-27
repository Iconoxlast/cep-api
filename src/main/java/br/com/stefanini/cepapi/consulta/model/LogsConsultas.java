package br.com.stefanini.cepapi.consulta.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Table(name = "logs_consultas")
@Entity(name = "LogsConsultas")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "iid")
public class LogsConsultas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long iid;
	@SuppressWarnings("unused")
	private String cconteudo;
	@SuppressWarnings("unused")
	private LocalDateTime devento;

}
