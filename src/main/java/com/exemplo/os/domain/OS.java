package com.exemplo.os.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import com.exemplo.os.domain.enuns.Prioridade;
import com.exemplo.os.domain.enuns.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OS {
	
	//define os atributos
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")   //formata dataAbertura
	private LocalDateTime dataAbertura;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")   //formata dataAbertura
	private LocalDateTime dataFechamento;
	//private Prioridade prioridade;
	private Integer prioridade;
	private String observacoes;
	//private Status status;
	private Integer status;
	
	@ManyToOne	//muitas OS para o mesmo tecnico				
	@JoinColumn(name = "tecnico_id")  //nome da coluna tecnico na tabela de OS
	private Tecnico tecnico;
	
	@ManyToOne  //muitas OS para o mesmo cliente
	@JoinColumn(name = "cliente_id")  //nome da coluna cliente na tabela de OS
	private Cliente cliente;
	
	//contrutor da super class (sem os parametros)
	public OS() {
		super();	
		this.setDataAbertura(LocalDateTime.now()); //pegar a data de abertura automaticamnete assim que a OS for criada.
		this.setPrioridade(Prioridade.BAIXA);	//setar para prioridade inicial BAIXA assim que a OS for criada.
		this.setStatus(Status.ABERTA);   //setar para status inicial ABERTA assim que a OS for criada.
	}
	
	//contrutor da super class (com os parametros)
	//public OS(Integer id, LocalDateTime dataAbertura, LocalDateTime dataFechamento, Prioridade prioridade,
	//		String observacoes, Status status, Tecnico tecnico, Tecnico cliente) {
	public OS(Integer id, Prioridade prioridade,
			String observacoes, Status status, Tecnico tecnico, Cliente cliente) {	
		super();
		this.id = id;
		//this.dataAbertura = dataAbertura;
		this.setDataAbertura(LocalDateTime.now()); //pegar a data de abertura automaticamnete assim que a OS for criada.
		//this.dataFechamento = dataFechamento;   //será setada qunado fechar a OS
		//this.prioridade = prioridade;
		this.prioridade = (prioridade == null) ? 0 : prioridade.getCod();   //operador ternario   
		this.observacoes = observacoes;
		//this.status = status;
		this.status = (status == null) ? 0 : status.getCod();   //operador ternario
		this.tecnico = tecnico;
		this.cliente = cliente;
	}
	

	//generate getters and setters
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
		//return prioridade;
		return Prioridade.toEnum(this.prioridade);
	}

	public void setPrioridade(Prioridade prioridade) {
		//this.prioridade = prioridade;
		this.prioridade = prioridade.getCod();
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Status getStatus() {
		//return status;
		return Status.toEnum(this.status);
	}

	public void setStatus(Status status) {
		//this.status = status;
		this.status = status.getCod();
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
	//generate hashCode() and iguals() apenas para o id
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OS other = (OS) obj;
		return Objects.equals(id, other.id);
	}	

}
