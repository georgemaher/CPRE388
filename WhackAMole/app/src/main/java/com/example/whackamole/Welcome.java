package com.example.whackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        Button start = findViewById(R.id.StartUp);
        /**
         * An onClickListener for the start button on the start screen.
         */
        start.setOnClickListener(v -> {
            Intent intent = new Intent(Welcome.this, MainActivity.class);
            startActivity(intent);
        });
    }
}