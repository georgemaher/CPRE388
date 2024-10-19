package com.example.whackamole;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.Random;


public class GameViewModel extends ViewModel {

    /**
     * declaring all the variables and initialize what's needed.
     */
    protected SharedPreferences sh;
    protected SharedPreferences.Editor editor;
    protected ImageButton[] mole_s = new ImageButton[9];
    protected Integer[] res = new Integer[9];
    protected int moleNumRand;
    protected Random random = new Random();
    protected int score = 0;
    protected long[] hide =  new long[9];
    protected TextView scoreView;
    protected MutableLiveData<Integer> misses = new MutableLiveData<>() ;
    protected int m = 0;
    protected long time = 1234128472;
    protected Handler handler = new Handler(Looper.getMainLooper());
    protected TextView Misses;
    protected int dec = 10;
    protected ImageButton mole0;
    protected ImageButton mole1;
    protected ImageButton mole2;
    protected ImageButton mole3;
    protected ImageButton mole4;
    protected ImageButton mole5;
    protected ImageButton mole6;
    protected ImageButton mole7;
    protected ImageButton mole8;
    protected MediaPlayer mediaPlayer;
    protected MediaPlayer mediaPlayer2;
    protected TextView highScore;
    int k = 0;
    /**
     *  initialize each element of the array to 1 mole image button
     *  set all of the holes to no mole_s
     *  initializing the sharedPreference Object.
     *  Initialize the textViews.
     */

    protected void init() {
        // initializing an array to easily loop through the buttons.
        mole_s[0] = mole0;
        mole_s[1] = mole1;
        mole_s[2] = mole2;
        mole_s[3] = mole3;
        mole_s[4] = mole4;
        mole_s[5] = mole5;
        mole_s[6] = mole6;
        mole_s[7] = mole7;
        mole_s[8] = mole8;
        for (int i = 0; i < mole_s.length; i++) {
            // for each element of the array, initially set image of mole down and set tag of down
            mole_s[i].setImageResource(R.drawable.no_mole);
            mole_s[i].setTag(R.drawable.no_mole);
            // set the res integer for the value of the corresponding mole tag
            res[i] = (Integer) mole_s[i].getTag();
        }
        //initialize score and set it to textView
        if(!sh.contains("score")){
            editor.putInt("score" , 0 );
            editor.commit();
        }
        highScore.setText("High Score: "+ sh.getInt("score",0));
        scoreView.setText("Score: " +score);
        Misses.setText("Misses= " + m);
    }
    /**
     *  The onCLickListeners for each image button.
     *  Once the button has a mole up res tag and was clicked, increase score and shift it to no mole.
     *  Updates the scoreView textView as the score increases.
     */
    protected void listeners() {

        mole0.setOnClickListener(v -> {
            if (res[0] == R.drawable.mole_up) {
                mediaPlayer2.start();
                mole0.setImageResource(R.drawable.no_mole);
                mole0.setTag(R.drawable.no_mole);
                res[0] = (Integer) mole0.getTag();
                mediaPlayer2.stop();
                score++;
            }
            scoreView.setText("Score: " + (score));
        });

        mole1.setOnClickListener(v -> {
            if (res[1] == R.drawable.mole_up) {
                mediaPlayer.start();
                mole1.setImageResource(R.drawable.no_mole);
                mole1.setTag(R.drawable.no_mole);
                mediaPlayer2.stop();
                res[1] = (Integer) mole1.getTag();
                score++;
            }
            scoreView.setText("Score: " + (score));
        });

        mole2.setOnClickListener(v -> {
            if (res[2] == R.drawable.mole_up) {
                mediaPlayer2.start();
                mole2.setImageResource(R.drawable.no_mole);
                mole2.setTag(R.drawable.no_mole);
                mediaPlayer2.stop();
                res[2] = (Integer) mole2.getTag();
                score++;
            }
            scoreView.setText("Score: " + (score));
        });

        mole3.setOnClickListener(v -> {
            if (res[3] == R.drawable.mole_up) {
                mediaPlayer2.start();
                mole3.setImageResource(R.drawable.no_mole);
                mole3.setTag(R.drawable.no_mole);
                mediaPlayer2.stop();
                res[3] = (Integer) mole3.getTag();
                score++;
            }
            scoreView.setText("Score: " + (score));
        });

        mole4.setOnClickListener(v -> {
            if (res[4] == R.drawable.mole_up) {
                mediaPlayer2.start();
                mole4.setImageResource(R.drawable.no_mole);
                mole4.setTag(R.drawable.no_mole);
                mediaPlayer2.stop();
                res[4] = (Integer) mole4.getTag();
                score++;
            }
            scoreView.setText("Score: " + (score));
        });

        mole5.setOnClickListener(v -> {
            if (res[5] == R.drawable.mole_up) {
                mediaPlayer2.start();
                mole5.setImageResource(R.drawable.no_mole);
                mole5.setTag(R.drawable.no_mole);
                mediaPlayer2.stop();
                res[5] = (Integer) mole5.getTag();
                score++;
            }
            scoreView.setText("Score: " + (score));
        });

        mole6.setOnClickListener(v -> {
            if (res[6] == R.drawable.mole_up) {
                mediaPlayer2.start();
                mole6.setImageResource(R.drawable.no_mole);

                mole6.setTag(R.drawable.no_mole);
                mediaPlayer2.stop();
                res[6] = (Integer) mole6.getTag();
                score++;
            }
            scoreView.setText("Score: " + (score));
        });

        mole7.setOnClickListener(v -> {
            if (res[7] == R.drawable.mole_up) {
                mediaPlayer2.start();
                mole7.setImageResource(R.drawable.no_mole);
                mole7.setTag(R.drawable.no_mole);
                mediaPlayer2.stop();
                res[7] = (Integer) mole7.getTag();
                score++;


            }
            scoreView.setText("Score: " + (score));
        });

        mole8.setOnClickListener(v -> {
            if (res[8] == R.drawable.mole_up) {
                mediaPlayer2.start();
                mole8.setImageResource(R.drawable.no_mole);
                mole8.setTag(R.drawable.no_mole);
                mediaPlayer2.stop();
                res[8] = (Integer) mole8.getTag();
                score++;


            }
            scoreView.setText("Score: " + (score));
        });
    }

    /**
     * a func to return the number of misses to update the textView and start end screen
     * @return number of misses
     */

    protected int getm(){
        return m;
    }

    /**
     * a getter method to return mutable live object
     * @return mutable live data object so it can be observed in the main activity
     */

    protected MutableLiveData<Integer> getMisses(){
        return misses;
    }

    /**
     *The main method running the game and has all the rules in it
     * allows only 8 misses.
     * The rate of mole_s appearing and disappearing increases over time.
     */
    protected void start() {
        misses.setValue(0);
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (m <= 8) {

                    for (int i = 0; i < hide.length; i++) {
                        if (hide[i] == time / 1000 && res[i] == R.drawable.mole_up) {
                            mole_s[i].setImageResource(R.drawable.no_mole);
                            mole_s[i].setTag(R.drawable.no_mole);
                            res[i] = (Integer) mole_s[i].getTag();
                            m ++;
                            misses.setValue(m);
                            Misses.setText("Misses= " + m);
                            k = i;
                        }
                    }
                    // random mole num
                    moleNumRand = random.nextInt(9);
                        //ensuring no mole_s appear at the same hole with another mole
                        while (res[moleNumRand] == R.drawable.mole_up && moleNumRand != k) {
                            // keep shuffling between resources until finds moleDown
                            moleNumRand = random.nextInt(9);
                        }
                        // the no mole switches to mole_up
                        mediaPlayer.start();
                        mole_s[moleNumRand].setImageResource(R.drawable.mole_up);
                        mole_s[moleNumRand].setTag(R.drawable.mole_up);
                        res[moleNumRand] = (Integer) mole_s[moleNumRand].getTag();
                        mediaPlayer.stop();
                        // if time left is greater or equal to 2 secs
                        if (time / 1000 >= 2) {
                            // set the hide time for that mole to time left -1
                            hide[moleNumRand] = time / 1000 - 1;
                        }
                    time -= 1000;
                    dec += 10;
                    handler.postDelayed(this, Math.max(1500-dec, 100));
                }
                else{
                    mediaPlayer.stop();
                    if (score > sh.getInt("score", 0)){
                        editor.putInt("score" , score);
                        editor.apply();
                        highScore.setText("High Score: "+ sh.getInt("score",0));
                    }
                    handler.removeCallbacks(this);
                    scoreView.setText("SCORE : "  + score );
                    for (ImageButton mole : mole_s) {
                        //irritate through each mole and put them all down
                        mole.setImageResource(R.drawable.no_mole);
                    }
                }
            }
        });
    }
}
