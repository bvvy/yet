package org.bvvy.yet.yel.ast;

import org.bvvy.yel.exception.YelEvalException;
import org.bvvy.yel.exp.ExpressionState;
import org.bvvy.yel.exp.TypedValue;
import org.bvvy.yel.exp.ValueRef;
import org.bvvy.yel.exp.YelMessage;
import org.bvvy.yel.exp.ast.Indexer;
import org.bvvy.yel.exp.ast.Node;
import org.bvvy.yel.exp.ast.NodeImpl;
import org.bvvy.yel.util.NumberUtils;
import org.bvvy.yet.calculator.Cell;
import org.bvvy.yet.calculator.InnerColumn;
import org.bvvy.yet.yel.ErrorTypedValue;

public class YetIndexer extends Indexer {
    public YetIndexer(int startPos, int endPos, Node expr) {
        super(startPos, endPos, expr);
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
            InnerColumn column = (InnerColumn) target;
            Cell cell = column.getCell(idx);
            Object value = cell.getValue(state.getContext());
            return () -> new TypedValue(value);
        }

        return super.getValueRef(state);
    }



}
