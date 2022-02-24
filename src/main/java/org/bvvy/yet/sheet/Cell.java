package org.bvvy.yet.sheet;


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

    public Object getValue(YetContext yetContext) {
        if (!calculated) {
            this.value = expression.getValue(yetContext);
            this.calculated = true;
        }
        return this.value;
    }
}
