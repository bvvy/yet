package org.bvvy.yet.calculator;

import org.bvvy.yet.sheet.Selection;
import org.bvvy.yet.sheet.SelectionOption;

import java.util.Arrays;
import java.util.List;

/**
 * @author bvvy
 * @date 2022/2/21
 */
public class SheetValue {
    private InnerSheet innerSheet;
    private Selection selection;

    public SheetValue(InnerSheet innerSheet, Selection selection) {
        this.innerSheet = innerSheet;
        this.selection = selection;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<SelectionOption> options = selection.getOptions();
        sb.append("# | ");
        for (int i = 0, optionsSize = options.size(); i < optionsSize; i++) {
            SelectionOption option = options.get(i);
            sb.append(option.getColumnName());
            if (optionsSize - 1 != i) {
                sb.append(" | ");
            }
        }
        sb.append("\n");
        int maxIndex = options
                .stream()
                .flatMapToInt(option -> Arrays.stream(option.getIndexes()))
                .max()
                .orElse(0);
        for (int i = 0; i <= maxIndex; i++) {
            sb.append(i).append(" | ");
            for (SelectionOption option : options) {
                String columnName = option.getColumnName();
                Object value = innerSheet.getColumn(columnName).getCell(i).getValue();
                sb.append(value).append(" | ");
            }
            sb.append("\n");

        }
        return sb.toString();
    }
}
