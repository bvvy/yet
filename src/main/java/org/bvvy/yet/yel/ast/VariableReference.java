package org.bvvy.yet.yel.ast;

import org.bvvy.yel.context.Context;
import org.bvvy.yel.exp.ExpressionState;
import org.bvvy.yel.exp.TypedValue;
import org.bvvy.yel.exp.ast.PropertyOrFieldReference;
import org.bvvy.yet.yel.context.YetContext;

public class VariableReference extends PropertyOrFieldReference {

    public VariableReference(String variableName, int startPos, int endPos) {
        super(false, variableName, startPos, endPos);
    }

    @Override
    public TypedValue getValueInternal(ExpressionState state) {
        Context context = state.getContext();
        YetContext yetContext = (YetContext) context;
        TypedValue env = yetContext.getEnv();
        return getValueInternal(env, state.getContext());
    }

}
