package com.example.whackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class End extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end);

        Intent endScreen = getIntent();
        String endScore = endScreen.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView score= findViewById(R.id.score);
        score.setText(endScore);

        Button end = findViewById(R.id.endGame);
        Button restart = findViewById(R.id.restartGame);

        /**
         * onClickListener for the end button
         * goes back  to the start up activity
         */
        end.setOnClickListener(v -> {
            Intent endTheGame = new Intent(End.this, Welcome.class);
            startActivity(endTheGame);
        });
        /**
        *   onClickListener for the start button
        * goes to the Main activity.
        */
        restart.setOnClickListener(v -> {
            Intent playGame = new Intent(End.this, MainActivity.class);
            startActivity(playGame);
        });

    }
}