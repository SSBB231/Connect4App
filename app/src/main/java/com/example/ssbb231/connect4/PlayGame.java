package com.example.ssbb231.connect4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.AdapterView;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class PlayGame extends AppCompatActivity {

    public static final String AI = "AI";

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        if(getIntent().getBooleanExtra(AI, false))
        {
            //THIS IS IF THERE IS AI
        }
        else
        {
            game = new VersusGame();
        }

        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                game.putPieceForCurrentPlayer(0);
                game.switchPlayers();
                Toast.makeText(PlayGame.this, game.toString(), Toast.LENGTH_SHORT).show();
                
                if(game.isOver())
                {
                    Toast.makeText(PlayGame.this, "GAME OVER", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
