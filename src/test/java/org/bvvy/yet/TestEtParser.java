package org.bvvy.yet;

import org.bvvy.yel.exp.YelExpression;
import org.bvvy.yel.exp.ast.OpEQ;
import org.bvvy.yel.exp.ast.OpNE;
import org.bvvy.yet.yel.ast.OpConcat;
import org.bvvy.yet.yel.ast.OpPercent;
import org.bvvy.yet.yel.ast.OpPower;
import org.bvvy.yet.yel.parser.EtParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author bvvy
 * @date 2022/2/5
 */
public class TestEtParser {

    @Test
    public void testParse() {
        EtParser etParser = new EtParser();
        YelExpression expr = etParser.parse("1 & 1");
        Assertions.assertEquals(OpConcat.class, expr.getAst().getClass());
        expr = etParser.parse("1 ^ 1");
        Assertions.assertEquals(OpPower.class, expr.getAst().getClass());
        expr = etParser.parse("1 = 1");
        Assertions.assertEquals(OpEQ.class, expr.getAst().getClass());
        expr = etParser.parse("1 <> 1");
        Assertions.assertEquals(OpNE.class, expr.getAst().getClass());
        expr = etParser.parse("1%");
        Assertions.assertEquals(OpPercent.class, expr.getAst().getClass());
    }

}
