package br.com.zup.mercadolivre.customvalidation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class ExistsFileValidator implements ConstraintValidator<ExistsFile, List<MultipartFile>> {
	
	private int totalEncontrado;

	@Override
	public boolean isValid(List<MultipartFile> value, ConstraintValidatorContext context) {
		if (value != null) {			
			System.out.println("resultado: "+value.isEmpty());
			
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
