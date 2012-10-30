package com.fullcontact.api.libs.fullcontact4j;

import com.fullcontact.api.libs.fullcontact4j.handlers.*;

public class FullContact {
    private String apiKey;
    private PersonHandler _personHandler;
    private PersonEnhancedHandler _personEnhancedHandler;
    private NameHandler _nameHandler;
    private LocationHandler _locationHandler;
    private CardSharkHandler _cardSharkHandler;

    public FullContact(String apiKey) {
        if (apiKey == null) {
            throw new IllegalArgumentException("apiKey cannot be null.");
        }

        if (apiKey.trim().length() == 0) {
            throw new IllegalArgumentException("apiKey cannot be empty.");
        }

        this.apiKey = apiKey;
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


}
