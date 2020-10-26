package br.com.bootcamp.cursoloja.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import br.com.bootcamp.cursoloja.model.Curso;
import br.com.bootcamp.cursoloja.repository.CursoRepository;

@Service
public class CursoService {
	
	@Autowired
	private CursoRepository repository;
	
	public List<Curso> filtraPor(Curso filtro){
		
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);

		Example<Curso> exemplo = Example.of(filtro, matcher);

		return repository.findAll(exemplo);
		 
	}
	
	public Curso salvar(Curso curso) {
		return repository.save(curso);
	}
	
	public Curso alterar(Integer id, Curso curso) {
		Curso cursoBanco = getCurso(id);
		
		cursoBanco.setNome(curso.getNome());
		cursoBanco.setPreco(curso.getPreco());
		cursoBanco.setAtualizadoEm(LocalDateTime.now());
		return salvar(cursoBanco);
	}
	
	public List<Curso> todosCursos(){
		return repository.findAll();
	}
	
	public Curso getCurso(Integer id) {
		return repository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
	}
	
	public void excluir(Integer id) {
		repository.deleteById(id);
	}
	
	
}
