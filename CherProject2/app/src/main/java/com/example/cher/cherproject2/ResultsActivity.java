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
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    TextView resultsTitleTextView;
    ListView categoryResultsListView;
    Cursor cursor;
    HPSQLiteHelper mHelper;
    CursorAdapter mCursorAdapter;
    String titleExtra;

    public static final String KEY_SENDING_PRIMARY_ID = "_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        initializeViews();

        mHelper = HPSQLiteHelper.getmInstance(ResultsActivity.this);
        createCursorAdapter();
        getIntentToChangeTitle();
        setCursorAndCursorAdaptersForVariousSearches();

        handleIntent(getIntent());
        createAndSetOnItemClickListener();
    }

    public void initializeViews() {
        resultsTitleTextView = (TextView) findViewById(R.id.resultsTitle_textView_id);
        categoryResultsListView = (ListView) findViewById(R.id.categoryResults_listView_id);
    }

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

    private void getIntentToChangeTitle() {
        Intent intentRetrieveFromMainActivity = getIntent();
        titleExtra = intentRetrieveFromMainActivity.getStringExtra(MainActivity.TYPES_KEY);
        resultsTitleTextView.setText(titleExtra);
    }

    private void setCursorAndCursorAdaptersForVariousSearches() {

        switch (resultsTitleTextView.getText().toString()) { //may be titleExtra
            case "Favorites":
                mCursorAdapter.swapCursor(cursor);
                categoryResultsListView.setAdapter(mCursorAdapter);
                break;
            case "View All":
                cursor = mHelper.getEntireTable();
                mCursorAdapter.swapCursor(cursor);
                categoryResultsListView.setAdapter(mCursorAdapter);
                break;
            case "Food":
                cursor = mHelper.getFoodRows();
                mCursorAdapter.swapCursor(cursor);
                categoryResultsListView.setAdapter(mCursorAdapter);
                break;
            case "Rides":
                cursor = mHelper.getRidesRows();
                mCursorAdapter.swapCursor(cursor);
                categoryResultsListView.setAdapter(mCursorAdapter);
                break;
            case "Shows":
                cursor = mHelper.getShowsRows();
                mCursorAdapter.swapCursor(cursor);
                categoryResultsListView.setAdapter(mCursorAdapter);
                break;
            case "Shopping":
                cursor = mHelper.getShoppingRows();
                mCursorAdapter.swapCursor(cursor);
                categoryResultsListView.setAdapter(mCursorAdapter);
                break;
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

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            if (resultsTitleTextView.getText().toString().equals("Favorites")) {
                Cursor cursor = mHelper.searchFavoritesRows(query);
                mCursorAdapter.changeCursor(cursor);
                mCursorAdapter.notifyDataSetChanged();
            } else if (resultsTitleTextView.getText().toString().equals("View All")) {
                cursor = mHelper.searchEntireTable(query);
                mCursorAdapter.changeCursor(cursor);
                mCursorAdapter.notifyDataSetChanged();
            } else if (resultsTitleTextView.getText().toString().equals("Food")) {
                cursor = mHelper.searchFoodRows(query);
                mCursorAdapter.changeCursor(cursor);
                mCursorAdapter.notifyDataSetChanged();
            } else if (resultsTitleTextView.getText().toString().equals("Rides")) {
                Cursor cursor = mHelper.searchRidesRows(query);
                mCursorAdapter.changeCursor(cursor);
                mCursorAdapter.notifyDataSetChanged();
            } else if (resultsTitleTextView.getText().toString().equals("Shows")) {
                Cursor cursor = mHelper.searchShowsRows(query);
                mCursorAdapter.changeCursor(cursor);
                mCursorAdapter.notifyDataSetChanged();
            } else if (resultsTitleTextView.getText().toString().equals("Shopping")) {
                Cursor cursor = mHelper.searchShoppingRows(query);
                mCursorAdapter.changeCursor(cursor);
                mCursorAdapter.notifyDataSetChanged();
            }

        }
    }

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