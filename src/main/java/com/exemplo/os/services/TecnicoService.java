package com.exemplo.os.services;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemplo.os.domain.Pessoa;
import com.exemplo.os.domain.Tecnico;
import com.exemplo.os.dtos.TecnicoDTO;
import com.exemplo.os.repository.PessoaRepository;
import com.exemplo.os.repository.TecnicoRepository;
import com.exemplo.os.services.exceptions.DataIntegratyViolationException;
import com.exemplo.os.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository repository;
	
	//@Autowired 
	//private PessoaRepository pessoaRepository; 
	
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);   //Optional indica que o id pode existir ou não
		//return obj.orElse(null);  //retorna o obj ou null se não existir - personalizar futuramento		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! - id : " + id +", Tipo : "+Tecnico.class.getName())); 	//tem que primeiro configurar a CLASS ObjectNotFoundException

	}

	public List<Tecnico> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}
	
	
	public Tecnico create(TecnicoDTO objDTO) {
		if(findByCPF(objDTO) !=null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados");
		}
		
		Tecnico newObj = new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone());
		return repository.save(newObj);
		
		//o return pode ser configurado também conforme lnha abaixo 
		//return repository.save(new Tecnico(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}	
	
	
	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		Tecnico oldOBJ = findById(id);   //verifica se existe um id válido, se não existir, não segue para as linhas abaixo
		
		//verifica se o CPF informado para o ID que será alterado é o mesmo cadastrado.
		if (findByCPF(objDTO) != null && findByCPF(objDTO).getId() !=id) {
			throw new DataIntegratyViolationException("CPF informado diferente do CPF cadastrado para esse id!");
		}
		
		//se tudo ok acima, processa as alterações.
		oldOBJ.setNome(objDTO.getNome());
		oldOBJ.setCpf(objDTO.getCpf());
		oldOBJ.setTelefone(objDTO.getTelefone());
		return repository.save(oldOBJ);
	}
	
	
	public void delete(Integer id) {		
		//findById(id); //verifica se o id existe 
		Tecnico obj = findById(id);
		if (obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Tècnico possui Orden de Serviço. Não pode ser deletado");
		}		
		repository.deleteById(id);		
	}		
	

	
	private Tecnico findByCPF(TecnicoDTO objDTO) {
		Tecnico obj = repository.findByCPF(objDTO.getCpf());
			
		if(obj != null) {
				return obj;
		}			
		return null;
	}
	
	
	
		
	
}
