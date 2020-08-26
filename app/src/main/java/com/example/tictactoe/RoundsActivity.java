package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RoundsActivity extends AppCompatActivity {
    Button oneRoundBtn, threeRoundBtn, fiveRoundBtn, backBtn, optionsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rounds);
         oneRoundBtn = findViewById(R.id.oneRoundBtn);
         threeRoundBtn= findViewById(R.id.threeRoundBtn);
         fiveRoundBtn = findViewById(R.id.fiveRoundBtn);
         backBtn = findViewById(R.id.backBtn);
         optionsBtn = findViewById(R.id.optionsBtn);
        oneRoundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData("1");
                startActivity(new Intent(RoundsActivity.this, BoardActivity.class));
            }
        });
        threeRoundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData("3");
                startActivity(new Intent(RoundsActivity.this, BoardActivity.class));
            }
        });
        fiveRoundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData("5");
                startActivity(new Intent(RoundsActivity.this, BoardActivity.class));
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RoundsActivity.this, ChoosePieceActivity.class));
            }
        });
        optionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoundsActivity.this, SettingsActivity.class);
                intent.putExtra("previousActivity", "RoundsActivity");
                startActivity(intent);
            }
        });


    }
    public void saveData( String num) {
        SharedPreferences sharedPreferences = getSharedPreferences("gameDetails", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("numRounds", num );
        editor.apply();
    }
}
