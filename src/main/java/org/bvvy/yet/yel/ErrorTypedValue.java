package org.bvvy.yet.yel;

import org.bvvy.yel.exp.TypedValue;

public class ErrorTypedValue extends TypedValue {

    public static final ErrorTypedValue REF_ERR = new ErrorTypedValue("#REF!");
    public static final ErrorTypedValue DIV0 = new ErrorTypedValue("#DIV/0");

    public ErrorTypedValue(Object value) {
        super(value);
    }
}
