package bg.ava.vendingexample.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.Inet4Address;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorMessageDto {
    private String message;
    private Integer statusCode;
    private String details;
}
