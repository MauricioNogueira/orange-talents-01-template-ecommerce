package br.com.zup.mercadolivre.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.zup.mercadolivre.customvalidation.Unique;
import br.com.zup.mercadolivre.models.Usuario;

public class CadastraUsuarioRequest {

	@NotBlank
	@Email
	@Unique(domainClass = Usuario.class, field = "login")
	private String login;
	
	@NotBlank
	@Size(min = 6, message = "deve ter no mínimo 6 caracteres")
	private String password;
	
	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "CadastraUsuarioRequest [login=" + login + ", password=" + password + "]";
	}

	public Usuario toModel() {
		return new Usuario(this.login, this.password);
	}
}
