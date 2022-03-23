package com.example.tp3_5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText text_url;
    EditText text_navigation;
    EditText text_partage;

    Button button_url;
    Button button_navigation;
    Button button_partage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_url = findViewById(R.id.url);
        text_navigation = findViewById(R.id.navigation);
        text_partage = findViewById(R.id.partage);

        button_url = findViewById(R.id.buttonUrl);
        button_navigation = findViewById(R.id.buttonNavigation);
        button_partage = findViewById(R.id.buttonPartage);

        button_url.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("QueryPermissionsNeeded")
            @Override
            public void onClick(View view) {
                String url = text_url.getText().toString();
                if (url.isEmpty()){
                    Toast.makeText(MainActivity.this, "Url vide!", Toast.LENGTH_SHORT).show();
                }else{
                    if (!url.startsWith("http://") && !url.startsWith("https://")) {
                        url = "https://www.google.com/search?q=" + url;
                    }
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    i.putExtra("url", url);
                    startActivity(i);
                }

            }
        });

        button_navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = text_navigation.getText().toString();
                if (url.isEmpty()){
                    Toast.makeText(MainActivity.this, "Url vide!", Toast.LENGTH_SHORT).show();
                }else{

                    Intent map = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="+url));
                    map.setPackage("com.google.android.apps.maps");
                    startActivity(map);
                }
            }
        });

        button_partage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = text_partage.getText().toString();
                if (text.isEmpty()){
                    Toast.makeText(MainActivity.this, "text vide!", Toast.LENGTH_SHORT).show();
                }else{

                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, text);
                    sendIntent.setType("text/plain");
                    Intent shareIntent = Intent.createChooser(sendIntent, null);
                    startActivity(shareIntent);
                }
            }
        });

    }
}