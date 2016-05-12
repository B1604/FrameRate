package edu.northwestern.framerate;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by B on 5/6/2016.
 */
public class ServiceLearning extends Service{
    public Context context;
    public static Writers writeLearning;
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;

    public DataCollector dc;
    public Timer timer_collection, timer_frame;

    public void onCreate(){
        Log.i(MainActivity.TAG, "In Learning Service now.");
        context = getApplicationContext();
        writeLearning = new Writers(context, MainActivity.LEARNING_CODE);
        sharedPreferences = context.getSharedPreferences(MainActivity.MYPREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        String header = "TIME - Accelerometer, AmbientLight, Battery, " +
                "CPUFreq, CPUUtil, GPUclk, Frame Rate\n";
        writeLearning.writeString(header);

        dc = new DataCollector(context);

        timer_collection = new Timer("ForData");
        TimerTask collect = new TimerTask() {
            @Override
            public void run() {
                dc.collectAll();
                writeLearning.writeDate();
                writeLearning.writeCollectedData(dc);
            }
        };
        timer_collection.scheduleAtFixedRate(collect, 0, 1000);

        timer_frame = new Timer();
        TimerTask change = new TimerTask() {
            @Override
            public void run() {
                int changeTo = randomFPS();
                editor.putInt("FPS", changeTo);
                editor.commit();

                MainActivity.changeFPS(changeTo);
            }
        };
        timer_frame.scheduleAtFixedRate(change, 120000, 120000);
    }

    public int randomFPS(){
        Random rand = new Random();
        int fps = rand.nextInt(7)+1;
        int frame_rate = 0;
        if(fps == 1){
            frame_rate = 30;
        }
        else if(fps == 2){
            frame_rate = 35;
        }
        else if(fps == 3){
            frame_rate = 40;
        }
        else if(fps == 4){
            frame_rate = 45;
        }
        else if(fps == 5){
            frame_rate = 50;
        }
        else if(fps == 6){
            frame_rate = 55;
        }
        else if(fps == 7){
            frame_rate = 60;
        }
        return frame_rate;
    }

    public void onDestroy(){
        String start = sharedPreferences.getString("LEARNING_START", "");
        Date now = Calendar.getInstance().getTime();
        String end = MainActivity.format.format(now);

        timer_frame.cancel();
        timer_collection.cancel();

        writeLearning.writeString("Last session started at " + start+"\n");
        writeLearning.writeString("Last session ended at " + end + "\n");
        writeLearning.close();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
