package com.exemplo.os.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemplo.os.domain.Cliente;
import com.exemplo.os.domain.OS;
import com.exemplo.os.domain.Tecnico;
import com.exemplo.os.domain.enuns.Prioridade;
import com.exemplo.os.domain.enuns.Status;
import com.exemplo.os.dtos.OSDTO;
import com.exemplo.os.repository.OSRepository;
import com.exemplo.os.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class OsService {
	@Autowired
	private OSRepository repository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	
	
	//método findById para OS
	public OS findById(Integer id){
		Optional<OS> obj = repository.findById(id);
		return obj.orElseThrow( () -> new ObjectNotFoundException
				("Objeto OS não encontrado!  Id : "+id+", Tipo"+OS.class.getName()));
	}
	
	//método findAll para OS (simples assim. Lista todas as OS na tabela do DB)
	public List<OS> findAll() {
		return repository.findAll();		
	}

	public OS create(@Valid OSDTO obj) {
		return fromDTO(obj); //retorna o método fromDTO abaixo
	}
	
	public OS update(@Valid OSDTO obj) {
		findById(obj.getId());
		return fromDTO(obj); //retorna o método fromDTO abaixo		
	}
	
	//Transformando de DTO para padrão normal (não DTO) - O DTO é usado apenas na transferência dos dados.
	private OS fromDTO(OSDTO obj) {
		OS newObj = new OS();
		newObj.setId(obj.getId());
		newObj.setObservacoes(obj.getObservacoes());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade().getCod())); //tem que transformar de integer (código) para stream (nome) - //alterado na aula-52 para código novamente 
		newObj.setStatus(Status.toEnum(obj.getStatus().getCod())); //tem que transformar de integer (código) para stream (nome)  - //alterado na aula-52 para código novamente
		
		Tecnico tec = tecnicoService.findById(obj.getTecnico()); //id do Tecnico
		Cliente cli = clienteService.findById(obj.getCliente()); //id do Cliente
		
		newObj.setTecnico(tec);
		newObj.setCliente(cli);
		
		//verifica se o status é de fechamento e setar a data de fechamento.
		if (newObj.getStatus().getCod().equals(2)) {
			newObj.setDataFechamento(LocalDateTime.now());			
		}
		
		return repository.save(newObj);		
	}
	
	
	//Não será implementado o método DELETE, pois assumido que a OS não pode ser deletada
	
	
	
}
