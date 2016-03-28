package com.example.cher.cherproject2;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * This is the second screen of the app. On this screen users can browse and search through the
 * WWOHP attractions that match the category they selected. Once an attraction is clicked on, a
 * third (DetailsActivity) screen will display detailed information about that specific attraction
 */

public class ResultsActivity extends AppCompatActivity {

    //region Private Variables
    private TextView resultsTitleTextView;
    private ListView categoryResultsListView;
    private Cursor cursor;
    private HPSQLiteHelper mHelper;
    private CursorAdapter mCursorAdapter;
    private String titleExtra;
    private ThemeSongSingleton themeSongSingleton;
    //endregion Private Variables

    //region Public Variables
    public static final String KEY_SENDING_PRIMARY_ID = "_id";
    //endregion Public Variables



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        themeSongSingleton = ThemeSongSingleton.getmInstance();
        initializeViews();
        mHelper = HPSQLiteHelper.getmInstance(ResultsActivity.this);
        createCursorAdapter();
        getIntentToChangeTitle();
        displayAttractionsInEachCategory();
        createAndSetOnItemClickListener();
    }

    /**
     * This method initializes all the views in this activity.
     */

    public void initializeViews() {
        resultsTitleTextView = (TextView) findViewById(R.id.resultsTitle_textView_id);
        categoryResultsListView = (ListView) findViewById(R.id.categoryResults_listView_id);
    }

    /**
     * This method creates the custom cursor adapter which allows the cursor to be displayed on the screen.
     */

    private void createCursorAdapter() {
        mCursorAdapter = new CursorAdapter(ResultsActivity.this, cursor, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.list_item_results_layout, parent, false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                ImageView resultsLogoImage = (ImageView) view.findViewById(R.id.resultsLogoImage_imageView_id);
                TextView resultsName = (TextView) view.findViewById(R.id.resultsName_textView_id);
                TextView resultsType = (TextView) view.findViewById(R.id.resultsType_textView_id);
                TextView resultsLocation = (TextView) view.findViewById(R.id.resultsLocation_textView_id);

                resultsLogoImage.setBackgroundResource(cursor.getInt(cursor.getColumnIndex(HPSQLiteHelper.COL_LOGO_IMAGE)));
                resultsName.setText(cursor.getString(cursor.getColumnIndex(HPSQLiteHelper.COL_NAME)));
                resultsType.setText(cursor.getString(cursor.getColumnIndex(HPSQLiteHelper.COL_TYPE)));
                resultsLocation.setText(cursor.getString(cursor.getColumnIndex(HPSQLiteHelper.COL_GENERAL_LOCATION)));
            }
        };
    }

    /**
     * This method changes the page's title to the name of the button that was pressed in the previous
     * (MainActivity) screen.
     */

    private void getIntentToChangeTitle() {
        Intent intentRetrieveFromMainActivity = getIntent();
        titleExtra = intentRetrieveFromMainActivity.getStringExtra(MainActivity.TYPES_KEY);
        resultsTitleTextView.setText(titleExtra);
    }

    /**
     * This method sets the cursors and cursor adapters necessary to display the unique list of
     * attractions for each category (button) displayed on the first screen.
     */

    private void displayAttractionsInEachCategory() {
        switch (resultsTitleTextView.getText().toString()) {
            case "Favorites":
                cursor = mHelper.getFavoriteRows();
                updateCursorAdapter();
                break;
            case "View All":
                cursor = mHelper.getEntireTable();
                updateCursorAdapter();
                break;
            case "Food":
                cursor = mHelper.getFoodRows();
                updateCursorAdapter();
                break;
            case "Rides":
                cursor = mHelper.getRidesRows();
                updateCursorAdapter();
                break;
            case "Shows":
                cursor = mHelper.getShowsRows();
                updateCursorAdapter();
                break;
            case "Shopping":
                cursor = mHelper.getShoppingRows();
                updateCursorAdapter();
                break;
        }
    }


    /**
     * This method swaps the new cursor for the old one and sets the cursor adapter to the proper list view.
     */

    private void updateCursorAdapter(){
        mCursorAdapter.swapCursor(cursor);
        categoryResultsListView.setAdapter(mCursorAdapter);
    }

    /**
     * This method inflates the custom menu in the app bar.
     * @param menu
     * @return
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    /**
     * This method sets up what will happen if and when a user clicks on a menu item (icon). If the
     * music button is clicked and music is playing turn it off. If the music button is clicked and
     * the music isn't playing, turn it on.
     * @param item
     * @returnf
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                performSearchAndDisplayResults(getIntent());
                return true;
            case R.id.music_hasSearch_menuButton:
                if(themeSongSingleton.isPlaying()){
                    stopService(new Intent(this, MusicService.class));
                } else {
                    startService(new Intent(this, MusicService.class));
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This method override the new intent needed to peform and display search results.
     * @param intent
     */

    @Override
    protected void onNewIntent(Intent intent) {
        performSearchAndDisplayResults(intent);
    }

    /**
     * This method sets cursors and cursor adapters for each of the category-specific searches
     * offered in this app. It performs searches based on user input and displays the results of the
     * search in the list view.
     *
     * @param intent
     */

    private void performSearchAndDisplayResults(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            if (resultsTitleTextView.getText().toString().equals("Favorites")) {
                cursor = mHelper.searchFavoritesRows(query);
                updateAndNotifyCursorAdapter();
            } else if (resultsTitleTextView.getText().toString().equals("View All")) {
                cursor = mHelper.searchEntireTable(query);
                updateAndNotifyCursorAdapter();
            } else if (resultsTitleTextView.getText().toString().equals("Food")) {
                cursor = mHelper.searchFoodRows(query);
                updateAndNotifyCursorAdapter();
            } else if (resultsTitleTextView.getText().toString().equals("Rides")) {
                cursor = mHelper.searchRidesRows(query);
                updateAndNotifyCursorAdapter();
            } else if (resultsTitleTextView.getText().toString().equals("Shows")) {
                cursor = mHelper.searchShowsRows(query);
                updateAndNotifyCursorAdapter();
            } else if (resultsTitleTextView.getText().toString().equals("Shopping")) {
                cursor = mHelper.searchShoppingRows(query);
                updateAndNotifyCursorAdapter();
            }
        }
    }

    /**
     * This method swaps the new cursor for the old one and notifies the cursor adapter of the change.
     */
    private void updateAndNotifyCursorAdapter(){
        mCursorAdapter.swapCursor(cursor);
        mCursorAdapter.notifyDataSetChanged();
    }

    /**
     * Sets on item click listener. Will launch detailed page for the attraction clicked.
     */

    private void createAndSetOnItemClickListener() {
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
}