package demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import io.github.hulang.iconcssgenerator.DefaultCssRuleGenerator;
import io.github.hulang.iconcssgenerator.ImageFileToIconCssGenerator;

public class ImageFileToIconCssGeneratorDemo {

    public static void main(String[] args) throws Exception {
        ImageFileToIconCssGenerator generator = new ImageFileToIconCssGenerator();
        generator.setCssRuleGenerator(new DefaultCssRuleGenerator());
        File dir = new File("images/icons");
        generator.setInputDir(dir);
        OutputStream os = new FileOutputStream(new File(dir.getParent(), "icons_test.css"));
        generator.setOutputStream(os);
        generator.generate();
    }
}
