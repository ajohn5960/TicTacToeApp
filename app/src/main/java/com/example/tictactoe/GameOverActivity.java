package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {
    private static final String TAG = "GameOverActivity";
    Button quitBtn, playAgnBtn, sameModeBtn;
    TextView whoWinsTV;
    String winner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        quitBtn = findViewById(R.id.quitBtn);
        playAgnBtn = findViewById(R.id.playAgnBtn);
        whoWinsTV = findViewById(R.id.whoWinsTV);
        sameModeBtn = findViewById(R.id.sameModeBtn);
        loadGameData();
        whoWinsTV.setText(winner);

        playAgnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameOverActivity.this, PlayersActivity.class));
            }
        });
        quitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameOverActivity.this, MainActivity.class));
            }
        });
        sameModeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameOverActivity.this, BoardActivity.class));
            }
        });
    }
    public void loadGameData( ) {
        SharedPreferences sharedPreferences = getSharedPreferences("gameDetails", MODE_PRIVATE);
        winner = sharedPreferences.getString("gameOverMsg", "");
    }
}
