package com.exemplo.os.domain;


import java.io.Serializable;
import java.util.Objects;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name =  "pessoa")
@Entity(name = "pessoa")
public abstract class Pessoa implements Serializable{	
	private static final long serialVersinUID = 1L;
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	@CPF
	private String cpf;
	private String telefone;
	
	// contructor from Super Class
	public Pessoa() {
		super();
		// TODO Auto-generated constructor stub
	}

	// contructor using fields
	public Pessoa(Integer idInteger, String nome, String cpf, String telefone) {
		super();
		this.id = idInteger;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
	}
	

	// generate gatters e setters  
	public Integer getId() {
		return id;
	}

	public void setId(Integer idInteger) {
		this.id = idInteger;
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

	
	// generate hashCode() and iguals()  - id and cpf
	@Override
	public int hashCode() {
		return Objects.hash(cpf, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id);
	}
	
	

}
