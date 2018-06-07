package io.github.hulang.iconcssgenerator;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class DefaultCssRuleGenerator extends AbstractCssRuleGenerator {
    private String relativeBasePath;

    public void setRelativeBasePath(String path) {
        if (StringUtils.isNotEmpty(path)) {
            relativeBasePath = path.endsWith("/") ? path : path + "/";
        }
    }

    @Override
    public StringBuilder rule(File imageFile, List<String> dirPath) {
        return new StringBuilder()
            .append(selector(imageFile, dirPath))
            .append(" {\n")
            .append("  ").append(declarationBlock(imageFile, dirPath))
            .append("\n}\n");
    }
    
    private StringBuilder selector(File imageFile, List<String> dirPath) {
        StringBuilder selector = new StringBuilder(".icon-");
        if (!dirPath.isEmpty()) {
            String[] lowerPath = new String[dirPath.size()];
            int i = 0;
            for (String path : dirPath) {
                lowerPath[i++] = path.toLowerCase();
            }
            selector.append(StringUtils.join(lowerPath, "-")).append("-");
        }
        
        String normalizeName = imageFile.getName();
        normalizeName = normalizeName.substring(0, imageFile.getName().lastIndexOf("."));
        normalizeName = normalizeName.replaceAll("_", "-");
        normalizeName = normalizeName.toLowerCase();
        selector.append(normalizeName);

        return selector;
    }


    private StringBuilder declarationBlock(File imageFile, List<String> dirPath) {
        StringBuilder url = new StringBuilder();
        url.append(relativeBasePath != null ? relativeBasePath : generator.getRelativeBasePath());
        if (!dirPath.isEmpty()) {
            url.append(StringUtils.join(dirPath.toArray(), "/")).append("/");
        }
        url.append(imageFile.getName());

        return new StringBuilder()
            .append("background: url(").append(url).append(") no-repeat center center;");
    }
}
