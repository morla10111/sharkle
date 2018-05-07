package de.cracksucht.sharkle;

/*
 *
 * <3<3<3 for malvina <3<3<3
 *
 * - morla
 *
 */

import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.SharedPreferences;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.String;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View mDecorView;
            mDecorView = getWindow().getDecorView();
            mDecorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                            | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                            | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
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

    @Override
    protected void onResume() {
        super.onResume();

        // get sharkles
        SharedPreferences sets = getApplicationContext().getSharedPreferences("sharklOmeter", 0);
        int sharkles = sets.getInt("sharkles", 0);
        //Toast.makeText(this, String.format("You SHARKLED %d times!",sharkles), Toast.LENGTH_SHORT).show();
        TextView ts = (TextView)findViewById(R.id.timessharkled);
        ts.setText(String.format("You SHARKLED %d times",sharkles));

        TextView som = (TextView)findViewById(R.id.sharkleometer);
        som.setText("SHARKLE-O-METER");

    }
}
