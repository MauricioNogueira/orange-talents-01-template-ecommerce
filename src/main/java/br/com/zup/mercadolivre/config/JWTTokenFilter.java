package br.com.zup.mercadolivre.config;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.zup.mercadolivre.repository.UsuarioRepository;
import br.com.zup.mercadolivre.service.JwtTokenUtil;

public class JWTTokenFilter extends OncePerRequestFilter {
	
	private final JwtTokenUtil jwtTokenUtil;
	private final UsuarioRepository usuarioRepository;
	
	public JWTTokenFilter(JwtTokenUtil jwtTokenUtil, UsuarioRepository usuarioRepository) {
		this.jwtTokenUtil = jwtTokenUtil;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String headerAuthorization = request.getHeader("Authorization");
		
		if (headerAuthorization == null || !headerAuthorization.startsWith("Bearer ")) {
			
			filterChain.doFilter(request, response);
			
			return;
		}
		
		String token = headerAuthorization.substring(7);
		boolean isValid = this.jwtTokenUtil.validate(token);
		
		System.out.println("Token is valid: "+isValid);
		
		if (isValid) {
			String username = this.jwtTokenUtil.getUsername(token);
			
			Optional<UserDetails> optional = this.usuarioRepository.findByLogin(username);
			
			this.authenticate(optional.get());
			
			filterChain.doFilter(request, response);
			
			return;
		} else {
			filterChain.doFilter(request, response);
			return;
		}
	}
	
	private void authenticate(UserDetails userDetails) {
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
				userDetails.getUsername(), userDetails.getPassword(), null);
		
		authentication.setDetails(userDetails);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}