package br.com.bootcamp.cursoloja.controller.request;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class CursoRequest {
	
	private Integer id;

	@NotBlank(message = "{curso.nome.obrigatorio}")
	private String nome;
	
	@Column(name="preco", precision=12, scale=2)
	@Min(value = 1)
	private Double preco;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	

}
