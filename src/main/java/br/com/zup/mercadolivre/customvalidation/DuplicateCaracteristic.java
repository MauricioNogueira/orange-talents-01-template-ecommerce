package br.com.zup.mercadolivre.customvalidation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = DuplicateCaracteristicValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DuplicateCaracteristic {
	String message() default "característica duplicada";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}