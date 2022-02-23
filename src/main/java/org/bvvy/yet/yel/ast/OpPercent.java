package org.bvvy.yet.yel.ast;

import org.bvvy.yel.exp.ExpressionState;
import org.bvvy.yel.exp.TypedValue;
import org.bvvy.yel.exp.ast.Node;
import org.bvvy.yel.exp.ast.Operator;

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
        return null;
    }
}
