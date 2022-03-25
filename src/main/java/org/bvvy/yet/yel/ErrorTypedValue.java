package org.bvvy.yet.yel;

import org.bvvy.yel.exp.TypedValue;

public class ErrorTypedValue extends TypedValue {

    public static final ErrorTypedValue REF_ERR = new ErrorTypedValue(new ErrorValue("#REF!"));
    public static final ErrorTypedValue DIV0_ERR = new ErrorTypedValue(new ErrorValue("#DIV/0"));

    public ErrorTypedValue(Object value) {
        super(value);
    }
}
