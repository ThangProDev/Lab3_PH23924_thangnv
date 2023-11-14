






package com.example.lab3_ph23924.Bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.lab3_ph23924.Bai1.MainActivity;
import com.example.lab3_ph23924.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class B2Activity extends AppCompatActivity {
    Resources resources;
    private String ipAddress;
    String strJson = "";
    String strUrlArr, strUrlObj;

    private static String TAG = MainActivity.class.getSimpleName();
    private Button btnMakeObjectRequest, btnMakeArrayRequest;
    // progress dialog
    private ProgressDialog pDialog;
    private TextView txtResponse;
    // temprorary string to show the parsed response
    private String jsonResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b2);
        resources = getResources();
        ipAddress = resources.getString(R.string.IPADDRESS);
        strUrlObj = ipAddress + "/thangnv_ph23924/person_object.json";
        strUrlArr = ipAddress + "/thangnv_ph23924/person_array.json";

        btnMakeObjectRequest = (Button) findViewById(R.id.btnObjRequest);
        btnMakeArrayRequest = (Button) findViewById(R.id.btnArrRequest);
        txtResponse = (TextView) findViewById(R.id.txtResponse);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        btnMakeObjectRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeJsonObjectRequest();
            }
        });

        btnMakeArrayRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeJsonArrayRequest();
            }
        });

    }
   public void makeJsonArrayRequest(){
       showDialog();

       JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(strUrlArr, new Response.Listener<JSONArray>() {
           @Override
           public void onResponse(JSONArray response) {
               Log.d("Json String", response.toString());
               try {
                for (int i = 0; i < response.length(); i++) {
                       JSONObject jsonObject = (JSONObject) response.get(i);

                       String name = jsonObject.getString("name");
                       String email = jsonObject.getString("email");

                       JSONObject phone = jsonObject.getJSONObject("phone");
                       String home = phone.getString("home");
                       String mobile = phone.getString("mobile");

                       strJson += "Name: "+name + "\n";
                       strJson += "Email: "+email+ "\n";
                       strJson += "Phone home: "+home+ "\n";
                       strJson += "Phone mobile: "+mobile+ "\n\n";
                    }
                txtResponse.setText(strJson);

                }catch (Exception e){
                       Log.e("JsonProcessing", "Error processing JSON", e);

               }
               hideDialog();
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               VolleyLog.d(TAG,"Error: "+ error.getMessage());

               Toast.makeText(B2Activity.this, "", Toast.LENGTH_SHORT).show();

           }
       });
       AppController.getInstance().addToRequestQueue(jsonArrayRequest);
   }
    private void makeJsonObjectRequest() {
        showDialog();
        JsonObjectRequest jsonObjReq = new
                JsonObjectRequest(Request.Method.GET, strUrlObj,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.d(TAG, jsonObject.toString());
                try {
                    // Parsing json object response
// response will be a json object
                    String name = jsonObject.getString("name");
                    String email = jsonObject.getString("email");
                    JSONObject phone = jsonObject.getJSONObject("phone");
                    String home = phone.getString("home");
                    String mobile = phone.getString("mobile");
                    jsonResponse = "";
                    jsonResponse += "Name: " + name + "\n\n";
                    jsonResponse += "Email: " + email + "\n\n";
                    jsonResponse += "Home: " + home + "\n\n";
                    jsonResponse += "Phone: " + mobile + "\n\n";
                    txtResponse.setText(jsonResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error: " +
                            e.getMessage(), Toast.LENGTH_LONG).show();
                }
                hideDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                VolleyLog.d(TAG, "Error: " + volleyError.getMessage());
                Toast.makeText(getApplicationContext(), "Error: " +
                        volleyError.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    private void showDialog() {
        if (!pDialog.isShowing()) {
            pDialog.show();
        }
    }
    private void hideDialog() {
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }
    }