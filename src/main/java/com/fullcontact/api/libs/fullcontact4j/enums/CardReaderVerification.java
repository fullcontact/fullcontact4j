package com.fullcontact.api.libs.fullcontact4j.enums;

/***
 * Verification level of CardReader Request.
 * higher levels cost more.
 */
public enum CardReaderVerification {
    /**
     * Card is transcribed once.
     */
    Low,
    /**
     * Card is transcribed and verified once.
     */
    Medium,
    /**
     * Card is transcribed and verified multiple times.
     */
    High
}
