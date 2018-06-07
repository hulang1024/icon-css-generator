package io.github.hulang.iconcssgenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Shell {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            printHelp();
            return;
        }
        
        String source = args[0];
        String destination = args[1];
        String relativeBasePath = null;
        if (args.length > 2) {
            relativeBasePath = args[2];
        }

        ImageFileToIconCssGenerator generator = new ImageFileToIconCssGenerator();
        
        DefaultCssRuleGenerator cssRuleGenerator = new DefaultCssRuleGenerator();
        cssRuleGenerator.setRelativeBasePath(relativeBasePath);
        generator.setCssRuleGenerator(cssRuleGenerator);

        File dir = new File(source);
        generator.setInputDir(dir);
        
        OutputStream os = new FileOutputStream(new File(destination));
        generator.setOutputStream(os);

        generator.generate();
    }

    private static void printHelp() {
        System.out.println("Usage:");
        System.out.println("iconcssgenerator source destination [relativeBasePath]");
    }
}
