package org.stephane.excel.tools;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {
    public static String getAbsolutePath(String fileName) {
        Path resourceDirectory = Paths.get("src", "test", "resources", fileName);
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        return absolutePath;
    }
}
