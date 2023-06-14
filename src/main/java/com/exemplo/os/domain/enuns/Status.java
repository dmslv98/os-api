package com.exemplo.os.domain.enuns;

public enum Status {
	
	ABERTA(0,"ABERTA" ),
	ANDAMENTO(1,"ANDAMENTO" ),
	ENCERRADA(2,"ENCERRADA" );
	
	private Integer cod;
	private String descricao;

	Status(int cod, String descricao) {
		// TODO Auto-generated constructor stub
		this.cod = cod;
		this.descricao = descricao;  
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
	public static Status toEnum(Integer cod){
		if (cod == null) {
			return null;
		}
		
		for (Status s : Status.values()) {
			if (cod.equals(s.getCod())) {
				return s;
			}
		}
		
		//caso não atendida as condições acima, lanca uma excessão
		throw new IllegalArgumentException("Status inválido!" + cod);
	}

}