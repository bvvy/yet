package org.bvvy.yet.yel.ast;

import org.bvvy.yel.exception.YelEvalException;
import org.bvvy.yel.exp.ExpressionState;
import org.bvvy.yel.exp.TypedValue;
import org.bvvy.yel.exp.YelMessage;
import org.bvvy.yel.exp.ast.Node;
import org.bvvy.yel.exp.ast.NodeImpl;
import org.bvvy.yet.calculator.InnerColumn;

public class YetIndexer extends NodeImpl {
    public YetIndexer(int startPos, int endPos, Node expr) {
        super(startPos, endPos, expr);
    }

    @Override
    public TypedValue getValueInternal(ExpressionState state) {

        TypedValue context = state.getActiveContextObject();
        Object target = context.getValue();
        TypedValue indexValue;
        Object index;
        if (target == null) {
            throw new YelEvalException(getStartPosition(), YelMessage.CANNOT_INDEX_INTO_NULL_VALUE);
        }

        state.pushActiveContextObject(state.getRootContextObject());
        indexValue = this.children[0].getValueInternal(state);
        index = indexValue.getValue();
        state.popActiveContextObject();

        if (target instanceof InnerColumn) {

        }

        throw new YelEvalException(YelMessage.NOT_ASSIGNABLE);
    }
}
