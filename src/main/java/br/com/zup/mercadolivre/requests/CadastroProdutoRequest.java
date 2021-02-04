package br.com.zup.mercadolivre.requests;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import br.com.zup.mercadolivre.customvalidation.DuplicateCaracteristic;
import br.com.zup.mercadolivre.customvalidation.ExistsId;
import br.com.zup.mercadolivre.models.Caracteristica;
import br.com.zup.mercadolivre.models.Categoria;
import br.com.zup.mercadolivre.models.Produto;
import br.com.zup.mercadolivre.models.Usuario;
import br.com.zup.mercadolivre.repository.CategoriaRepository;

public class CadastroProdutoRequest {

	@NotBlank
	private String nome;
	
	@NotBlank
	@Size(max = 1000)
	private String descricao;
	
	@NotNull
	@Positive
	private BigDecimal valor;
	
	@NotNull
	@PositiveOrZero
	private int quantidade;
	
	@NotNull
	@ExistsId(domainClass = Categoria.class)
	private Long categoriaId;
	
	@Size(min = 3, message = "produto precisa ter no mínimo 3 características")
	@DuplicateCaracteristic
	@Valid
	private List<CaracteristicaRequest> caracteristicas = new ArrayList<>();
	
	public CadastroProdutoRequest(String nome, String descricao, BigDecimal valor, int quantidade, Long categoriaId,
			List<CaracteristicaRequest> caracteristicas) {
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.quantidade = quantidade;
		this.categoriaId = categoriaId;
		this.caracteristicas = caracteristicas;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public List<CaracteristicaRequest> getCaracteristicas() {
		return caracteristicas;
	}

	@Override
	public String toString() {
		return "CadastroProdutoRequest [nome=" + nome + ", descricao=" + descricao + ", valor=" + valor
				+ ", quantidade=" + quantidade + ", categoriaId=" + categoriaId + ", caracteristicas=" + caracteristicas
				+ "]";
	}

	public Produto toModel(CategoriaRepository categoriaRepository, Usuario usuario) {
		Optional<Categoria> optional = categoriaRepository.findById(categoriaId);
		
		Categoria categoria = optional.get();
		
		List<Caracteristica> listaCaracteristicas = new ArrayList<>();
		Produto produto = new Produto(this.nome, this.valor, this.quantidade, this.descricao, categoria, usuario);
				
		this.caracteristicas.forEach(caracteristica -> {
			listaCaracteristicas.add(new Caracteristica(caracteristica.getNome(), caracteristica.getDescricao(), produto));
		});
		
		produto.getCaracteristicas().addAll(listaCaracteristicas);
		
		return produto;
	}
}
