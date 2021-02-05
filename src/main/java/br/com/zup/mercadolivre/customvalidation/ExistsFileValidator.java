package br.com.zup.mercadolivre.customvalidation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class ExistsFileValidator implements ConstraintValidator<ExistsFile, List<MultipartFile>> {
	
	private int totalEncontrado = 0;

	@Override
	public boolean isValid(List<MultipartFile> value, ConstraintValidatorContext context) {
		if (value != null) {			
			value.forEach(imagem -> {
				if (imagem.getSize() != 0) {
					totalEncontrado++;
				}
			});
			
			if (this.totalEncontrado > 0) return true; 
		}
		
		return false;
	}
}
