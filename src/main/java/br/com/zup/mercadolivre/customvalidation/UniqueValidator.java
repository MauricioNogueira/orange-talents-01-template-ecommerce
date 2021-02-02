package br.com.zup.mercadolivre.customvalidation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique, String> {
	
	private Class<?> domainClass;
	private String field;
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void initialize(Unique constraintAnnotation) {
		this.domainClass = constraintAnnotation.domainClass();
		this.field = constraintAnnotation.field();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Query query = manager.createQuery("SELECT u From "+domainClass.getName()+" u WHERE "+field+" = :value");
		query.setParameter("value", value);
		
		List<?> list = query.getResultList();
		
		return list.isEmpty();
	}
}
