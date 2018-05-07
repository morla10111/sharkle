package de.cracksucht.sharkle;

/*
 *
 * <3<3<3 for malvina <3<3<3
 *
 * - morla
 *
 */

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import android.content.Intent;
import android.app.PendingIntent;
import android.widget.ImageView;
import java.util.Random;
import android.widget.Toast;

import android.media.MediaPlayer;
import android.os.SystemClock;



/**
 * Implementation of App Widget functionality.
 */
public class sharkleWidget extends AppWidgetProvider {

    private ImageView sharkleIV;
    public static String INTENT_ON_CLICK_FORMAT = "de.cracksucht.sharkle.id.%d.click";
;

   @Override
   public void onReceive(Context context, Intent intent) {
       super.onReceive(context, intent);

       //Toast.makeText(context, "blahhh", Toast.LENGTH_SHORT).show();

       if (intent.getAction().startsWith("de.cracksucht.sharkle")) {

           int id = intent.getIntExtra("widgetId", 0);
           AppWidgetManager manager = AppWidgetManager.getInstance(context);
           RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.sharkle_widget);

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
           MediaPlayer mediaPlayer = MediaPlayer.create(context, hey[number]);
           mediaPlayer.start();

           drawSharkleWave(manager, views, id);
           drawSharkleMove(manager, views, id);

           // inc sharkles
           SharedPreferences settings = context.getSharedPreferences("sharklOmeter", 0);
           int sharkles = settings.getInt("sharkles", 0);
           sharkles++;
           SharedPreferences.Editor editor = settings.edit();
           editor.putInt("sharkles", sharkles);
           editor.apply();
       }
   }

    static void drawSharkleWave(AppWidgetManager manager, RemoteViews views, int viewID){

        int delay=100;
        int times=2;
        int j=0;

        int[] sharkle= {
                R.drawable.sharkle_wave0,
                R.drawable.sharkle_wave1,
                R.drawable.sharkle_wave2,
                R.drawable.sharkle_wave3
        };

        for(int i=0; i<=times; i++) {
            j=0;
            for (int img : sharkle) {
                // wave!
                views.setImageViewResource(R.id.sharkleIV, img);
                //bubble
                if( j % 2 == 0 )
                    views.setImageViewResource(R.id.bubbleIV, R.drawable.sharkle_bubble0);
                else
                    views.setImageViewResource(R.id.bubbleIV, R.drawable.sharkle_bubble1);

                manager.updateAppWidget(viewID, views);
                SystemClock.sleep(delay);

                j++;
            }

        }
        // clear bubble...
        views.setImageViewResource(R.id.bubbleIV, 0);
        manager.updateAppWidget(viewID, views);
    }

    static void drawSharkleMove(AppWidgetManager manager, RemoteViews views, int viewID){

        int delay=90;
        int times=1;

        int[] sharkle= {
                R.drawable.sharkle_idle1,
                R.drawable.sharkle_idle2,
                R.drawable.sharkle_idle3,
                R.drawable.sharkle_idle4,
                R.drawable.sharkle_idle5,
                R.drawable.sharkle_idle6,
                R.drawable.sharkle_idle7,
                R.drawable.sharkle_idle6,
                R.drawable.sharkle_idle5,
                R.drawable.sharkle_idle4,
                R.drawable.sharkle_idle3,
                R.drawable.sharkle_idle2,
                R.drawable.sharkle_idle1,
                R.drawable.sharkle_idle0
        };

        for(int i=0; i<=times; i++) {
            for (int img : sharkle) {
                views.setImageViewResource(R.id.sharkleIV, img);
                manager.updateAppWidget(viewID, views);
                SystemClock.sleep(delay);
            }
        }
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.sharkle_widget);
        //views.setTextViewText(R.id.appwidget_text, widgetText);


        Intent intent = new Intent(String.format(INTENT_ON_CLICK_FORMAT, appWidgetId));
        intent.setClass(context, sharkleWidget.class);
        intent.putExtra("widgetId", appWidgetId);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.sharkleIV, pi);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // There may be multiple widgets active, so update all of them
        //Toast.makeText(context, "foo", Toast.LENGTH_SHORT).show();
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);

        }

    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

