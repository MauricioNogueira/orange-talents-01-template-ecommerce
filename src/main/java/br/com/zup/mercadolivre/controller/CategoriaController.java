package br.com.zup.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.dto.CategoriaDto;
import br.com.zup.mercadolivre.models.Categoria;
import br.com.zup.mercadolivre.repository.CategoriaRepository;
import br.com.zup.mercadolivre.requests.CadastraCategoriaRequest;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@PostMapping
	public ResponseEntity<CategoriaDto> cadastrar(@RequestBody @Valid CadastraCategoriaRequest request) {
		Categoria categoria = request.toModel(this.categoriaRepository);		
		
		this.categoriaRepository.save(categoria);
		
		return ResponseEntity.ok().body(new CategoriaDto(categoria.getNome()));
	}
}
