package org.bvvy.yet.sheet;

/**
 * @author bvvy
 * @date 2022/2/21
 */
public class SelectionOption {
    private String columnName;
    private int[] index;

    public SelectionOption(String columnName, int[] index) {
        this.columnName = columnName;
        this.index = index;
    }

    public String getColumnName() {
        return columnName;
    }

    public int[] getIndex() {
        return index;
    }
}
