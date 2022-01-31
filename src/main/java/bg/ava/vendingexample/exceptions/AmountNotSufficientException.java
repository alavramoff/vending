package bg.ava.vendingexample.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class AmountNotSufficientException extends RuntimeException{

    private static final long serialVersionUID = 1l;
    private String message;
}
