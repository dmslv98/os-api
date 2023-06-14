package com.exemplo.os.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Tecnico extends Pessoa implements Serializable{	
	private static final long serialVersinUID = 1L;
	
	@SuppressWarnings("unused")
	
	@JsonIgnore
	@OneToMany(mappedBy = "tecnico")  //mapeado pelo tecnico na OS
	private List<OS> list = new ArrayList<>();	

	// contructor from Super Class  
	public Tecnico() {
		super();		
	}

	public Tecnico(Integer idInteger, String nome, String cpf, String telefone) {
		super(idInteger, nome, cpf, telefone);		
	}

	public List<OS> getList() {
		return list;
	}

	public void setList(List<OS> list) {
		this.list = list;
	}	
		

}
