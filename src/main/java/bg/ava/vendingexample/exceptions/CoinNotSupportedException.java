package bg.ava.vendingexample.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@AllArgsConstructor
@NoArgsConstructor
@ResponseStatus(HttpStatus.CONFLICT)
@Getter
@Setter
public class CoinNotSupportedException extends RuntimeException {

    private static final long serialVersionUID = 1l;

    @Value(value = "${data.exception.details.message3Details}")
    private String message;
}
