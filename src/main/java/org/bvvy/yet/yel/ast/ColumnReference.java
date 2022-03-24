package org.bvvy.yet.yel.ast;

import org.bvvy.yel.exp.ExpressionState;
import org.bvvy.yel.exp.TypedValue;
import org.bvvy.yel.exp.ast.PropertyOrFieldReference;
import org.bvvy.yet.yel.YetExpressionState;

public class ColumnReference extends PropertyOrFieldReference {


    public ColumnReference(boolean nullSafe, String propertyOrFieldName, int startPos, int endPos) {
        super(nullSafe, propertyOrFieldName, startPos, endPos);
    }

    @Override
    public TypedValue getValueInternal(ExpressionState state) {
        YetExpressionState expressionState = (YetExpressionState) state;
        if (expressionState.getIteratorKey().equals(getName())) {
            return new TypedValue(expressionState.getActiveIndexContext());
        }
        return super.getValueInternal(state);
    }
}
