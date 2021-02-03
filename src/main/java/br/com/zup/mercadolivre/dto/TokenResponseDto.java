package br.com.zup.mercadolivre.dto;

public class TokenResponseDto {

	private String type;
	private String token;
	
	public TokenResponseDto(String type, String token) {
		this.type = type;
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public String getToken() {
		return token;
	}
}
