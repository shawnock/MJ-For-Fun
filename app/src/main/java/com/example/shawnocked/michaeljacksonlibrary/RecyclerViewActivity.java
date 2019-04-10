package com.example.shawnocked.michaeljacksonlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/*
 Class handles images appearances on RecyclerView layout
 */

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        // receive data
        Intent i = this.getIntent();
        ArrayList<String> imagesStore = (ArrayList<String>) i.getSerializableExtra("IMAGE");
        ArrayList<String> trackNameStore = (ArrayList<String>) i.getSerializableExtra("TRACKNAME");
        ArrayList<String> previewStore = (ArrayList<String>) i.getSerializableExtra("PREVIEWLIST");
        ArrayList<String> collectionStore = (ArrayList<String>) i.getSerializableExtra("COLLECTIONLIST");
        ArrayList<String> releaseStore = (ArrayList<String>) i.getSerializableExtra("DATELIST");

        this.recyclerView = findViewById(R.id.rv_images);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        this.recyclerViewAdapter = new RecyclerViewAdapter(imagesStore, trackNameStore, previewStore, collectionStore, releaseStore,this);
        recyclerView.setAdapter(this.recyclerViewAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
