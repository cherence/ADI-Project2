package com.example.cher.cherproject2;

/**
 * Created by leisforkokomo on 3/23/16.
 */
public class ThemeSongSingleton {
    boolean isPlaying;
    static ThemeSongSingleton mInstance;

    /**
     * Empty constructor.
     */

    public ThemeSongSingleton() {
    }

    /**
     * Getter for boolean #isPlaying
     * @return
     */

    public boolean isPlaying() {
        return isPlaying;
    }

    /**
     * Setter for boolean #isPlaying
     * @param isPlaying
     */

    public void setIsPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    /**
     * This method creates a singleton of the ThemeSongSingleton class.
     * @return
     */

    public static ThemeSongSingleton getmInstance(){
        if(mInstance == null){
            mInstance = new ThemeSongSingleton();
        }
        return mInstance;
    }
}
