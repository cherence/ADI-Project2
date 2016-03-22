package com.example.cher.cherproject2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by leisforkokomo on 3/21/16.
 */
public class MusicService extends Service {

    MediaPlayer hedwigsThemeMusicPlayer;
    public static boolean isPlaying = true;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        hedwigsThemeMusicPlayer = MediaPlayer.create(this, R.raw.hedwigs_theme_music);
        hedwigsThemeMusicPlayer.setLooping(true);
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        hedwigsThemeMusicPlayer.stop();
        isPlaying = false;
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        hedwigsThemeMusicPlayer.start();
        isPlaying = true;
        return super.onStartCommand(intent, flags, startId);
    }
}