package org.bvvy.yet.calculator;

import org.bvvy.yel.exp.YelExpression;
import org.bvvy.yel.exp.ast.*;
import org.bvvy.yel.parser.YelParser;
import org.bvvy.yet.sheet.Cell;
import org.bvvy.yet.sheet.Selection;
import org.bvvy.yet.sheet.SelectionOption;
import org.bvvy.yet.yel.context.YetContext;

import java.util.ArrayDeque;
import java.util.Deque;
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
        for (SelectionOption option : options) {
            InnerColumn column = innerSheet.getColumn(option.getColumnName());
            YetContext yetContext = new YetContext(selection.getEnv(), innerSheet);
            YelExpression expression = yelParser.parse(column.getName());
            Node ast = expression.getAst();
            Deque<Object> stack = new ArrayDeque<>();
            if (ast instanceof MethodReference) {

            } else if (ast instanceof CompoundExpression) {
                Node[] children = ((CompoundExpression) ast).getChildren();
                // column
                if (children[0] instanceof PropertyOrFieldReference) {
                    PropertyOrFieldReference columnAst = (PropertyOrFieldReference) children[0];
                }
            } else if (ast instanceof Operator) {

            } else if (ast instanceof Literal) {

            }
            for (int index : option.getIndexes()) {
                Cell cell = column.getCell(index);
                cell.getValue(yetContext);
            }
        }
        return null;
    }
}
