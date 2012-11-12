package com.fullcontact.api.libs.fullcontact4j.entity.batch;

import com.fullcontact.api.libs.fullcontact4j.FullContact;
import com.fullcontact.api.libs.fullcontact4j.config.Constants;
import com.fullcontact.api.libs.fullcontact4j.entity.cardshark.ViewRequestEntity;
import com.fullcontact.api.libs.fullcontact4j.entity.cardshark.ViewRequestsEntity;
import com.fullcontact.api.libs.fullcontact4j.entity.enhanced.PersonEnhancedEntity;
import com.fullcontact.api.libs.fullcontact4j.entity.location.LocationEnrichmentEntity;
import com.fullcontact.api.libs.fullcontact4j.entity.location.LocationNormalizerEntity;
import com.fullcontact.api.libs.fullcontact4j.entity.name.NameEntity;
import com.fullcontact.api.libs.fullcontact4j.entity.name.NameParserEntity;
import com.fullcontact.api.libs.fullcontact4j.entity.name.NameSimilarityEntity;
import com.fullcontact.api.libs.fullcontact4j.entity.name.NameStatsEntity;
import com.fullcontact.api.libs.fullcontact4j.entity.person.PersonEntity;
import com.fullcontact.api.libs.fullcontact4j.handlers.*;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BatchResponse {

    @SerializedName("status")
    private int statusCode;

    Map<String, String> results = new HashMap<String, String>();

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, String> getResults() {
        return results;
    }

    public void setResults(Map<String, String> results) {
        this.results = results;
    }

    public void addResult(String key, String jsonResponse) {
        this.results.put(key, jsonResponse);
    }

    public List<NameEntity> getNameNormalizerResults() {
        List<NameEntity> entities = new ArrayList<NameEntity>();
        NameHandler nameHandler = getClient().getNameHandler();
        for (String apiUrl : results.keySet()) {
            if (apiUrl.startsWith(Constants.API_URL_NAME_NORMALIZATION)) {
                entities.add(nameHandler.parseNormalizationJsonResponse(results.get(apiUrl)));
            }
        }
        return entities;
    }

    public List<NameEntity> getNameDeducerResults() {
        List<NameEntity> entities = new ArrayList<NameEntity>();
        NameHandler nameHandler = getClient().getNameHandler();
        for (String apiUrl : results.keySet()) {
            if (apiUrl.startsWith(Constants.API_URL_NAME_DEDUCER)) {
                entities.add(nameHandler.parseDeducerJsonResponse(results.get(apiUrl)));
            }
        }
        return entities;
    }

    public List<NameParserEntity> getNameParserResults() {
        List<NameParserEntity> entities = new ArrayList<NameParserEntity>();
        NameHandler nameHandler = getClient().getNameHandler();
        for (String apiUrl : results.keySet()) {
            if (apiUrl.startsWith(Constants.API_URL_NAME_PARSER)) {
                entities.add(nameHandler.parseParserJsonResponse(results.get(apiUrl)));
            }
        }
        return entities;
    }

    public List<NameStatsEntity> getNameStatsResults() {
        List<NameStatsEntity> entities = new ArrayList<NameStatsEntity>();
        NameHandler nameHandler = getClient().getNameHandler();
        for (String apiUrl : results.keySet()) {
            if (apiUrl.startsWith(Constants.API_URL_NAME_STATS)) {
                entities.add(nameHandler.parseStatsJsonResponse(results.get(apiUrl)));
            }
        }
        return entities;
    }

    public List<NameSimilarityEntity> getNameSimilarityResults() {
        List<NameSimilarityEntity> entities = new ArrayList<NameSimilarityEntity>();
        NameHandler nameHandler = getClient().getNameHandler();
        for (String apiUrl : results.keySet()) {
            if (apiUrl.startsWith(Constants.API_URL_NAME_SIMILARITY)) {
                entities.add(nameHandler.parseSimilarityJsonResponse(results.get(apiUrl)));
            }
        }
        return entities;
    }

    public List<PersonEntity> getPersonResults() {
        List<PersonEntity> entities = new ArrayList<PersonEntity>();
        PersonHandler personHandler = getClient().getPersonHandler();
        for (String apiUrl : results.keySet()) {
            if (apiUrl.startsWith(Constants.API_URL_PERSON)) {
                entities.add(personHandler.parseJsonResponse(results.get(apiUrl)));
            }
        }
        return entities;
    }

    public List<PersonEnhancedEntity> getEnhancedDataResults() {
        List<PersonEnhancedEntity> entities = new ArrayList<PersonEnhancedEntity>();
        PersonEnhancedHandler personHandler = getClient().getPersonEnhancedHandler();
        for (String apiUrl : results.keySet()) {
            if (apiUrl.startsWith(Constants.API_URL_PERSON_ENHANCED_DATA)) {
                entities.add(personHandler.parseJsonResponse(results.get(apiUrl)));
            }
        }
        return entities;
    }

    public List<LocationNormalizerEntity> getLocationNormalizerResults() {
        List<LocationNormalizerEntity> entities = new ArrayList<LocationNormalizerEntity>();
        LocationHandler locationHandler = getClient().getLocationHandler();
        for (String apiUrl : results.keySet()) {
            if (apiUrl.startsWith(Constants.API_URL_LOCATION_NORMALIZATION)) {
                entities.add(locationHandler.parseNormalizerJsonResponse(results.get(apiUrl)));
            }
        }
        return entities;
    }

    public List<LocationEnrichmentEntity> getLocationEnrichmentResults() {
        List<LocationEnrichmentEntity> entities = new ArrayList<LocationEnrichmentEntity>();
        LocationHandler locationHandler = getClient().getLocationHandler();
        for (String apiUrl : results.keySet()) {
            if (apiUrl.startsWith(Constants.API_URL_LOCATION_ENRICHMENT)) {
                entities.add(locationHandler.parseEnrichmentJsonResponse(results.get(apiUrl)));
            }
        }
        return entities;
    }

    public List<ViewRequestsEntity> getCardSharkRequestsResults() {
        if(!_csRequestsProcessed){
            processCardSharkRequests();
        }
        return _csReqestsEntities;
    }

    public List<ViewRequestEntity> getCardSharkRequestResults() {
        if(!_csRequestsProcessed){
            processCardSharkRequests();
        }
        return _csReqestEntities;
    }

    private void processCardSharkRequests() {
        CardSharkHandler cardSharkHandler = getClient().getCardSharkHandler();
        for (String apiUrl : results.keySet()) {
            if (apiUrl.startsWith(_csRequestsUrl)) {
                String apiUrlTrimmed = apiUrl.replaceFirst(_csRequestsUrl, "");
                if (apiUrlTrimmed.length() > 1) {
                    try{
                        _csReqestEntities.add(cardSharkHandler.parseViewRequestJsonResponse(results.get(apiUrl)));
                    }catch(Exception e){}
                } else {
                    try{
                        _csReqestsEntities.add(cardSharkHandler.parseViewRequestsJsonResponse(results.get(apiUrl)));
                    }catch(Exception e){}
                }
            }
        }
        _csRequestsProcessed = true;
    }

    //TODO: Disposable Email Address results


    private FullContact getClient() {
        if (_client == null) {
            _client = new FullContact("parsing_key");
        }
        return _client;
    }

    private FullContact _client;
    private String _csRequestsUrl = "https://api.fullcontact.com/v2/cardShark";
    private List<ViewRequestEntity> _csReqestEntities = new ArrayList<ViewRequestEntity>();
    private List<ViewRequestsEntity> _csReqestsEntities = new ArrayList<ViewRequestsEntity>();
    private boolean _csRequestsProcessed = false;

}
