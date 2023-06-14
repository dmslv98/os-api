package com.exemplo.os.resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.exemplo.os.domain.Cliente;
import com.exemplo.os.dtos.ClienteDTO;
import com.exemplo.os.services.ClienteService;

import jakarta.servlet.Servlet;
import jakarta.validation.Valid;


@CrossOrigin("*")  //para receber requisição de qualquer origem (plataforma)
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	
	
	// Listar Cliente pelo id - ex. localhost:8080/cliente/id
	
	@GetMapping(value = "/{id}")	
	//public ResponseEntity<Cliente> findById(@PathVariable Integer id) {		
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
		Cliente obj = service.findById(id);
		ClienteDTO objDTO = new ClienteDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
		
	
	//Listar todos os Clientes
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		
		//opcao-de-config-1 ou //opcao-de-config-2
		//List<Cliente> list = service.findAll();		
		//List<ClienteDTO> listDTO = new ArrayList<>();
					
		
		//opcao-de-config-1
	    /**
		for (Cliente obj : list) {
			listDTO.add(new ClienteDTO(obj));
		}
		**/
		
		//opcao-de-config-2
		//list.forEach(obj -> listDTO.add(new ClienteDTO(obj)));
		
		
		//opcao-de-config-3
		List<ClienteDTO> listDTO = service.findAll()
				.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());		
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	
	//Criar Cliente
	@PostMapping
	public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO objDTO) {
		Cliente newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(newObj.getId()).toUri();      
		
		return ResponseEntity.created(uri).build();		
	}
	
	
	//Alterar Cliente
	@PutMapping(value = "/{id}")   //carrega a variável de path (id) para buscar o cliente
	public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO objDTO) {
		ClienteDTO newObj = new ClienteDTO(service.update(id, objDTO));
		return ResponseEntity.ok().body(newObj);
	}
	
	//Excluir Cliente
	/*
		@DeleteMapping(value = "/{id}")   //carrega a variável de path (id) para buscar o cliente
		public ResponseEntity<Void> delete(@PathVariable Integer id) {
			service.delete{id};
		}
	*/
	
	@DeleteMapping(value = "/{id}") 
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
}