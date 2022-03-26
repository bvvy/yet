package org.bvvy.yet.function.support;

import org.bvvy.yel.context.method.GlobalFunction;
import org.bvvy.yel.context.method.YelFunction;

public class If implements YelFunction {

    @GlobalFunction
    public Object ifFun(Boolean condition, Object trueExp, Object falseExp) {
        return condition ? trueExp : falseExp;
    }
}
