package org.bvvy.yet;

import org.bvvy.yel.parser.YelParser;

public class YetConfig {

    private YelParser yelParser;

    public YetConfig() {
        this.yelParser = new YelParser();
    }

    public YelParser getYelParser() {
        return this.yelParser;
    }
}
