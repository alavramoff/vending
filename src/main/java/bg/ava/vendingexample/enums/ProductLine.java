package bg.ava.vendingexample.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ProductLine {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN;

    private static Map<Integer, ProductLine> order;

    static {
        order = Arrays.stream(ProductLine.values()).sequential()
                .collect(Collectors.toMap(ProductLine::ordinal, Function.identity()));
    }

    public static ProductLine returnProductLineById(Integer id) {
        return order.get(id-1);
    }
}
