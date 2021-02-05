package br.com.zup.mercadolivre.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotNull
	@Positive
	private BigDecimal valor;
	
	@NotNull
	@PositiveOrZero
	private int quantidade;
	
	@NotBlank
	@Size(max = 1000)
	private String descricao;
	
	@NotNull
	@ManyToOne
	private Categoria categoria;
	private LocalDate dataCriacao = LocalDate.now();
	
	@OneToMany(mappedBy = "produto",cascade = CascadeType.ALL)
	private List<Caracteristica> caracteristicas = new ArrayList<>();
	
	@OneToMany(mappedBy = "produto")
	private List<Imagem> imagens;
	
	@ManyToOne
	private Usuario usuario;
	
	@Deprecated
	public Produto() {}
	
	public Produto(String nome, BigDecimal valor, int quantidade, String descricao, Categoria categoria, Usuario usuario) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public List<Imagem> getImagens() {
		return this.imagens;
	}
}
