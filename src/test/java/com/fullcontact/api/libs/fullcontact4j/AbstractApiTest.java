package com.fullcontact.api.libs.fullcontact4j;

import junit.framework.TestCase;
import java.io.*;

abstract class AbstractApiTest extends TestCase {
    public static String newline = System.getProperty("line.separator");

    protected String loadJson(String fileName) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("src/test/resources/" + fileName))));

        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            sb.append(newline);
        }

        return sb.toString();
    }
}
