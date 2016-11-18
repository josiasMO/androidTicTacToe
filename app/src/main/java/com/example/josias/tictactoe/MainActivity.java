package com.example.josias.tictactoe;

import android.net.Uri;
import android.support.v7.app.AlertDialog;
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
    AlertDialog popupEnd;


    Boolean xTurn, timeRunning;
    int i, k;
    int numOfMoves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        innitiateButtons();
        clearButtons();

        timeElapsed = (Chronometer) findViewById(R.id.gameTime);
        timeElapsed.setText("00:00");

        xTurn = true;
        timeRunning = false;
        handleNewGame();

        popupEnd = new AlertDialog.Builder(this).create();
    }

    protected void innitiateButtons() {
        newGame = (Button) findViewById(R.id.buttonNewGame);
        matrix[0] = (Button) findViewById(R.id.button1x1);
        matrix[1] = (Button) findViewById(R.id.button1x2);
        matrix[2] = (Button) findViewById(R.id.button1x3);
        matrix[3] = (Button) findViewById(R.id.button2x1);
        matrix[4] = (Button) findViewById(R.id.button2x2);
        matrix[5] = (Button) findViewById(R.id.button2x3);
        matrix[6] = (Button) findViewById(R.id.button3x1);
        matrix[7] = (Button) findViewById(R.id.button3x2);
        matrix[8] = (Button) findViewById(R.id.button3x3);

    }

    protected void clearButtons() {

        for (i = 0; i < matrix.length; i++) {
            final int j = i;
            matrix[j].setText("");
        }

    }

    protected void handleNewGame() {
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numOfMoves = 0;
                if (timeRunning) {
                    stopCount();
                    timeRunning = false;
                } else {
                    clearButtons();
                    startCount();
                    timeRunning = true;
                }

                handleMatrix();

            }
        });

    }

    protected void startCount() {
        timeElapsed.setBase(SystemClock.elapsedRealtime());
        timeElapsed.start();
    }

    protected void stopCount() {
        timeElapsed.stop();
    }


    protected void handleMatrix() {
        for (i = 0; i < matrix.length; i++) {
            //Button loop = matrix.get(i);
            final int j = i;
            matrix[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ((matrix[j].getText().toString() != "O") && (matrix[j].getText().toString() != "X") && timeRunning) {
                        if (xTurn) {
                            matrix[j].setText("X");
                            xTurn = false;
                            numOfMoves++;
                            if (numOfMoves >= 5)
                                checkEndGame("X");
                        } else if (!xTurn && (matrix[j].getText().toString() != "O") && (matrix[j].getText().toString() != "X")) {
                            matrix[j].setText("O");
                            xTurn = true;
                            numOfMoves++;
                            if (numOfMoves >= 5)
                                checkEndGame("O");
                        }
                        if (numOfMoves == 9)
                            noWinner();

                    }
                }
            });
        }
    }

    protected void checkEndGame(String player) {
        for (k = 0; k < 3; k++) {
            final int j = k;
            if ((matrix[j*3].getText().toString() == matrix[(j*3)+1].getText().toString()) && (matrix[(j*3)+1].getText().toString() == matrix[(j*3)+2].getText().toString()) && (matrix[j*3].getText().toString() == player)){
                popupEnd.setMessage("Player "+player+" is the Winner!!!");
                popupEnd.setCancelable(true);
                popupEnd.show();
                endGame();
            }
            else if((matrix[j].getText().toString() == matrix[3+j].getText().toString()) && (matrix[j].getText().toString() == matrix[6+j].getText().toString()) && (matrix[j].getText().toString() == player)){
                popupEnd.setMessage("Player "+player+" is the Winner!!!");
                popupEnd.setCancelable(true);
                popupEnd.show();
                endGame();
            }
            else if((matrix[0].getText().toString() == matrix[4].getText().toString()) && (matrix[0].getText().toString() == matrix[8].getText().toString()) && (matrix[0].getText().toString() == player)){
                popupEnd.setMessage("Player "+player+" is the Winner!!!");
                popupEnd.setCancelable(true);
                popupEnd.show();
                endGame();

            }
            else if((matrix[2].getText().toString() == matrix[4].getText().toString()) && (matrix[2].getText().toString() == matrix[6].getText().toString()) &&(matrix[2].getText().toString() ==player)) {
                popupEnd.setMessage("Player "+player+" is the Winner!!!");
                popupEnd.setCancelable(true);
                popupEnd.show();
                endGame();
            }
        }


    }

    protected void noWinner() {
        popupEnd.setMessage("No Winner!!!");
        popupEnd.setCancelable(true);
        popupEnd.show();
        endGame();
    }
    protected void endGame() {
        stopCount();
        timeRunning = false;

    }
}