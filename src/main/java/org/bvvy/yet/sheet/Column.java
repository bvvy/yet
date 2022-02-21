package org.bvvy.yet.sheet;

/**
 * @author bvvy
 */
public class Column {
    private String name;
    private String formula;
    private ColumnOption columnOption;

    public Column(String name, String formula) {
        this.name = name;
        this.formula = formula;
    }

    public Column(String name, String formula, ColumnOption columnOption) {
        this.name = name;
        this.formula = formula;
    }

    public String getName() {
        return name;
    }

    public String getFormula() {
        return formula;
    }

    public ColumnOption getColumnOption() {
        return columnOption;
    }
}
