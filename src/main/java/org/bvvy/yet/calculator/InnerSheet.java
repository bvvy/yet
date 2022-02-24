package org.bvvy.yet.calculator;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author bvvy
 * @date 2022/2/21
 */
public class InnerSheet {

    private Map<String, InnerColumn> innerColumns = new ConcurrentHashMap<>();

    public void addColumn(InnerColumn column) {
        innerColumns.put(column.getName(), column);
    }

    public InnerColumn getColumn(String columnName) {
        return innerColumns.get(columnName);
    }


    public boolean containsColumn(String columnName) {
        return innerColumns.containsKey(columnName);
    }
}
