package org.bvvy.yet;

import org.bvvy.yet.calculator.SheetValue;
import org.bvvy.yet.sheet.*;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author bvvy
 */
public class TestYet {

    @Test
    public void test() {

        Map<String, Object> env = new HashMap<>();
        env.put("sumAssured", 1000);
        env.put("premRate", 200);
        env.put("premiumFrequency", 12);
        env.put("modalFactor", 0.0853);

        Column standardPremium = new Column("standardPremium", "ROUND(sumAssured * premiumRate / 1000,2)");
        Column modalPremium = new Column("modalPremium", "standardPremium * modalFactor");
        Column annualizedPremium = new Column("annualizedPremium", "modalPremium * premiumFrequency");

        SheetOption sheetOption = new SheetOption();
        Sheet sheet = new Sheet(sheetOption);
        sheet.addColumn(standardPremium);
        sheet.addColumn(modalPremium);
        sheet.addColumn(annualizedPremium);

        SelectionOption option = new SelectionOption("standardPremium", new int[]{1});
        Selection selection = new Selection(env, Collections.singletonList(option));
        Yet yet = new Yet();
        SheetValue sheetValue = yet.calculate(sheet, selection);
        System.out.println(sheetValue);

    }

}
