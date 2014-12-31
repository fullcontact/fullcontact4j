package com.fullcontact.example.webhook.spark;

import com.fullcontact.api.libs.fullcontact4j.http.person.PersonResponse;
import com.fullcontact.example.webhook.WebhookResponse;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * This uses a library that makes it easy to embed a web server into your application code:
 * <a href="http://sparkjava.com/documentation.html">SparkJava</a>
 *
 * Note that this requires Java 8 to be functional.
 *
 * To use, simply direct the Person API to use a webhookUrl that is something of the form
 * <code>http://xxxxxxxx/webhook</code> to invoke the webhook.
 * <p>
 * For example (always a free query when using bart@fullcontact.com):<p>
 * <code>https://api.fullcontact.com/v2/person.json?email=bart@fullcontact.com&apiKey=API_KEY&webhookUrl=http://HOST_PART/webhook&webhookBody=json</code>
 * <p>
 * Supported options:
 * <ul>
 * <li><code>webhookId </code>- see the correlation ID rippled through.</li>
 * <li><code>webhookBody=json</code> - see the webhook payload in true JSON (without, sender defaults to URL Encoded payload)</li>
 * </ul>
 * <p>
 * <b>ProTip</b>: Use Ruby gem 'proxylocal' to bypass local firewalls:
 * <p>
 * <code>
 * $> proxylocal 4567
 * </code>
 *
 * @author michie <ken@fullcontact.com>
 */
public class SparkWebhookReceiver {
    private PersonWebhookHandler handler;

    public SparkWebhookReceiver(PersonWebhookHandler handler) {
        this.handler = handler;
    }

    public void setupWebhookListener(int port) {
        // Assign port
        Spark.port(port);

        // Specify the endpoint for which the POST will be directed to
        Spark.post("/webhook", new Route() {
                    @Override
                    public String handle(Request request, Response response) throws Exception {
                        handler.handleWebhook(WebhookResponse.fromJson(request.body()));
                        // Translates to 200 response status code and the below text in the response body
                        return "OK";
                    }
                }
        );
    }

    public static void main(String[] args) {
        int port = 4567;
        if (args.length > 0) {
            // Optional port was assigned
            port = Integer.valueOf(args[0]);
        }
        new SparkWebhookReceiver(new PersonWebhookHandler()).setupWebhookListener(port);
    }
}

/**
 * Simple webhook handler - simply logs to System.out with basic information about the profile
 */
class PersonWebhookHandler {
    public void handleWebhook(WebhookResponse val) {
        String logMessage;
        if (wasAHit(val.getResult())) {
            logMessage = "Hey hey, I received a webhook! This person is named: " +
                    val.getResult().getContactInfo().getFullName() + " and they have " +
                    val.getResult().getSocialProfiles().size() + " Social Profiles";
        } else {
            logMessage = "I got a webhook, but nothing was found...";
        }
        System.out.println(logMessage + (val.getWebhookId() != null ? ", correlation ID=" + val.getWebhookId() : ""));
    }

    private boolean wasAHit(PersonResponse response) {
        if (response != null && response.status == 200) return true;
        return false;
    }
}