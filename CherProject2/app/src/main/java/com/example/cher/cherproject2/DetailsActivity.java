package com.example.cher.cherproject2;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class DetailsActivity extends AppCompatActivity {


    private ImageView detailsHeaderImageView;
    private TextView detailsNameTextView;
    private TextView detailsTypeTextView;
    private Button detailsFavoriteStatusButton;
    private TextView detailsLocationTextView;
    private TextView detailsInfoTextView;
    private ImageView detailsMapImageView;
    private TextView reviewTitleTextView;
    private ListView reviewListView;
    private EditText writeReviewEditText;
    private Button addReviewButton;
    private HPSQLiteHelper mHelper;
    private int idOfItemPressed;
    private Attraction itemClicked;
    private ThemeSongSingleton themeSongSingleton;
    private Cursor reviewCursor;
    private CursorAdapter simpleCursorAdapter;
    private String userInputReviews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        themeSongSingleton = ThemeSongSingleton.getmInstance();
        initializeViews();
        mHelper = HPSQLiteHelper.getmInstance(DetailsActivity.this);
        getAndSetIntent();
        setFavoriteButtonImage();
        createAndSetFavoriteButton();
        displayReviews(idOfItemPressed);
        insertRowsToReviewTable();
    }

    /**
     *This method inflates the custom menu.
     * @param menu
     * @return
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_no_search, menu);
        return true;
    }

    /**
     * This method sets up what will happen if and when a user clicks on a menu item (icon).
     * @param item
     * @return
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.music_noSearch_menuButton:
                if(themeSongSingleton.isPlaying()){//if they click it and music is playing, turn it off
                    stopService(new Intent(this, MusicService.class)); //startService();
                } else {//if they click it and music isn't playing turn it on.
                    startService(new Intent(this, MusicService.class)); //stopService();
                }
                return true;
            default: //if we get here, the user's action isn't recognized so invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     *This method initializes all the views in this activity.
     */

    private void initializeViews(){
        detailsHeaderImageView = (ImageView) findViewById(R.id.detailsHeaderImage_imageView_id);
        detailsNameTextView = (TextView) findViewById(R.id.detailsName_textView_id);
        detailsTypeTextView = (TextView) findViewById(R.id.detailsType_textView_id);
        detailsFavoriteStatusButton = (Button) findViewById(R.id.detailsFavoriteStatus_button_id);
        detailsLocationTextView = (TextView) findViewById(R.id.detailsLocation_textView_id);
        detailsInfoTextView = (TextView) findViewById(R.id.detailsInfo_textView_id);
        detailsMapImageView = (ImageView) findViewById(R.id.detailsMap_imageView_id);
        reviewTitleTextView = (TextView) findViewById(R.id.reviewTitle_textView_id);
        reviewListView = (ListView) findViewById(R.id.review_ListView_id);
        writeReviewEditText = (EditText) findViewById(R.id.review_editText_id);
        addReviewButton = (Button) findViewById(R.id.review_addReview_button_id);
    }

    /**
     *This method gets the intent sent from the #ResultsActivity (int _id) and creates and instance of the Attraction object for the row in the HP_Table with that _id.
     *Then this method utilizes the getters of the Attraction object to set the views of the activity.
     */

    private void getAndSetIntent(){
        idOfItemPressed = getIntent().getIntExtra(ResultsActivity.KEY_SENDING_PRIMARY_ID, -1);

        if(idOfItemPressed >= 0){
            itemClicked = mHelper.createObject(idOfItemPressed);
            detailsNameTextView.setText(itemClicked.getmName());
            detailsTypeTextView.setText(itemClicked.getmType());
            detailsLocationTextView.setText(itemClicked.getmGeneralLocation());
            detailsInfoTextView.setText(itemClicked.getmInformation());
            detailsHeaderImageView.setBackgroundResource(itemClicked.getmHeaderImageRId());
            detailsMapImageView.setBackgroundResource(itemClicked.getmMapImageRId());
        }
    }

    /**
     * This method sets the image of the favorite button in this activity.
     * If the object's favoriteStatus is equal to #MainActivity.NOT_FAVORITE, an empty gold-colored heart will be displayed when the screen loads.
     * If the object's favoriteStatus is equal to #MainActivity.FAVORITE, a filled-in scarlet-colored heart will be displayed when the screen loads.
     */

    private void setFavoriteButtonImage(){
        if(itemClicked.getFavoriteStatus().equals(MainActivity.NOT_FAVORITE)){
            detailsFavoriteStatusButton.setBackgroundResource(R.drawable.empty_heart_icon);
        } else{
            detailsFavoriteStatusButton.setBackgroundResource(R.drawable.filled_heart_icon);
        }
    }

    /**
     * This method sets the click listener for the favorite button.
     * Whenever the favorite button is clicked, the featured attraction's favoriteStatus will be updated in the object and the HP_TABLE.
     * If the featured attraction's favoriteStatus is #MainActivity.NOT_FAVORITE when clicked, the favoriteStatus will change to #MainActivity.FAVORITE.
     * If the featured attraction's favoriteStatus is #MainActivity.FAVORITE when clicked, the favoriteStatus will change to #MainActivity.NOT_FAVORITE.
     */

    private void createAndSetFavoriteButton(){

        detailsFavoriteStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (itemClicked.getFavoriteStatus()) {
                    case MainActivity.NOT_FAVORITE:
                        detailsFavoriteStatusButton.setBackgroundResource(R.drawable.filled_heart_icon);
                        mHelper.updateFavoriteStatus(idOfItemPressed, MainActivity.FAVORITE);
                        itemClicked.setFavoriteStatus(MainActivity.FAVORITE);
                        break;
                    case MainActivity.FAVORITE:
                        detailsFavoriteStatusButton.setBackgroundResource(R.drawable.empty_heart_icon);
                        mHelper.updateFavoriteStatus(idOfItemPressed, MainActivity.NOT_FAVORITE);
                        itemClicked.setFavoriteStatus(MainActivity.NOT_FAVORITE);
                        break;
                }
            }
        });
    }

    /**
     * This method creates the reviewCursor and the simple CursorAdapter that will display the results of the reviewCursor.
     * It also sets the CursorAdapter.
     * @param idOfItemPressed
     */

    public void displayReviews(int idOfItemPressed){
        reviewCursor = mHelper.getReviewRows(idOfItemPressed);
        reviewCursor.moveToFirst();
        String[] columns = new String[]{HPSQLiteHelper.COL_REVIEW};
        int[] viewNames = new int[]{android.R.id.text1};
        simpleCursorAdapter = new SimpleCursorAdapter(DetailsActivity.this,android.R.layout.simple_list_item_1, reviewCursor, columns, viewNames,0);
        reviewListView.setAdapter(simpleCursorAdapter);
    }


    /**
     * This method will insert a review to the REVIEWS_TABLE whenever a user clicks on the add review button.
     * It will also display the review in the ListView as soon as it is submitted by the user.
     */

    public void insertRowsToReviewTable(){
        addReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInputReviews = writeReviewEditText.getText().toString();
                if (userInputReviews.isEmpty()){
                    Toast.makeText(DetailsActivity.this, "Please write a review.", Toast.LENGTH_SHORT).show();
                } else {
                    mHelper.insertReviews(idOfItemPressed, userInputReviews);
                    reviewCursor.moveToFirst();
                    reviewCursor = mHelper.getReviewRows(idOfItemPressed);
                    simpleCursorAdapter.swapCursor(reviewCursor);
                    simpleCursorAdapter.notifyDataSetChanged();
                    writeReviewEditText.getText().clear();
                }
            }
        });
    }
}