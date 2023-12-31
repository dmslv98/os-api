package com.exemplo.os.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.exemplo.os.domain.OS;
import com.exemplo.os.domain.enuns.Prioridade;
import com.exemplo.os.domain.enuns.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotEmpty;

public class OSDTO  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	
	private Integer id;	
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")   //formata dataAbertura
	private LocalDateTime dataAbertura;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")   //formata dataAbertura
	private LocalDateTime dataFechamento;
	
	
	
	//private LocalDateTime dataAbertura;	
	//private LocalDateTime dataFechamento;	
	
	private Integer prioridade;
	
	@NotEmpty(message = "O campo OBCERVAÇÕES é requerido!")
	private String observacoes;	
	
	private Integer status;
	
	//private Tecnico tecnico;
	//@NotEmpty(message = "O campo TÉCNICO é requerido!")
	private Integer tecnico;   //para receber apenas o id do tecnico
	
	
	//private Tecnico cliente;
	//@NotEmpty(message = "O campo CLIENTE é requerido!")
	private Integer cliente;   //para receber apenas o id do cliente
	
	//super class constructor - 
	public OSDTO() {
		super();		
	}

	//super class constructor - using fields
	/*
	public OSDTO(Integer id, LocalDateTime dataAbertura, LocalDateTime dataFechamento, Integer prioridade,
			String observacoes, Integer status, Tecnico tecnico, Tecnico cliente) {
		
	public OSDTO(OS obj ) {	
		super();
		this.id = id;
		this.dataAbertura = dataAbertura;
		this.dataFechamento = dataFechamento;
		this.prioridade = prioridade;
		this.observacoes = observacoes;
		this.status = status;
		this.tecnico = tecnico;		
		this.cliente = cliente;
	}	
	*/
	
	//Alterada a configuração acima para usar os parâmentros da classe OS ( OS obj), conforme abaixo.
	
	public OSDTO(OS obj ) {	
		super();
		this.id = obj.getId();
		this.dataAbertura = obj.getDataAbertura();
		this.dataFechamento = obj.getDataFechamento();
		this.prioridade = obj.getPrioridade().getCod();  //pegar o código
		this.observacoes = obj.getObservacoes();
		this.status = obj.getStatus().getCod();			//pegar o código
		this.tecnico = obj.getTecnico().getId();		//pegar o id
		this.cliente = obj.getCliente().getId();		//pegar o id
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Prioridade getPrioridade() {
		return Prioridade.toEnum(prioridade);
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Status getStatus() {
		return Status.toEnum(this.status);
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTecnico() {
		return tecnico;
	}

	public void setTecnico(Integer tecnico) {
		this.tecnico = tecnico;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}	
	
	

}
