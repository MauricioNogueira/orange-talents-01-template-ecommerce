package br.com.zup.mercadolivre.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.zup.mercadolivre.models.Produto;

public class ProdutoDto {

	private String nome;
	private BigDecimal valor;
	private int quantidade;
	private String descricao;
	private String nomeCategoria;
	private LocalDate dataCriacao;
	private List<CaracteristicaDto> caracteristicas = new ArrayList<>();
	
	public ProdutoDto(Produto produto) {
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.quantidade = produto.getQuantidade();
		this.descricao = produto.getDescricao();
		this.nomeCategoria = produto.getCategoria().getNome();
		this.dataCriacao = produto.getDataCriacao();
		this.caracteristicas.addAll(produto.getCaracteristicas().stream().map(CaracteristicaDto::new).collect(Collectors.toList()));
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

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public List<CaracteristicaDto> getCaracteristicas() {
		return caracteristicas;
	}
}
