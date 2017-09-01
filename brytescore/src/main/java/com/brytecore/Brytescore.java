package com.brytecore;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class Brytescore {

    // ------------------------------------ static variables ------------------------------------ //
    // Variables used to fill event data for tracking
    private static String _url = "https://api.brytecore.com";
    private static String hostname = "com.brytecore.mobile";
    private static String library = "Android";
    private static String libraryVersion = "0.0.0";

    private HashMap<String, String> eventNames = new HashMap<String, String>() {{
        put("authenticated", "authenticated");
        put("brytescoreUUIDCreated", "brytescoreUUIDCreated");
        put("heartBeat", "heartBeat");
        put("pageView", "pageView");
        put("registeredAccount", "registeredAccount");
        put("sessionStarted", "sessionStarted");
        put("startedChat", "startedChat");
        put("submittedForm", "submittedForm");
        put("updatedUserInfo", "updatedUserInfo");
    }};

    // ------------------------------------ dynamic variables ----------------------------------- //
    private String _apiKey;

    // Variables to hold package-wide IDs
    private Integer userId;
    private String anonymousId;
    private String sessionId;
    private String pageViewId;

    // Variables used to fill event data for tracking
    // When additional packages are loaded, they are added to this dictionary
    private HashMap<String, String> schemaVersion = new HashMap<String, String>() {{
        put("analytics","0.3.1");
    }};

    // Variables for mode statuses
    private Boolean devMode = false;
    private Boolean debugMode = false;
    private Boolean impersonationMode = false;
    private Boolean validationMode = false;

    // ------------------------------------ public functions: ----------------------------------- //
    /**
     * Sets the API key.
     *
     * @param apiKey The API key.
     */
    public Brytescore(String apiKey) { this._apiKey = apiKey; }

    /**
     * Returns the current API key
     */
    public String getAPIKey() { return _apiKey;}

    /**
     * Sets dev mode.
     * Logs events to the console instead of sending to the API.
     *
     * @param enabled If true, then dev mode is enabled.
     */
    public void devMode(Boolean enabled) {
        System.out.printf("Toggling dev mode %b", enabled);
        devMode = enabled;
    }

    /**
     * Start a pageView.
     *
     * @param data The pageView data.
     * @param data.isImpersonating
     * @param data.pageUrl
     * @param data.pageTitle
     * @param data.referrer
     */
    public void pageView(HashMap<String, String> data) {
        System.out.println("Calling pageView");
        track(eventNames.get("pageView"), "Viewed a Page", data);
    }

    // ------------------------------------ private functions: ---------------------------------- //
    /**
     * Main track function
     *
     * @param eventName The event name.
     * @param eventDisplayName The event display name.
     * @param data The event data.
     * @param data.isImpersonating
     */

    private void track(String eventName, String eventDisplayName, HashMap<String, String> data) {
        System.out.println("Calling track");

        // TODO: check impersonation mode
        sendRequest("track", eventName, eventDisplayName);
    }

    private void sendRequest(String path, String eventName, String eventDisplayName) {
        System.out.printf("Calling sendRequest %s %s %s\n", path, eventName, eventDisplayName);

        if (devMode) {
            // Generate the request endpoint
            String requestEndpoint = _url + "/" + path;
            try {
                URL url = new URL(requestEndpoint);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            // HttpURLConnection con = (HttpURLConnection) url.openConnection();
            // con.setRequestMethod("GET");
        } else {
            System.out.println("Dev mode is enabled");
        }

    }
}

