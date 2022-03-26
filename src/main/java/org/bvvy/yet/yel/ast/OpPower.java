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
 * @date 2022/2/3
 */
public class OpPower extends Operator {
    public OpPower(int startPos, int endPos, Node ... operand) {
        super("^", startPos, endPos, operand);
    }

    @Override
    public TypedValue getValueInternal(ExpressionState state) {
        Object left = getLeftOperand().getValue(state);
        Object right = getRightOperand().getValue(state);
        if (left instanceof ErrorValue) {
            return new TypedValue(left);
        } else if (right instanceof ErrorValue) {
            return new TypedValue(right);
        }
        if (left instanceof Number && right instanceof Number) {
            Number leftNumber = ((Number) left);
            Number rightNumber = ((Number) right);
            Double leftDouble = NumberUtils.convertNumberToTargetClass(leftNumber, Double.class);
            Double rightDouble = NumberUtils.convertNumberToTargetClass(rightNumber, Double.class);
            return new TypedValue(BigDecimal.valueOf(Math.pow(leftDouble, rightDouble)));
        }
        throw new RuntimeException("percent operator not support type " + left.getClass());
    }
}
