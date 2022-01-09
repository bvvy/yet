package org.bvvy.yet;

import org.bvvy.yet.sheet.Column;
import org.bvvy.yet.sheet.Sheet;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author bvvy
 */
public class TestYet {

    @Test
    public void test() {

        Column policyYear = new Column("policyYear", "i + 1");
        Column standardPremium = new Column("standardPremium", "ROUND(sumAssured * premiumRate[i] / 1000,2)");
        Column modalPremium = new Column("modalPremium", "standardPremium * modalFactor");
        Column annualizedPremium = new Column("annualizedPremium", "modalPremium * premiumFrequency");
        List<Column> columns = Arrays.asList(policyYear, standardPremium, modalPremium, annualizedPremium);
        Sheet sheet = new Sheet(columns);
//        Yet yet = new Yet(sheet);
//        yet.calculate("standardPremium");
//        yet.calculate("policyYear[1]");
//        yet.calculate("modalPremium[1]");
//        yet.calculate("annualizedPremium[1]");
    }

}
