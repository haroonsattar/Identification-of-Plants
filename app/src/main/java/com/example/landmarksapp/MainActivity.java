package com.example.landmarksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Button mbutton3;
    private Button mbutton6;
    private Button mbutton7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbutton3 = findViewById(R.id.button3);
        mbutton6 = findViewById(R.id.button6);
        mbutton7 = findViewById(R.id.button7);
        ImageButton simpleImageButtonHome = (ImageButton)findViewById(R.id.map);
        mbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, TakePicture.class);
                startActivity(intent);
            }
        });
        mbutton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ManualSearch.class);
                startActivity(intent);
            }
        });
        mbutton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, UploadPicture.class);
                startActivity(intent);
            }
        });
        simpleImageButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW , Uri.parse("geo: 4377.3.2085.789"));
                startActivity(intent);
            }
        });
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
}