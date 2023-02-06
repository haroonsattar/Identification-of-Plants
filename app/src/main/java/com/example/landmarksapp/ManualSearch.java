package com.example.landmarksapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ManualSearch extends AppCompatActivity {

    TextView search;
    Button b_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_search);

        search = findViewById(R.id.search);
        b_search = findViewById(R.id.b_search);

        b_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchTerms = search.getText().toString();
                if (!searchTerms.equals("")) {
                    searchNet(searchTerms);
                }

            }
        });
    }

    private void searchNet(String words){
        try {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, words);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            searchNetCompat(words);
        }
    }

    private void searchNetCompat(String words){
        try {
            Uri uri = Uri.parse("https://www.google.com/#q=" + words);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.putExtra(SearchManager.QUERY, words);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
        }
    }
}

