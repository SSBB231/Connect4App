package com.example.ssbb231.connect4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.AdapterView;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Observable;

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
                    endGame();
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
                    endGame();
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
                        endGame();
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
                        endGame();
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

    private void endGame(){
            AlertDialog.Builder alert = new AlertDialog.Builder(PlayGame.this);
        boolean won = false; //check if the player won.
        if(game.getBoard().isWin() && !game.getCurrentPlayer().isPlayer1()){
            won = true;
        }
        String title = "Game Over";

        if(isAI) {     //only update the leaderboard if this is an AI game
            final EditText name = new EditText(PlayGame.this);
            alert.setMessage("Please Input Your Username");
            if(won){
                title = "Congratulations, You Won!";
            }
            alert.setTitle(title);
            alert.setView(name);

            alert.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    //Send the username back to the Leaderboard and update their score
                    String inputVal = name.getText().toString();
                    Toast.makeText(PlayGame.this, inputVal, Toast.LENGTH_SHORT).show();
                    Intent lb = new Intent(PlayGame.this, LeaderBoardActivity.class);
                    lb.putExtra(LeaderBoardActivity.DATA, true);
                    lb.putExtra("username", inputVal);
                    if(game.getBoard().isWin() && !game.getCurrentPlayer().isPlayer1()) {  //Player won the game
                        lb.putExtra("score", 1);
                    }
                    else{   //Player lost the game
                        lb.putExtra("score", 0);
                    }
                    startActivity(lb);
                }
            });

            alert.setNegativeButton("Cancel :(", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {  //Go to main menu without updating
                    Toast.makeText(PlayGame.this, "Cancel", Toast.LENGTH_SHORT).show();
                    setResult(PlayGame.RESULT_OK);
                    finish();
                }
            });
        }
        else{   //if this is a versus game thank the player and go to the main menu
            alert.setMessage("Thank you for playing!");
            if(won){
                title = "Player 1 Won";
            }
            else if(game.getBoard().isWin()){ //player 2 won
                title = "Player 2 Won";
            }
            alert.setTitle(title);
            alert.setPositiveButton("Play another!", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    //Send the username back to the Main menu
                    Toast.makeText(PlayGame.this, "Playing one more!", Toast.LENGTH_SHORT).show();
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);  //She aint pretty but she gets the job done
                }

            });

            alert.setNegativeButton("Back to the Main Menu", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    setResult(PlayGame.RESULT_OK);
                    finish();
                }
            });
        }
        alert.show();
    }
}
