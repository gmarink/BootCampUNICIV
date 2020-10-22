package br.com.bootcampo.cursoloja.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.bootcampo.cursoloja.model.Curso;
import br.com.bootcampo.cursoloja.repository.CursoRepository;

@Configuration
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
