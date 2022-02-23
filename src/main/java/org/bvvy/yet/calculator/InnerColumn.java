package org.bvvy.yet.calculator;

import org.bvvy.yel.exp.Expression;

import java.util.Map;

/**
 * @author bvvy
 * @date 2022/2/21
 */
public class InnerColumn {

    private Map<Integer, Expression> cells;

    public InnerColumn(Map<Integer, Expression> cells) {
        this.cells = cells;
    }

    public void calculate(SheetContext context) {
    }

    public Expression getCell(int index) {
        return cells.get(index);
    }
}
