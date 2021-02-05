package br.com.zup.mercadolivre.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Imagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String link;
	
	@NotNull
	@ManyToOne
	private Produto produto;
	
	@Deprecated
	public Imagem() {}
	
	public Imagem(String link, Produto produto) {
		this.link = link;
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public String getLink() {
		return link;
	}

	public Produto getProdutoId() {
		return produto;
	}
}
