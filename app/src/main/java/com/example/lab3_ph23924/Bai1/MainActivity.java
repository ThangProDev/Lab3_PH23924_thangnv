package com.example.lab3_ph23924.Bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.lab3_ph23924.Bai2.B2Activity;
import com.example.lab3_ph23924.Bai3.B3Activity;
import com.example.lab3_ph23924.Bai4.B4Activity;
import com.example.lab3_ph23924.R;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    GetContract getContract;
    Button btn2, btn3, btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lv);
        btn2 = findViewById(R.id.btn_b2);
        btn3 = findViewById(R.id.btn_b3);
        btn4 = findViewById(R.id.btn_b4);
        if (listView != null) {
            getContract = new GetContract(MainActivity.this, listView);
            getContract.execute();
        } else {
            Log.e("MainActivity", "ListView is null");
        }
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, B2Activity.class);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, B3Activity.class);
                startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, B4Activity.class);
                startActivity(intent);
            }
        });
    }
}