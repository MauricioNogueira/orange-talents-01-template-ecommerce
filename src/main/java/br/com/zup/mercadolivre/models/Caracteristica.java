package br.com.zup.mercadolivre.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Caracteristica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Produto produto;
	
	public Caracteristica(String nome, String descricao, Produto produto) {
		this.nome = nome;
		this.descricao = descricao;
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
}
