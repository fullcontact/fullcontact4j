#FullContact4j

A Java client for the [FullContact API](http://www.fullcontact.com/docs).

For FullContact4j 2.0, we've designed the client from the bottom up with tons of cool new stuff.

See the changelog [here.](https://github.com/fullcontact/fullcontact4j/wiki/changelog)

__Coming from version 1? Read this [wiki page](https://github.com/fullcontact/fullcontact4j/wiki/Coming-from-v1.0) for a rundown of the big changes, then continue here.__

##Add to your project##
FullContact uses [Bintray](https://bintray.com/) as a repository.

__Maven__
```xml
<repositories>
  <repository>
    <id>fullcontact</id>
    <url>http://dl.bintray.com/content/fullcontact/fullcontact-oss</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>com.fullcontact</groupId>
    <artifactId>fullcontact4j</artifactId>
    <version>2.2.1</version>
  </dependency>
</dependencies>
```
__Gradle__
```groovy
repositories {
    maven {
        url "http://dl.bintray.com/content/fullcontact/fullcontact-oss"
    }
}

dependencies {
    compile group: "com.fullcontact", name: "fullcontact4j", version: "2.2.1"
}
```

##Dependencies##
__OkHttp__, which FullContact uses as an HTTP client.

__Retrofit__, for interacting with the FullContact API.

__Jackson__, a JSON library, for conversion of API responses.


##Working with FullContact4j##
FullContact4j 2.0 is designed from the ground up to be as painless to use as possible. FullContact clients are customizable and require just one line of code to set up; requests take only one line to create and one to execute. FullContact4j abstracts away all the headaches associated with HTTP communication with the API, ranging from managing possible response scenarios to keeping your requests below your account's rate limit (queries per second). Make a request object, execute it, and get a response object back.
###Quick Overview###
Firstly, read the [API documentation](https://www.fullcontact.com/developer/docs/)! FullContact4j provides an object layer to FullContact API communication, but understanding webhooks, response flows, request parameters, and common snags is still important.

Once you’re on board with the API behavior, using FullContact4j is easy. Look up social and demographic data for an email address in just 3 lines of code:
```java
FullContact fullContact = FullContact.withApiKey("your-api-key").build();
PersonRequest personRequest = fullContact.buildPersonRequest().email("bart@fullcontact.com").build();
PersonResponse personResponse = fullContact.sendRequest(personRequest);
```
(Don't have an API key? You can pick one up for free [right here.](https://www.fullcontact.com/developer/try-fullcontact/))

`withApiKey` returns a FullContact client builder. You can specify timeouts, your own `OkHttpClient`, a user agent, and much more.

Behind the scenes, FullContact4j has done a lot for you:
* authenticated with FullContact
* accounted for rate limiting, sending the request if you are below your rate limit (or waiting to send your request until the time is right if you have exceeded your account limit).
* checked for errors and parsed useful messages/data
* turned the response JSON into a perfectly normal Java object.

If you prefer asynchronous calls, that requires only one change:
```java
fullContact.sendRequest(personRequest, new FCCallback() {
  public void success(PersonResponse response) {
    System.out.println("got a response back!");
  }

  public void failure(FullContactException exception) {
    System.out.println("got an error :( ");
  }
});
```

You can see a simple demo of this client in action in the [Hello World app example!](https://github.com/fullcontact/fullcontact4j/blob/master/example/src/main/java/com/fullcontact/example/FullContactHelloWorld.java)

###Making Requests###
First, let’s get our request builder.
```java
fullContact.buildPersonRequest()
```
All API requests can be made by calling their corresponding `build____Request()` method. Then, add relevant parameters and build:
```java
fullContact.buildPersonRequest().email(“bart@fullcontact.com”).build();
```
It’s generally good practice to specify [webhooks](https://www.fullcontact.com/developer/docs/person/#webhook-flow-diagram) in your request, in case of a 202 response from Person API or a Card Reader upload for instance. That’s all configurable as well:
```java
fullContact.buildPersonRequest()
  .webhookUrl(“http://www.example-site.com/api/finishedlookup”)
  .webhookBody(true)
  .email(“bart@fullcontact.com”)
  .build();
```
If the webhook URL is specified, all Person API responses will by default return a 202 response code (see [webhooks documentation](https://www.fullcontact.com/developer/docs/person/#webhook-flow-diagram) for more detail). If you need to test webhooks before implementing them in your own technology, you can always use something like [requestb.in](http://requestb.in).


If you clearly mis-configured your request (adding two search parameters to Person API, or not giving a name to the name deducer API, etc), `build()` will throw an `IllegalArgumentException` with a message about what you did wrong:
```java
java.lang.IllegalArgumentException: Request must specify exactly one: email or username
  at com.fullcontact.api.libs.fullcontact4j.http.name.NameDeduceRequest$Builder.validate(NameDeduceRequest.java:51)
  …
```
Now let’s send our request. Since FullContact4j uses a thread pool to execute requests, multiple consumers should be fine to make thread-safe requests from the same client instance. This can be done synchronously or asynchronously.

Synchronously is a little easier:
```java
PersonRequest request = fullContact.buildPersonRequest().email(“bart@fullcontact.com”).build();
PersonResponse response = fullContact.sendRequest(request);
```
Every `request` has a corresponding `response` that it will return when `sendRequest` is called. This object contains all response data back from FullContact.

Asynchronous requests are similar. `sendRequest()` just takes one more parameter -- a `FCCallback`. It has a generic type parameter of the response type (`PersonRequest` corresponds to `PersonResponse`, `LocationNormalizationRequest` to `LocationNormalizationResponse`, etc). `success()` is called when a useful response is returned from the APIs:
```java
fullContact.sendRequest(locationEnrichmentRequest, new FCCallback() {
  public void success(LocationEnrichmentResponse response) {
    System.out.println("got a response back!");
  }

  public void failure(FullContactException exception) {
    System.out.println("got an error :( ");
  }
});
```

You can turn webhook responses into these response objects using `fromJson`:
```java
public void onCardReaderWebhook(String body) {
  CardReaderFullResponse response = CardReaderFullResponse.fromJson(body);
  System.out.println("Just got a card reader transcribed for " + response.getContact().getName().toString());
}

public void onPersonWebhook(String body) {
  PersonResponse response = PersonResponse.fromJson(body);
  System.out.println("Just got social profile info for " + response.getContactInfo().getName().toString());
}
```

As long as a response returns 200 or 202, FullContact4j will turn it into a java object representation holding all the information the regular JSON response would. If it's not in the JSON, it'll be an empty field.
```java
PersonResponse response = client.sendRequest(personRequest);
if(response.getStatus() == 200) { //202 (searching) is possible here, we'll get an empty response!
  for(SocialProfile profile : response.getSocialProfiles()) {
    System.out.println("Check out my " + profile.getTypeId() + " profile: " + profile.getUrl());
  }
}

LocationEnrichmentResponse location = client.sendRequest(locationRequest);
System.out.println("I found a new place in " + location.getContinent() + " with a population of " + location.getPopulation());
```

__When you're done with the client, be sure to call `shutDown()` on it.__ Otherwise, you could end up with a small memory leak from unfinished request threads.
###Error Handling###
If an error is encountered (these correspond to yellow/red non-2xx response codes on the [API flow diagrams](https://www.fullcontact.com/developer/docs/person/#flow-diagram)), a `FullContactException` is created with useful information about the error, including `errorCode` and a message from the FullContact APIs about the nature of the error. For synchronous requests, this will cause `sendRequest()` to throw `FullContactException`. In asynchronous requests, `FCCallback.failure(FullContactException exception)` is called instead.
###Supported APIs###
FullContact4j supports all Person, Company, Email, Name, Location, Card Reader, and Account Statistics endpoints. These can all be accessed from their respective `build_____Request()` methods in the FullContact client. Some will need required parameters (like `CardReaderRequest`’s requirement for a front image `InputStream`) and automatically create a builder with those required parameters set.
###Advanced###
In the background, FullContact4j is making requests using an `OkHttpClient`. You can supply your own `OkHttpClient`.
```java
OkHttpClient client = new OkHttpClient();
client.setReadTimeout(3000, TimeUnit.MILLISECONDS);
FullContact fcClient = FullContact.withApiKey("your-api-key").httpClient(client).build();
```
The user agent and request executor thread count are also configurable. The client will rate limit the amount of requests sent based on the rate limit for your plan. It will hold a request queue and execute at the maximum every `1/ratelimit` seconds with some leeway if you haven’t sent requests in a certain period of time. __FullContact4j guarantees no rate limiting exceptions only as long a single client instance is the only user of an API key__. Multiple client instances, multiple consumers, etc, will all cause a 403 to be sent to the client and is not considered a bug. Likewise, overages are still possible. Make sure to check your [account stats](https://www.fullcontact.com/developer/docs/account-stats) periodically to avoid overages.
