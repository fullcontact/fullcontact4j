package com.fullcontact.api.libs.fullcontact4j.http;

import com.fullcontact.api.libs.fullcontact4j.FullContactApi;
import retrofit.Callback;

import java.util.HashMap;
import java.util.Map;

public abstract class FCRequest<T extends FCResponse> {

    protected final Map<String, String> params;

    protected FCRequest(Map<String, String> params) {
        for(Map.Entry<String, String> ents: params.entrySet()) {
            Boolean badKey = ents.getKey() == null || ents.getKey().isEmpty();
            Boolean badValue = ents.getValue() == null || ents.getValue().isEmpty();
            if(badKey || badValue) {
                throw new IllegalArgumentException("A parameter is null or empty. Parameter: \"" + ents.getKey() +
                "\", Value: \"" + ents.getValue() + "\"");
            }
        }
        this.params = params;
    }

    public boolean hasParam(String key) {
        return params.containsKey(key);
    }

    public String getParam(String key) {
        return params.get(key);
    }
    /**
     * This and the builders are the only things that should be overridden for any request class.
     * This method should do exactly one thing: make an api call through the FullContactHttpInterface object.
     * @param callback the callback supplied by the client
     */
    protected abstract void makeRequest(FullContactApi api, Callback<T> callback);

    private String getFullURL(String baseUrl) {
        StringBuilder builder = new StringBuilder();
        String result = baseUrl + "?";
        for(Map.Entry<String, String> entry : params.entrySet()) {
            if(builder.length() != 0) {
                builder.append("&");
            }
            builder.append(entry.getKey() + "=" + entry.getValue());
        }
        return result + builder.toString();
    }

    /**
     * This type signature probably looks pretty weird. Let's explain...
     *
     * A Builder needs to be able to return itself from a function, for method chaining. For most Builders, this is fine
     * -- just have each method return Builder. But what if we want all Builders extending from BaseBuilder here
     * (PersonRequest.Builder, CompanyRequest.Builder...) to support
     * inherited builder methods like `customParam(String key, String value)`? If BaseBuilder has `customParam()` here,
     * then `customParam()` returns a BaseBuilder, not the special subclass like PersonRequest.Builder
     * that is ACTUALLY being used. If you do `personBuilder.email("a@a.com").customParam("key","value")`,
     * now you've got an instance of BaseBuilder when it's actually a PersonRequest.Builder, and you lose all of
     * PersonRequest.Builder's methods!
     *
     * So how do we know what BaseBuilder subclass to actually return from `customParam()`?
     *
     * Generics to the rescue! Type Param B is the actual Builder subclass, i.e. PersonRequest.Builder,
     * that will be returned from all method-chaining builder functions.
     * Now we just return B from all our builder methods, and the right subclass is returned.
     * @param <B> the actual Builder type (must be a subclass of builder)
     * @param <R> the FCRequest subclass that will actually be built
     */
    protected abstract static class BaseBuilder<B extends BaseBuilder<B,R>, R extends FCRequest> {
        protected Map<String, String> params;

        public BaseBuilder() {
            params = new HashMap<String, String>();
        }

        /**
         * Actually validates and builds the request.
         * If there are missing/bad parameters, an IllegalArgumentException will be thrown.
         */
        public R build() {
            validate();

            // if a param was empty or null, remove it from the param list.
            Map<String, String> validParamsOnly = new HashMap<String, String>();
            for(Map.Entry<String, String> entry : params.entrySet()) {
                if(entry.getValue() != null && !entry.getValue().trim().isEmpty()) {
                    validParamsOnly.put(entry.getKey(), entry.getValue());
                }
            }
            params = validParamsOnly;
            return createInstance();
        }

        public B customParam(String param, String value) {
            params.put(param, value);
            return self();
        }

        protected abstract R createInstance();

        /**
         * Validates all the parameters in this request before it's built (it is considered a 100% valid request after it is built).
         * If something about the parameters is incorrect, IllegalArgumentException should be thrown.
         */
        protected abstract void validate();

        protected boolean hasParam(String param) {
            return params.containsKey(param);
        }

        /**
         * Addendum to the explanation above -- we would like to have `customParam()` end with `return this;` but
         * according to the compiler, all we know about `this` is that it's an instance of a BaseBuilder, and we need
         * some type B. In our use-case, `this` will be the B type, but the compiler doesn't know that.
         * We can give the compiler `this` as the type B in a safe way by having
         * each BaseBuilder implementation return itself in this method.
         */
        protected abstract B self();
    }



}
