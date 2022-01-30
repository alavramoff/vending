package bg.ava.vendingexample.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class QuantityValidator implements ConstraintValidator<QuantityValidation, Integer>
{
    public boolean isValid(Integer quantity, ConstraintValidatorContext cxt) {
        return quantity >=0 && quantity <=10;
    }
}
