package br.com.zup.mercadolivre.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(unique = true)
	private String nome;
	
	@ManyToOne
	private Categoria categoria;
	
	@Deprecated
	public Categoria() {}
	
	public Categoria(String nome) {
		this.nome = nome;
	}
	
	public void setCategoria(Categoria categoriaMae) {
		this.categoria = categoriaMae;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
}