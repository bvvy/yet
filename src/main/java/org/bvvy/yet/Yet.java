package org.bvvy.yet;

import org.bvvy.yet.calculator.SheetCalculator;
import org.bvvy.yet.calculator.SheetResolver;
import org.bvvy.yet.calculator.SheetValue;
import org.bvvy.yet.sheet.Selection;
import org.bvvy.yet.sheet.Sheet;

/**
 * @author bvvy
 */
public class Yet {

    private YetConfig yetConfig;
    private SheetResolver sheetResolver;

    public Yet() {
        this.sheetResolver = new SheetResolver(yetConfig);
    }

    public SheetValue calculate(Sheet sheet, Selection selection) {
        SheetCalculator calculator = sheetResolver.resolve(sheet);
        return calculator.calculate(selection);
    }
}
