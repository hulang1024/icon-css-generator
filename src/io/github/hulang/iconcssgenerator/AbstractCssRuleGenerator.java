package io.github.hulang.iconcssgenerator;

import java.io.File;
import java.util.List;

public abstract class AbstractCssRuleGenerator {
    protected ImageFileToIconCssGenerator generator;
    
    protected void setGenerator(ImageFileToIconCssGenerator generator) {
        this.generator = generator;
    }

    public StringBuilder rule(File imageFile, List<String> dirPath) {
        return new StringBuilder()
            .append(selector(imageFile, dirPath))
            .append(declarationBlock(imageFile, dirPath))
            .append("\n");
    }

    public abstract StringBuilder selector(File imageFile, List<String> dirPath);
    public abstract StringBuilder declarationBlock(File imageFile, List<String> dirPath);
}
