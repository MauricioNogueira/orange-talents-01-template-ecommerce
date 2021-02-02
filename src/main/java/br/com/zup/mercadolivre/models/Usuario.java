package br.com.zup.mercadolivre.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Email
	@Column(unique = true)
	private String login;
	
	@NotBlank
	@Size(min = 6)
	private String password;
	private LocalDate dataCriacao = LocalDate.now();
	
	@Deprecated
	public Usuario() {}
	
	public Usuario(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}
}