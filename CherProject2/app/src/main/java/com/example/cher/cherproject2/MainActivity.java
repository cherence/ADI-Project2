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
    HPSQLiteHelper mHelper;

    public static final String HOGSMEADE = "Hogsmeade";
    public static final String HOGWARTS = "Hogwarts";
    public static final String DIAGON_ALLEY = "Diagon Alley";
    public static final String KNOCKTURN_ALLEY = "Knockturn Alley";
    public static final String LONDON = "London, UK";


    static final String TYPES_KEY = "key for favorites button";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        mHelper = HPSQLiteHelper.getmInstance(MainActivity.this);
        insertRows();
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

    public void insertRows(){
        mHelper.insert("Food", "Three Broomsticks", HOGSMEADE, "false", R.string.threeBroomSticks, R.drawable.threebroomsticks_logo, R.drawable.threebroomsticks_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Rides", "Dragon's Challenge", HOGSMEADE, "false",  R.string.dragonsChallenge, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Shows", "Celestina Warbeck Concert", DIAGON_ALLEY, "false",  R.string.celestinaWarbeck, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Shopping", "Borgin & Burkes", KNOCKTURN_ALLEY, "false",  R.string.borginBurkes, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
    }

    private void setIntents(){
        intentSendToResultsActivity = new Intent(MainActivity.this, ResultsActivity.class);
    }

    private void createAndSetFavoritesButton(){
        favoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String favorites = favoritesButton.getText().toString();
                intentSendToResultsActivity.putExtra(TYPES_KEY, favorites);
                startActivity(intentSendToResultsActivity);
            }
        });
    }

    private void createAndSetAllResultsButton(){
        allResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String allResults = allResultsButton.getText().toString();
                intentSendToResultsActivity.putExtra(TYPES_KEY, allResults);
                startActivity(intentSendToResultsActivity);
            }
        });
    }

    private void createAndSetFoodButton(){
        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String food = foodButton.getText().toString();
                intentSendToResultsActivity.putExtra(TYPES_KEY, food);
                startActivity(intentSendToResultsActivity);
            }
        });
    }

    private void createAndSetRidesButton(){
        ridesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rides = ridesButton.getText().toString();
                intentSendToResultsActivity.putExtra(TYPES_KEY, rides);
                startActivity(intentSendToResultsActivity);
            }
        });
    }

    private void createAndSetShowsButton(){
        showsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shows = showsButton.getText().toString();
                intentSendToResultsActivity.putExtra(TYPES_KEY, shows);
                startActivity(intentSendToResultsActivity);
            }
        });
    }

    private void createAndSetShoppingButton(){
        shoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shopping = shoppingButton.getText().toString();
                intentSendToResultsActivity.putExtra(TYPES_KEY, shopping);
                startActivity(intentSendToResultsActivity);
            }
        });
    }
}
