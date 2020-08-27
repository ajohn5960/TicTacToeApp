package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;



public class ChoosePieceActivity extends AppCompatActivity {
    Button  optionsBtn, backBtn;
    TextView  plyOneTV, plyTwoTV;
    ImageView topLeftIV, topRightIV, botLeftIV, botRightIV;
    String numPlayers;
    int xResId, oResId, xSelectResId, oSelectResId; // all four var save res id of the .jpg image
    boolean xPlyOneSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_piece);
         optionsBtn = findViewById(R.id.optionsBtn);
         backBtn = findViewById(R.id.backBtn);
          topLeftIV = findViewById(R.id.imageView1);
         topRightIV = findViewById(R.id.imageView2);
         botLeftIV = findViewById(R.id.imageView3);
         botRightIV = findViewById(R.id.imageView4);
         plyOneTV = findViewById(R.id.playerOneTV);
         plyTwoTV = findViewById(R.id.playerTwoTV);

        loadGameData();

        topLeftIV.setImageResource(xResId);
        topRightIV.setImageResource(oResId);
        botLeftIV.setImageResource(oResId);
        botRightIV.setImageResource(xResId);

        String playerTwoTitle = "";
        if(numPlayers.equals("1")){
            playerTwoTitle = "CPU";
            plyTwoTV.setText(playerTwoTitle);
        }

        topLeftIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xPlyOneSelected = true;
                setImageView(xPlyOneSelected);
                saveData(xPlyOneSelected);
                startActivity(new Intent(ChoosePieceActivity.this, RoundsActivity.class));
            }
        });
        topRightIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xPlyOneSelected = false;
                setImageView(xPlyOneSelected);
                saveData(xPlyOneSelected);
                startActivity(new Intent(ChoosePieceActivity.this, RoundsActivity.class));
            }
        });
        botLeftIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xPlyOneSelected = true;
                setImageView(xPlyOneSelected);
                saveData(xPlyOneSelected);
                startActivity(new Intent(ChoosePieceActivity.this, RoundsActivity.class));
            }
        });
        botRightIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xPlyOneSelected = false;
                setImageView(xPlyOneSelected);
                saveData(xPlyOneSelected);
                startActivity(new Intent(ChoosePieceActivity.this, RoundsActivity.class));
            }
        });


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChoosePieceActivity.this, PlayersActivity.class));
            }
        });
        optionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoosePieceActivity.this, SettingsActivity.class);
                intent.putExtra("previousActivity", "ChoosePieceActivity");
                startActivity(intent);
            }
        });
    }

    public void saveData( boolean xPlyOneSelected) {
        SharedPreferences sharedPreferences = getSharedPreferences("gameDetails", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(xPlyOneSelected) {
            editor.putString("plyOnePiece","X");
        }
        else{
            editor.putString("plyOnePiece", "O");
        }
        editor.apply();
    }
    public void loadGameData( ) {
        SharedPreferences sharedPreferences = getSharedPreferences("gameDetails", MODE_PRIVATE);
        numPlayers = sharedPreferences.getString("numPlayers", "");
        xResId = sharedPreferences.getInt("xResId", R.drawable.redx);
        oResId = sharedPreferences.getInt("oResId", R.drawable.blo);
        xSelectResId = sharedPreferences.getInt("xSelectResId", R.drawable.redxselect);
        oSelectResId = sharedPreferences.getInt("oSelectResId", R.drawable.bloselected);

    }
    private void setImageView(boolean xPlyOneSelected){
        Bitmap xselect = BitmapFactory.decodeResource(getResources(),xSelectResId);
        Bitmap oselect = BitmapFactory.decodeResource(getResources(),oSelectResId);
        Bitmap xpiece = BitmapFactory.decodeResource(getResources(), xResId);
        Bitmap opiece = BitmapFactory.decodeResource(getResources(), oResId);
        if(xPlyOneSelected){

            topLeftIV.setImageBitmap(xselect);
            topRightIV.setImageBitmap(opiece);
            botLeftIV.setImageBitmap(oselect);
            botRightIV.setImageBitmap(xpiece);
        }
        else{
            topLeftIV.setImageBitmap(xpiece);
            topRightIV.setImageBitmap(oselect);
            botLeftIV.setImageBitmap(opiece);
            botRightIV.setImageBitmap(xselect);
        }
    }


}
