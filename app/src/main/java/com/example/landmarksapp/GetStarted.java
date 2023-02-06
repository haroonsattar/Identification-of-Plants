package com.example.landmarksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class GetStarted extends AppCompatActivity {
    private Button mbutton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        mbutton2 = findViewById(R.id.button2);
        mbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GetStarted.this, Login.class);
                startActivity(intent);
            }
        });

    }


}