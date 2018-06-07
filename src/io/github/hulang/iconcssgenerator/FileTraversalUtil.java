package io.github.hulang.iconcssgenerator;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileTraversalUtil {
    public static interface Closure {
        void f(File file, List<String> path);
    }
    
    public static void traverse(File dir, Closure closure) {
        recursive(dir, 0, new ArrayList<String>(), closure);
    }
    
    private static void recursive(File dir, int dirLevel, List<String> dirPath, Closure closure) {
        if (dirLevel > 0) {
            dirPath.add(dir.getName());
        }
        for (File file : dir.listFiles()) {
            closure.f(file, dirPath);
            if (file.isDirectory() ) {
                List<String> newPath = new ArrayList<String>();
                newPath.addAll(0, dirPath);
                recursive(file, dirLevel + 1, newPath, closure);
            }
        }
    }
}
