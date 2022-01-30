package bg.ava.vendingexample.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class PriceValidator implements ConstraintValidator<PriceValidation, BigDecimal>
{
    public boolean isValid(BigDecimal price, ConstraintValidatorContext cxt) {
        return price.compareTo(new BigDecimal("0.00")) > 0 ;
    }
}
