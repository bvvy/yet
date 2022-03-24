package org.bvvy.yet.yel;

import org.bvvy.yel.context.Context;
import org.bvvy.yel.exception.YelEvalException;
import org.bvvy.yel.exp.CompiledExpression;
import org.bvvy.yel.exp.ExpressionState;
import org.bvvy.yel.exp.YelExpression;
import org.bvvy.yel.exp.YelMessage;
import org.bvvy.yel.exp.ast.Node;
import org.bvvy.yel.parser.YelParserConfig;
import org.bvvy.yet.yel.context.YetContext;

public class YetExpression extends YelExpression {

    public YetExpression(Node ast, YelParserConfig configuration) {
        super(ast, configuration);
    }

    @Override
    public Object getValue(Context context) {
        YetContext yetContext = (YetContext) context;
        CompiledExpression compiledAst = this.getCompiledAst();
        if (compiledAst != null) {
            try {
                return compiledAst.getValue(context.getRootObject().getValue(), context);
            } catch (Exception e) {
                throw new YelEvalException(e, YelMessage.EXCEPTION_RUNNING_COMPILED_EXPRESSION);
            }
        }
        ExpressionState state = new YetExpressionState(yetContext, yetContext.getIteratorKey());
        checkCompile();
        return getAst().getValue(state);
    }

}
