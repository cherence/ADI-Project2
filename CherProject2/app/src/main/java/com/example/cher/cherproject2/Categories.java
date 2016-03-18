package com.example.cher.cherproject2;

import android.widget.ImageView;

/**
 * Created by leisforkokomo on 3/13/16.
 */
public class Categories {
    String mName;
    String mGeneralLocation;
    String mInformation;
    int mLogoImageRId;
    int mHeaderImageRId;
    int mMapImageRId;

    public Categories() {
    }

    public Categories(String mName, String mGeneralLocation, String mInformation, int mLogoImageRId, int mHeaderImageRId, int mMapImageRId) {
        this.mName = mName;
        this.mGeneralLocation = mGeneralLocation;
        this.mInformation = mInformation;
        this.mLogoImageRId = mLogoImageRId;
        this.mHeaderImageRId = mHeaderImageRId;
        this.mMapImageRId = mMapImageRId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmGeneralLocation() {
        return mGeneralLocation;
    }

    public void setmGeneralLocation(String mGeneralLocation) {
        this.mGeneralLocation = mGeneralLocation;
    }

    public String getmInformation() {
        return mInformation;
    }

    public void setmInformation(String mInformation) {
        this.mInformation = mInformation;
    }

    public int getmLogoImageRId() {
        return mLogoImageRId;
    }

    public void setmLogoImageRId(int mLogoImageRId) {
        this.mLogoImageRId = mLogoImageRId;
    }

    public int getmHeaderImageRId() {
        return mHeaderImageRId;
    }

    public void setmHeaderImageRId(int mHeaderImageRId) {
        this.mHeaderImageRId = mHeaderImageRId;
    }

    public int getmMapImageRId() {
        return mMapImageRId;
    }

    public void setmMapImageRId(int mMapImageRId) {
        this.mMapImageRId = mMapImageRId;
    }
}

