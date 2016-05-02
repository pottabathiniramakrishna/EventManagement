package com.inforica.booker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.inforica.booker.R;
import com.inforica.booker.network.VolleyUtil;
import com.inforica.booker.views.MyCustomProgressDialog;
import com.localytics.android.Localytics;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by user on 4/14/2016.
 */
public class LoginScreen extends SuperActivity implements View.OnClickListener {
    private static final String TAG = "LoginScreen";
    TextView whatsthis;
    TextView errorTextView;
    Button sign_in;
    EditText email_text;
    LoginScreen loginScreen;
    MyCustomProgressDialog routeProgressDialog;
    public final Pattern pattern = Pattern
            .compile("[a-zA-Z0-9+._%-+]{1,256}" + "@"
                    + "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" + "(" + "."
                    + "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" + ")+");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen_activity);
        whatsthis = (TextView) findViewById(R.id.whatsthis);
        whatsthis.setOnClickListener(this);
        sign_in = (Button) findViewById(R.id.sign_in);
        sign_in.setOnClickListener(this);
        email_text = (EditText) findViewById(R.id.email_text);
        errorTextView = (TextView) findViewById(R.id.login_error_string);
        loginScreen = this;
        routeProgressDialog = (MyCustomProgressDialog) MyCustomProgressDialog.ctor(this);
        routeProgressDialog.setCancelable(false);
    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);

        setIntent(intent);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.whatsthis:
                Intent i = new Intent(getApplicationContext(), WhatIsOfficePage.class);
                startActivity(i);
                break;
            case R.id.sign_in:
                    String emailId =email_text.getText().toString().trim();
                    Matcher emailPatternMatcher = pattern.matcher(emailId);
                    boolean emailFormat = emailPatternMatcher.matches();
                    errorTextView.setText("");
                    errorTextView.setVisibility(View.INVISIBLE);
                    if (email_text.getText().toString().trim().length() == 0) {
                        email_text.setError("Please enter E-mail ID");
                        email_text.requestFocus();
                        showKeyboard();

                    } else if (emailFormat == false) {
                        email_text.setError(Html.fromHtml("<font color='white'>Please enter valid E-mail ID</font>"));
                        email_text.requestFocus();
                        showKeyboard();
                    } else {
//                        if (email_text.getText().toString().length() != 0) {}
                        validateSignIn(emailId);
                        Localytics.tagEvent("sign in : "+emailId);
                    }

                break;
        }
    }


    /**
     * validates email
     * @param emailId
     */
    public void validateSignIn(String emailId) {

        if(!VolleyUtil.hasNetworkConnection(getApplicationContext())){
            processResponse("Network issue");
            return;
        }
        // Tag used to cancel the request
        String tag_json_obj = "json_obj_req";
        // used for debuging purpose
        String url = "http://vainavisolutions.com/login.php";

        //postParams : to end data in POST request
        final Map<String, String> postParams = new HashMap<String, String>();
        postParams.put("email", emailId);


        loginScreen.routeProgressDialog.show();

        StringRequest jsonObjReq = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        Log.d(TAG, response.toString());
                        loginScreen.routeProgressDialog.dismiss();
                        processResponse(response);

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.d(TAG, error.toString());
                        loginScreen.routeProgressDialog.dismiss();
                        processResponse("Server Error");
                    }
                })
        {
            @Override
            protected Map<String, String> getParams()
            {
                return postParams;
            }
        };

      /*  JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());

                        routeProgressDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Log.d(TAG, error.toString());
                routeProgressDialog.hide();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", "abc@androidhive.info");

                return params;
            }

        };*/




        // Adding request to request queue
        VolleyUtil.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    /**
     *Process the Response from server
     * @param response
     */
    private void processResponse(String response) {

        if ("true".equalsIgnoreCase(response)) {
            Intent intent = new Intent(this, CalenderPickerRecyclerViewActivity.class);
            startActivity(intent);
            finish();
        } else if("false".equalsIgnoreCase(response)){
            Intent intent = new Intent(this, MainContent.class);
            startActivity(intent);
            finish();
            errorTextView.setVisibility(View.VISIBLE);
            errorTextView.setText("Inavlid Email id");
        } else if("Network issue".equalsIgnoreCase(response)){
            errorTextView.setVisibility(View.VISIBLE);
            errorTextView.setText("No Internet Connetion");
        } else if("Server Error".equalsIgnoreCase(response)){
            errorTextView.setVisibility(View.VISIBLE);
            errorTextView.setText("OOPS!!! Something went wrong, please try after some time. ");
        }
    }
}
