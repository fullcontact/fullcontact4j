package com.fullcontact.api.libs.fullcontact4j;

import com.fullcontact.api.libs.fullcontact4j.http.cardreader.model.UnverifiedField;
import junit.framework.TestCase;

/**
 * @author Skylar Lowery (skylar@fullcontact.com)
 * @date 4/9/14
 *
 *
 */
public class UnverifiedFieldTest extends TestCase {

    public void testParse() throws Exception {
        String url1 = "urls[1]";
        String nameKey = "givenName";
        String invalid = "11111111";

        UnverifiedField url = UnverifiedField.parse(url1);
        UnverifiedField givenName = UnverifiedField.parse(nameKey);

        assertEquals("urls", url.getKey());
        assertTrue(1 == url.getIndex());
        assertEquals("givenName", givenName.getKey());
        assertNull(givenName.getIndex());
        assertNull(UnverifiedField.parse(invalid));
        assertNull(UnverifiedField.parse(""));
        assertNull(UnverifiedField.parse("   "));
        assertNull(UnverifiedField.parse(null));
    }

}
