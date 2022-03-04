package org.bvvy.yet.calculator;

import org.bvvy.yel.exp.Expression;
import org.bvvy.yet.YetConfig;
import org.bvvy.yet.sheet.*;

/**
 * @author bvvy
 * @date 2022/2/21
 */
public class SheetResolver {

    private YetConfig yetConfig;

    public SheetResolver(YetConfig yetConfig) {
        this.yetConfig = yetConfig;
    }

    public SheetCalculator resolve(Sheet sheet) {
        SheetOption sheetOption = sheet.getSheetOption();
        int start = sheetOption.getStart();
        int end = sheetOption.getEnd();
        InnerSheet innerSheet = new InnerSheet();
        for (Column column : sheet.getColumns()) {
            InnerColumn innerColumn = new InnerColumn(column.getName());
            Expression expression = yetConfig.getYelParser().parse(column.getFormula());
            ColumnOption columnOption = column.getColumnOption();
            if (columnOption != null) {
                start = columnOption.getStart();
                end = columnOption.getEnd();
            }
            for (int i = start; i <= end; i++) {
                innerColumn.addCell(new Cell(i, expression));
            }
            innerSheet.addColumn(innerColumn);
        }
        return new SheetCalculator(innerSheet);
    }
}
