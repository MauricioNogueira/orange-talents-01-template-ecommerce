package br.com.zup.mercadolivre.customvalidation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Long> {
	
	@PersistenceContext
	private EntityManager manager;
	private Class<?> domainClass;
	
	@Override
	public void initialize(ExistsId constraintAnnotation) {
		this.domainClass = constraintAnnotation.domainClass();
	}

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		Query query = manager.createQuery("select c from "+domainClass.getName()+" c where c.id = :id");
		query.setParameter("id", value);
		List<?> result = query.getResultList();
		
		if (value != null && result.isEmpty()) {
			return false;
		}
		
		return true;
	}
}
