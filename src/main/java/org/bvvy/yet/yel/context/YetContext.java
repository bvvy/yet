package org.bvvy.yet.yel.context;

import org.bvvy.yel.context.Context;
import org.bvvy.yel.context.accessor.MapAccessor;
import org.bvvy.yel.context.accessor.PropertyAccessor;
import org.bvvy.yel.context.accessor.ReflectivePropertyAccessor;
import org.bvvy.yel.context.comparator.StandardTypeComparator;
import org.bvvy.yel.context.comparator.TypeComparator;
import org.bvvy.yel.context.method.GlobalMethodResolver;
import org.bvvy.yel.context.method.MethodResolver;
import org.bvvy.yel.context.method.StandardTypeConverter;
import org.bvvy.yel.context.method.TypeConverter;
import org.bvvy.yel.context.overloader.OperatorOverloader;
import org.bvvy.yel.exp.ExpressionState;
import org.bvvy.yel.exp.TypedValue;
import org.bvvy.yet.calculator.InnerSheet;
import org.bvvy.yet.function.support.If;
import org.bvvy.yet.function.support.Round;

import java.math.RoundingMode;
import java.util.*;

public class YetContext implements Context {

    private final TypedValue rootObject;
    private String iteratorKey;
    private final Deque<Integer> indexContext = new ArrayDeque<>();
    private TypedValue env;

    public YetContext() {
        this.rootObject = TypedValue.NULL;
    }

    public YetContext(Object env, InnerSheet sheet) {
        this.env = new TypedValue(env);
        this.rootObject = new TypedValue(sheet);
    }

    public TypedValue getEnv() {
        return env;
    }

    public Integer getActiveIndexContext() {
        return indexContext.element();
    }

    public void pushIndexContext(int index) {
        indexContext.push(index);
    }

    public void popIndexContext() {
        indexContext.pop();
    }

    public ExpressionState getYelExpressionState(Context context) {
        return new ExpressionState(context);
    }

    public void setIteratorKey(String iteratorKey) {
        this.iteratorKey = iteratorKey;
    }

    public String getIteratorKey() {
        return iteratorKey;
    }

    @Override
    public List<PropertyAccessor> getPropertyAccessors() {
        return Arrays.asList(
                new SheetAccessor(),
                new MapAccessor(),
                new ReflectivePropertyAccessor()
        );
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
        GlobalMethodResolver methodResolver = new GlobalMethodResolver();
        methodResolver.registerFunction("ROUND", new Round());
        methodResolver.registerFunction("ROUNDUP", new Round(RoundingMode.UP));
        methodResolver.registerFunction("ROUNDDOWN", new Round(RoundingMode.DOWN));
        methodResolver.registerFunction("IF", new If());
        return Collections.singletonList(methodResolver);
    }

    @Override
    public TypeConverter getTypeConverter() {
        return new StandardTypeConverter();
    }

    @Override
    public OperatorOverloader getOperatorOverloader() {
        return new YetOperatorOverloader();
    }
}
