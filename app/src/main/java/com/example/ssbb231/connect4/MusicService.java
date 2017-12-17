package com.example.ssbb231.connect4;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;

public class MusicService extends Service {
    private MediaPlayer player;
    public MusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        player = MediaPlayer.create(this, R.raw.braincandy);
        player.setLooping(true);
        player.setVolume(100,100);

    }

    @Override
    public void onDestroy() {
        player.stop();
        player.release();
        super.onDestroy();
    }

    @Override
    public int onStartCommand (Intent intent, int flags,
                               int startid) {
        int song = intent.getIntExtra(MainActivity.SONG, 1);

        switch (song)
        {
            case 0: {
                if(player != null)
                    player.release();
                player = MediaPlayer.create(this, R.raw.braincandy);
                break;
            }
            case 1:
                if(player != null)
                    player.release();
                player = MediaPlayer.create(this, R.raw.elevator);
                break;
            case 2:
                if(player != null)
                    player.release();
                player = MediaPlayer.create(this, R.raw.bassoon);
                break;
            case 3:
                if(player != null)
                    player.release();
                player = MediaPlayer.create(this, R.raw.xylophone);
                break;
            default:
                break;
        }

        player.start();
        return START_NOT_STICKY;
    }


    @Override
    public IBinder onBind(Intent intent) {

        throw new UnsupportedOperationException("Not yet implemented");
    }
}
