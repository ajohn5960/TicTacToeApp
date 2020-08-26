package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayersActivity extends AppCompatActivity {
    Button optionsBtn, onePlayerBtn, twoPlayerBtn, backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
         optionsBtn = findViewById(R.id.optionsBtn);
         backBtn = findViewById(R.id.backBtn);

         onePlayerBtn = findViewById(R.id.onePlayerBtn);
         twoPlayerBtn = findViewById(R.id.twoPlayerBtn);

        onePlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData("1");
                startActivity(new Intent(PlayersActivity.this, ChoosePieceActivity.class));
            }
        });
        twoPlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData("2");
                startActivity(new Intent(PlayersActivity.this, ChoosePieceActivity.class));
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlayersActivity.this, MainActivity.class));
            }
        });
        optionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayersActivity.this, SettingsActivity.class);
                intent.putExtra("previousActivity", "PlayersActivity");
                startActivity(intent);
            }
        });
    }
    public void saveData( String num) {
        SharedPreferences sharedPreferences = getSharedPreferences("gameDetails", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("numPlayers", num );
        editor.apply();
    }
}
