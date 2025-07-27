package br.com.stefanini.cepapi.consulta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.stefanini.cepapi.consulta.model.LogsConsultas;

public interface LogsConsultasRepository extends JpaRepository<LogsConsultas, Long> {

}
