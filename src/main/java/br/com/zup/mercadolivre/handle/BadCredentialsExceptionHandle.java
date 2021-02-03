package br.com.zup.mercadolivre.handle;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.zup.mercadolivre.dto.ErrorResponseDto;

@RestControllerAdvice
public class BadCredentialsExceptionHandle {

	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(BadCredentialsException.class)
	public ErrorResponseDto handle(BadCredentialsException exception) {
		
		return new ErrorResponseDto("Dados de acesso inválido");
	}
}