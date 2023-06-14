package com.exemplo.os.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Cliente extends Pessoa implements Serializable {
	@SuppressWarnings("unused")
	private static final long serialVersinUID =1L;
	
	@OneToMany(mappedBy = "cliente")  //mapeado pelo cliente na OS
	private List<OS> list = new ArrayList<>();	
	
    // contructor from Super Class
	public Cliente() {
		super();
	}

	public Cliente(Integer id, String nome, String cpf, String telefone) {
		super(id, nome, cpf, telefone);
	}

	public List<OS> getList() {
		return list;
	}

	public void setList(List<OS> list) {
		this.list = list;
	}

	
}
