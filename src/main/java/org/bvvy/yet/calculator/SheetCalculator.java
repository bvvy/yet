package org.bvvy.yet.calculator;

import org.bvvy.yel.parser.YelParser;
import org.bvvy.yet.sheet.Selection;
import org.bvvy.yet.sheet.SelectionOption;
import org.bvvy.yet.yel.context.YetContext;

import java.util.List;

/**
 * @author bvvy
 * @date 2022/2/21
 */
public class SheetCalculator {
    private InnerSheet innerSheet;
    private YelParser yelParser;

    public SheetCalculator(InnerSheet innerSheet) {
        this.innerSheet = innerSheet;
    }

    public SheetValue calculate(Selection selection) {
        List<SelectionOption> options = selection.getOptions();
        YetContext yetContext = new YetContext(selection.getEnv(), innerSheet);
        for (SelectionOption option : options) {
            InnerColumn column = innerSheet.getColumn(option.getColumnName());
            for (int index : option.getIndexes()) {
                Cell cell = column.getCell(index);
                cell.getValue(yetContext);
            }
        }
        return null;
    }
}
