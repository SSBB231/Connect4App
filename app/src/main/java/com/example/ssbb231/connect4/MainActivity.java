package com.example.ssbb231.connect4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toggleMusic(View view)
    {
        /*
        Logic to start or stop music in here
         */
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
