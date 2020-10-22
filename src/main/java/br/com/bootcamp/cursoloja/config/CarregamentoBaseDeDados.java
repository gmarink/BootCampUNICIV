package br.com.bootcamp.cursoloja.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.bootcamp.cursoloja.model.Curso;
import br.com.bootcamp.cursoloja.repository.CursoRepository;

@Configuration
@Profile("dev")
public class CarregamentoBaseDeDados {
	
	@Autowired
	private CursoRepository repository;
	
	@Bean
	CommandLineRunner carregaBanco() {
		return args ->{
			Curso curso1 = new Curso("Curso Java" , 123.55);
			Curso curso2 = new Curso("Curso Web" , 200.55);
			
			repository.save(curso1);
			repository.save(curso2);
		
		};
	}

}
