package de.cracksucht.sharkle;

/*
 *
 * <3<3<3 for malvina <3<3<3
 *
 * - morla
 *
 */

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import  android.media.MediaPlayer.OnPreparedListener;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        // FIXME: does not work for me :/
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // play random hey
        int[] hey={
                R.raw.hey_0,
                R.raw.hey_1,
                R.raw.hey_2,
                R.raw.hey_3,
                R.raw.hey_4,
                R.raw.hey_5,
                R.raw.hey_6,
                R.raw.hey_7
        };

        int number = (new Random().nextInt(7));
        MediaPlayer mediaPlayer = MediaPlayer.create(this, hey[number]);
        mediaPlayer.start();

        setContentView(R.layout.activity_main);
    }
}
