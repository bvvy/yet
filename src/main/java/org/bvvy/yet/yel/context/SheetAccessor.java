package org.bvvy.yet.yel.context;

import org.bvvy.yel.context.Context;
import org.bvvy.yel.context.accessor.PropertyAccessor;
import org.bvvy.yel.exp.TypedValue;
import org.bvvy.yet.calculator.InnerColumn;
import org.bvvy.yet.calculator.InnerSheet;

public class SheetAccessor implements PropertyAccessor {

    @Override
    public boolean canRead(Context context, Object target, String name) {
        return (target instanceof InnerSheet && ((InnerSheet) target).containsColumn(name));
    }

    @Override
    public TypedValue read(Context context, Object target, String name) {
        InnerSheet sheet = (InnerSheet) target;
        InnerColumn value = sheet.getColumn(name);
        return new TypedValue(value);
    }
}
