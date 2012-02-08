package com.fullcontact.api.libs.fullcontact4j.entity;

import com.google.gson.annotations.SerializedName;

public class Chats {

	@SerializedName("client")
	private String chatClient;

	@SerializedName("handle")
	private String chatHandle;

	public String getChatClient() {
		return chatClient;
	}

	public void setChatClient(String chatClient) {
		this.chatClient = chatClient;
	}

	public String getChatHandle() {
		return chatHandle;
	}

	public void setChatHandle(String chatHandle) {
		this.chatHandle = chatHandle;
	}

}
