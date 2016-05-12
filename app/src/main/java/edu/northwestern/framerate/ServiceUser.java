package edu.northwestern.framerate;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by B on 5/6/2016.
 */
public class ServiceUser extends Service {
    private Context context;

    public static Writers writeUserSpecific;
    public int dissatisfaction;
    public DissatisfactionReportReceiver drp;
    public DataCollector dC;
    public Timer timer;

    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;

    public final int INTERVAL = 120000;

    public void onCreate(){
        Log.i(MainActivity.TAG, "In UserSpecific Service now.");
        context = getApplicationContext();
        writeUserSpecific = new Writers(context, MainActivity.USER_CODE);
        dissatisfaction = 0;
        dC = new DataCollector(context);

        sharedPreferences = getSharedPreferences(MainActivity.MYPREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        IntentFilter intentFilter = new IntentFilter("edu.northwestern.framerate.USER_DISSATISFACTION");
        drp = new DissatisfactionReportReceiver();
        registerReceiver(drp, intentFilter);

        timer = new Timer();
        TimerTask runPrediction = new TimerTask() {
            @Override
            public void run() {

                dC.collectAll();
                dissatisfaction = PredictionSpecific.predictor(dC);
                int currentFPS = sharedPreferences.getInt("FPS", 0);
                int newFPS = 60;

                if(dissatisfaction == 0){
                    if(currentFPS == 60)
                        newFPS = 55;
                    else if(currentFPS == 55)
                        newFPS = 50;
                    else if(currentFPS == 50)
                        newFPS = 45;
                    else if(currentFPS == 45)
                        newFPS = 40;
                    else if(currentFPS == 40)
                        newFPS = 35;
                    else if (currentFPS == 35 || currentFPS == 30)
                        newFPS = 30;

                    writeUserSpecific.writeDate();
                    writeUserSpecific.writeString("No dissatisfaction, decreasing to "+ newFPS +"\n");
                }
                else if(dissatisfaction == 1){
                    if(currentFPS == 60 || currentFPS == 55)
                        newFPS = 60;
                    else if(currentFPS == 50)
                        newFPS = 55;
                    else if(currentFPS == 45)
                        newFPS = 50;
                    else if(currentFPS == 40)
                        newFPS = 45;
                    else if(currentFPS == 35)
                        newFPS = 40;
                    else if (currentFPS == 30)
                        newFPS = 35;

                    writeUserSpecific.writeDate();
                    writeUserSpecific.writeString("Dissatisfaction detected, increasing to "+ newFPS+ "\n");
                }
                editor.putInt("FPS", newFPS);
                editor.commit();

                writeUserSpecific.writeDate();
                writeUserSpecific.writeString("FPS: " + newFPS + "\n");

                MainActivity.changeFPS(newFPS);
            }
        };
        timer.scheduleAtFixedRate(runPrediction, INTERVAL, INTERVAL);

    }

    public void onDestroy(){
        String start = sharedPreferences.getString("USER_START", "");
        Date now = Calendar.getInstance().getTime();
        String end = MainActivity.format.format(now);

        writeUserSpecific.writeString("Last session started at " + start + "\n");
        writeUserSpecific.writeString("Last session ended at " + end + "\n");

        writeUserSpecific.close();
        timer.cancel();
        unregisterReceiver(drp);

    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
