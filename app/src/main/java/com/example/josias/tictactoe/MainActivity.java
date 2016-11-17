package com.example.josias.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.os.SystemClock;

public class MainActivity extends AppCompatActivity {

    Button newGame;
    Button matrix[] = new Button[9];
    Chronometer timeElapsed;

    Boolean xTurn, timeRunning;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        innitiateButtons();
        clearButtons();

        timeElapsed = (Chronometer)findViewById(R.id.gameTime);
        timeElapsed.setText("00:00");

        xTurn = true;
        timeRunning = false;
        handleNewGame();

    }
    protected void innitiateButtons(){
        newGame = (Button)findViewById(R.id.buttonNewGame);
        matrix[0] = (Button)findViewById(R.id.button1x1);
        matrix[1] = (Button)findViewById(R.id.button1x2);
        matrix[2] = (Button)findViewById(R.id.button1x3);
        matrix[3] = (Button)findViewById(R.id.button2x1);
        matrix[4] = (Button)findViewById(R.id.button2x2);
        matrix[5] = (Button)findViewById(R.id.button2x3);
        matrix[6] = (Button)findViewById(R.id.button3x1);
        matrix[7] = (Button)findViewById(R.id.button3x2);
        matrix[8] = (Button)findViewById(R.id.button3x3);

    }
    protected void clearButtons(){

        for(i = 0; i < matrix.length;i++){
            final int j = i;
            matrix[j].setText("");
        }

    }
    protected void handleNewGame(){
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(timeRunning) {
                    stopCount();
                    timeRunning = false;
                }
                else {
                    clearButtons();
                    startCount();
                    timeRunning = true;
                }

            handleMatrix();

            }
        });

    }
    protected void startCount(){
        timeElapsed.setBase(SystemClock.elapsedRealtime());
        timeElapsed.start();
    }
    protected void stopCount(){
        timeElapsed.stop();
    }


    protected void handleMatrix(){
        for(i = 0; i < matrix.length; i++) {
            //Button loop = matrix.get(i);
            final int j = i;
            matrix[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ((matrix[j].getText().toString() != "O") && (matrix[j].getText().toString() != "X") && timeRunning) {
                        if (xTurn) {
                            matrix[j].setText("X");
                            xTurn = false;
                        } else if (!xTurn && (matrix[j].getText().toString() != "O") && (matrix[j].getText().toString() != "X")) {
                            matrix[j].setText("O");
                            xTurn = true;
                        }
                    }
                }
            });
        }
    }


}