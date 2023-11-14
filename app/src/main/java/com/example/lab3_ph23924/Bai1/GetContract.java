package com.example.lab3_ph23924.Bai1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.example.lab3_ph23924.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetContract extends AsyncTask<Void, Void, Void> {
    private String TAG = MainActivity.class.getSimpleName();
    Context context;
    Resources resources;
    private String ipAddress;

    ArrayList<Contract> contactList;
    private ProgressDialog pDialog;
    private ListView lv;
    public String url;
    ContractAdapter adapter;



    public GetContract(Context context, ListView lv) {
        this.lv = lv;
        this.context = context;
        contactList = new ArrayList<>();
        this.resources = context.getResources();
        this.ipAddress = resources.getString(R.string.IPADDRESS);
        this.url = ipAddress + "/thangnv_ph23924/index.php";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        context = lv.getContext(); // Thay thế YourActivityName bằng tên activity của bạn
        adapter = new ContractAdapter(context, new ArrayList<>());
        if (context != null) {
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        contactList = new ArrayList<>();
        HttpHandler handler = new HttpHandler();
        String jsonStr = handler.makeServiceCall(url);
        Log.e(TAG, "Response from url: "+ jsonStr);
        if (jsonStr != null) {
            try {
                JSONObject jsonObject = new JSONObject(jsonStr);
                // Getting JSON Array node
                JSONArray contacts = jsonObject.getJSONArray("contacts");
                // looping through all Contacts
                for (int i = 0; i < contacts.length(); i++) {
                    JSONObject c = contacts.getJSONObject(i);
                    String id = c.getString("id");
                    String name = c.getString("name");
                    String email = c.getString("email");
                    String address = c.getString("address");
                    String gender = c.getString("gender");
                    // Phone node is JSON Object
                    JSONObject phone = c.getJSONObject("phone");
                    String mobile = phone.getString("mobile");
                    String home = phone.getString("home");
                    String office = phone.getString("office");
                    Contract contact = new Contract();
                    contact.setId(id);
                    contact.setName(name);
                    contact.setEmail(email);
                    contact.setMobile(mobile);
                    contactList.add(contact);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, "Json parsing error: " + e.getMessage());
            }
        } else {
            Log.e(TAG, "Couldn't get json from server.");
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.dismiss();
        }
        if (context != null && adapter != null) {
            adapter = new ContractAdapter(context, contactList);
            lv.setAdapter(adapter);
        } else {
            Log.e(TAG, "Context or adapter is null");
        }
    }
}
