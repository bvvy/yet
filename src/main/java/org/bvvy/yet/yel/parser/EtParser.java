package org.bvvy.yet.yel.parser;

import org.bvvy.yel.exp.YelExpression;
import org.bvvy.yel.exp.ast.*;
import org.bvvy.yel.exp.token.ITokenKind;
import org.bvvy.yel.exp.token.Token;
import org.bvvy.yel.exp.token.TokenKind;
import org.bvvy.yel.exp.token.Tokenizer;
import org.bvvy.yel.parser.YelParser;
import org.bvvy.yel.parser.YelParserConfig;
import org.bvvy.yet.yel.YetExpression;
import org.bvvy.yet.yel.ast.*;
import org.bvvy.yet.yel.token.EtTokenKind;
import org.bvvy.yet.yel.token.EtTokenizer;

import java.util.List;

/**
 * @author bvvy
 * @date 2021/11/22
 */
public class EtParser extends YelParser {

    public EtParser() {
        this.configuration = new YelParserConfig();
    }

    public EtParser(YelParserConfig configuration) {
        this.configuration = configuration;
    }

    @Override
    public YelExpression parse(String expression) {
        Tokenizer tokenizer = new EtTokenizer(expression);
        List<Token> tokens = tokenizer.process();
        this.tokenStream = tokens;
        this.tokenStreamLength = tokens.size();
        this.constructedNodes.clear();
        this.tokenStreamPointer = 0;
        Node ast = this.eatExpression();
        return new YetExpression(ast, this.configuration);
    }

    protected Node eatExpression() {
        return eatRelationalExpression();
    }

    protected Node eatRelationalExpression() {
        Node expr = eatConcatExpression();
        if (peekToken(TokenKind.GT, TokenKind.LT, TokenKind.LE)
                || peekToken(TokenKind.GE, EtTokenKind.EQ, EtTokenKind.NE)) {
            Token t = takeToken();
            Node rhExpr = eatConcatExpression();
            ITokenKind tk = t.getKind();

            if (tk == TokenKind.GT) {
                return new OpGT(t.getStartPos(), t.getEndPos(), expr, rhExpr);
            } else if (tk == TokenKind.LT) {
                return new OpLT(t.getStartPos(), t.getEndPos(), expr, rhExpr);
            } else if (tk == TokenKind.LE) {
                return new OpLE(t.getStartPos(), t.getEndPos(), expr, rhExpr);
            } else if (tk == TokenKind.GE) {
                return new OpGE(t.getStartPos(), t.getEndPos(), expr, rhExpr);
            } else if (tk == EtTokenKind.EQ) {
                return new OpEQ("=", t.getStartPos(), t.getEndPos(), expr, rhExpr);
            } else {
                return new OpNE("<>", t.getStartPos(), t.getEndPos(), expr, rhExpr);
            }
        }
        return expr;

    }

    protected Node eatConcatExpression() {
        Node expr = eatSumExpression();
        while (peekToken(EtTokenKind.CONCAT)) {
            Token t = takeToken();
            Node rhExpr = eatSumExpression();
            expr = new OpConcat(t.getStartPos(), t.getEndPos(), expr, rhExpr);
        }
        return expr;
    }

    @Override
    protected Node eatSumExpression() {
        Node expr = eatProductExpression();
        while (peekToken(TokenKind.PLUS, TokenKind.MINUS)) {
            Token t = takeToken();
            Node rhExpr = eatProductExpression();
            //
            if (t.getKind() == TokenKind.PLUS) {
                expr = new OpPlus(t.getStartPos(), t.getEndPos(), expr, rhExpr);
            } else if (t.getKind() == TokenKind.MINUS) {
                expr = new OpMinus(t.getStartPos(), t.getEndPos(), expr, rhExpr);
            }
        }
        return expr;
    }

    @Override
    protected Node eatProductExpression() {
        Node expr = eatPowerExpression();
        while (peekToken(TokenKind.STAR, TokenKind.DIV)) {
            Token t = takeToken();
            Node rhExpr = eatPowerExpression();
            if (t.getKind() == TokenKind.STAR) {
                expr = new OpMultiply(t.getStartPos(), t.getEndPos(), expr, rhExpr);
            } else if (t.getKind() == TokenKind.DIV) {
                expr = new OpDivide(t.getStartPos(), t.getEndPos(), expr, rhExpr);
            }
        }
        return expr;
    }

    protected Node eatPowerExpression() {
        Node expr = eatPercentExpression();
        while (peekToken(EtTokenKind.POWER)) {
            Token t = takeToken();
            Node rhExpr = eatPercentExpression();
            expr = new OpPower(t.getStartPos(), t.getEndPos(), expr, rhExpr);
        }
        return expr;
    }

    protected Node eatPercentExpression() {
        Node expr = eatUnaryExpression();
        while (peekToken(EtTokenKind.PERCENT)) {
            Token t = takeToken();
            expr = new OpPercent(t.getStartPos(), t.getEndPos(), expr);
        }
        return expr;
    }

    @Override
    protected boolean maybeEatIndexer() {
        Token t = peekToken();
        if (!peekToken(TokenKind.LSQUARE, true)) {
            return false;
        }
        Node expr = eatExpression();
        eatToken(TokenKind.RSQUARE);
        this.constructedNodes.push(new YetIndexer(t.getStartPos(), t.getEndPos(), expr));
        return true;
    }

    protected boolean maybeEatMethodProperty(boolean nullSafeNavigation) {
        if (peekToken(TokenKind.IDENTIFIER)) {
            Token methodOrPropertyName = takeToken();
            Node[] args = maybeEatMethodArgs();
            if (args == null) {
                push(new ColumnReference(nullSafeNavigation, methodOrPropertyName.stringValue(),
                        methodOrPropertyName.getStartPos(), methodOrPropertyName.getEndPos()));
                return true;
            }
            push(new MethodReference(nullSafeNavigation, methodOrPropertyName.stringValue(),
                    methodOrPropertyName.getStartPos(), methodOrPropertyName.getEndPos(), args));
            return true;
        }
        return false;
    }
}
