package br.com.bootcampo.cursoloja.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

 

@Entity
public class Arquivo {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable = false)
	private String nome;
	@Column
	private String tipo;
	private byte[] dados;

	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public byte[] getDados() {
		return dados;
	}
	public void setDados(byte[] dados) {
		this.dados = dados;
	}

}
