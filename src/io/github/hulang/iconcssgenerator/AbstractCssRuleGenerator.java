package io.github.hulang.iconcssgenerator;

import java.io.File;
import java.util.List;

public abstract class AbstractCssRuleGenerator {
    protected ImageFileToIconCssGenerator generator;

    protected void setGenerator(ImageFileToIconCssGenerator generator) {
        this.generator = generator;
    }

    public abstract StringBuilder rule(File imageFile, List<String> path);
}
