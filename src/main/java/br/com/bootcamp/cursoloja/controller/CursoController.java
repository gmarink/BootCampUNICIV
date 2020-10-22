package br.com.bootcamp.cursoloja.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.bootcamp.cursoloja.model.Curso;
import br.com.bootcamp.cursoloja.service.CursoService;

@RestController
@Validated
@RequestMapping("/curso")
public class CursoController {
	
	@Autowired
	private CursoService cursoservice;
	
	@GetMapping
	public List<Curso> todos(){
		return cursoservice.todosCursos();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Curso salvar(@RequestBody @Valid Curso curso){
		return cursoservice.salvar(curso);
	}
 
	@PutMapping("{id}")
	public Curso alterar(@PathVariable Integer id, @RequestBody Curso curso){
		return cursoservice.alterar(id, curso);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Integer id) {
		cursoservice.excluir(id);
	}
	

}
