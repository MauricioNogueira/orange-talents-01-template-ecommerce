package br.com.zup.mercadolivre.handle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.zup.mercadolivre.dto.ErroValidacaoDto;

@RestControllerAdvice
public class ValidacaoHandle {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroValidacaoDto> handle(MethodArgumentNotValidException exception) {
		List<ErroValidacaoDto> errosDto = new ArrayList<>();
		
		List<FieldError> erros = exception.getBindingResult().getFieldErrors();
		
		erros.forEach(erro -> {
			errosDto.add(new ErroValidacaoDto(erro.getField(), erro.getDefaultMessage()));
		});
		
		return errosDto;
	}
}
