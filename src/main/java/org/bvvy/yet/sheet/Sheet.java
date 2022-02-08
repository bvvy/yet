package org.bvvy.yet.sheet;

import java.util.List;

/**
 * @author bvvy
 */
public class Sheet {
    private List<Column> columns;
    private SheetOption sheetOption;

    public Sheet(SheetOption sheetOption) {
        this.sheetOption = sheetOption;
    }

    public void addColumn(Column column) {
        columns.add(column);
    }
}
