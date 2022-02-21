package org.bvvy.yet.calculator;

import org.bvvy.yel.exp.Expression;
import org.bvvy.yet.context.Context;
import org.bvvy.yet.sheet.Column;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bvvy
 * @date 2022/2/21
 */
public class InnerColumn {

    private Map<Integer, Expression> cells = new HashMap<>();
    private Column column;

    public InnerColumn(Column column) {
        this.column = column;
    }

    public void x(Context context) {
    }
}
