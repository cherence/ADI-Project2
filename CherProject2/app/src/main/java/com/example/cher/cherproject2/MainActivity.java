package com.example.cher.cherproject2;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView welcomeImage;
    Button favoritesButton;
    Button allResultsButton;
    Button foodButton;
    Button ridesButton;
    Button showsButton;
    Button shoppingButton;
    Intent intentSendToResultsActivity;

    static Boolean isFaveButtonPressed = false;
    static Boolean isAllResultsButtonPressed = false;
    static Boolean isFoodButtonPressed = false;
    static Boolean isRidesButtonPressed = false;
    static Boolean isShowButtonPressed = false;
    static Boolean isShopButtonPressed = false;
    static final String FAVORITES_BUTTON_KEY = "key for favorites button";
    static final String ALL_RESULTS_BUTTON_KEY = "key for all results button";
    static final String FOOD_BUTTON_KEY = "key for food button";
    static final String RIDES_BUTTON_KEY = "key for rides button";
    static final String SHOWS_BUTTON_KEY = "key for shows button";
    static final String SHOPPING_BUTTON_KEY = "key for shopping button";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setIntents();

        createAndSetFavoritesButton();
        createAndSetAllResultsButton();
        createAndSetFoodButton();
        createAndSetRidesButton();
        createAndSetShowsButton();
        createAndSetShoppingButton();

    }

    private void initializeViews(){
        welcomeImage = (ImageView) findViewById(R.id.welcome_imageView_id);
        favoritesButton = (Button) findViewById(R.id.favorite_button_id);
        allResultsButton = (Button) findViewById(R.id.allResults_button_id);
        foodButton = (Button) findViewById(R.id.food_button_id);
        ridesButton = (Button) findViewById(R.id.rides_button_id);
        showsButton = (Button) findViewById(R.id.shows_button_id);
        shoppingButton = (Button) findViewById(R.id.shopping_button_id);
    }

    private void setIntents(){
        intentSendToResultsActivity = new Intent(MainActivity.this, ResultsActivity.class);
        intentSendToResultsActivity.putExtra(FAVORITES_BUTTON_KEY, "Favorites");
        intentSendToResultsActivity.putExtra(ALL_RESULTS_BUTTON_KEY, "View All");
        intentSendToResultsActivity.putExtra(FOOD_BUTTON_KEY, "Food");
        intentSendToResultsActivity.putExtra(RIDES_BUTTON_KEY, "Rides");
        intentSendToResultsActivity.putExtra(SHOWS_BUTTON_KEY, "Shows");
        intentSendToResultsActivity.putExtra(SHOPPING_BUTTON_KEY, "Shopping");
    }

    private void createAndSetFavoritesButton(){
        favoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFaveButtonPressed = true;
                startActivity(intentSendToResultsActivity);
            }
        });
    }

    private void createAndSetAllResultsButton(){
        allResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isAllResultsButtonPressed = true;
                startActivity(intentSendToResultsActivity);
            }
        });
    }

    private void createAndSetFoodButton(){
        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFoodButtonPressed = true;
                startActivity(intentSendToResultsActivity);
            }
        });
    }

    private void createAndSetRidesButton(){
        ridesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRidesButtonPressed = true;
                startActivity(intentSendToResultsActivity);
            }
        });
    }

    private void createAndSetShowsButton(){
        showsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShowButtonPressed= true;
                startActivity(intentSendToResultsActivity);
            }
        });
    }

    private void createAndSetShoppingButton(){
        shoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShopButtonPressed = true;
                startActivity(intentSendToResultsActivity);
            }
        });
    }
}
