package org.bvvy.yet.calculator;

import java.util.Map;

/**
 * @author bvvy
 * @date 2022/2/21
 */
public class InnerSheet {


    private Map<String, InnerColumn> columnMap;

    public InnerSheet() {
    }


    public InnerColumn getColumn(String columnName) {
        return columnMap.get(columnName);
    }
}
