package br.com.zup.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.dto.UsuarioDto;
import br.com.zup.mercadolivre.models.Usuario;
import br.com.zup.mercadolivre.repository.UsuarioRepository;
import br.com.zup.mercadolivre.requests.CadastraUsuarioRequest;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping
	public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid CadastraUsuarioRequest request) {
		Usuario usuario = request.toModel();
		
		this.usuarioRepository.save(usuario);
		
		return ResponseEntity.ok().body(new UsuarioDto(usuario));
	}
}
