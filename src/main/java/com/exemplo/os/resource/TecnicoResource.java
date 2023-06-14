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

import com.exemplo.os.domain.Tecnico;
import com.exemplo.os.dtos.TecnicoDTO;
import com.exemplo.os.services.TecnicoService;

import jakarta.servlet.Servlet;
import jakarta.validation.Valid;


@CrossOrigin("*")  //para receber requisição de qualquer origem (plataforma)
@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {
	
	@Autowired
	private TecnicoService service;
	
	
	
	// Listar Tecnico pelo id - ex. localhost:8080/tecnico/id
	
	@GetMapping(value = "/{id}")	
	//public ResponseEntity<Tecnico> findById(@PathVariable Integer id) {		
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
		Tecnico obj = service.findById(id);
		TecnicoDTO objDTO = new TecnicoDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
		
	
	//Listar todos os Técnicos
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll() {
		
		//opcao-de-config-1 ou //opcao-de-config-2
		//List<Tecnico> list = service.findAll();		
		//List<TecnicoDTO> listDTO = new ArrayList<>();
					
		
		//opcao-de-config-1
	    /**
		for (Tecnico obj : list) {
			listDTO.add(new TecnicoDTO(obj));
		}
		**/
		
		//opcao-de-config-2
		//list.forEach(obj -> listDTO.add(new TecnicoDTO(obj)));
		
		
		//opcao-de-config-3
		List<TecnicoDTO> listDTO = service.findAll()
				.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());		
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	
	//Criar Técnico
	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO) {
		Tecnico newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(newObj.getId()).toUri();      
		
		return ResponseEntity.created(uri).build();		
	}
	
	
	//Alterar Técnico
	@PutMapping(value = "/{id}")   //carrega a variável de path (id) para buscar o tecnico
	public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO objDTO) {
		TecnicoDTO newObj = new TecnicoDTO(service.update(id, objDTO));
		return ResponseEntity.ok().body(newObj);
	}
	
	//Excluir Técnico
	/*
		@DeleteMapping(value = "/{id}")   //carrega a variável de path (id) para buscar o tecnico
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