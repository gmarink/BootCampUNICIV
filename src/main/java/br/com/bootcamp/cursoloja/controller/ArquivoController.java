package br.com.bootcamp.cursoloja.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.bootcamp.cursoloja.model.Arquivo;
import br.com.bootcamp.cursoloja.repository.ArquivoRepository;

@RestController
public class ArquivoController {
	
	
	@Autowired
	private ArquivoRepository arquivoRepository;
	
	@GetMapping("/download/{id}")
	public ResponseEntity<Resource> download(@PathVariable("id") String id){
		Arquivo arquivo = arquivoRepository.findById(id)
				.orElseThrow(
				() -> new EntityNotFoundException());
		
		return ResponseEntity.ok().
				contentType(MediaType.parseMediaType(arquivo.getTipo()))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + arquivo.getNome() + "\"")
				.body(new ByteArrayResource(arquivo.getDados()));
		
	}
}
