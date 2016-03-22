package com.example.cher.cherproject2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class DetailsActivity extends AppCompatActivity {

    ImageView detailsHeaderImageView;
    TextView detailsNameTextView;
    TextView detailsTypeTextView;
    Button detailsFavoriteStatusButton;
    TextView detailsLocationTextView;
    TextView detailsInfoTextView;
    ImageView detailsMapImageView;
    HPSQLiteHelper mHelper;
    int idOfItemPressed;
    Categories itemClicked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initializeViews();
        mHelper = HPSQLiteHelper.getmInstance(DetailsActivity.this);
        getAndSetIntent();
        setFavoriteButtonImage();
        createAndSetFavoriteButton();

    }

    private void initializeViews(){
        detailsHeaderImageView = (ImageView) findViewById(R.id.detailsHeaderImage_imageView_id);
        detailsNameTextView = (TextView) findViewById(R.id.detailsName_textView_id);
        detailsTypeTextView = (TextView) findViewById(R.id.detailsType_textView_id);
        detailsFavoriteStatusButton = (Button) findViewById(R.id.detailsFavoriteStatus_button_id);
        detailsLocationTextView = (TextView) findViewById(R.id.detailsLocation_textView_id);
        detailsInfoTextView = (TextView) findViewById(R.id.detailsInfo_textView_id);
        detailsMapImageView = (ImageView) findViewById(R.id.detailsMap_imageView_id);
    }

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

    private void setFavoriteButtonImage(){
        if(itemClicked.getFavoriteStatus().equals(MainActivity.NOT_FAVORITE)){
            detailsFavoriteStatusButton.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
        } else{
            detailsFavoriteStatusButton.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
        }
    }

    private void createAndSetFavoriteButton(){

        detailsFavoriteStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (itemClicked.getFavoriteStatus()){
                    case MainActivity.NOT_FAVORITE:
                        detailsFavoriteStatusButton.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
                        mHelper.updateFavoriteStatus(idOfItemPressed, MainActivity.FAVORITE);
                        itemClicked.setFavoriteStatus(MainActivity.FAVORITE);
                        break;
                    case MainActivity.FAVORITE:
                        detailsFavoriteStatusButton.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
                        mHelper.updateFavoriteStatus(idOfItemPressed, MainActivity.NOT_FAVORITE);
                        itemClicked.setFavoriteStatus(MainActivity.NOT_FAVORITE);
                        break;
                }
            }
        });

    }
}