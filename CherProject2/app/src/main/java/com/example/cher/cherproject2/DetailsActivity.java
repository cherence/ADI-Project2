package com.example.cher.cherproject2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    ImageView detailsHeaderImageView;
    TextView detailsNameTextView;
    TextView detailsTypeTextView;
    TextView detailsLocationTextView;
    TextView detailsInfoTextView;
    ImageView detailsMapImageView;
    HPSQLiteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initializeViews();
        helper = HPSQLiteHelper.getmInstance(DetailsActivity.this);
        getAndSetIntent();

    }

    private void initializeViews(){
        detailsHeaderImageView = (ImageView) findViewById(R.id.detailsHeaderImage_imageView_id);
        detailsNameTextView = (TextView) findViewById(R.id.detailsName_textView_id);
        detailsTypeTextView = (TextView) findViewById(R.id.detailsType_textView_id);
        detailsLocationTextView = (TextView) findViewById(R.id.detailsLocation_textView_id);
        detailsInfoTextView = (TextView) findViewById(R.id.detailsInfo_textView_id);
        detailsMapImageView = (ImageView) findViewById(R.id.detailsMap_imageView_id);
    }

    private void getAndSetIntent(){
        int idOfItemPressed = getIntent().getIntExtra(ResultsActivity.KEY_SENDING_PRIMARY_ID, -1);

        if(idOfItemPressed >= 0){
            Food foodItemClicked = helper.createFoodObject(idOfItemPressed);
            detailsNameTextView.setText(foodItemClicked.getmName());
            detailsTypeTextView.setText(foodItemClicked.getmType());
            detailsLocationTextView.setText(foodItemClicked.getmGeneralLocation());
            detailsInfoTextView.setText(foodItemClicked.getmInformation());
            detailsHeaderImageView.setImageResource(foodItemClicked.getmHeaderImageRId());
            detailsMapImageView.setImageResource(foodItemClicked.getmMapImageRId());
        }
    }
}
