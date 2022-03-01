package org.bvvy.yet.calculator;

import org.bvvy.yet.sheet.Cell;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author bvvy
 * @date 2022/2/21
 */
public class InnerColumn {

    private String name;
    private Map<Integer, Cell> cells = new ConcurrentHashMap<>();

    public InnerColumn(String name) {
        this.name = name;
    }

    public void addCell(Cell cell) {
        this.cells.put(cell.getIndex(), cell);
    }

    public void calculate(SheetContext context) {
    }

    public Cell getCell(int index) {
        return cells.get(index);
    }

    public String getName() {
        return this.name;
    }
}
