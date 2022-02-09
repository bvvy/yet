package org.bvvy.yet;

import org.bvvy.yet.context.Context;
import org.bvvy.yet.sheet.Column;
import org.bvvy.yet.sheet.Selection;
import org.bvvy.yet.sheet.Sheet;
import org.bvvy.yet.sheet.SheetOption;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

/**
 * @author bvvy
 */
public class TestYet {

    @Test
    public void test() {

        Column policyYear = new Column("policyYear", "i + 1");
        Column sumAssured = new Column("sumAssured", "#sumAssured");
        Column standardPremium = new Column("standardPremium", "ROUND(sumAssured * premiumRate / 1000,2)");
        Column modalPremium = new Column("modalPremium", "standardPremium * modalFactor");
        Column annualizedPremium = new Column("annualizedPremium", "modalPremium * premiumFrequency");

        SheetOption sheetOption = new SheetOption();
        Sheet sheet = new Sheet(sheetOption);
        sheet.addColumn(policyYear);
        sheet.addColumn(sumAssured);
        sheet.addColumn(standardPremium);
        sheet.addColumn(modalPremium);
        sheet.addColumn(annualizedPremium);
//        sheet.calculate(context);
        Context context = new Context();
        Selection selection = new Selection();
        Yet yet = new Yet();
        yet.calculate(sheet, selection);

    }

}
