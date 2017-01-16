package com.fullcontact.api.libs.fullcontact4j.http.cardreader;

import com.fullcontact.api.libs.fullcontact4j.http.FCResponse;
import lombok.*;


/**
 * This response is sent to the client after it successfully uploads a new card to be processed.
 * It does not contain the card's transcribed information, just data about when it will be available.
 */
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CardReaderUploadConfirmResponse extends FCResponse {
    private boolean queued;
    private String id;
    private int estimatedWaitTimeMinutes;
}
