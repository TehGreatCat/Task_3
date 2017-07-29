package com.romanov;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;

public class HttpRequester {

    private final HttpClient httpClient;

    private final String API_KEY;

    public HttpRequester(HttpClient httpClient, String API_KEY) {
        this.httpClient = httpClient;
        this.API_KEY = API_KEY;
    }

    public String TranslateRequester(String text) throws IOException, HttpException, JSONException {
        StringBuilder sb = new StringBuilder();
        sb.append("https://translate.yandex.net/api/v1.5/tr.json/translate?").
                append("key=").append(API_KEY).
                append("&lang=en-ru").
                append("&text=").append(URLEncoder.encode(text, "UTF-8"));
        HttpResponse response;
        HttpPost req = new HttpPost(sb.toString());
        response = httpClient.execute(req);
        if (response.getStatusLine().getStatusCode() == 200) {
            JSONObject json = new JSONObject(EntityUtils.toString(response.getEntity()));
            String trasnlation = json.getJSONArray("text").getString(0);
            return trasnlation;
        } else {
            return "something went wrong";
        }
    }
}
