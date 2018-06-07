package io.github.hulang.iconcssgenerator;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class DefaultCssRuleGenerator extends AbstractCssRuleGenerator {
    @Override
    public StringBuilder rule(File imageFile, List<String> path) {
        return new StringBuilder()
            .append(selector(imageFile, path))
            .append(" {\n")
            .append("  ").append(declarationBlock(imageFile, path))
            .append("\n}\n");
    }
    
    private StringBuilder selector(File imageFile, List<String> dirPath) {
        String normalizeName = imageFile.getName();
        normalizeName = normalizeName.substring(0, imageFile.getName().lastIndexOf("."));
        normalizeName = normalizeName.replaceAll("_", "-");
        normalizeName = normalizeName.toLowerCase();
        
        StringBuilder selector = new StringBuilder(".icon-");
        if (!dirPath.isEmpty()) {
            selector.append(StringUtils.join(dirPath.toArray(), "-")).append("-");
        }
        selector.append(normalizeName);

        return selector;
    }


    private StringBuilder declarationBlock(File imageFile, List<String> dirPath) {
        StringBuilder url = new StringBuilder(generator.getRelativeBasePath());
        if (!dirPath.isEmpty()) {
            url.append(StringUtils.join(dirPath.toArray(), "/")).append("/");
        }
        url.append(imageFile.getName());

        return new StringBuilder()
            .append("background: url(").append(url).append(") no-repeat center center;");
    }
}
