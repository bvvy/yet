package org.bvvy.yet.yel.ast;

import org.bvvy.yel.exp.ExpressionState;
import org.bvvy.yel.exp.TypedValue;
import org.bvvy.yel.exp.ast.Node;
import org.bvvy.yel.exp.ast.Operator;

/**
 * @author bvvy
 * @date 2022/2/3
 */
public class OpConcat extends Operator {
    public OpConcat(int startPos, int endPos, Node ... operand) {
        super("&", startPos, endPos, operand);
    }

    @Override
    public TypedValue getValueInternal(ExpressionState state) {
        return null;
    }
}
