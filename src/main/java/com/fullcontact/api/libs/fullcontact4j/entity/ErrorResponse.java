package com.fullcontact.api.libs.fullcontact4j.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
/**
 * When FullContact's APIs return a non-200 result, they also send in the response body
 * simple but useful information about the error.
 */
public class ErrorResponse {

    public int status;

    public String message;

    public String requestId;
}
