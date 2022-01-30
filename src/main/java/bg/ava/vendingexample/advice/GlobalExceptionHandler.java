package bg.ava.vendingexample.advice;

import bg.ava.vendingexample.exceptions.AmountNotSufficientException;
import bg.ava.vendingexample.exceptions.CoinNotSupportedException;
import bg.ava.vendingexample.dtos.ErrorMessageDto;
import bg.ava.vendingexample.exceptions.ProductNotAvailableException;
import bg.ava.vendingexample.exceptions.QuantityLimitException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @Value(value = "${data.exception.message1}")
    private String message1;
    @Value(value = "${data.exception.message2}")
    private String message2;
    @Value(value = "${data.exception.message3}")
    private String message3;
    @Value(value = "${data.exception.message4}")
    private String message4;

    @ExceptionHandler(value = AmountNotSufficientException.class)
    public ResponseEntity<Object> amountNotSufficientException(AmountNotSufficientException blogNotFoundException) {
        return new ResponseEntity<>(message2, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> productNotAvailable(Exception exception) {
        return new ResponseEntity<>(message3, HttpStatus.GONE);
    }

    @ExceptionHandler(value = CoinNotSupportedException.class)
    @ResponseBody
    public final ResponseEntity<?> coinNotSupported(CoinNotSupportedException exception) {
        ErrorMessageDto error = new ErrorMessageDto(message3,HttpStatus.NOT_ACCEPTABLE.value(), exception.getMessage());
        return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = ProductNotAvailableException.class)
    @ResponseBody
    public final ResponseEntity<?> coinNotSupported(ProductNotAvailableException exception) {
        ErrorMessageDto error = new ErrorMessageDto(message1,HttpStatus.NOT_ACCEPTABLE.value(), exception.getMessage());
        return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = QuantityLimitException.class)
    @ResponseBody
    public final ResponseEntity<?> coinNotSupported(QuantityLimitException exception) {
        ErrorMessageDto error = new ErrorMessageDto(message4,HttpStatus.INSUFFICIENT_STORAGE.value(), exception.getMessage());
        return new ResponseEntity<>(error,HttpStatus.INSUFFICIENT_STORAGE);
    }
}