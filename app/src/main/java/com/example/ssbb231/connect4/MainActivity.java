package com.example.ssbb231.connect4;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Intent musicSvc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        musicSvc = null;
    }

    public void toggleMusic(View view)
    {
        if(musicSvc == null){
            musicSvc = new Intent(MainActivity.this, MusicService.class);
            startService(musicSvc);
        }
        else{
            stopService(musicSvc);
            musicSvc = null;
        }
    }


    public void startLeaderBoard(View view)
    {
        Intent leader = new Intent(this, LeaderBoardActivity.class);
        leader.putExtra(LeaderBoardActivity.DATA, false);
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
