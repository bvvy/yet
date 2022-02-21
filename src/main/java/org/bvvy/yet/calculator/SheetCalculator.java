package org.bvvy.yet.calculator;

import org.bvvy.yet.sheet.Selection;
import org.bvvy.yet.sheet.SelectionOption;

import java.util.List;

/**
 * @author bvvy
 * @date 2022/2/21
 */
public class SheetCalculator {
    private InnerSheet innerSheet;

    public SheetCalculator(InnerSheet innerSheet) {
        this.innerSheet = innerSheet;
    }

    public SheetValue calculate(Selection selection) {
        List<SelectionOption> options = selection.getOptions();
        for (SelectionOption option : options) {
            InnerColumn column = innerSheet.getColumn(option.getColumnName());
        }
        return null;
    }
}
