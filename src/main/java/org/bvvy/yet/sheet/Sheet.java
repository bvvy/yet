package org.bvvy.yet.sheet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bvvy
 */
public class Sheet {
    private List<Column> columns;
    private SheetOption sheetOption;

    public SheetOption getSheetOption() {
        return sheetOption;
    }


    public Sheet(SheetOption sheetOption) {
        this.sheetOption = sheetOption;
        this.columns = new ArrayList<>();
    }

    public void addColumn(Column column) {
        columns.add(column);
    }

    public List<Column> getColumns() {
        return this.columns;
    }
}
