package org.bvvy.yet.yel.ast;

import org.bvvy.yel.exp.ast.Node;
import org.bvvy.yel.exp.ast.Operator;

/**
 * @author bvvy
 * @date 2022/2/3
 */
public class OpPower extends Operator {
    public OpPower(int startPos, int endPos, Node ... operand) {
        super("^", startPos, endPos, operand);
    }
}
