package org.bvvy.yet.sheet;

public class SheetOption {

    private String iteratorKey = "i";
    private int start = 0;
    private int end = 255;

    public SheetOption() {
    }

    public SheetOption(String iteratorKey, int start, int end) {
        this.iteratorKey = iteratorKey;
        this.start = start;
        this.end = end;
    }

    public SheetOption(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public String getIteratorKey() {
        return iteratorKey;
    }


}
