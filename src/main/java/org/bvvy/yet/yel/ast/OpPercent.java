package org.bvvy.yet.yel.ast;

import org.bvvy.yel.exp.ast.Node;
import org.bvvy.yel.exp.ast.Operator;

/**
 * @author bvvy
 * @date 2022/2/5
 */
public class OpPercent extends Operator {
    public OpPercent(int startPos, int endPos, Node expr) {
        super("%", startPos, endPos, expr);

    }
}
