package com.exemplo.os.dtos;

import java.io.Serializable;

import org.hibernate.validator.constraints.br.CPF;

import com.exemplo.os.domain.Cliente;

import jakarta.validation.constraints.NotEmpty;

public class ClienteDTO implements Serializable {	
	private static final long serialVersinUID = 1L;	
	
	private Integer id;
	
	@NotEmpty(message ="Campo Nome é requerido")
	private String nome;
	
	@CPF
	@NotEmpty(message ="Campo CPF é requerido")
	private String cpf;
	
	@NotEmpty(message ="Campo Telefone é requerido")
	private String telefone;
	
	
	
	public ClienteDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//public ClienteDTO(Integer id, String nome, @CPF String cpf, String telefone) {
	public ClienteDTO(Cliente obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.telefone = obj.getTelefone();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
   
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	
	
	

}
