package com.example.cher.cherproject2;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
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
    SharedPreferences sharedPreferences;
    boolean firstLaunch;
    boolean isPlaying;


    public static final String HOGSMEADE = "Hogsmeade";
    public static final String HOGWARTS = "Hogwarts";
    public static final String DIAGON_ALLEY = "Diagon Alley";
    public static final String KNOCKTURN_ALLEY = "Knockturn Alley";
    public static final String LONDON = "London, UK";
    public static final String FAVORITE = "alohomora";
    public static final String NOT_FAVORITE = "colloportus";
    public static final String TYPES_KEY = "key for all button";
    public static final String SHARED_PREFERENCES_KEY = "key for sharedPreferences";
    public static final String FIRST_RUN_KEY = "sharedPreferences key for inserting rows";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isPlaying = MusicService.isPlaying;
        firstLaunch = true;

        initializeViews();
        mHelper = HPSQLiteHelper.getmInstance(MainActivity.this);
        makeTableOrRetrieveSharedPreferences();
//        populateTable();

        saveSharedPreferences();
        setIntents();

        createAndSetFavoritesButton();
        createAndSetAllResultsButton();
        createAndSetFoodButton();
        createAndSetRidesButton();
        createAndSetShowsButton();
        createAndSetShoppingButton();
    }

    private void makeTableOrRetrieveSharedPreferences(){
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
        firstLaunch = sharedPreferences.getBoolean(FIRST_RUN_KEY, true);
        //if it's the very first time...
        if (firstLaunch == false) {
            return;
        } if (firstLaunch == true){
            populateTable();
        }
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

    public void populateTable(){
        mHelper.insert("Food", "Three Broomsticks", HOGSMEADE, NOT_FAVORITE, R.string.threeBroomSticks, R.drawable.threebroomsticks_logo, R.drawable.threebroomsticks_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Food", "Hog\'s Head Pub", HOGSMEADE, NOT_FAVORITE, R.string.hogsHeadPub, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Food", "Leaky Cauldron", DIAGON_ALLEY, NOT_FAVORITE, R.string.leakyCauldron, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Food", "The Hopping Pot", DIAGON_ALLEY, NOT_FAVORITE, R.string.hoppingPot, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Food", "Florean Fortescue\'s Ice-Cream Parlour", DIAGON_ALLEY, NOT_FAVORITE, R.string.floreanFortescue, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Rides", "Dragon's Challenge", HOGSMEADE, NOT_FAVORITE, R.string.dragonsChallenge, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Rides", "Flight of the Hippogriff", HOGWARTS, NOT_FAVORITE, R.string.flightHippogriff, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Rides", "Harry Potter and the Forbidden Journey", HOGWARTS, NOT_FAVORITE, R.string.forbiddenJourney, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Rides", "Escape from Gringotts", DIAGON_ALLEY, NOT_FAVORITE, R.string.escapeGringotts, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Rides", "Hogsmeade Station", HOGSMEADE, NOT_FAVORITE, R.string.hogsmeadeStation, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Rides", "King\'s Cross Station", LONDON, NOT_FAVORITE, R.string.kingsCrossStation, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Shows", "Celestina Warbeck Concert", DIAGON_ALLEY, NOT_FAVORITE,  R.string.celestinaWarbeck, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Shows", "Frog Choir", HOGWARTS, NOT_FAVORITE,  R.string.frogChoir, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Shows", "Olivanders Wand Experience", HOGSMEADE, NOT_FAVORITE,  R.string.olivandersExperienceH, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Shows", "Olivanders Wand Experience", DIAGON_ALLEY, NOT_FAVORITE,  R.string.olivandersExperienceDA, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Shows", "Tales of Beadle the Bard", DIAGON_ALLEY, NOT_FAVORITE,  R.string.talesBeadle, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Shows", "Triwizard Spirit Rally", HOGWARTS, NOT_FAVORITE,  R.string.triwizardSpirit, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Shopping", "Borgin & Burkes", KNOCKTURN_ALLEY, NOT_FAVORITE,  R.string.borginBurkes, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Shopping", "Dervish & Banges", HOGSMEADE, NOT_FAVORITE,  R.string.dervishBanges, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Shopping", "Filch\'s Emporium of Confiscated Goods", HOGWARTS, NOT_FAVORITE,  R.string.filchsEmporium, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Shopping", "Gringotts Money Exchange", DIAGON_ALLEY, NOT_FAVORITE,  R.string.gringottMoneyExchange, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Shopping", "Honeydukes", HOGSMEADE, NOT_FAVORITE,  R.string.honeydukes, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Shopping", "Mandam Malkin\'s Robes for All Occasions", DIAGON_ALLEY, NOT_FAVORITE,  R.string.madamMalkins, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Shopping", "Magical Menagerie", HOGSMEADE, NOT_FAVORITE,  R.string.magicalMenagerie, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Shopping", "Olivanders", HOGSMEADE, NOT_FAVORITE,  R.string.olivandersH, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Shopping", "Olivanders", DIAGON_ALLEY, NOT_FAVORITE,  R.string.olivandersExperienceDA, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Shopping", "Owl Post & Owlery", HOGSMEADE, NOT_FAVORITE,  R.string.owlPostOwlery, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Shopping", "Quality Quidditch Supplies", DIAGON_ALLEY, NOT_FAVORITE,  R.string.qualityQuidditch, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Shopping", "Weasley\'s Wizard Wheezes", DIAGON_ALLEY, NOT_FAVORITE,  R.string.weasleysWizard, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Shopping", "Wisacre\'s Wizarding Equipment", DIAGON_ALLEY, NOT_FAVORITE,  R.string.wiseacresWizarding, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Shopping", "Scribbulus", DIAGON_ALLEY, NOT_FAVORITE,  R.string.scribbulus, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Shopping", "Shutterbutton\'s Photography Studio", DIAGON_ALLEY, NOT_FAVORITE,  R.string.shutterbuttons, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
        mHelper.insert("Shopping", "Wands by Gregorovitch", DIAGON_ALLEY, NOT_FAVORITE,  R.string.wandsByGregorovitch, R.drawable.generic_logo, R.drawable.generic_header, R.drawable.threebroomsticks_map);
    }


    public void saveSharedPreferences(){
        firstLaunch = false;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(FIRST_RUN_KEY, firstLaunch);
        editor.commit();
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
