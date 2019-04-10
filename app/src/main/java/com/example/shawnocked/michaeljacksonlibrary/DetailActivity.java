package com.example.shawnocked.michaeljacksonlibrary;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/*
 An activity exhibits detail information for a specific Michael Jackson's track
 */

public class DetailActivity extends AppCompatActivity {

    TextView trackNameHolder;
    TextView collectionHolder;
    TextView dateHolder;
    TextView previewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i = this.getIntent();
        String trackName = (String) i.getStringExtra("TRACK");
        String collection = (String) i.getStringExtra("COLLECTION");
        String date = (String) i.getStringExtra("DATE");
        date = date.substring(0, date.indexOf("T"));
        final String preview = (String) i.getStringExtra("PREVIEW");

        trackNameHolder = (TextView) findViewById(R.id.trackName);
        trackNameHolder.setText(trackName);
        collectionHolder = (TextView) findViewById(R.id.collection);
        collectionHolder.setText("$ " + collection);
        dateHolder = (TextView) findViewById(R.id.date);
        dateHolder.setText(date);

        previewHolder = (TextView) findViewById(R.id.preview);
        previewHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse(preview));
                startActivity(browserIntent);
            }
        });

    }
}
