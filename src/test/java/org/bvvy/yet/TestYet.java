package org.bvvy.yet;

import org.bvvy.yet.calculator.SheetValue;
import org.bvvy.yet.sheet.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
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
        Column sumAssured = new Column("sumAssured", "#sumAssured");
        Column premRate = new Column("premiumRate", "200");
        Column premiumFrequency = new Column("premiumFrequency", "12");
        Column modalFactor = new Column("modalFactor", "0.0853");

        Column standardPremium = new Column("standardPremium", "sumAssured[i] * premiumRate[i] / 1000");
        Column totalStandardPremium = new Column("totalStandardPremium", "IF(i=0,0,totalStandardPremium[i-1] + standardPremium[i])");
        Column modalPremium = new Column("modalPremium", "standardPremium[i] * modalFactor[i]");
        Column annualizedPremium = new Column("annualizedPremium", "modalPremium[i] * premiumFrequency[i]");

        SheetOption sheetOption = new SheetOption();
        Sheet sheet = new Sheet(sheetOption);
        sheet.addColumn(sumAssured);
        sheet.addColumn(premRate);
        sheet.addColumn(premiumFrequency);
        sheet.addColumn(modalFactor);
        sheet.addColumn(standardPremium);
        sheet.addColumn(modalPremium);
        sheet.addColumn(annualizedPremium);
        sheet.addColumn(totalStandardPremium);
        SelectionOption option = new SelectionOption("totalStandardPremium", new int[]{2});
        SelectionOption option2 = new SelectionOption("modalPremium", new int[]{1});
        SelectionOption option3 = new SelectionOption("standardPremium", new int[]{1});
        SelectionOption option4 = new SelectionOption("annualizedPremium", new int[]{10});
        Selection selection = new Selection(env, Arrays.asList(option, option2, option3, option4));
        Yet yet = new Yet();
        SheetValue sheetValue = yet.calculate(sheet, selection);
        System.out.println(sheetValue);

    }

}
