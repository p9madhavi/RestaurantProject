package com.modules;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileHelper {

    public static void writeLines(List<String>lines, File aFile) throws IOException {
        FileUtils.writeLines(aFile, lines);
}

}
