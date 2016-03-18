package com.example.cher.cherproject2;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.telecom.Call;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.jar.Manifest;

public class ResultsActivity extends AppCompatActivity {

    TextView resultsTitleTextView;
    ListView categoryResultsListView;
    Cursor cursor;
    HPSQLiteHelper mHelper;
//    ArrayList<Food> foodArrayList;
//    ArrayList<Rides> ridesArrayList;
//    ArrayList<Shows> showsArrayList;
//    ArrayList<Shopping> shoppingArrayList;
//    FoodAdapter foodAdapter;
//    RidesAdapter ridesAdapter;
//    ShowsAdapter showsAdapter;
//    ShopsAdapter shopsAdapter;
//    ArrayList<Favorites> favoritesArrayList; don't know if i'll need this
    public static final String HOGSMEADE = "Hogsmeade";
    public static final String HOGWARTS = "Hogwarts";
    public static final String DIAGON_ALLEY = "Diagon Alley";
    public static final String KNOCKTURN_ALLEY = "Knockturn Alley";
    public static final String LONDON = "London, UK";
    public static final String DETAILS_TITLE_KEY = "key for details title";
    public static final String KEY_SENDING_PRIMARY_ID = "_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        initializeViews();
        mHelper = HPSQLiteHelper.getmInstance(ResultsActivity.this);



//        instantiateNewFoodAndAddToFoodArrayList();
//        instantiateNewRidesAndAddToRidesArrayList();
//        instantiateNewShowsAndAddToShowsArrayList();
//        instantiateNewShopsAndAddToShopsArrayList();

//        createAndSetAdapters();
        getIntentToChangeTitleAndSetAdapter();
//        createAndSetOnItemClickListener();
        handleIntent(getIntent());
        createAndSetOnItemClickListener();
    }

    public void initializeViews(){
        resultsTitleTextView = (TextView) findViewById(R.id.resultsTitle_textView_id);
        categoryResultsListView = (ListView) findViewById(R.id.categoryResults_listView_id);
    }

    private void getIntentToChangeTitleAndSetAdapter(){
        Intent intentRetrieveFromMainActivity = getIntent();
        String faveTitle = intentRetrieveFromMainActivity.getStringExtra(MainActivity.FAVORITES_BUTTON_KEY);
        String allResultsTitle = intentRetrieveFromMainActivity.getStringExtra(MainActivity.ALL_RESULTS_BUTTON_KEY);
        String foodTitle = intentRetrieveFromMainActivity.getStringExtra(MainActivity.FOOD_BUTTON_KEY);
        String ridesTitle = intentRetrieveFromMainActivity.getStringExtra(MainActivity.RIDES_BUTTON_KEY);
        String showsTitle = intentRetrieveFromMainActivity.getStringExtra(MainActivity.SHOWS_BUTTON_KEY);
        String shopsTitle = intentRetrieveFromMainActivity.getStringExtra(MainActivity.SHOPPING_BUTTON_KEY);

        if(MainActivity.isFaveButtonPressed == true){
            resultsTitleTextView.setText(faveTitle);
            MainActivity.isFaveButtonPressed = false;
        } else if(MainActivity.isAllResultsButtonPressed == true){
            resultsTitleTextView.setText(allResultsTitle);
            MainActivity.isAllResultsButtonPressed = false;
            cursor = mHelper.getEntireTable();
        } else if(MainActivity.isFoodButtonPressed == true){
            resultsTitleTextView.setText(foodTitle);
            MainActivity.isFoodButtonPressed = false;
//            categoryResultsListView.setAdapter(foodAdapter);
        } else if(MainActivity.isRidesButtonPressed == true){
            resultsTitleTextView.setText(ridesTitle);
            MainActivity.isRidesButtonPressed = false;
//            categoryResultsListView.setAdapter(ridesAdapter);
        } else if(MainActivity.isShowButtonPressed == true){
            resultsTitleTextView.setText(showsTitle);
            MainActivity.isShowButtonPressed = false;
//            categoryResultsListView.setAdapter(showsAdapter);
        } else if (MainActivity.isShopButtonPressed == true){
            resultsTitleTextView.setText(shopsTitle);
            MainActivity.isShopButtonPressed = false;
//            categoryResultsListView.setAdapter(shopsAdapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent){
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);

        }
    }

    private void createAndSetOnItemClickListener(){
        categoryResultsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentSendPrimaryID = new Intent(ResultsActivity.this, DetailsActivity.class);
                cursor.moveToPosition(position);
                intentSendPrimaryID.putExtra(KEY_SENDING_PRIMARY_ID, cursor.getInt(cursor.getColumnIndex(HPSQLiteHelper.COL_ID)));
                startActivity(intentSendPrimaryID);
            }
        });

    }

//    private void createAndSetOnItemClickListener(){
//        categoryResultsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intentSendToDetailsActivity = new Intent(ResultsActivity.this, DetailsActivity.class);
//                intentSendToDetailsActivity.putExtra(DETAILS_TITLE_KEY, detailsTitleString);
//                startActivity(intentSendToDetailsActivity);
//            }
//        });
//    }

//    private void instantiateNewFoodAndAddToFoodArrayList(){
//        Food food1 = new Food("Three Broomsticks", HOGSMEADE, getString(R.string.threeBroomSticks), 1);
//        Food food2 = new Food("Hog's Head Pub", HOGSMEADE, "", 2);
//        Food food3 = new Food("Leaky Cauldron", DIAGON_ALLEY, "", 3);
//        Food food4 = new Food("The Hopping Pot", DIAGON_ALLEY, "", 4);
//        Food food5 = new Food("Florean Fortescue's Ice-Cream Parlour", DIAGON_ALLEY, "", 5);
//
//        foodArrayList = new ArrayList<>();
//        foodArrayList.add(food1);
//        foodArrayList.add(food2);
//        foodArrayList.add(food3);
//        foodArrayList.add(food4);
//        foodArrayList.add(food5);
//    }
//
//    private void instantiateNewRidesAndAddToRidesArrayList(){
//        Rides rides1 = new Rides("Dragon Challenge", HOGSMEADE, "", 6);
//        Rides rides2 = new Rides("Flight of the Hippogriff", HOGWARTS, "", 7);
//        Rides rides3 = new Rides("Harry Potter and the Forbidden Journey", HOGSMEADE, "", 8);
//        Rides rides4 = new Rides("Escape from Gringotts", DIAGON_ALLEY, "", 9);
//        Rides rides5 = new Rides("Hogsmeade Station", HOGSMEADE, "", 10);
//        Rides rides6 = new Rides("King's Cross Station", LONDON, "", 11);
//
//        ridesArrayList = new ArrayList<>();
//        ridesArrayList.add(rides1);
//        ridesArrayList.add(rides2);
//        ridesArrayList.add(rides3);
//        ridesArrayList.add(rides4);
//        ridesArrayList.add(rides5);
//        ridesArrayList.add(rides6);
//    }

//    private void instantiateNewShowsAndAddToShowsArrayList(){
//        Shows shows1 = new Shows("Celestina Warbeck Concert", DIAGON_ALLEY, "", 12);
//        Shows shows2 = new Shows("Frog Choir", HOGSMEADE, "", 13);
//        Shows shows3 = new Shows("Olivanders Wand Experience", HOGSMEADE, "", 14);
//        Shows shows4 = new Shows("Olivanders Wand Experience", DIAGON_ALLEY, "",15);
//        Shows shows5 = new Shows("Tales of Beadle the Bard", DIAGON_ALLEY, "", 16);
//        Shows shows6 = new Shows("Triwizard Spirit Rally", HOGSMEADE, "", 17);
//
//        showsArrayList = new ArrayList<>();
//        showsArrayList.add(shows1);
//        showsArrayList.add(shows2);
//        showsArrayList.add(shows3);
//        showsArrayList.add(shows4);
//        showsArrayList.add(shows5);
//        showsArrayList.add(shows6);
//    }

//    private void instantiateNewShopsAndAddToShopsArrayList(){
//        Shopping shopping1 = new Shopping("Borgin & Burkes", KNOCKTURN_ALLEY, "", 18);
//        Shopping shopping2 = new Shopping("Dervish & Banges", HOGSMEADE, "", 19);
//        Shopping shopping3 = new Shopping("Filch's Emporium of Cnfiscated Goods", HOGWARTS, "", 20);
//        Shopping shopping4 = new Shopping("Gringotts Money Exchange", DIAGON_ALLEY, "", 21);
//        Shopping shopping5 = new Shopping("Honeydukes", HOGSMEADE, "", 22);
//        Shopping shopping6 = new Shopping("Madam Malkin's Robes for All Occasions", DIAGON_ALLEY, "", 23);
//        Shopping shopping7 = new Shopping("Magical Menagerie", DIAGON_ALLEY, "", 24);
//        Shopping shopping8 = new Shopping("Olivanders", DIAGON_ALLEY, "", 25);
//        Shopping shopping9 = new Shopping("Olivanders", HOGSMEADE, "", 26);
//        Shopping shopping10 = new Shopping("Owl Post/Owlery", DIAGON_ALLEY, "", 27);
//        Shopping shopping11 = new Shopping("Quality Quidditch Supplies", KNOCKTURN_ALLEY, "", 28);
//        Shopping shopping12 = new Shopping("Weasley's Wizard Wheezes", KNOCKTURN_ALLEY, "", 29);
//        Shopping shopping13 = new Shopping("Wiseacre's Wizarding Equipment", KNOCKTURN_ALLEY, "", 30);
//        Shopping shopping14 = new Shopping("Scribbulus", DIAGON_ALLEY, "", 31);
//        Shopping shopping15 = new Shopping("Shutterbutton's Photography Studio", DIAGON_ALLEY, "", 32);
//
//        shoppingArrayList = new ArrayList<>();
//        shoppingArrayList.add(shopping1);
//        shoppingArrayList.add(shopping2);
//        shoppingArrayList.add(shopping3);
//        shoppingArrayList.add(shopping4);
//        shoppingArrayList.add(shopping5);
//        shoppingArrayList.add(shopping6);
//        shoppingArrayList.add(shopping7);
//        shoppingArrayList.add(shopping8);
//        shoppingArrayList.add(shopping9);
//        shoppingArrayList.add(shopping10);
//        shoppingArrayList.add(shopping11);
//        shoppingArrayList.add(shopping12);
//        shoppingArrayList.add(shopping13);
//        shoppingArrayList.add(shopping14);
//        shoppingArrayList.add(shopping15);
//    }

//    private void createAndSetAdapters(){
//        foodAdapter = new FoodAdapter(this, foodArrayList);
//        ridesAdapter = new RidesAdapter(this, ridesArrayList);
//        showsAdapter = new ShowsAdapter(this, showsArrayList);
//        shopsAdapter = new ShopsAdapter(this, shoppingArrayList);
//    }

}