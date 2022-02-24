package org.bvvy.yet.yel.context;

import org.bvvy.yel.context.Context;
import org.bvvy.yel.context.accessor.PropertyAccessor;
import org.bvvy.yel.context.comparator.StandardTypeComparator;
import org.bvvy.yel.context.comparator.TypeComparator;
import org.bvvy.yel.context.method.GlobalMethodResolver;
import org.bvvy.yel.context.method.MethodResolver;
import org.bvvy.yel.context.method.StandardTypeConverter;
import org.bvvy.yel.context.method.TypeConverter;
import org.bvvy.yel.exp.TypedValue;

import java.util.Collections;
import java.util.List;

public class YetContext implements Context {

    private TypedValue rootObject;

    public YetContext() {
        this.rootObject = TypedValue.NULL;
    }

    public YetContext(Object rootObject) {
        this.rootObject = new TypedValue(rootObject);
    }

    @Override
    public List<PropertyAccessor> getPropertyAccessors() {
        return Collections.singletonList(new SheetAccessor());
    }

    @Override
    public TypedValue getRootObject() {
        return this.rootObject;
    }

    @Override
    public TypeComparator getTypeComparator() {
        return new StandardTypeComparator();
    }

    @Override
    public List<MethodResolver> getMethodResolvers() {
        return Collections.singletonList(new GlobalMethodResolver());
    }

    @Override
    public TypeConverter getTypeConverter() {
        return new StandardTypeConverter();
    }
}