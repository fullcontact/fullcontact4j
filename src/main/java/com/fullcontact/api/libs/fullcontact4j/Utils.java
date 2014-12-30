package com.fullcontact.api.libs.fullcontact4j;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.logging.Level;

public class Utils {

    public static String encodeToStringAndClose(InputStream in) {
        if(in == null) {
            return "";
        }
        ByteArrayOutputStream buffer = null;
        try {
            int nRead;
            byte[] data = new byte[16384];
            buffer = new ByteArrayOutputStream();

            while ((nRead = in.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        return encodeBase64String(buffer.toByteArray());
    }

    private static String encodeBase64String(final byte[] binaryData) {
        return new String(Base64.encodeBase64(binaryData, false), Charset.forName("UTF-8"));
    }

    public static File loadFile(String fileName) throws IOException {
        return new File("src/test/resources/" + fileName);
    }


    public static void log(Level l, String log) {
        if(l.intValue() >= FullContact.logLevel.intValue()) {
            System.out.println(FCConstants.LOG_PREFIX + " " + log);
        }
    }

    public static boolean isValidEmail(String email) {
        //regex tries to match [any]@[any].[any]
        return email.contains("@");
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
