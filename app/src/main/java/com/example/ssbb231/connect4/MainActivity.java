package com.example.ssbb231.connect4;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Intent musicSvc;
    private int song;
    public static final String SONG = "SONG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        musicSvc = null;
        song = 0;
    }

    public void toggleMusic(View view)
    {
        if(musicSvc == null){
            musicSvc = new Intent(MainActivity.this, MusicService.class);
            musicSvc.putExtra(SONG, song);
            startService(musicSvc);
        }
        else{
            stopService(musicSvc);
            song++;
            song %= 4;
            musicSvc = null;
        }
    }


    public void startLeaderBoard(View view)
    {
        Intent leader = new Intent(this, LeaderBoardActivity.class);
        startActivity(leader);
    }

    public void startGame(View view)
    {
        Intent game = new Intent(this, PlayGame.class);

        if(view.getId() == R.id.bOnePlayer)
        {
            game.putExtra(PlayGame.AI, true);
        }
        else
        {
            game.putExtra(PlayGame.AI, false);
        }

        startActivityForResult(game, 1);
    }

    public void displayName(View view)
    {
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
    }
}
