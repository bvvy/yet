package org.bvvy.yet.yel;

import org.bvvy.yel.context.Context;
import org.bvvy.yel.exp.Expression;
import org.bvvy.yel.exp.ExpressionState;
import org.bvvy.yet.yel.context.YetContext;

import java.util.ArrayDeque;
import java.util.Deque;

public class YetExpressionState extends ExpressionState {

    private final String iteratorKey;

    public YetExpressionState(Context context, String iteratorKey) {
        super(context);
        this.iteratorKey = iteratorKey;
    }

    public String getIteratorKey() {
        return iteratorKey;
    }

    public Integer getActiveIndexContext() {
        YetContext context = (YetContext) getContext();
        return context.getActiveIndexContext();
    }

    public void pushIndexContext(int index) {
        YetContext context = (YetContext) getContext();
        context.pushIndexContext(index);
    }

    public void popIndexContext() {
        YetContext context = (YetContext) getContext();
        context.popIndexContext();
    }

    public ExpressionState getYelExpressionState(Context context) {
        return new ExpressionState(context);
    }

}
