package com.example.ssbb231.connect4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.AdapterView;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class PlayGame extends AppCompatActivity {

    public static final String AI = "AI";

    private Game game;
    private boolean isAI;

    private OnItemClickListener normalListener, aiListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        final ImageAdapter imageAdapter = new ImageAdapter(this);
        gridview.setAdapter(imageAdapter);
        //gridview.setAdapter(new ImageAdapter(this));

        if(getIntent().getBooleanExtra(AI, false))
        {
            game = new AIGame("P1");
            isAI = true;
        }
        else
        {
            game = new VersusGame();
            isAI = false;
        }

        normalListener = new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                if(game.isOver())
                {
                    Toast.makeText(PlayGame.this, "GAME OVER", Toast.LENGTH_SHORT).show();
                    return;
                }

//                Toast.makeText(PlayGame.this, Integer.toString(position%7), Toast.LENGTH_SHORT).show();

                //This will put piece for the current player and switchPlayers if placement was successful
                int madeMove = game.putPieceForCurrentPlayer(position%7);

                if(madeMove != -1) {
//                    Toast.makeText(PlayGame.this, game.getBoardString(), Toast.LENGTH_SHORT).show();
                    ImageView imageView = (ImageView) v;
                    int imgPos = (madeMove * 7)+(position%7);
                    if(game.getCurrentPlayer().getPieceType() == PieceType.RED){
                        //imageView.setImageResource(R.drawable.red);
                        imageAdapter.addPiece(imgPos,"RED");
                    }
                    else{
                        //imageView.setImageResource(R.drawable.yellow);
                        imageAdapter.addPiece(imgPos,"YELLOW");
                    }

                    game.switchPlayers();
                }
                else {
                    Toast.makeText(PlayGame.this, "INVALID MOVE", Toast.LENGTH_SHORT).show();
                }
                if(game.isOver())
                {
                    Toast.makeText(PlayGame.this, "GAME OVER", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        };

        aiListener = new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                for (int i = 0; i < 2; i++) {
                    if(game.isOver())
                    {
                        Toast.makeText(PlayGame.this, "GAME OVER", Toast.LENGTH_SHORT).show();
                        return;
                    }

//                Toast.makeText(PlayGame.this, Integer.toString(position%7), Toast.LENGTH_SHORT).show();

                    //This will put piece for the current player and switchPlayers if placement was successful
                    int madeMove = game.putPieceForCurrentPlayer(position%7);

                    if(madeMove != -1) {
//                    Toast.makeText(PlayGame.this, game.getBoardString(), Toast.LENGTH_SHORT).show();
                        ImageView imageView = (ImageView) v;
                        int imgPos;

                        if (game.getCurrentPlayer().getPieceType() == PieceType.RED)
                        {
                            imgPos = (madeMove * 7)+(position%7);
                        }
                        else
                        {
                            imgPos = (madeMove * 7)+(game.getCurrentPlayer().getMove());
                        }

                        if(game.getCurrentPlayer().getPieceType() == PieceType.RED){
                            //imageView.setImageResource(R.drawable.red);
                            imageAdapter.addPiece(imgPos,"RED");
                        }
                        else{
                            //imageView.setImageResource(R.drawable.yellow);
                            imageAdapter.addPiece(imgPos,"YELLOW");
                        }

                        game.switchPlayers();
                    }
                    else {
                        Toast.makeText(PlayGame.this, "INVALID MOVE", Toast.LENGTH_SHORT).show();
                    }
                    if(game.isOver())
                    {
                        Toast.makeText(PlayGame.this, "GAME OVER", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

            }
        };

        if (isAI)
        {
            gridview.setOnItemClickListener(aiListener);
        }
        else
        {
            gridview.setOnItemClickListener(normalListener);
        }
    }
}
