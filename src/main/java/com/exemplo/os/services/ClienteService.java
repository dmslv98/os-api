package com.exemplo.os.services;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemplo.os.domain.Pessoa;
import com.exemplo.os.domain.Cliente;
import com.exemplo.os.dtos.ClienteDTO;
import com.exemplo.os.repository.PessoaRepository;
import com.exemplo.os.repository.ClienteRepository;
import com.exemplo.os.services.exceptions.DataIntegratyViolationException;
import com.exemplo.os.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	//@Autowired 
	//private PessoaRepository pessoaRepository; 
	
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);   //Optional indica que o id pode existir ou não
		//return obj.orElse(null);  //retorna o obj ou null se não existir - personalizar futuramento		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! - id : " + id +", Tipo : "+Cliente.class.getName())); 	//tem que primeiro configurar a CLASS ObjectNotFoundException

	}

	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}
	
	
	public Cliente create(ClienteDTO objDTO) {
		if(findByCPF(objDTO) !=null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados");
		}
		
		Cliente newObj = new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone());
		return repository.save(newObj);
		
		//o return pode ser configurado também conforme lnha abaixo 
		//return repository.save(new Cliente(null, objDTO.getNome(), objDTO.getCpf(), objDTO.getTelefone()));
	}	
	
	
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		Cliente oldOBJ = findById(id);   //verifica se existe um id válido, se não existir, não segue para as linhas abaixo
		
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
		Cliente obj = findById(id);
		if (obj.getList().size() > 0) {
			throw new DataIntegratyViolationException("Cliente possui Orden de Serviço. Não pode ser deletado");
		}		
		repository.deleteById(id);		
	}		
	

	
	private Cliente findByCPF(ClienteDTO objDTO) {
		Cliente obj = repository.findByCPF(objDTO.getCpf());
			
		if(obj != null) {
				return obj;
		}			
		return null;
	}
	
	
	
		
	
}
