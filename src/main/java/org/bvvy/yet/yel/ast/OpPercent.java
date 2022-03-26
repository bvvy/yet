package org.bvvy.yet.yel.ast;

import org.bvvy.yel.exp.ExpressionState;
import org.bvvy.yel.exp.TypedValue;
import org.bvvy.yel.exp.ast.Node;
import org.bvvy.yel.exp.ast.Operator;
import org.bvvy.yel.util.NumberUtils;
import org.bvvy.yet.yel.ErrorValue;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author bvvy
 * @date 2022/2/5
 */
public class OpPercent extends Operator {
    public OpPercent(int startPos, int endPos, Node expr) {
        super("%", startPos, endPos, expr);

    }

    @Override
    public TypedValue getValueInternal(ExpressionState state) {
        Object left = getLeftOperand().getValue(state);
        if (left instanceof ErrorValue) {
            return new TypedValue(left);
        }
        if (left instanceof Number) {
            Number leftNumber = ((Number) left);
            BigDecimal leftBigDecimal = NumberUtils.convertNumberToTargetClass(leftNumber, BigDecimal.class);
            return new TypedValue(leftBigDecimal.divide(new BigDecimal(100), leftBigDecimal.scale() + 2, RoundingMode.UNNECESSARY));
        }
        throw new RuntimeException("percent operator not support type " + left.getClass());
    }
}
