package br.com.bootcamp.cursoloja.controller.response;

import br.com.bootcamp.cursoloja.model.Curso;

public class CursoResponse {
	
	private Curso curso;

	public CursoResponse(Curso curso) {
		super();
		this.curso = curso;
	}

	public Integer getId() {
		return curso.getId();
	}
	
	public String getNome() {
		return curso.getNome();
	}
	
	public Double getPreco() {
		return curso.getPreco();
	}
	
	

}
