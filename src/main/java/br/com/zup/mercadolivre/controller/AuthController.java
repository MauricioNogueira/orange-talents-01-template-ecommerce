package br.com.zup.mercadolivre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.dto.TokenResponseDto;
import br.com.zup.mercadolivre.models.Usuario;
import br.com.zup.mercadolivre.requests.AuthRequest;
import br.com.zup.mercadolivre.service.JwtTokenUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@PostMapping("/sign")
	public ResponseEntity<TokenResponseDto> authenticate(@RequestBody @Valid AuthRequest request) {
		Authentication authentication = request.toAuthentication(authManager);
		
		Usuario usuario = (Usuario) authentication.getPrincipal();
		
		String token = this.jwtTokenUtil.generateToken(usuario);
		
		return ResponseEntity.ok().body(new TokenResponseDto("Bearer", token));
	}
}