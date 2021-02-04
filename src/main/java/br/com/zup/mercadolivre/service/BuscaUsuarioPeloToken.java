package br.com.zup.mercadolivre.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.zup.mercadolivre.models.Usuario;
import br.com.zup.mercadolivre.repository.UsuarioRepository;

@Service
public class BuscaUsuarioPeloToken {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario getUsuarioAutenticado(String bearer) {
		String token = bearer.substring(7);
		String username = this.jwtTokenUtil.getUsername(token);
		Optional<UserDetails> optional = this.usuarioRepository.findByLogin(username);
		
		if (optional.isPresent()) {
			return (Usuario) optional.get();
		}
		
		return null;
	}
}
