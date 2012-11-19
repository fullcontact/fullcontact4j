package com.fullcontact.api.libs.fullcontact4j;

import com.fullcontact.api.libs.fullcontact4j.entity.icon.IconListResponse;

import java.io.*;

public class IconTest extends AbstractApiTest {

    public void test_getValidIconTypes() throws IOException, FullContactException {
        String json = loadJson("icon.validList.json");
        IconListResponse response = new FullContact("fake_api_key").getIconHandler().parseIconsListJsonResponse(json);
        assertNotNull(response);
    }

}
