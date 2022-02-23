package org.bvvy.yet.calculator;

import org.bvvy.yel.exp.Expression;
import org.bvvy.yet.YetConfig;
import org.bvvy.yet.sheet.Column;
import org.bvvy.yet.sheet.ColumnOption;
import org.bvvy.yet.sheet.Sheet;
import org.bvvy.yet.sheet.SheetOption;

import java.util.HashMap;
import java.util.Map;

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
        Map<Integer, Expression> cells = new HashMap<>();
        Map<String, InnerColumn> innerColumns = new HashMap<>();
        for (Column column : sheet.getColumns()) {
            Expression expression = yetConfig.getYelParser().parse(column.getFormula());
            ColumnOption columnOption = column.getColumnOption();
            if (columnOption != null) {
                start = columnOption.getStart();
                end = columnOption.getEnd();
            }
            for (int i = start; i <= end; i++) {
                cells.put(i, expression);
            }
            InnerColumn innerColumn = new InnerColumn(cells);
            innerColumns.put(column.getName(), innerColumn);
        }
        InnerSheet innerSheet = new InnerSheet(innerColumns);
        return new SheetCalculator(innerSheet);
    }
}
