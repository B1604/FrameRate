package edu.northwestern.framerate;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by B on 5/3/2016.
 */
public class ServiceStatic extends Service{
    private Context context;

    public static Writers writeStatic;
    public Timer timer;
    public int dissatisfaction;
    public DissatisfactionReportReceiver drp;
    public DataCollector dataCollector;
    public SharedPreferences sharedPrefs;
    public SharedPreferences.Editor editor;

    public final long INTERVAL = 120000;

    public void onCreate(){
        Log.i(MainActivity.TAG, "In Static Service now.");
        context = getApplicationContext();
        writeStatic = new Writers(context, MainActivity.STATIC_CODE);
        dissatisfaction = 0;
        dataCollector = new DataCollector(context);

        sharedPrefs = getSharedPreferences(MainActivity.MYPREFS, Context.MODE_PRIVATE);
        editor = sharedPrefs.edit();

        IntentFilter intentFilter = new IntentFilter("edu.northwestern.framerate.STATIC_DISSATISFACTION");
        drp = new DissatisfactionReportReceiver();
        registerReceiver(drp, intentFilter);

        timer = new Timer();
        TimerTask runPrediction = new TimerTask() {
            @Override
            public void run() {
                dataCollector.collectAll();
                dissatisfaction = PredictionStatic.staticPredictor(dataCollector);
                int currentFPS = sharedPrefs.getInt("FPS", 0);
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

                    writeStatic.writeDate();
                    writeStatic.writeString("No dissatisfaction, decreasing to "+ newFPS +"\n");
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

                    writeStatic.writeDate();
                    writeStatic.writeString("Dissatisfaction detected, increasing to "+ newFPS+ "\n");
                }
                editor.putInt("FPS", newFPS);
                editor.commit();

                writeStatic.writeDate();
                writeStatic.writeString("FPS: "+ newFPS+ "\n");

                MainActivity.changeFPS(newFPS);
            }
        };
        timer.scheduleAtFixedRate(runPrediction, INTERVAL, INTERVAL);

    }

    public void onDestroy(){
        String start = sharedPrefs.getString("STATIC_START", "");
        Date now = Calendar.getInstance().getTime();
        String end = MainActivity.format.format(now);

        writeStatic.writeString("Last session started at " + start+"\n");
        writeStatic.writeString("Last session ended at " + end + "\n");

        writeStatic.close();
        timer.cancel();
        unregisterReceiver(drp);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
//TODO: (D)Problems -> bunun problemi daha da karisik. Basliyor ama crush ediyor, file'i olusturuyor.