package org.bvvy.yet.function.support;

import org.bvvy.yel.context.method.GlobalFunction;
import org.bvvy.yel.context.method.YelFunction;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author bvvy
 * @date 2022/2/14
 */
public class Round implements YelFunction {

    private final RoundingMode roundingMode;

    public Round(RoundingMode roundingMode) {
        this.roundingMode = roundingMode;
    }

    public Round() {
        this(RoundingMode.HALF_UP);
    }

    @GlobalFunction
    public BigDecimal round(BigDecimal value, Integer scale) {
        return value.setScale(scale, roundingMode);
    }

    @GlobalFunction
    public BigDecimal round(BigDecimal value) {
        return round(value, 2);
    }

}
