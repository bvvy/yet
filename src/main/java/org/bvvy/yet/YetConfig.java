package org.bvvy.yet;

import org.bvvy.yel.parser.YelParser;
import org.bvvy.yet.yel.parser.EtParser;

public class YetConfig {

    private YelParser yelParser;

    public YetConfig() {
        this.yelParser = new EtParser();
    }

    public YelParser getYelParser() {
        return this.yelParser;
    }
}
