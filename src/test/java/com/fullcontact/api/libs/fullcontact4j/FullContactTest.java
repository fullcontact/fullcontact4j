package com.fullcontact.api.libs.fullcontact4j;

import com.fullcontact.api.libs.fullcontact4j.entity.FullContactEntity;
import junit.framework.TestCase;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: danlynn
 * Date: 2/7/12
 * Time: 5:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class FullContactTest extends TestCase {
    public static String newline = System.getProperty("line.separator");

    public void test_parse_name() throws IOException {
        String json = loadJson("lorangb@gmail.com.json");

        FullContactEntity entity = new FullContact("fake_api_key").parseResponseJson(json);

        assertEquals("Lorang", entity.getContactInfo().getFamilyName());
        assertEquals("Bart", entity.getContactInfo().getGivenName());
        assertEquals("Bart Lorang", entity.getContactInfo().getFullName());
    }

    private String loadJson(String fileName) throws IOException {

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
