package br.com.bootcampo.cursoloja.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;

 

@Entity
public class Curso {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="nome", length=100, nullable=false)
	@NotBlank(message="{curso.nome.obrigatorio}")
	private String nome;
	
	@Column(name="preco", precision=12, scale=2)
	private Double preco;
	
	@OneToOne
	@JoinColumn(name = "arquivo_id") //nome da coluna de relação
	private Arquivo arquivo;
	
	@Column(columnDefinition = "timestamp default current_timestamp", insertable = false, updatable = false)
	private LocalDateTime criadoEm;
	
	private LocalDateTime atualizadoEm;

	
	
	public Curso(String nome, Double preco) {
		super();
		this.nome = nome;
		this.preco = preco;
	}

	public Curso() {
		//this.criadoEm = LocalDateTime.now();
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

	public Arquivo getArquivo() {
		return arquivo;
	}

	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(LocalDateTime criadoEm) {
		this.criadoEm = criadoEm;
	}

	public LocalDateTime getAtualizadoEm() {
		return atualizadoEm;
	}

	public void setAtualizadoEm(LocalDateTime atualizadoEm) {
		this.atualizadoEm = atualizadoEm;
	}

	 
}
