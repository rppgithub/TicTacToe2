package com.example.android.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int activePlayer = 0;
    int[] boardState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean won = false;

    public void dropIn(View view){

        ImageView counter = (ImageView)view;



        int tapped = Integer.parseInt(counter.getTag().toString());

        //if not tapped
        if ((boardState[tapped] == 2) && !won) {

            counter.setTranslationY(-1000f);

            boardState[tapped] = activePlayer;

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);

                activePlayer = 1;
            } else {

                counter.setImageResource(R.drawable.red);

                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for (int[] winningPosition: winningPositions){
                if ( boardState[winningPosition[0]] == boardState[winningPosition[1]] &&
                boardState[winningPosition[1]] == boardState[winningPosition[2]] &&
                        boardState[winningPosition[0]] != 2) {

                    String winner = "Red";

                    if (boardState[winningPosition[0]] == 0) {
                        winner = "Yellow";

                    }

                    TextView winnerMessage = (TextView)findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " has won!");
                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                    layout.setVisibility(view.VISIBLE);

                    won = true;
                } else {
                    boolean gameisOver = true;

                    for(int counterState : boardState){
                        if (counterState ==2) gameisOver = false;
                    }

                    if ( gameisOver){

                        TextView winnerMessage = (TextView)findViewById(R.id.winnerMessage);
                        winnerMessage.setText("It's a draw");
                        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                        layout.setVisibility(view.VISIBLE);


                    }

                }
            }

        }

    }

    public void playAgain(View view){

        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(view.INVISIBLE);

        activePlayer = 0;

        //init board
        for (int i=0; i<boardState.length;i++){
            boardState[i] = 2;
        }

        //init Grid
        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for (int i=0; i< gridLayout.getChildCount();i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);

        }
        won = false;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
