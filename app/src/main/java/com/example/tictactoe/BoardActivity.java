package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BoardActivity extends AppCompatActivity {
    private static final String TAG = "BoardActivity";
    ImageView oneImg, twoImg, threeImg, fourImg, fiveImg, sixImg, sevenImg, eightImg, nineImg;
    Button mainBtn, optionsBtn;
    TextView scoreBoardTV, roundsTV;
    String numPlayers, plyOnePiece, plyTwoPiece, winningPiece, sbMsgPrefix, sbMsgSuffix;
    int whoGoesFirst, N=3, M =8, numRounds, plyOneScore=0, plyTwoScore=0, currRound=1, saveLastImgIdx;
    int xResId, oResId, xSelectResId, oSelectResId;  // all four var save res id of the .jpg image
    boolean isPlyOneTurn, didImgChange = false;
    String board[][] = {
            {"_", "_", "_"},
            {"_", "_", "_"},
            {"_", "_", "_"},
    };
    int plyOneCounter[] ={0,0,0,0,0,0,0,0}, plyTwoCounter [] = {0,0,0,0,0,0,0,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        initializeBtnImgTV();
       if(savedInstanceState==null) {
            loadGameData(); //sets numPlayers, numRounds, plyOnePiece, plyTwoPiece
            setWhoGoesFirst();
       }
        oneImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLastImgIdx  = 0;
                     onNextTurn();
            }
        });

        twoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLastImgIdx  = 1;
                    onNextTurn();
            }
        });
        threeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLastImgIdx  = 2;
                onNextTurn();
            }
        });
        fourImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLastImgIdx  = 3;
                onNextTurn();
            }
        });
        fiveImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLastImgIdx  = 4;
                onNextTurn();
            }
        });
        sixImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLastImgIdx  = 5;
                onNextTurn();
            }
        });
        sevenImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLastImgIdx  = 6;
                onNextTurn();
            }
        });
        eightImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLastImgIdx  = 7;
                onNextTurn();
            }
        });
        nineImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLastImgIdx  = 8;
                onNextTurn();
            }
        });

        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainBtnDialog mainBtnDialog = new mainBtnDialog();
                mainBtnDialog.show(getSupportFragmentManager(), "examole");
                //startActivity(new Intent(BoardActivity.this, MainActivity.class));
            }
        });
        optionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoardActivity.this, SettingsActivity.class);
                intent.putExtra("previousActivity", "BoardActivity");
                startActivity(intent);
                finish();
            }
        });

    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) { // save data when move from this activity to settings activity
        super.onSaveInstanceState(outState);
        outState.putString("numPlayers" , numPlayers);
        outState.putString("plyOnePiece", plyOnePiece);
        outState.putString("plyTwoPiece", plyTwoPiece);
        outState.putString("winningPiece", winningPiece);
        outState.putString("sbMsgPrefix", sbMsgPrefix);
        outState.putString("sbMsgSuffix", sbMsgSuffix);
        outState.putInt("whoGoesFirst", whoGoesFirst);
        outState.putInt("N", N); //change this to static or constant unchangeable val
        outState.putInt("M", M);
        outState.putInt("numRounds", numRounds);
        outState.putInt("plyOneScore", plyOneScore);
        outState.putInt("plyTwoScore", plyTwoScore);
        outState.putInt("currRound", currRound);
        outState.putInt("saveLastImgIdx", saveLastImgIdx);
        outState.putInt("xResId", xResId);
        outState.putInt("oResId", oResId);
        outState.putInt("xSelectResId", xSelectResId);
        outState.putInt("oSelectResId", oSelectResId);
        outState.putBoolean("isPlyOneTurn", isPlyOneTurn);
        outState.putBoolean("didImgChange", didImgChange);
        outState.putStringArray("boardRowOne", board[0]);
        outState.putStringArray("boardRowTwo", board[1]);
        outState.putStringArray("boardRowThree", board[2]);
        outState.putIntArray("plyOneCounter", plyOneCounter);
        outState.putIntArray("plyTwoCounter", plyTwoCounter);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) { // restore data when come back from settings activity
        super.onRestoreInstanceState(savedInstanceState);
        numPlayers = savedInstanceState.getString("numPlayers");
        plyOnePiece = savedInstanceState.getString("plyOnePiece");
        plyTwoPiece = savedInstanceState.getString("plyTwoPiece");
        winningPiece = savedInstanceState.getString("winningPiece");
        sbMsgPrefix = savedInstanceState.getString("sbMsgPrefix");
        sbMsgSuffix = savedInstanceState.getString("sbMsgSuffix");
        whoGoesFirst = savedInstanceState.getInt("plyTwoPiece");
        N = savedInstanceState.getInt("N");
        M = savedInstanceState.getInt("M");
        numRounds = savedInstanceState.getInt("numRounds");
        plyOneScore = savedInstanceState.getInt("plyOneScore");
        plyTwoScore = savedInstanceState.getInt("plyTwoScore");
        currRound = savedInstanceState.getInt("currRound");
        saveLastImgIdx = savedInstanceState.getInt("saveLastImgIdx");
        xResId = savedInstanceState.getInt("xResId");
        oResId = savedInstanceState.getInt("oResId");
        xSelectResId = savedInstanceState.getInt("xSelectResId");
        oSelectResId = savedInstanceState.getInt("oSelectResId");
        isPlyOneTurn = savedInstanceState.getBoolean("isPlyOneTurn");
        didImgChange = savedInstanceState.getBoolean("didImgChange");
        board[0] = savedInstanceState.getStringArray("boardRowOne");
        board[1] = savedInstanceState.getStringArray("boardRowTwo");
        board[2] = savedInstanceState.getStringArray("boardRowThree");
        plyOneCounter = savedInstanceState.getIntArray("plyOneCounter");
        plyTwoCounter = savedInstanceState.getIntArray("plyTwoCounter");

        String sbMsg = sbMsgPrefix + plyOneScore + " - " + plyTwoScore + sbMsgSuffix;
        scoreBoardTV.setText(sbMsg);
        String roundMsg = "Round " + currRound;
        roundsTV.setText(roundMsg);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String msgFour = "onStart()";
        Log.i(TAG, msgFour);
    }

    @Override
    protected void onStop() {
        super.onStop();
        String msgFive = "onStop()";
        Log.i(TAG, msgFive);
    }

    void onNextTurn(){   // mark board, check if game over, if on one player mode execute cpu turn method
        changeImg(saveLastImgIdx);
            if(didImgChange) {
                isGameOver();
                isPlyOneTurn = !isPlyOneTurn;
                 cpuTurn();
             }
        didImgChange = false;
    }
    void changeImg( int imgCoord){
        int r = imgCoord/3, c = imgCoord % 3;
        if(board[r][c].equals("_")) {   //check if space is empty.
            if (isPlyOneTurn) {
                board[r][c] = plyOnePiece;
                updateArrCounter(imgCoord, isPlyOneTurn);
                showPieceOnIV(plyOnePiece, getImg(imgCoord));
            }
            if (!isPlyOneTurn && numPlayers.equals("2")) { //2 player mode
                board[r][c] = plyTwoPiece;
                updateArrCounter(imgCoord, isPlyOneTurn);
                showPieceOnIV(plyTwoPiece, getImg(imgCoord));
            }
        }
    }
    public void cpuTurn(){
        if(!isPlyOneTurn && numPlayers.equals("1")){
            int cpuCoord = cpuPickIdx();
            int r = cpuCoord/3, c = cpuCoord % 3;
            saveLastImgIdx = cpuCoord;
            if(  board[r][c].equals("_")) {
                board[r][c] = plyTwoPiece;
                updateArrCounter(cpuCoord, isPlyOneTurn);
                showPieceOnIV(plyTwoPiece, getImg(cpuCoord));
                isPlyOneTurn = !isPlyOneTurn;
            }
            isGameOver();
        }
    }
    void setWhoGoesFirst(){ // pick a rand player to go first
        whoGoesFirst = (int)(Math.random() * 100);
        if(whoGoesFirst % 2 ==0){
            Toast success = Toast.makeText(BoardActivity.this, plyOnePiece+ " goes first!", Toast.LENGTH_SHORT);
            success.show();
        }
        else{
            Toast success = Toast.makeText(BoardActivity.this, plyTwoPiece+ " goes first!", Toast.LENGTH_SHORT);
            success.show();
            cpuTurn();
        }
        isPlyOneTurn = true;
    }


    int cpuPickIdx() {
        // 1. check if 2 in a line, complete line and win
        // 2. check if human has 2 in a row
            // a. if they do, loop through and find empty spot
            // b. else, place piece close to other piece to make 3 in a line
        // 3. default, pick rand spot

        int winCoord = -1;  // the cpu's (plyTwo) winning move coordinate
        int offCoord = -1;  // offence coordinate to block human's 3 in a line
        int defCoord = -1;  // defence coordinate to make 3 in a line for cpu win
        int randCoord = 0;  // rand coordinate, when no off or def moves possible (beginning of game)
        int currCoord = -1; // converts 2 digit coordinate to single digit

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (board[r][c].equals("_")) {
                    currCoord = r * 3 + c;
                    if (plyTwoCounter[r] == 2 || plyTwoCounter[c + N] == 2) { // check row and col
                        winCoord = currCoord;
                        return winCoord;
                    }
                    if (r == c && plyTwoCounter[M - 1] == 2) { // check left diag
                        winCoord = currCoord;
                        return winCoord;
                    }
                    if (plyTwoCounter[M - 2] == 2 && currCoord % 2 == 0 && currCoord > 0 && currCoord < M) { // check right diag
                        winCoord = currCoord;
                        return winCoord;
                    }
                    if (offCoord == -1) {
                        if (plyOneCounter[r] == 2) { //row
                            offCoord = currCoord;
                        }
                        if (plyOneCounter[c + N] == 2) { //col
                            offCoord = currCoord;
                        }
                        if (r == c && plyOneCounter[M - 1] == 2) { //left diag
                            offCoord = currCoord;
                        }
                        if (plyOneCounter[M - 2] == 2 && currCoord % 2 == 0 && currCoord > 0 && currCoord < 8) { //right diag
                            offCoord = currCoord;
                        }
                    }
                    if (defCoord == -1) {
                        if (plyTwoCounter[r] == 1 && (plyTwoCounter[r] + plyOneCounter[r]) < 2) { //row
                            defCoord = currCoord;
                        }
                        if (plyTwoCounter[c + N] == 1 && (plyTwoCounter[r + N] + plyOneCounter[r + N]) < 2) { //col
                            defCoord = currCoord;
                        }
                        if (r == c && (plyTwoCounter[M - 1] + plyOneCounter[M - 1]) < 2) { //left diag
                            defCoord = currCoord;
                        }
                        if ((plyTwoCounter[M - 2] + plyOneCounter[M - 2]) < 2 && currCoord % 2 == 0 && currCoord > 0 && currCoord < 8) { //right diag
                            defCoord = currCoord;
                        }
                    }
                    if (randCoord == 0) {
                        if (currCoord % 2 == 0 && (plyOneCounter[r] > 0 || plyOneCounter[c + N] <8)) { // choose corner or center position
                            randCoord = currCoord;
                        }
                    }

                }
            }
        }
        if (offCoord != -1) {
            return offCoord;
        }
        if (defCoord != -1) {
            return defCoord;
        }
        return randCoord;
    }

    private void showPieceOnIV(String piece, ImageView img){
        if(piece.equals("X")) {
            img.setImageBitmap(BitmapFactory.decodeResource(getResources(), xResId));
            didImgChange = true;
        }
        if(piece.equals("O")) {
            img.setImageBitmap(BitmapFactory.decodeResource(getResources(), oResId));
            didImgChange = true;
        }
    }

    public void isGameOver(){
        winningPiece = checkForWin();
        if(currRound < numRounds){
                if(winningPiece.equals("X") ||winningPiece.equals("O") || isBoardFilled()){ //game over
                 setUpNextRound();
             }
         }
         if(currRound == numRounds) {   //after last round completes, move to Game over activity
             if (winningPiece.equals(plyOnePiece)) {
                 plyOneScore++;
                 saveWinnerOfGame();
                 startActivity(new Intent(BoardActivity.this, GameOverActivity.class));
             }
             if (winningPiece.equals(plyTwoPiece)) {
                 plyTwoScore++;
                 saveWinnerOfGame();
                 startActivity(new Intent(BoardActivity.this, GameOverActivity.class));
             }
             if (isBoardFilled()) {
                 saveWinnerOfGame();
                 startActivity(new Intent(BoardActivity.this, GameOverActivity.class));
             }
         }
    }
    void setUpNextRound(){ // keep track of score, reset var, print game info on TV
       boolean interrupted ;
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            interrupted = true;
            String msg = "catch exception";
            scoreBoardTV.setText(msg);
        }
        currRound++;
        if(winningPiece.equals(plyOnePiece)){
            plyOneScore++;
        }
        if(winningPiece.equals(plyTwoPiece)){
            plyTwoScore++;
        }
        resetGame();

        if(numRounds!=1) { // score board and rounds TV needed for when rounds > 1
            String sbFinalMsg = sbMsgPrefix + plyOneScore + " - " + plyTwoScore + sbMsgSuffix;
            scoreBoardTV.setText(sbFinalMsg);

            String roundsMsg = "Round " + currRound;
            roundsTV.setText(roundsMsg);
        }
    }
    void resetGame() {     //clears counter arrays, char board, and makes all IV blank for next round
        winningPiece = "_";
        for (int i = 0; i < M; i++) {
            plyOneCounter[i] = 0;
            plyTwoCounter[i] = 0;
        }
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                board[r][c] = "_";
            }
        }
        //reset img views to white color
        for(int i =0; i<=M; i++) {
            (getImg(i)).setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.whitespace));
        }
    }
    void saveWinnerOfGame(){
        String gameOverMsg = "";
        String score = "";
        if(plyOneScore>plyTwoScore){
            gameOverMsg = plyOnePiece + " won!" ;
            if(numRounds>1)
                 score = "\n" + plyOneScore + " - " + plyTwoScore;
            saveGameOverMsg(gameOverMsg+score);
        }
        if(plyTwoScore>plyOneScore){
            gameOverMsg = plyTwoPiece + " won!" ;
            if(numRounds>1)
                 score = "\n" + plyTwoScore + " - " + plyOneScore;
            saveGameOverMsg(gameOverMsg+score);
        }
        if(plyTwoScore == plyOneScore){
            gameOverMsg = "It is a tie.";
            saveGameOverMsg(gameOverMsg);
        }
    }

    public void saveGameOverMsg( String winner) {
        SharedPreferences sharedPreferences = getSharedPreferences("gameDetails", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("gameOverMsg", winner );
        editor.apply();
    }


    public String checkForWin(){
        for (int i = 0; i < plyOneCounter.length; i++) {
            if (plyOneCounter[i] == 3) {
                return plyOnePiece;
            }
            if (plyTwoCounter[i] == 3) {
                return plyTwoPiece;
            }
        }
        return " "; // tie
    }

    // counter arr stores the number of pieces of a particular row, col, or diagonal
    // each idx keeps track of the count -> counter arr = [1st row, 2nd row, 3rd row, 1st col, 2nd col, 3rd col, right diagonal, left diagonal]
    // after each turn must update count
    private void updateArrCounter(int coord, boolean isPlyOneTurn) {
        int r = coord / 3;
        int c = coord % 3;
        //printBoard(board);
        if (isPlyOneTurn) {
            if (r == c) {
                //left diagonal
                plyOneCounter[M - 1]++; //last index
            }
            if (coord % 2 == 0 && coord > 0 && coord < 8) {
                //right diag
                plyOneCounter[M - 2]++; //second last index
            }
            plyOneCounter[r]++;
            plyOneCounter[c + N]++; // Col
        }
        else {
            if (r == c) {
                //left diagonal
                plyTwoCounter[M - 1]++; //last index
            }
            if (coord % 2 == 0 && coord > 0 && coord < 8) {
                ///right diag
                plyTwoCounter[M - 2]++; //second last index
            }
            plyTwoCounter[r]++;
            plyTwoCounter[c + N]++; // COL

        }
    }
    private ImageView getImg(int coord)   // retrieves imageViews that make up board (3 x 3)
    {
        switch (coord){
            case 0: return oneImg;
            case 1: return twoImg;
            case 2: return threeImg;
            case 3: return fourImg;
            case 4: return fiveImg;
            case 5: return sixImg;
            case 6: return sevenImg;
            case 7: return eightImg;
            case 8: return nineImg;
            default: return null;
        }
    }
    public boolean isBoardFilled() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j].equals("_")) {
                    return false;
                }
            }
        }
        return true;
    }

    public void loadGameData( ) { // load data from previous activities
        SharedPreferences sharedPreferences = getSharedPreferences("gameDetails", MODE_PRIVATE);
        numPlayers = sharedPreferences.getString("numPlayers", "");
        plyOnePiece = sharedPreferences.getString("plyOnePiece", "");
        xResId = sharedPreferences.getInt("xResId", R.drawable.redx);
        oResId = sharedPreferences.getInt("oResId", R.drawable.blo);
        xSelectResId = sharedPreferences.getInt("xSelectResId", R.drawable.redxselect);
        oSelectResId = sharedPreferences.getInt("oSelectResId", R.drawable.bloselected);

        if(plyOnePiece.equals("X")){
            sbMsgPrefix = "X  ";   // score board prefix
            sbMsgSuffix = "  O";   // score board suffix
            plyTwoPiece = "O";
        }
        if(plyOnePiece.equals("O")){
            sbMsgPrefix = "O  ";
            sbMsgSuffix = "  X";
            plyTwoPiece = "X";
        }

        String rounds = sharedPreferences.getString("numRounds", "");
        numRounds = Integer.parseInt(rounds);


        if(numRounds == 1){
            String sbMsg = sbMsgPrefix + sbMsgSuffix;
            scoreBoardTV.setText(sbMsg);
        }
        if(numRounds >1){
            String sbMsg = sbMsgPrefix + "0 - 0" + sbMsgSuffix;
            scoreBoardTV.setText(sbMsg);
            String roundsMsg = "Round 1";
            roundsTV.setText(roundsMsg);
        }
    }

    void initializeBtnImgTV(){
        mainBtn = findViewById(R.id.backBtn);
        optionsBtn = findViewById(R.id.optionsBtn);
        oneImg = findViewById(R.id.imageView1);
        twoImg = findViewById(R.id.imageView2);
        threeImg = findViewById(R.id.imageView3);
        fourImg = findViewById(R.id.imageView4);
        fiveImg = findViewById(R.id.imageView5);
        sixImg = findViewById(R.id.imageView6);
        sevenImg = findViewById(R.id.imageView7);
        eightImg = findViewById(R.id.imageView8);
        nineImg = findViewById(R.id.imageView9);
        scoreBoardTV = findViewById(R.id.scoreTV);
        roundsTV = findViewById(R.id.roundsTV);
    }




}
