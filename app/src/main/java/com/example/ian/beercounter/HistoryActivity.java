package com.example.ian.beercounter;

import android.app.Activity;
import android.content.*;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.*;

import java.util.*;


/**
 * Created by Ian on 2018-03-19.
 */

public class HistoryActivity extends Activity {
    private ListView beerHistory;
    private ArrayAdapter<String> beerAdapter;
    private List<String> allBeerHistory;
    private String[] beerArray;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beer_history_layout);

        findMyViews();
        getBeerHistory();
    }

    private void findMyViews() {

        beerHistory = findViewById(R.id.history_listview);
        allBeerHistory = new ArrayList<>();
    }

    private void getBeerHistory() {

        SharedPreferences sp = getSharedPreferences("BeerInformation", MODE_PRIVATE);
        String beerHistory = sp.getString("history", "");

        beerArray = beerHistory.split(",");

        for (int i = 0; i < beerArray.length; i++) {
            allBeerHistory.add(beerArray[i]);
        }

        setupBeerHistory();
    }


    protected void setupBeerHistory() {

        beerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, allBeerHistory);
        beerHistory.setAdapter(beerAdapter);
        beerAdapter.notifyDataSetChanged();
    }


    public void goBackToMainActivity(View view) {

        Intent goToMain = new Intent(this, MainActivity.class);
        startActivity(goToMain);
    }
}
