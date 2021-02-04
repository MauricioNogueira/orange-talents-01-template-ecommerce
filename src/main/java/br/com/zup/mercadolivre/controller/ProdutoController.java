package br.com.zup.mercadolivre.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.dto.ProdutoDto;
import br.com.zup.mercadolivre.models.Produto;
import br.com.zup.mercadolivre.models.Usuario;
import br.com.zup.mercadolivre.repository.CategoriaRepository;
import br.com.zup.mercadolivre.repository.ProdutoRepository;
import br.com.zup.mercadolivre.requests.CadastroProdutoRequest;
import br.com.zup.mercadolivre.service.BuscaUsuarioPeloToken;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private BuscaUsuarioPeloToken buscaUsuarioToken;

	@PostMapping
	@Transactional
	public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid CadastroProdutoRequest request, @RequestHeader("Authorization") String authorization) {
		
		Usuario usuario = this.buscaUsuarioToken.getUsuarioAutenticado(authorization);
		
		Produto produto = request.toModel(this.categoriaRepository, usuario);
				
		this.produtoRepository.save(produto);
		
		return ResponseEntity.ok().body(new ProdutoDto(produto));
	}
}
