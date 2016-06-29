package com.fullcontact.api.libs.fullcontact4j.http;

import com.fullcontact.api.libs.fullcontact4j.FCConstants;
import com.fullcontact.api.libs.fullcontact4j.Utils;
import retrofit.client.Header;
import retrofit.client.Response;

public class FCRateLimits {

    private int requestsRemaining;
    private int maxRequestsPerSecond;
    private int secondsToReset;

    public FCRateLimits(int requestsRemaining, int maxRequestsPerSecond, int secondsToReset) {
        this.requestsRemaining = requestsRemaining;
        this.maxRequestsPerSecond = maxRequestsPerSecond;
        this.secondsToReset = secondsToReset;
    }

    public int getMaxRequestsPerSecond() {
        return maxRequestsPerSecond;
    }

    public int getSecondsToReset() {
        return secondsToReset;
    }

    public int getRequestsRemaining() {
        return requestsRemaining;
    }

    public static FCRateLimits fromResponseNullable(Response response) {
        Integer maxRequestsPerSecond = null;
        Integer requestsRemaining = null;
        Integer secondsUntilNextSession = null;

        try {
            for (Header h : response.getHeaders()) {
                if (FCConstants.HEADER_RATE_LIMIT_PER_MINUTE.equals(h.getName())) {
                    maxRequestsPerSecond = Integer.parseInt(h.getValue()) / 60;
                }
                if (FCConstants.HEADER_RATE_LIMIT_REMAINING.equals(h.getName())) {
                    requestsRemaining = Integer.parseInt(h.getValue());
                }
                if (FCConstants.HEADER_RATE_LIMIT_RESET_TIME.equals(h.getName())) {
                    secondsUntilNextSession = Integer.parseInt(h.getValue());
                }
            }

        } catch(Exception e) {
            Utils.info("Error extracting rate limit headers: " + response.getHeaders());
            return null;
        }

        if(maxRequestsPerSecond == null || requestsRemaining == null || secondsUntilNextSession == null) {
            Utils.info("Missing rate limit headers info, not including in response. Raw Headers: " + response.getHeaders());
            return null;
        }

        return new FCRateLimits(requestsRemaining, maxRequestsPerSecond, secondsUntilNextSession);
    }
}
