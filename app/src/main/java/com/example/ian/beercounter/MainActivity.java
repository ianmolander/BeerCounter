package com.example.ian.beercounter;


import android.content.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import java.util.*;


public class MainActivity extends AppCompatActivity {

    private TextView beerCounter, beerPriceTotal, latestBeer;
    private int totalBeerCount, totalBeerSum;
    private String latestBeerName;
    private List<String> beerHistory;
    private StringBuilder stringBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findMyViews();
        getBeerInfo();
        getBeerHistory();
    }

    private void findMyViews() {

        beerCounter = findViewById(R.id.total_beer_amountCounter_view);
        beerPriceTotal = findViewById(R.id.total_beer_amount_view);
        latestBeer = findViewById(R.id.latest_beer_view);
        beerHistory = new ArrayList<>();
        stringBuilder = new StringBuilder();
    }


    private void getBeerInfo() {

        SharedPreferences sp = getSharedPreferences("BeerInformation", MODE_PRIVATE);
        totalBeerSum = sp.getInt("totalSum", 0);
        totalBeerCount = sp.getInt("totalCount", 0);
        latestBeerName = sp.getString("latestBeer", "");

        setBeerInfo(latestBeerName, totalBeerSum, totalBeerCount);
    }


    private void setBeerInfo(String latestBeerName, int totalBeerSum, int totalBeerCount) {

        beerCounter.setText("Du har druckit:\n" + totalBeerCount + " öl");
        beerPriceTotal.setText("Till ett värde av:\n" + totalBeerSum + ":-");
        latestBeer.setText("Senast drack du:\n" + latestBeerName);
    }

    private void getBeerHistory() {

        SharedPreferences sp = getSharedPreferences("BeerInformation", MODE_PRIVATE);
        String history = sp.getString("history", "");

        String[] beerArray = history.split(",");

        for (int i = 0; i < beerArray.length; i++) {
            beerHistory.add(beerArray[i]);
        }
    }


    private void storeBeerInfo(int sum, int count, String name) {

        SharedPreferences sp = getSharedPreferences("BeerInformation", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putInt("totalSum", sum);
        editor.putInt("totalCount", count);
        editor.putString("latestBeer", name);
        editor.apply();
        getBeerInfo();
    }

    private void saveBeerHistory() {

        for (String s : beerHistory) {
            stringBuilder.append(s);
            stringBuilder.append(",");
        }

        SharedPreferences sp = getSharedPreferences("BeerInformation", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("history", stringBuilder.toString());
        editor.apply();
    }


    public void eraseBeerHistory(View view) {

        SharedPreferences sp = getSharedPreferences("BeerInformation", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putInt("totalSum", 0);
        editor.putInt("totalCount", 0);
        editor.putString("latestBeer", "");
        editor.putString("history", "");
        editor.apply();

        Toast toast = Toast.makeText(this, "HISTORIKEN RADERAD!", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        getBeerInfo();
        getBeerHistory();
    }


    public void showBeerHistory(View view) {

        Intent getHistoryScreen = new Intent(this, HistoryActivity.class);
        startActivity(getHistoryScreen);
    }


    public void goToBeerActivity(View view) {

        Intent getBeerScreen = new Intent(this, BeerActivity.class);
        final int result = 1;
        getBeerScreen.putExtra("activitCall", "");
        startActivityForResult(getBeerScreen, result);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Beer beer = (Beer) data.getSerializableExtra("beer");
        latestBeerName = beer.getName();
        totalBeerSum = totalBeerSum + beer.getPrice() * beer.getQuantity();
        totalBeerCount = totalBeerCount + beer.getQuantity();

        storeBeerInfo(totalBeerSum, totalBeerCount, latestBeerName);
        beerHistory.add(latestBeerName);
        saveBeerHistory();
    }
}
