package org.bvvy.yet.calculator;

import org.bvvy.yel.context.Context;
import org.bvvy.yet.sheet.Selection;
import org.bvvy.yet.sheet.SelectionOption;

import java.util.List;

public class SheetContext {

    private final List<SelectionOption> selectionOptions;

    public SheetContext(Selection selection) {
        this.selectionOptions = selection.getOptions();
    }

    public List<SelectionOption> getSelectionOptions() {
        return selectionOptions;
    }

    public Context getYelContext() {
        return null;
    }
}
