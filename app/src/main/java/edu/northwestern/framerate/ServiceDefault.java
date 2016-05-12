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
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by B on 5/2/2016.
 */
public class ServiceDefault extends Service {

    private Context context;
    public static Writers writeDefault;
    public SharedPreferences sharedPrefs;
    public Timer timer;

    public void onCreate(){
        Log.i(MainActivity.TAG, "In Default Service now.");
        context = getApplicationContext();
        writeDefault = new Writers(context, MainActivity.DEFAULT_CODE);
        sharedPrefs = context.getSharedPreferences(MainActivity.MYPREFS, Context.MODE_PRIVATE);

        timer = new Timer();
        TimerTask reboot = new TimerTask() {
            @Override
            public void run() {
                MainActivity.changeFPS(MainActivity.DEFAULT_FPS);
            }
        };
        timer.scheduleAtFixedRate(reboot, 120000, 120000);
    }
    public void onDestroy(){
        String start = sharedPrefs.getString("DEFAULT_START", "");
        Date now = Calendar.getInstance().getTime();
        String end = MainActivity.format.format(now);

        writeDefault.writeString("Last session started at " + start + "\n");
        writeDefault.writeString("Last session ended at " + end + "\n");
        writeDefault.close();

        timer.cancel();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
