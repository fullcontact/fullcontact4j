package com.fullcontact.api.libs.fullcontact4j.entity.person;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

public class Chat {

	private String client;
	private String handle;

    public String getClient() {
        return client;
    }

    public String getHandle() {
        return handle;
    }
}
