package org.bvvy.yet.yel;

import org.bvvy.yel.context.Context;
import org.bvvy.yel.exp.ExpressionState;
import org.bvvy.yel.exp.YelExpression;
import org.bvvy.yel.exp.ast.Node;
import org.bvvy.yel.parser.YelParserConfig;

public class YetExpression extends YelExpression {

    public YetExpression(Node ast, YelParserConfig configuration) {
        super(ast, configuration);
    }

    @Override
    protected ExpressionState createExpressionState(Context context) {
        return new YetExpressionState(context);
    }
}
