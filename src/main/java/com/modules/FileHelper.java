package com.modules;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FileHelper {

    public static void writeToFile(List<String>lines, File aFile) throws IOException {
        FileUtils.writeLines(aFile, lines);
    }
    public static List<String> readFromFile(File aFile)  throws IOException{
       return FileUtils.readLines(aFile, StandardCharsets.UTF_8);
    }

}
