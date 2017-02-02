package com.fullcontact.api.libs.fullcontact4j;

import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
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

    public static String loadFileAsString(String fileName) throws IOException {
        InputStream is = Utils.class.getClassLoader().getResourceAsStream(fileName);
        return new String(read(is, 1));
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

    private static final int MAX_BUFFER_SIZE = Integer.MAX_VALUE - 8;
    private static final int BUFFER_SIZE = 8192;

    // Stolen from java 7 Files.read
    private static byte[] read(InputStream source, int initialSize) throws IOException {
        int capacity = initialSize;
        byte[] buf = new byte[capacity];
        int nread = 0;
        int n;
        for (;;) {
            // read to EOF which may read more or less than initialSize (eg: file
            // is truncated while we are reading)
            while ((n = source.read(buf, nread, capacity - nread)) > 0)
                nread += n;

            // if last call to source.read() returned -1, we are done
            // otherwise, try to read one more byte; if that failed we're done too
            if (n < 0 || (n = source.read()) < 0)
                break;

            // one more byte was read; need to allocate a larger buffer
            if (capacity <= MAX_BUFFER_SIZE - capacity) {
                capacity = Math.max(capacity << 1, BUFFER_SIZE);
            } else {
                if (capacity == MAX_BUFFER_SIZE)
                    throw new OutOfMemoryError("Required array size too large");
                capacity = MAX_BUFFER_SIZE;
            }
            buf = Arrays.copyOf(buf, capacity);
            buf[nread++] = (byte)n;
        }
        return (capacity == nread) ? buf : Arrays.copyOf(buf, nread);
    }
}
