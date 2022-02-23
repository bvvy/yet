package org.bvvy.yet.sheet;

import java.util.ArrayList;
import java.util.List;

public class Selection {

    private Object env;

    private List<SelectionOption> options = new ArrayList<>();

    public Selection() {
    }

    public Selection(Object env, List<SelectionOption> options) {
        this.env = env;
        this.options = options;
    }

    public List<SelectionOption> getOptions() {
        return options;
    }

    public Object getEnv() {
        return env;
    }
}
