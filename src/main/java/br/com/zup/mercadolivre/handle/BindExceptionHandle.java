package br.com.zup.mercadolivre.handle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.zup.mercadolivre.dto.ErroValidacaoDto;

@RestControllerAdvice
public class BindExceptionHandle {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public List<ErroValidacaoDto> handle(BindException exception) {
		List<FieldError> errors = exception.getFieldErrors();
		List<ErroValidacaoDto> errosDto = new ArrayList<>();
		
		errors.forEach(erro -> {
			errosDto.add(new ErroValidacaoDto(erro.getField(), erro.getDefaultMessage()));
		});
		
		return errosDto;
	}
}
