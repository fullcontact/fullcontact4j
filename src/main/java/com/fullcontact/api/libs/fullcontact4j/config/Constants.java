package com.fullcontact.api.libs.fullcontact4j.config;

public class Constants {

    public static final String API_BASE_DEFAULT = "https://api.fullcontact.com/v2";
    public static final String LOG_PREFIX = "[fc4j]";

    public static final String API_ENDPOINT_PERSON = "/person.json";
    public static final String API_ENDPOINT_CARDREADER = "/cardReader";
    public static final String API_ENDPOINT_DISPOSABLE_EMAIL = "/disposable.json";
    public static final String API_ENDPOINT_NAME_NORMALIZER = "/normalizer.json";
    public static final String API_ENDPOINT_LOCATION_NORMALIZER = "/locationNormalizer.json";
    public static final String API_ENDPOINT_BATCH = "/batch.json";
    public static final String API_ENDPOINT_ICON = "/icon";
    public static final String API_ENDPOINT_ACCOUNT_STATS = "/stats.json";

    public static final String HEADER_RATE_LIMIT_PER_MINUTE = "X-Rate-Limit-Limit";
    public static final String HEADER_RATE_LIMIT_REMAINING = "X-Rate-Limit-Remaining";
    public static final String HEADER_RATE_LIMIT_RESET_TIME = "X-Rate-Limit-Reset";
    public static final String HEADER_AUTH_API_KEY = "X-FullContact-APIKey";
    public static final String HEADER_AUTH_ACCESS_TOKEN = "X-FullContact-AccessToken";
    public static final String HEADER_USER_AGENT = "User-Agent";
    public static final String HEADER_DEFAULT_AGENT = "FullContact4j";

    public static final String API_WEBHOOK = "webhookUrl";
    /*
    public static final String API_URL_PERSON = API_BASE + "/v2/person.json?";
    public static final String API_URL_PERSON_ENHANCED_DATA = API_BASE + "/v2/enhanced.json?";
    public static final String API_URL_NAME_NORMALIZATION = API_BASE + "/v2/name/normalizer.json?";
    public static final String API_URL_NAME_DEDUCER = API_BASE + "/v2/name/deducer.json?";
    public static final String API_URL_NAME_SIMILARITY = API_BASE + "/v2/name/similarity.json?";
    public static final String API_URL_NAME_STATS = API_BASE + "/v2/name/stats.json?";
    public static final String API_URL_NAME_PARSER = API_BASE + "/v2/name/parser.json?";
    public static final String API_URL_LOCATION_NORMALIZATION = API_BASE + "/v2/address/locationNormalizer.json?";
    public static final String API_URL_LOCATION_ENRICHMENT = API_BASE + "/v2/address/locationEnrichment.json?";
    public static final String API_URL_CARDREADER_UPLOAD = API_BASE + "/v2/cardReader?";
    public static final String API_URL_CARDREADER_VIEW_REQUEST = API_BASE + "/v2/cardReader/{0}?";
    public static final String API_URL_CARDREADER_VIEW_REQUESTS = API_BASE + "/v2/cardReader?";
    public static final String API_URL_BATCH_PROCESS = API_BASE + "/v2/batch.json?";
    public static final String API_URL_EMAIL_DISPOSABLE_DOMAIN = API_BASE + "/v2/email/disposable.json?";
    public static final String API_URL_ICON = API_BASE + "/v2/icon?";
    public static final String API_URL_ICON_TYPE_ID = API_BASE + "/v2/icon/{0}/{1}/{2}?";
    */

    public static final String API_KEY_FORMAT = "apiKey={0}";
    public static final String EMAIL_FORMAT = "email={0}";
    public static final String USERNAME_FORMAT = "username={0}";
    public static final String TWITTER_FORMAT = "twitter={0}";
    public static final String FACEBOOK_FORMAT = "facebookUsername={0}";
    public static final String TIMEOUT_SECONDS_FORMAT = "timeoutSeconds={0}";
    public static final String QUERY_FORMAT = "q={0}";
    public static final String QUERY1_FORMAT = "q1={0}";
    public static final String QUERY2_FORMAT = "q2={0}";
    public static final String NAME_FORMAT = "name={0}";
    public static final String GIVEN_NAME_FORMAT = "givenName={0}";
    public static final String FAMILY_NAME_FORMAT = "familyName={0}";
    public static final String PLACE_FORMAT = "place={0}";
    public static final String INCLUDE_ZERO_POPULATION = "includeZeroPopulation={0}";
    public static final String CASING = "casing={0}";
    public static final String WEBHOOK_URL = "webhookUrl={0}";
    public static final String FORMAT = "format={0}";
    public static final String PAGE = "page={0}";
    public static final String TYPE_ID_FORMAT = "typeId={0}";
    public static final String SIZE_FORMAT = "size={0}";
    public static final String STYLE_FORMAT = "style={0}";
    public static final String EMAIL_MD5_FORMAT = "emailMD5={0}";

    public static final String PARAM_EMAIL = "email";
    public static final String PARAM_TWITTER = "twitter";
    public static final String PARAM_FACEBOOK_USERNAME = "facebookUsername";
    public static final String PARAM_WEBHOOK_URL = "webhookUrl";
    public static final String PARAM_WEBHOOK_ID = "webhookId";
    public static final String PARAM_API_KEY = "apiKey";
    public static final String PARAM_FORMAT = "format";
    public static final String PARAM_VERIFIED = "verified";
    public static final String PARAM_RETURNED_DATA = "returnedData";
    public static final String PARAM_CASING = "casing";
    public static final String PARAM_SANDBOX = "sandbox";
    public static final String PARAM_ACCESS_TOKEN = "accessToken";
    public static final String PARAM_URID = "URID";

    public static final String API_KEY_HEADER_NAME = "X-FullContact-APIKey";

    public static final String UTF_8_CHARSET = "UTF-8";
}
