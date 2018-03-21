package com.example.ian.beercounter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.*;
import android.widget.*;

/**
 * Created by Ian on 2018-03-18.
 */

public class BeerActivity extends Activity {

    private EditText beerPrice, beerQuantity;
    private AutoCompleteTextView beerName;
    private static final String[] ALOTOFBEERS;
    private String beerNameInput, beerQuantityInput, beerPriceInput;
    private ArrayAdapter<String> beerAdapter;
    private Beer beer;


    static {
        ALOTOFBEERS = new String[]{
                "Norrlands Guld", "Mariestad", "Mellerud Ekologisk", "Eriksberg Juvelpilsner",
                "Electric Nurse", "Åbro", "Nils Oscar", "Grebbestad Lager", "Falcon",
                "Pripps Blå", "Nya Carnegiebryggeriet", "Pistonhead Flat Tire", "Peroni Nastro Azzurro",
                "Kronenbourg", "Lapin Kulta", "Carlsberg", "Mikkeller", "San Miguel", "Guinness Draught",
                "Kilkenny", "Heineken", "Grolsch Premium Lager", "Amstel", "Staropramen", "Zlatopramen",
                "Krusovice", "Starobrno Premium", "Grolsch", "Gränges", "Spendrup", "Sofiero", "Kung",
                "Södra IPA", "S:t Eriks IPA", "Poppels Wheat IPA", "Electric Nurse Hoppy IPA", "Nils Oscar India Ale",
                "Pelle på Stenen IPA", "Wästkust IPA", "Oppigård New Sweden IPA", "Oppigård Single Hop Ale",
                "Oppigård Amarillo", "Oppigård Golden Ale", "Oppigård Winter Ale", "Nils Oscar Alkoholfri",
                "Nils Oscar Everyday Pils", "Nils Oscar Granat", "Banana Bread Bear", "BrewDog", "Budvar",
                "Budweiser", "Pilsner Urquell", "Staropramen Dark", "Breznak", "Staropramen Non-Alcoholic",
                "Brooklyn Lager", "Brooklyn East India Pale Ale"
        };
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beer_layout);

        findMyViews();
        setupBeerAdapter();
    }


    private void findMyViews() {

        beerPrice = findViewById(R.id.beer_price);
        beerQuantity = findViewById(R.id.beer_quantity);
        beerName = findViewById(R.id.beer_name);
    }


    private void setupBeerAdapter() {

        beerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, ALOTOFBEERS);
        beerName.setAdapter(beerAdapter);
    }


    public void getUserInput(View view) {

        beerNameInput = String.valueOf(beerName.getText());
        beerQuantityInput = String.valueOf(beerQuantity.getText());
        beerPriceInput = String.valueOf(beerPrice.getText());

        checkInput(beerNameInput, beerPriceInput, beerQuantityInput);
    }


    private void checkInput(String name, String price, String quantity) {

        if (name.isEmpty() || quantity.isEmpty() || price.isEmpty()) {
            Toast toast = Toast.makeText(this, "FYLL I ALLA FÄLT!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            findMyViews();
        } else {
            sendBeerInfo(name, price, quantity);
        }
    }


    private void sendBeerInfo(String name, String price, String quantity) {

        Intent sendBack = new Intent();
        int newBeerPrice = Integer.parseInt(price);
        int newBeerQuantity = Integer.parseInt(quantity);

        beer = new Beer(name, newBeerPrice, newBeerQuantity);

        sendBack.putExtra("beer", beer);
        setResult(RESULT_OK, sendBack);

        Toast toast = Toast.makeText(this, "SPARAD", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        finish();
    }

}











