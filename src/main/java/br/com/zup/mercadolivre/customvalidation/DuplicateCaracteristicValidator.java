package br.com.zup.mercadolivre.customvalidation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.zup.mercadolivre.requests.CaracteristicaRequest;

public class DuplicateCaracteristicValidator implements ConstraintValidator<DuplicateCaracteristic, List<CaracteristicaRequest>> {

	@Override
	public boolean isValid(List<CaracteristicaRequest> value, ConstraintValidatorContext context) {
		Set<String> listaCaracteristica = new HashSet<String>();
		
		value.forEach(carac -> {
			listaCaracteristica.add(carac.getNome());
		});
		
		return listaCaracteristica.size() == value.size();
	}
}
