package com.example.cher.cherproject2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leisforkokomo on 3/13/16.
 */
public class RidesAdapter extends ArrayAdapter<Rides> {
    List<Rides> data = new ArrayList<>();
    Rides rides;
    View rowItem;
    ImageView imageLogoResults;
    TextView nameResults;
    TextView generalLocationResults;

    public RidesAdapter(Context context, ArrayList<Rides> objects) {
        super(context, -1, objects);
        this.data = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_results_layout, parent, false);
        initializeViews();
        rides = data.get(position);
        setViews();
        setImagesDependingOnImageNumber();
        return rowItem;
    }

    private void initializeViews(){
        imageLogoResults = (ImageView) rowItem.findViewById(R.id.resultsLogoImage_imageView_id);
        nameResults = (TextView) rowItem.findViewById(R.id.resultsName_textView_id);
        generalLocationResults = (TextView) rowItem.findViewById(R.id.resultsLocation_textView_id);
    }

    private void setViews(){
        nameResults.setText(rides.mName);
        generalLocationResults.setText(rides.mGeneralLocation);
    }

    private void setImagesDependingOnImageNumber(){
        if(rides.mLogoImageNumber==6){
            imageLogoResults.setImageResource(R.drawable.dragonchallenge);
        }

    }
}
