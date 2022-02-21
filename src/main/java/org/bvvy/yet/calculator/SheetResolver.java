package org.bvvy.yet.calculator;

import org.bvvy.yel.exp.Expression;
import org.bvvy.yet.YetConfig;
import org.bvvy.yet.sheet.Column;
import org.bvvy.yet.sheet.Sheet;
import org.bvvy.yet.sheet.SheetOption;

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
        sheetOption.getIteratorKey();
        for (Column column : sheet.getColumns()) {
            Expression expression = yetConfig.getYelParser().parse(column.getFormula());
        }
        InnerSheet innerSheet = new InnerSheet();
        return new SheetCalculator(innerSheet);
    }
}
