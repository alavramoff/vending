package bg.ava.vendingexample.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ResponseStatus(HttpStatus.INSUFFICIENT_STORAGE)
public class QuantityLimitException extends RuntimeException{
    private String message;
}
