package com.example.ssbb231.connect4;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service {
    private MediaPlayer player;
    public MusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this,
                R.raw.braincandy);
        player.setLooping(true);
        player.setVolume(100,100);

    }

    @Override
    public void onDestroy() {
        player.stop();
        super.onDestroy();
    }

    @Override
    public int onStartCommand (Intent intent, int flags,
                               int startid) {
        player.start();
        return START_NOT_STICKY;
    }


    @Override
    public IBinder onBind(Intent intent) {

        throw new UnsupportedOperationException("Not yet implemented");
    }
}
