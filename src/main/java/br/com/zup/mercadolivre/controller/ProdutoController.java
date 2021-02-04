package br.com.zup.mercadolivre.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.dto.ProdutoDto;
import br.com.zup.mercadolivre.models.Produto;
import br.com.zup.mercadolivre.repository.CategoriaRepository;
import br.com.zup.mercadolivre.repository.ProdutoRepository;
import br.com.zup.mercadolivre.requests.CadastroProdutoRequest;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid CadastroProdutoRequest request) {
		Produto produto = request.toModel(this.categoriaRepository);
		
		System.out.println(produto.getNome());
		
		this.produtoRepository.save(produto);
		
		return ResponseEntity.ok().body(new ProdutoDto(produto));
	}
}
