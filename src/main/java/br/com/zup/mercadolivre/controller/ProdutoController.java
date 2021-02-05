package br.com.zup.mercadolivre.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.dto.ProdutoDto;
import br.com.zup.mercadolivre.models.Imagem;
import br.com.zup.mercadolivre.models.Produto;
import br.com.zup.mercadolivre.models.Usuario;
import br.com.zup.mercadolivre.repository.CategoriaRepository;
import br.com.zup.mercadolivre.repository.ImagemRepository;
import br.com.zup.mercadolivre.repository.ProdutoRepository;
import br.com.zup.mercadolivre.requests.CadastroProdutoRequest;
import br.com.zup.mercadolivre.requests.NovaImagemRequest;
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
	
	@Autowired
	private ImagemRepository imagemRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid CadastroProdutoRequest request, @RequestHeader("Authorization") String authorization) {
		
		Usuario usuario = this.buscaUsuarioToken.getUsuarioAutenticado(authorization);
		
		Produto produto = request.toModel(this.categoriaRepository, usuario);
				
		this.produtoRepository.save(produto);
		
		return ResponseEntity.ok().body(new ProdutoDto(produto));
	}
	
	@Transactional
	@PostMapping("/{id}/imagens")
	public ResponseEntity<ProdutoDto> adicionarImagens(@RequestHeader("Authorization") String authorization, @Valid NovaImagemRequest request, @PathVariable("id") Long id) {
		try {
			
			Optional<Produto> optional = this.produtoRepository.findById(id);
			
			Usuario usuario = this.buscaUsuarioToken.getUsuarioAutenticado(authorization);
			Produto produto = optional.get();
			
			if (!produto.getUsuario().equals(usuario)) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			}
			
			List<Imagem> imagens = request.toModel(produto);
			
			this.imagemRepository.saveAll(imagens);
			
			return ResponseEntity.ok().body(new ProdutoDto(produto));
			
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
