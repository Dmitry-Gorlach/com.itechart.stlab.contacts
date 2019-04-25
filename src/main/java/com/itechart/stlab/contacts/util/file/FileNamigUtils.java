package com.itechart.stlab.contacts.util.file;

import org.apache.commons.lang.RandomStringUtils;

import java.io.File;

public class FileNamigUtils {

    public static String getUniqueFilePath(String path) {
        int RANDOM_DIGITS_NUMBER = 10;
        String randomPath = path + RandomStringUtils.randomNumeric(RANDOM_DIGITS_NUMBER);
        while (new File(randomPath).exists()) {
            randomPath = path + RandomStringUtils.randomNumeric(RANDOM_DIGITS_NUMBER);
        }
        return randomPath;
    }
}
