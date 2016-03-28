package com.example.cher.cherproject2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * This class creates a services that allows a Harry Potter theme song to be played in all activities.
 * It also allows the user start or stop playing the song from any activity.
 * Created by leisforkokomo on 3/21/16.
 */
public class MusicService extends Service {

    MediaPlayer hedwigsThemeMusicPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * This method creates a media player that will play the desired Harry Potter theme on a loop.
     */

    @Override
    public void onCreate() {
        super.onCreate();
        hedwigsThemeMusicPlayer = MediaPlayer.create(this, R.raw.hedwigs_theme_music);
        hedwigsThemeMusicPlayer.setLooping(true);
    }

    /**
     * This method will stop the theme song from playing.
     */

    @Override
    public void onDestroy() {
        hedwigsThemeMusicPlayer.stop();
        ThemeSongSingleton.getmInstance().setIsPlaying(false);
        super.onDestroy();
    }

    /**
     * This method will start playing the theme song.
     * @param intent
     * @param flags
     * @param startId
     * @return
     */

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        hedwigsThemeMusicPlayer.start();
        ThemeSongSingleton.getmInstance().setIsPlaying(true);
        return super.onStartCommand(intent, flags, startId);
    }
}
