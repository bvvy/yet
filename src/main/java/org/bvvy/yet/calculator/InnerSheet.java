package org.bvvy.yet.calculator;

import org.bvvy.yel.exp.Expression;
import org.bvvy.yet.sheet.SelectionOption;

import java.util.List;
import java.util.Map;

/**
 * @author bvvy
 * @date 2022/2/21
 */
public class InnerSheet {


    private Map<String, InnerColumn> innerColumns;

    public InnerSheet(Map<String, InnerColumn> innerColumns) {
        this.innerColumns = innerColumns;
    }

    public InnerColumn getColumn(String columnName) {
        return innerColumns.get(columnName);
    }


    public boolean containsColumn(String columnName) {
        return innerColumns.containsKey(columnName);
    }
}
