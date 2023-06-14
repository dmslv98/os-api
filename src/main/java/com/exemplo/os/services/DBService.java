package com.exemplo.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.exemplo.os.domain.Cliente;
import com.exemplo.os.domain.OS;
import com.exemplo.os.domain.Tecnico;
import com.exemplo.os.domain.enuns.Prioridade;
import com.exemplo.os.domain.enuns.Status;
import com.exemplo.os.repository.ClienteRepository;
import com.exemplo.os.repository.OSRepository;
import com.exemplo.os.repository.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository  tecnicoRepository;
	
	@Autowired
	private ClienteRepository  clienteRepository;
	
	@Autowired
	private OSRepository  osRepository;
	
	
	public void instanciaDB() {
		
		Tecnico t1 = new Tecnico(null, "Valdir Cezar", "144.785.300-84", "(88) 98888-8888");
		Tecnico t2 = new Tecnico(null, "Linus Torvalds", "641.760.040-88", "(88) 94545-4545");
		Cliente c1 = new Cliente(null, "Betina Campos", "598.508.200-80", "(88) 98888-7777");
		OS os1 = new OS(null, Prioridade.ALTA, "Teste create OS", Status.ANDAMENTO, t1, t1);
		
		t1.getList().add(os1);
		c1.getList().add(os1);
		
		tecnicoRepository.saveAll(Arrays.asList(t1,t2));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));
		
	}

}
