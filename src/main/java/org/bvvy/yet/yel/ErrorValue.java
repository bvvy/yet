package org.bvvy.yet.yel;

public class ErrorValue {

    private final String name;

    public ErrorValue(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
