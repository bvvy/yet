package org.bvvy.yet.calculator;


import org.bvvy.yel.context.Context;
import org.bvvy.yel.exp.Expression;
import org.bvvy.yet.yel.context.YetContext;

public class Cell {

    private Object value;
    private int index;
    private Expression expression;
    private boolean calculated;

    public Cell(int index, Expression expression) {
        this.index = index;
        this.expression = expression;
    }

    public int getIndex() {
        return index;
    }

    public boolean isCalculated() {
        return calculated;
    }

    public Object getValue(Context yetContext) {
        if (!calculated) {
            this.value = expression.getValue(yetContext);
            this.calculated = true;
        }
        return this.value;
    }
}
