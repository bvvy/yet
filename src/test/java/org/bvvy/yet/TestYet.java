package org.bvvy.yet;

import org.bvvy.yet.calculator.SheetValue;
import org.bvvy.yet.sheet.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author bvvy
 */
public class TestYet {

    @Test
    public void test() {

        LocalDateTime start = LocalDateTime.now();
        Map<String, Object> env = new HashMap<>();
        env.put("sumAssured", 1000);
        env.put("modalFactor", 25);
        env.put("premRate", 40000);
        env.put("premFrequency", 4.0);
        env.put("xRate", Arrays.asList(1.1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1.1));
        Column sumAssured = new Column("sumAssured", "#sumAssured");
        Column premRate = new Column("premiumRate", "#premRate ^ (1.0 / 2.0)");
        Column premiumFrequency = new Column("premiumFrequency", "#premFrequency");
        Column modalFactor = new Column("modalFactor", "#modalFactor%");
        Column xRate = new Column("xRate", "#xRate[i]");

        Column standardPremium = new Column("standardPremium", "sumAssured * premiumRate / 1000");
        Column totalStandardPremium = new Column("totalStandardPremium", "IF(i=0,0,totalStandardPremium[i-1] + standardPremium)");
        Column modalPremium = new Column("modalPremium", "standardPremium * modalFactor");
        Column annualizedPremium = new Column("annualizedPremium", "modalPremium * premiumFrequency * xRate");
        Column totalAnnualizedPremium = new Column("totalAnnualizedPremium", "IF(i=0,0,totalAnnualizedPremium[i-1] + annualizedPremium)");
        Column totalAll = new Column("totalAll", "totalStandardPremium + totalAnnualizedPremium");

        SheetOption sheetOption = new SheetOption(0, 1000);
        Sheet sheet = new Sheet(sheetOption);
        sheet.addColumn(sumAssured);
        sheet.addColumn(premRate);
        sheet.addColumn(premiumFrequency);
        sheet.addColumn(modalFactor);
        sheet.addColumn(standardPremium);
        sheet.addColumn(modalPremium);
        sheet.addColumn(annualizedPremium);
        sheet.addColumn(totalStandardPremium);
        sheet.addColumn(totalAnnualizedPremium);
        sheet.addColumn(totalAll);
        sheet.addColumn(xRate);
        SelectionOption totalAnnualizedPremium_ = new SelectionOption("totalAnnualizedPremium", new int[]{10});
        SelectionOption totalAll_ = new SelectionOption("totalAll", new int[]{10});
        SelectionOption totalStandardPremium_ = new SelectionOption("totalStandardPremium", new int[]{10});
        Selection selection = new Selection(env, Arrays.asList(
                totalStandardPremium_,
                totalAnnualizedPremium_,
                totalAll_
        )
        );
        Yet yet = new Yet();
        SheetValue sheetValue = yet.calculate(sheet, selection);
        System.out.println(start.until(LocalDateTime.now(), ChronoUnit.MILLIS));
        System.out.println(sheetValue);

    }

}
