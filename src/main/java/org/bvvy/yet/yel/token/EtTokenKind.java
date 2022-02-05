package org.bvvy.yet.yel.token;

import org.bvvy.yel.exp.token.ITokenKind;

public enum EtTokenKind implements ITokenKind {
    CONCAT("&"),
    POWER("^"),
    PERCENT("%"),
    EQ("="),
    NE("<>");

    final char[] tokenChars;
    private final boolean hasPayload;

    EtTokenKind(String tokenString) {
        this.tokenChars = tokenString.toCharArray();
        this.hasPayload = this.tokenChars.length == 0;
    }

    EtTokenKind() {
        this("");
    }

    public int getLength() {
        return tokenChars.length;
    }

    @Override
    public char[] getTokenChars() {
        return tokenChars;
    }
}
