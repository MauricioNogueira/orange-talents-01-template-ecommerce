package br.com.zup.mercadolivre.dto;

import br.com.zup.mercadolivre.models.Usuario;

public class UsuarioDto {

	private String login;
	
	public UsuarioDto(Usuario usuario) {
		this.login = usuario.getLogin();
	}

	public String getLogin() {
		return login;
	}
}