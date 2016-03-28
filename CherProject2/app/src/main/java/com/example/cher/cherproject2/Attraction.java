package com.example.cher.cherproject2;

import android.widget.ImageView;

/**
 * Created by leisforkokomo on 3/13/16.
 */
public class Attraction {
    private String mType;
    private String mName;
    private String mGeneralLocation;
    private String favoriteStatus;
    private int mInformation;
    private int mLogoImageRId;
    private int mHeaderImageRId;
    private int mMapImageRId;

    public Attraction() {
    }

    public Attraction(String mType, String mName, String mGeneralLocation, String favoriteStatus, int mInformation, int mLogoImageRId, int mHeaderImageRId, int mMapImageRId) {
        this.mType = mType;
        this.mName = mName;
        this.mGeneralLocation = mGeneralLocation;
        this.favoriteStatus = favoriteStatus;
        this.mInformation = mInformation;
        this.mLogoImageRId = mLogoImageRId;
        this.mHeaderImageRId = mHeaderImageRId;
        this.mMapImageRId = mMapImageRId;
    }

    public String getmType() {
        return mType;
    }

    public String getmName() {
        return mName;
    }

    public String getmGeneralLocation() {
        return mGeneralLocation;
    }

    public String getFavoriteStatus() {
        return favoriteStatus;
    }

    public void setFavoriteStatus(String favoriteStatus) {
        this.favoriteStatus = favoriteStatus;
    }

    public int getmInformation() {
        return mInformation;
    }

    public int getmHeaderImageRId() {
        return mHeaderImageRId;
    }

    public int getmMapImageRId() {
        return mMapImageRId;
    }
}


