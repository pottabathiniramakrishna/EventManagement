package com.inforica.booker.service;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 2/9/2016.
 */
public class JSONParser {
    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";

    // constructor
    public JSONParser() {
    }

    public static JSONObject makeHttpRequest(String url, String method, List<NameValuePair> params) {
        // Making HTTP request
        try {
            // check for request method
            if (method == "POST") {
                // request method is POST
                // defaultHttpClient
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.addHeader("Authorization", "bearer g7OxgIlbZAkEluRu9rB6hA15zrEX-WCdezgBFfgNl4UuJ7U9Sernk1jr2F0BXswSTX35QYDbKyoT0SwMor-wwoA3J5HLFVEyuKWQ0nq8RuD7gqtYly36fTYS2BfsQ-fU7c4b3MYtq7dhdwYrF8IlZGOTN51n6ZZCg_XildO0HXK9T9Q1hN1nw2umHyv8ij1IrNgl4yRjOkujeR9XkhvCOedFEoNDfzddEKnsvzByaQWnviY7");
//                httpPost.setHeader("Content-Type", "application/json");
                httpPost.setEntity(new UrlEncodedFormEntity(params));
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

            } else if (method == "GET") {
                // request method is GET
                DefaultHttpClient httpClient = new DefaultHttpClient();
//                String paramString = URLEncodedUtils.format(params, "utf-8");
//                url += "?" ;
                url = addLocationToUrl(url);

                HttpGet httpGet = new HttpGet(url);
                httpGet.addHeader("Authorization", "bearer g7OxgIlbZAkEluRu9rB6hA15zrEX-WCdezgBFfgNl4UuJ7U9Sernk1jr2F0BXswSTX35QYDbKyoT0SwMor-wwoA3J5HLFVEyuKWQ0nq8RuD7gqtYly36fTYS2BfsQ-fU7c4b3MYtq7dhdwYrF8IlZGOTN51n6ZZCg_XildO0HXK9T9Q1hN1nw2umHyv8ij1IrNgl4yRjOkujeR9XkhvCOedFEoNDfzddEKnsvzByaQWnviY7");
//                httpGet.setHeader("Content-Type", "application/json");

                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        // return JSON String
        return jObj;
    }


    public static JSONObject makeHttpRequest(String url, String method, String params) {
        // Making HTTP request
        try {
            // check for request method
            if (method == "POST") {
                // request method is POST
                // defaultHttpClient
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.addHeader("Authorization", "bearer g7OxgIlbZAkEluRu9rB6hA15zrEX-WCdezgBFfgNl4UuJ7U9Sernk1jr2F0BXswSTX35QYDbKyoT0SwMor-wwoA3J5HLFVEyuKWQ0nq8RuD7gqtYly36fTYS2BfsQ-fU7c4b3MYtq7dhdwYrF8IlZGOTN51n6ZZCg_XildO0HXK9T9Q1hN1nw2umHyv8ij1IrNgl4yRjOkujeR9XkhvCOedFEoNDfzddEKnsvzByaQWnviY7");
//                httpPost.setHeader("Content-Type", "application/json");
//                StringEntity stringEntity = new StringEntity();

                StringEntity stringEntity = new StringEntity(params, "UTF-8");
                stringEntity.setContentType("application/json");
                httpPost.setEntity(stringEntity);


                httpPost.setEntity(stringEntity);
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();

            } else if (method == "GET") {
                // request method is GET
                DefaultHttpClient httpClient = new DefaultHttpClient();
//                String paramString = URLEncodedUtils.format(params, "utf-8");
//                url += "?" ;
                url = addLocationToUrl(url);

                HttpGet httpGet = new HttpGet(url);
                httpGet.addHeader("Authorization", "bearer g7OxgIlbZAkEluRu9rB6hA15zrEX-WCdezgBFfgNl4UuJ7U9Sernk1jr2F0BXswSTX35QYDbKyoT0SwMor-wwoA3J5HLFVEyuKWQ0nq8RuD7gqtYly36fTYS2BfsQ-fU7c4b3MYtq7dhdwYrF8IlZGOTN51n6ZZCg_XildO0HXK9T9Q1hN1nw2umHyv8ij1IrNgl4yRjOkujeR9XkhvCOedFEoNDfzddEKnsvzByaQWnviY7");
//                httpGet.setHeader("Content-Type", "application/json");

                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        // return JSON String
        return jObj;
    }

    public static String addLocationToUrl(String url) {
        if (!url.endsWith("?"))
            url += "?";

        List<NameValuePair> params = new LinkedList<NameValuePair>();

   /*     if (lat != 0.0 && lon != 0.0){
            params.add(new BasicNameValuePair("lat", String.valueOf(lat)));
            params.add(new BasicNameValuePair("lon", String.valueOf(lon)));
        }

        if (address != null && address.getPostalCode() != null)
            params.add(new BasicNameValuePair("postalCode", address.getPostalCode()));
        if (address != null && address.getCountryCode() != null)
            params.add(new BasicNameValuePair("country",address.getCountryCode()));*/

        params.add(new BasicNameValuePair("UserId", "56121dd09e10c4151ccc46fa"));

        String paramString = URLEncodedUtils.format(params, "utf-8");

        url += paramString;
        return url;
    }
}
