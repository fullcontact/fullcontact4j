package com.fullcontact.api.libs.fullcontact4j;


import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.logging.Level;

public class Utils {

    public static String encodeToStringAndClose(ByteArrayOutputStream image) throws IOException {
        String imageString = null;
        byte[] imageBytes = image.toByteArray();

        BASE64Encoder encoder = new BASE64Encoder();
        imageString = encoder.encode(imageBytes);
        image.close();
        return imageString;
    }

    public static String loadJson(InputStream stream, String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(loadFile(fileName))));

        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }

        return sb.toString();
    }

    public static File loadFile(String fileName) throws IOException {
        return new File("src/test/resources/" + fileName);
    }


    public static void log(Level l, String log) {
        if(l.intValue() >= FullContact.logLevel.intValue()) {
            System.out.println(Constants.LOG_PREFIX + " " + log);
        }
    }

    public static boolean isValidEmail(String email) {
        return email.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+");
    }

    public static boolean isAlphabeticalOnly(String str) {
        return str.matches("[a-zA-z]*");
    }

    /**
     * Log important events like client startup, exceptions, etc.
     * @param log string to log
     */
    public static void info(String log) {
        log(Level.INFO, log);
    }

    /**
     * Log workflow-related events, like sending a request, debug info, etc.
     * @param log string to log
     */
    public static void verbose(String log) {
        log(Level.FINE, log);
    }

}
