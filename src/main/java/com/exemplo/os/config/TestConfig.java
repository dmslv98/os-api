package com.exemplo.os.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.exemplo.os.domain.Cliente;
import com.exemplo.os.domain.OS;
import com.exemplo.os.domain.Tecnico;
import com.exemplo.os.domain.enuns.Prioridade;
import com.exemplo.os.domain.enuns.Status;
import com.exemplo.os.repository.ClienteRepository;
import com.exemplo.os.repository.OSRepository;
import com.exemplo.os.repository.TecnicoRepository;
import com.exemplo.os.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public void instanciaDB() {
		this.dbService.instanciaDB();	
		
	}
	

}
