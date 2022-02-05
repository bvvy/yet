package org.bvvy.yet.yel.token;

import org.bvvy.yel.exp.token.Token;
import org.bvvy.yel.exp.token.TokenKind;
import org.bvvy.yel.exp.token.Tokenizer;

import java.util.List;

/**
 * @author bvvy
 * @date 2021/11/22
 */
public class EtTokenizer extends Tokenizer {

    public EtTokenizer(String expression) {
        super(expression);
    }

    @Override
    public List<Token> process() {
        while (this.pos < this.max) {
            char ch = this.charsToProcess[this.pos];
            if (isIdentifierStart(ch)) {
                lexIdentifier();
            } else {
                switch (ch) {
                    case '+':
                        pushCharToken(TokenKind.PLUS);
                        break;
                    case '_':
                        lexIdentifier();
                        break;
                    case '-':
                        pushCharToken(TokenKind.MINUS);
                        break;
                    case ':':
                        pushCharToken(TokenKind.COLON);
                        break;
                    case '.':
                        pushCharToken(TokenKind.DOT);
                        break;
                    case ',':
                        pushCharToken(TokenKind.COMMA);
                        break;
                    case '*':
                        pushCharToken(TokenKind.STAR);
                        break;
                    case '/':
                        pushCharToken(TokenKind.DIV);
                        break;
                    case '%':
                        pushCharToken(EtTokenKind.PERCENT);
                        break;
                    case '(':
                        pushCharToken(TokenKind.LPAREN);
                        break;
                    case ')':
                        pushCharToken(TokenKind.RPAREN);
                        break;
                    case '[':
                        pushCharToken(TokenKind.LSQUARE);
                        break;
                    case ']':
                        pushCharToken(TokenKind.RSQUARE);
                        break;
                    case '#':
                        pushCharToken(TokenKind.HASH);
                        break;
                    case '^':
                        pushCharToken(EtTokenKind.POWER);
                        break;
                    case '=':
                        pushPairToken(EtTokenKind.EQ);
                        break;
                    case '&':
                        pushCharToken(EtTokenKind.CONCAT);
                        break;
                    case '?':
                        if (isTwoCharToken(TokenKind.ELVIS)) {
                            pushPairToken(TokenKind.ELVIS);
                        } else if (isTwoCharToken(TokenKind.SAFE_NAVI)) {
                            pushPairToken(TokenKind.SAFE_NAVI);
                        } else {
                            pushCharToken(TokenKind.QMARK);
                        }
                        break;
                    case '>':
                        if (isTwoCharToken(TokenKind.GE)) {
                            pushPairToken(TokenKind.GE);
                        } else {
                            pushCharToken(TokenKind.GT);
                        }
                        break;
                    case '<':
                        if (isTwoCharToken(TokenKind.LE)) {
                            pushPairToken(TokenKind.LE);
                        } else if (isTwoCharToken(EtTokenKind.NE)) {
                            pushPairToken(EtTokenKind.NE);
                        } else {
                            pushPairToken(TokenKind.LT);
                        }
                        break;
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        lexNumberLiteral(ch == '0');
                        break;
                    case ' ':
                    case '\t':
                    case '\r':
                    case '\n':
                        this.pos++;
                        break;
                    case '\'':
                        lexQuotedStringLiteral();
                        break;
                    case '"':
                        lexDoubleQuotedStringLiteral();
                        break;
                    case 0:
                        //
                        this.pos++;
                        break;
                }
            }
        }
        return this.tokens;

    }
}
