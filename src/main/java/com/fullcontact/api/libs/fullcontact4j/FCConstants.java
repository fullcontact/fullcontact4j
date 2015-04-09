package com.fullcontact.api.libs.fullcontact4j;

public class FCConstants {

    public static final String API_BASE_DEFAULT = "https://api.fullcontact.com/v2";
    public static final String LOG_PREFIX = "[fc4j]";
    public static final String VERSION = "2.0";
    public static final String USER_AGENT_BASE = "FullContact4j/" + VERSION;

    public static final String API_ENDPOINT_PERSON = "/person.json";
    public static final String API_ENDPOINT_COMPANY = "/company/lookup.json";
    public static final String API_ENDPOINT_CARDREADER = "/cardReader";
    public static final String API_ENDPOINT_DISPOSABLE_EMAIL = "/disposable.json";
    public static final String API_ENDPOINT_NAME_NORMALIZER = "/name/normalizer.json";
    public static final String API_ENDPOINT_NAME_DEDUCER = "/name/deducer.json";
    public static final String API_ENDPOINT_NAME_SIMILARITY = "/name/similarity.json";
    public static final String API_ENDPOINT_NAME_STATS = "/name/stats.json";
    public static final String API_ENDPOINT_NAME_PARSER = "/name/parser.json";
    public static final String API_ENDPOINT_LOCATION_NORMALIZER = "/address/locationNormalizer.json";
    public static final String API_ENDPOINT_LOCATION_ENRICHMENT = "/address/locationEnrichment.json";
    public static final String API_ENDPOINT_ACCOUNT_STATS = "/stats.json";

    public static final String HEADER_RATE_LIMIT_PER_MINUTE = "X-Rate-Limit-Limit";
    public static final String HEADER_RATE_LIMIT_REMAINING = "X-Rate-Limit-Remaining";
    public static final String HEADER_RATE_LIMIT_RESET_TIME = "X-Rate-Limit-Reset";
    public static final String HEADER_AUTH_API_KEY = "X-FullContact-APIKey";
    public static final String HEADER_AUTH_ACCESS_TOKEN = "X-FullContact-AccessToken";
    public static final String HEADER_USER_AGENT = "User-Agent";

    public static final String PARAM_PERSON_EMAIL = "email";
    public static final String PARAM_PERSON_EMAIL_MD5 = "emailMD5";
    public static final String PARAM_PERSON_QUEUE = "queue";
    public static final String PARAM_PERSON_TWITTER = "twitter";
    public static final String PARAM_PERSON_FACEBOOK = "facebookUsername";
    public static final String PARAM_PERSON_FACEBOOK_ID = "facebookId";
    public static final String PARAM_PERSON_PHONE = "phone";
    public static final String PARAM_PERSON_PHONE_COUNTRY_CODE = "countryCode";
    public static final String PARAM_PERSON_CALLBACK = "callback";

    public static final String PARAM_COMPANY_DOMAIN = "domain";

    public static final String PARAM_CARD_VERIFIED = "verified";
    public static final String PARAM_CARD_RETURNED_DATA = "returnedData";
    public static final String PARAM_CARD_CASING = "casing";
    public static final String PARAM_CARD_SANDBOX = "sandbox";
    public static final String PARAM_CARD_URID = "URID";
    public static final String PARAM_CARD_DIAGNOSTICS = "diagnostics";
    public static final String PARAM_CARD_PAGE = "page";

    public static final String PARAM_NAME_QUERY = "q";
    public static final String PARAM_NAME_EMAIL = "email";
    public static final String PARAM_NAME_USERNAME = "username";
    public static final String PARAM_NAME_CASING = "casing";
    public static final String PARAM_NAME_NAME1 = "q1";
    public static final String PARAM_NAME_NAME2 = "q2";
    public static final String PARAM_NAME_GIVEN_NAME = "givenName";
    public static final String PARAM_NAME_NAME = "name";
    public static final String PARAM_NAME_FAMILY_NAME = "familyName";

    public static final String PARAM_LOCATION_PLACE = "place";

    public static final String PARAM_ACCOUNT_STATS_PERIOD = "period";

    public static final String PARAM_WEBHOOK_URL = "webhookUrl";
    public static final String PARAM_WEBHOOK_ID = "webhookId";
    public static final String PARAM_WEBHOOK_BODY = "webhookBody";


    public static final String UTF_8_CHARSET = "UTF-8";
}
