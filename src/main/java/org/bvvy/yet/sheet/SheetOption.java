package org.bvvy.yet.sheet;

public class SheetOption {

    private String iteratorKey = "i";
    private int start = 0;
    private int end = 256;

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public void setIteratorKey(String iteratorKey) {
        this.iteratorKey = iteratorKey;
    }

    public String getIteratorKey() {
        return iteratorKey;
    }


}
