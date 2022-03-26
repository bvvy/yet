package org.bvvy.yet.calculator;


import org.bvvy.yel.context.Context;
import org.bvvy.yel.exp.Expression;
import org.bvvy.yet.yel.context.YetContext;

public class Cell {

    private Object value;
    private int index;
    private Expression expression;
    private String iteratorKey;
    private boolean calculated;


    public Cell(int index, Expression expression, String iteratorKey) {
        this.index = index;
        this.expression = expression;
        this.iteratorKey = iteratorKey;
    }

    public int getIndex() {
        return index;
    }

    public String getIteratorKey() {
        return iteratorKey;
    }

    public Expression getExpression() {
        return expression;
    }

    public boolean isCalculated() {
        return calculated;
    }

    public Object getValue() {
        return value;
    }

    public Object getValue(Context context) {
        if (!calculated) {
            YetContext yetContext = (YetContext) context;
            yetContext.pushIndexContext(this.index);
            this.value = expression.getValue(context);
            this.calculated = true;
        }
        return this.value;
    }

}
