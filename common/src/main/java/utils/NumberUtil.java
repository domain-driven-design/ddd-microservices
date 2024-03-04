package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtil {

    private final NumberMode mode;
    private final boolean scaleAfterEachCalculation;

    private BigDecimal result;

    public static NumberUtil init(NumberMode mode) {
        return new NumberUtil(mode, BigDecimal.ZERO, false);
    }

    public static NumberUtil init(NumberMode mode, BigDecimal initialValue) {
        return new NumberUtil(mode, initialValue, false);
    }

    public static NumberUtil init(NumberMode mode, BigDecimal initialValue, boolean scaleAfterEachCalculation) {
        return new NumberUtil(mode, initialValue, scaleAfterEachCalculation);
    }

    private NumberUtil(NumberMode mode, BigDecimal initialValue, boolean scaleAfterEachCalculation) {
        this.mode = mode;
        this.result = initialValue;
        this.scaleAfterEachCalculation = scaleAfterEachCalculation;
    }

    private BigDecimal scale(BigDecimal result) {
        BigDecimal number = result;
        if (scaleAfterEachCalculation) {
            number = result.setScale(mode.getScale(), RoundingMode.HALF_UP);
        }
        return number;
    }

    public NumberUtil add(BigDecimal num) {
        result = scale(result.add(num));
        return this;
    }

    public NumberUtil subtract(BigDecimal num) {
        result = scale(result.subtract(num));
        return this;
    }

    public NumberUtil multiply(BigDecimal num) {
        result = scale(result.multiply(num));
        return this;
    }

    public NumberUtil divide(BigDecimal num) {
        if (num.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Divisor cannot be zero");
        }
        result = result.divide(num, mode.getScale(), RoundingMode.HALF_UP);
        return this;
    }

    public BigDecimal getResult() {
        return scaleAfterEachCalculation ? result : result.setScale(mode.getScale(), RoundingMode.HALF_UP);
    }

    public BigDecimal normalize() {
        return divide(BigDecimal.valueOf(mode.getFactor())).result;
    }

    public BigDecimal convert() {
        return multiply(BigDecimal.valueOf(mode.getFactor())).result;
    }

    public static BigDecimal toBigDecimal(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof String) {
            return new BigDecimal((String) value);
        }
        if (value instanceof Number) {
            return BigDecimal.valueOf(((Number) value).doubleValue());
        }
        throw new IllegalArgumentException(); //todo
    }

}
