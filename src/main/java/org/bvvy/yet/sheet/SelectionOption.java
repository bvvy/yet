package org.bvvy.yet.sheet;

/**
 * @author bvvy
 * @date 2022/2/21
 */
public class SelectionOption {
    private String columnName;
    private int[] indexes;

    public SelectionOption(String columnName, int[] indexes) {
        this.columnName = columnName;
        this.indexes = indexes;
    }

    public String getColumnName() {
        return columnName;
    }

    public int[] getIndexes() {
        return indexes;
    }
}
