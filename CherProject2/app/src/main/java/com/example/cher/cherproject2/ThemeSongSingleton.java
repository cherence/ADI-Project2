package com.example.cher.cherproject2;

/**
 * Created by leisforkokomo on 3/23/16.
 */
public class ThemeSongSingleton {
    boolean isPlaying;
    static ThemeSongSingleton mInstance;

    public ThemeSongSingleton() {
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setIsPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    public static ThemeSongSingleton getmInstance(){
        if(mInstance == null){
            mInstance = new ThemeSongSingleton();
        }
        return mInstance;
    }
}
