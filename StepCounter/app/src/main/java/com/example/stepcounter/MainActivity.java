package com.example.stepcounter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    SensorManager sensorManager;
    TextView textView,textView1,textView2,textView3,textView4;
    Sensor sensor;
    double magPrev;
    int steps=0;
    String string4 = "";
    double magDiff = 0.0;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        button=findViewById(R.id.button);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        button.setOnClickListener(view -> steps =0);
        SensorEventListener sensorEventListener = new SensorEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(sensorEvent != null){
                    DecimalFormat decimalFormat= new DecimalFormat("##.0"),decimalFormat1= new DecimalFormat("##.0"),decimalFormat2 = new DecimalFormat("##.0");
                    DecimalFormat decimalFormat4= new DecimalFormat("##.0");
                    String string,string1,string2,string3;
                    float xMag = sensorEvent.values[0];
                    float yMag = sensorEvent.values[1];
                    float zMag = sensorEvent.values[2];
                    string = decimalFormat.format(xMag);
                    string1 = decimalFormat1.format(yMag);
                    string2 = decimalFormat2.format(zMag);
                    textView1.setText("X-Axis: " +string);
                    textView2.setText("Y-Axis: " + string1);
                    textView3.setText("ZMag: "+ string2);
                    double mag = Math.sqrt(Math.pow(xMag,2) + Math.pow(yMag,2) + Math.pow(zMag,2));
                    magDiff = mag - magPrev;
                    string3 = decimalFormat4.format(magDiff);
                    magPrev = mag;
                    textView.setText("Steps: "+steps);

                    if(magDiff > 1.42 && magDiff < 6 ){
                        string4  += string3 + "\n" ;
                        textView4.setText(string4);
                        steps ++;
                    }
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };
        sensorManager.registerListener(sensorEventListener,sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }


}