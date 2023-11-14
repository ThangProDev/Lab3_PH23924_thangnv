
package com.example.lab3_ph23924.Bai4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.example.lab3_ph23924.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class B4Activity extends AppCompatActivity {
    private ListView listView;
    private View parentView;
    private ArrayList<ContactB4> contactList;
    private MyContactAdapterB4 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        contactList = new ArrayList<>();
        parentView = findViewById(R.id.parentLayout);
        listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                    @Override
                                                    public void onItemClick(AdapterView<?> parent, View view, int
                                                            position, long id) {
                                                        Snackbar.make(parentView,
                                                                contactList.get(position).getName() + " => " +
                                                                        contactList.get(position).getPhone().getHome(),
                                                                Snackbar.LENGTH_LONG).show();
                                                    }
                                                });
        Toast toast = Toast.makeText(getApplicationContext(),"string_click_to_load", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        FloatingActionButton fab = (FloatingActionButton)  findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if
                (InternetConnectionB4.checkConnection(getApplicationContext())) {
                    final ProgressDialog dialog;
                    dialog = new ProgressDialog(B4Activity.this);

                    dialog.setTitle("string_getting_json_title");

                    dialog.setMessage("string_getting_json_message");
                    dialog.show();
                    //Creating an object of our api interface
                    ApiServiceB4 api = RetroClientB4.getApiService();
                    // Calling JSON
                    Call<ContactListB4> call = api.getMyJSON();

                    // Enqueue Callback will be call when get response...
                    call.enqueue(new Callback<ContactListB4>() {
                        @Override
                        public void onResponse(Call<ContactListB4> call,
                                               Response<ContactListB4> response) {
                            //Dismiss Dialog
                            dialog.dismiss();
                            if(response.isSuccessful()) {
                                // Got Successfully
                                contactList = response.body().getContacts();
                                // Binding that List to Adapter
                                adapter = new
                                        MyContactAdapterB4(B4Activity.this, contactList);
                                listView.setAdapter(adapter);
                            } else {
                                Snackbar.make(parentView,"string_some_thing_wrong", Snackbar.LENGTH_LONG).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<ContactListB4> call, Throwable t) {
                            dialog.dismiss();
                        }
                    });
                } else {
                    Snackbar.make(parentView,  "string_internet_connection_not_available", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it ispresent.
//                getMenuInflater().inflate(R.menu, menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//
//        }
}