package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    Button start,stop,reset;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // inflate view from xml sources
          setContentView(R.layout.activity_main);
         StopWatchViewModel stopWatchViewModel = new ViewModelProvider(this).get(StopWatchViewModel.class);
         stopWatchViewModel.text = (TextView) findViewById(R.id.textView);
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
        reset = findViewById(R.id.reset);
        if (stopWatchViewModel.running){
            start.setVisibility(View.INVISIBLE);
            stop.setVisibility(View.VISIBLE);
        }
        else{
            start.setVisibility(View.VISIBLE);
            stop.setVisibility(View.INVISIBLE);
        }

        final Observer<String> obs = new Observer<String>() {
            @Override
            public void onChanged(String str) {
               stopWatchViewModel.text.setText (stopWatchViewModel.getS());
            }
        };
        stopWatchViewModel.getText().observe(this, obs);
       start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopWatchViewModel.startClick();
                start.setVisibility(View.INVISIBLE);
                stop.setVisibility(View.VISIBLE);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopWatchViewModel.stopClick();
                start.setVisibility(View.VISIBLE);
                stop.setVisibility(View.INVISIBLE);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopWatchViewModel.resetClick();
            }
        });

    }

}