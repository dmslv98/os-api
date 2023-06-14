package com.exemplo.os.domain.enuns;

public enum Prioridade {	
	
	BAIXA(0,"BAIXA" ),
	MEDIA(1,"MEDIA" ),
	ALTA(2,"ALTA" );
	
	private Integer cod;
	private String descricao;

	Prioridade(int cod, String descricao) {
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
	
	
	public static Prioridade toEnum(Integer cod){
		if (cod == null) {
			return null;
		}
		
		for (Prioridade p : Prioridade.values()) {
			if (cod.equals(p.getCod())) {
				return p;
			}
		}
		
		//caso não atendida as condições acima, lanca uma excessão
		throw new IllegalArgumentException("Prioridade inválida!" + cod);
	}

}
