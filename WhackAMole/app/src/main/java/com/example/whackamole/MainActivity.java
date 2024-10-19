package com.example.whackamole;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "WHACK A MOLE";
    private String scoreEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        /**
        * Connecting the viewModel to this activity
         * finding the imageButtons & textViews by their name ID
        */
        GameViewModel view = new ViewModelProvider(this).get(GameViewModel.class);
        view.sh = getPreferences(Context.MODE_PRIVATE); // 0 - for private mode
        view.editor = view.sh.edit();
        view.mole0 = findViewById(R.id.mole1);
        view.mole1 = findViewById(R.id.mole2);
        view.mole2 = findViewById(R.id.mole3);
        view.mole3 = findViewById(R.id.mole4);
        view.mole4 = findViewById(R.id.mole5);
        view.mole5 = findViewById(R.id.mole6);
        view.mole6 = findViewById(R.id.mole7);
        view.mole7 = findViewById(R.id.mole8);
        view.mole8 = findViewById(R.id.mole9);
        view.scoreView = findViewById(R.id.score);
        view.Misses = findViewById(R.id.Misses);
        view.highScore = findViewById(R.id.highScore);
        view.mediaPlayer = MediaPlayer.create(this,R.raw.audio3);
        view.mediaPlayer2= MediaPlayer.create(this,R.raw.audio2);
        view.init();
        /**
         * Observe the mutable Live Data and start the end screen when Misses is >= 8
         */
        final Observer<Integer> o = integer -> {
         if (view.getm() >= 8){
             // get the end game activity to start
             view.mediaPlayer.stop();
             Intent endScreen = new Intent(MainActivity.this, End.class);
             scoreEnd = view.scoreView.getText().toString();
             endScreen.putExtra(EXTRA_MESSAGE, scoreEnd);
             startActivity(endScreen);
         }
        };

        view.getMisses().observe(this,o);
        view.listeners();
        view.start();


    }


        }


