package com.example.stopwatch;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Locale;

public class StopWatchViewModel extends ViewModel {
     private long startTime = 0 , timeMil = 0 , timeSwap = 0 , updateTime = 0;
     private int mil, secs , mins , hours;
    protected boolean running = false;
    protected  Handler handler = new Handler(Looper.getMainLooper());
     protected TextView text ;
     protected MutableLiveData <String> str = new MutableLiveData<>() ;
     public String s = "";


     Runnable runnable = new Runnable() {
        @Override
        public void run() {
            timeMil=   SystemClock.uptimeMillis() - startTime;
            updateTime = timeSwap  + timeMil;
            mil=(int) updateTime % 1000;
            secs = (int) updateTime / 1000 % 60;
            mins = (int) updateTime / 1000 /60 % 60;
            hours = (int) updateTime / 1000 /60 /60 % 60;
            s = (String.format("%02d" , hours)+":"+String.format("%02d",mins)+":" +String.format("%02d",secs) + "." +String.format("%01d" , mil/100));
            str .setValue(s);
            text.setText(s);
            handler.postDelayed(this,10);
        }
    };

    public MutableLiveData<String> getText(){
        return str;
    }
    public String getS(){
        return s;
    }
    public void startClick(){
        startTime = SystemClock.uptimeMillis();
        handler.postDelayed(runnable , 10);
        running = true;
    }
    public void stopClick(){

        timeSwap += timeMil;
        running = false;
        handler.removeCallbacks(runnable);
    }
    public void resetClick(){

            if(running){
//                text.setText("00:00:00.0");
                startTime = SystemClock.uptimeMillis();
                mil = 0 ;
                secs= 0 ;
                hours = 0;
                timeSwap = 0;
                timeMil = 0;
                handler.postDelayed(runnable,10);
            }
            else{
                s= "00:00:00.0";
                text.setText(s);
                mil = 0 ;
                secs= 0 ;
                hours = 0;
                timeSwap = 0;
                timeMil = 0;
                handler.removeCallbacks(runnable);
            }
        }
    }



