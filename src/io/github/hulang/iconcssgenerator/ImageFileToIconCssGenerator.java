package io.github.hulang.iconcssgenerator;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

public class ImageFileToIconCssGenerator {
    private File inputDir;
    private OutputStream out;
    private AbstractCssRuleGenerator cssRuleGenerator;
    
    public void setInputDir(File dir) {
        inputDir = dir;
    }

    public void setOutputStream(OutputStream os) {
        out = os;
    }

    public void setCssRuleGenerator(AbstractCssRuleGenerator cssRuleGenerator) {
        this.cssRuleGenerator = cssRuleGenerator;
        this.cssRuleGenerator.setGenerator(this);
    }
    
    public AbstractCssRuleGenerator getCssRuleGenerator() {
        return cssRuleGenerator;
    }

    public String getRelativeBasePath() {
        return inputDir.getName().endsWith("/") ? inputDir.getName() : inputDir.getName() + "/";
    }

    public void generate() {
        final StringBuilder sbCss = new StringBuilder();
        FileTraversalUtil.traverse(inputDir, new FileTraversalUtil.Closure() {
            @Override
            public void f(File file, List<String> dirPath) {
                if (isImageFile(file)) {
                    sbCss.append(cssRuleGenerator.rule(file, dirPath));
                }
            }
        });
        try {
            out.write(sbCss.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] imageFileExts = {"jpg", "jpeg", "jpe", "gif", "png"};

    private boolean isImageFile(File file) {
        if (file.isDirectory())
            return false;
        int dotIndex = file.getName().lastIndexOf(".");
        if (dotIndex != -1) {
            return ArrayUtils.contains(imageFileExts, file.getName().substring(dotIndex + 1));
        }
        return false;
    }
}
