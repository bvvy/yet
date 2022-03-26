package org.bvvy.yet.yel.ast;

import org.bvvy.yel.exp.ExpressionState;
import org.bvvy.yel.exp.TypedValue;
import org.bvvy.yel.exp.ast.PropertyOrFieldReference;
import org.bvvy.yel.util.NumberUtils;
import org.bvvy.yet.calculator.Cell;
import org.bvvy.yet.calculator.InnerColumn;
import org.bvvy.yet.yel.ErrorTypedValue;
import org.bvvy.yet.yel.YetExpressionState;

public class ColumnReference extends PropertyOrFieldReference {

    private boolean startNode;

    public ColumnReference(boolean nullSafe, String propertyOrFieldName, int startPos, int endPos) {
        super(nullSafe, propertyOrFieldName, startPos, endPos);
    }

    public void setStartNode(boolean startNode) {
        this.startNode = startNode;
    }

    @Override
    public TypedValue getValueInternal(ExpressionState state) {
        YetExpressionState expressionState = (YetExpressionState) state;
        if (expressionState.getIteratorKey().equals(getName())) {
            return new TypedValue(expressionState.getActiveIndexContext());
        }
        if (startNode) {
            Integer index = expressionState.getActiveIndexContext();
            TypedValue contextObject = super.getValueInternal(state);
            Object target = contextObject.getValue();
            if (target instanceof InnerColumn) {
                if (index < 0) {
                    return ErrorTypedValue.REF_ERR;
                }
                InnerColumn column = (InnerColumn) target;
                Cell cell = column.getCell(index);
                Object value = cell.getValue(state.getContext());
                return new TypedValue(value);
            }
        }
        return super.getValueInternal(state);
    }
}
