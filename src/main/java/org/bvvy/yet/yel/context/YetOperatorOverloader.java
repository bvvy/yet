package org.bvvy.yet.yel.context;

import org.bvvy.yel.context.overloader.DefaultOperatorOverloader;
import org.bvvy.yet.yel.ErrorValue;

public class YetOperatorOverloader extends DefaultOperatorOverloader {

    @Override
    public Object add(Object left, Object right) {
        if (left instanceof ErrorValue) {
            return left;
        }
        else if (right instanceof ErrorValue) {
            return right;
        }
        return super.add(left, right);
    }

    @Override
    public Object minus(Object left, Object right) {
        if (left instanceof ErrorValue) {
            return left;
        }
        else if (right instanceof ErrorValue) {
            return right;
        }
        return super.minus(left, right);
    }

    @Override
    public Object mod(Object left, Object right) {
        if (left instanceof ErrorValue) {
            return left;
        }
        else if (right instanceof ErrorValue) {
            return right;
        }
        return super.mod(left, right);
    }

    @Override
    public Object multiply(Object left, Object right) {
        if (left instanceof ErrorValue) {
            return left;
        }
        else if (right instanceof ErrorValue) {
            return right;
        }
        return super.multiply(left, right);
    }

    @Override
    public Object divide(Object left, Object right) {
        if (left instanceof ErrorValue) {
            return left;
        }
        else if (right instanceof ErrorValue) {
            return right;
        }
        return super.divide(left, right);
    }
}
