package br.com.zup.mercadolivre.requests;

import javax.validation.constraints.NotBlank;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class AuthRequest {

	@NotBlank
	private String login;
	
	@NotBlank
	private String password;
	
	public AuthRequest(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "AuthRequest [login=" + login + ", password=" + password + "]";
	}
	
	public Authentication toAuthentication(AuthenticationManager manager) {
		return manager.authenticate(new UsernamePasswordAuthenticationToken(this.login, this.password));
	}
}