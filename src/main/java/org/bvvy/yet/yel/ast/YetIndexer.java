package org.bvvy.yet.yel.ast;

import org.bvvy.yel.exception.YelEvalException;
import org.bvvy.yel.exp.ExpressionState;
import org.bvvy.yel.exp.TypedValue;
import org.bvvy.yel.exp.ValueRef;
import org.bvvy.yel.exp.YelMessage;
import org.bvvy.yel.exp.ast.Node;
import org.bvvy.yel.exp.ast.NodeImpl;
import org.bvvy.yel.util.NumberUtils;
import org.bvvy.yet.calculator.Cell;
import org.bvvy.yet.calculator.InnerColumn;
import org.bvvy.yet.yel.ErrorTypedValue;
import org.bvvy.yet.yel.YetExpressionState;

public class YetIndexer extends NodeImpl {
    public YetIndexer(int startPos, int endPos, Node expr) {
        super(startPos, endPos, expr);
    }

    @Override
    public TypedValue getValueInternal(ExpressionState state) {
        return getValueRef(state).getValue();
    }

    @Override
    public ValueRef getValueRef(ExpressionState state) {
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
            Integer idx = NumberUtils.convertNumberToTargetClass((Number) index, Integer.class);
            if (idx < 0) {
                return () -> ErrorTypedValue.REF_ERR;
            }
            YetExpressionState expressionState = (YetExpressionState) state;
            expressionState.pushIndexContext(idx);
            InnerColumn column = (InnerColumn) target;
            Cell cell = column.getCell(idx);
            Object value = cell.getValue(state.getContext());
            expressionState.popIndexContext();
            return () -> new TypedValue(value);
        }

        throw new YelEvalException(YelMessage.NOT_ASSIGNABLE);
    }



}
