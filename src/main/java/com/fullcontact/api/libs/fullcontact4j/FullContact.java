package com.fullcontact.api.libs.fullcontact4j;

import com.fullcontact.api.libs.fullcontact4j.handlers.*;
import com.fullcontact.api.libs.fullcontact4j.http.FullContactHttpRequest;

public class FullContact {
    private String apiKey;
    private PersonHandler _personHandler;
    private PersonEnhancedHandler _personEnhancedHandler;
    private NameHandler _nameHandler;
    private LocationHandler _locationHandler;
    private CardSharkHandler _cardSharkHandler;
    private BatchHandler _batchHandler;
    private EmailHandler _emailHandler;
    private IconHandler _iconHandler;
    private CardReaderHandler _cardReaderHandler;

    public FullContact(String apiKey) {
        if (apiKey == null) {
            throw new IllegalArgumentException("apiKey cannot be null.");
        }

        if (apiKey.trim().length() == 0) {
            throw new IllegalArgumentException("apiKey cannot be empty.");
        }

        this.apiKey = apiKey;
    }

    /***
     * Sets a custom User-Agent to be sent with each request
     * @param userAgent
     * @return
     */
    public FullContact setUserAgent(String userAgent){
        FullContactHttpRequest.setUserAgent(userAgent);
        return this;
    }

    public PersonHandler getPersonHandler(){
        if(_personHandler == null){
            _personHandler =  new PersonHandler(apiKey);
        }
        return _personHandler;
    }

    public PersonEnhancedHandler getPersonEnhancedHandler(){
        if(_personEnhancedHandler == null){
            _personEnhancedHandler =  new PersonEnhancedHandler(apiKey);
        }
        return _personEnhancedHandler;
    }

    public NameHandler getNameHandler(){
        if(_nameHandler == null){
            _nameHandler =  new NameHandler(apiKey);
        }
        return _nameHandler;
    }

    public LocationHandler getLocationHandler(){
        if(_locationHandler == null){
            _locationHandler =  new LocationHandler(apiKey);
        }
        return _locationHandler;
    }

    public CardSharkHandler getCardSharkHandler(){
        if(_cardSharkHandler == null){
            _cardSharkHandler =  new CardSharkHandler(apiKey);
        }
        return _cardSharkHandler;
    }

    public CardReaderHandler getCardReaderHandler() {
        if(_cardReaderHandler == null) {
            _cardReaderHandler = new CardReaderHandler(apiKey);
        }
        return _cardReaderHandler;
    }

    public BatchHandler getBatchHandler(){
        if(_batchHandler == null){
            _batchHandler =  new BatchHandler(apiKey);
        }
        return _batchHandler;
    }

    public EmailHandler getEmailHandler(){
        if(_emailHandler == null){
            _emailHandler =  new EmailHandler(apiKey);
        }
        return _emailHandler;
    }

    public IconHandler getIconHandler(){
        if(_iconHandler == null){
            _iconHandler =  new IconHandler(apiKey);
        }
        return _iconHandler;
    }

}
