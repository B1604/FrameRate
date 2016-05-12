package edu.northwestern.framerate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public Context context;
    public Button start_default, stop_default, start_static, stop_static;
    public Button start_learning, stop_learning, start_userSpec, stop_userSpec, dissatisfied;

    public static SharedPreferences sharedPrefs;
    public static SharedPreferences.Editor editor;
    public static final String MYPREFS = "MyPrefs";

    public static final int DEFAULT_FPS = 60;
    public static final int DEFAULT_CODE = 0;
    public static final int STATIC_CODE = 1;
    public static final int LEARNING_CODE = 2;
    public static final int USER_CODE = 3;
    public static final String TAG = "FRAME_APP";

    public static DateFormat format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "MainActivity created");
        context = getApplicationContext();

        start_default = (Button)findViewById(R.id.start_default);
        stop_default = (Button) findViewById(R.id.stop_default);
        start_static = (Button)findViewById(R.id.start_static);
        stop_static = (Button) findViewById(R.id.stop_static);
        start_learning = (Button) findViewById(R.id.start_learning);
        stop_learning = (Button) findViewById(R.id.stop_learning);
        start_userSpec = (Button) findViewById(R.id.start_userSpecific);
        stop_userSpec = (Button) findViewById(R.id.stop_userSpecific);
        dissatisfied = (Button)findViewById(R.id.dis_button);

        sharedPrefs = getSharedPreferences(MYPREFS, Context.MODE_PRIVATE);
        editor = sharedPrefs.edit();
        format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        start_default.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("MODE", DEFAULT_CODE);
                editor.commit();

                Date now = Calendar.getInstance().getTime();
                String start_time = format.format(now);
                editor.putString("DEFAULT_START", start_time);
                editor.commit();

                editor.putInt("FPS", DEFAULT_FPS);
                editor.commit();

                changeFPS(DEFAULT_FPS);
            }
        });
        stop_default.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(context, ServiceDefault.class));
                Log.i(TAG, "Default service stopped.");
            }
        });

        start_static.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("MODE", STATIC_CODE);
                editor.commit();

                Date now = Calendar.getInstance().getTime();
                String start_time = format.format(now);
                editor.putString("STATIC_START", start_time);
                editor.commit();

                Intent intentService = new Intent(MainActivity.this, ServiceStatic.class);
                context.startService(intentService);
                Log.i(TAG, "Static service should start now.");
            }
        });

        stop_static.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(context, ServiceStatic.class));
                Log.i(TAG, "Static service stopped.");
            }
        });

        start_learning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("MODE", LEARNING_CODE);
                editor.commit();

                Date now = Calendar.getInstance().getTime();
                String start_time = format.format(now);
                editor.putString("LEARNING_START", start_time);
                editor.commit();

                Intent intentService = new Intent(MainActivity.this, ServiceLearning.class);
                context.startService(intentService);
                Log.i(TAG, "Learning service should start now.");
            }
        });

        stop_learning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(context, ServiceLearning.class));
                Log.i(TAG, "Learning service stopped.");
            }
        });

        start_userSpec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putInt("MODE", USER_CODE);
                editor.commit();

                Date now = Calendar.getInstance().getTime();
                String start_time = format.format(now);
                editor.putString("USER_START", start_time);
                editor.commit();

                Intent intentService = new Intent(MainActivity.this, ServiceUser.class);
                context.startService(intentService);
                Log.i(TAG, "UserSpecific service should start now.");
            }
        });

        stop_userSpec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(context, ServiceUser.class));
                Log.i(TAG, "UserSpecific service stopped.");
            }
        });


        dissatisfied.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mode = sharedPrefs.getInt("MODE", 0);
                if(mode == 0){
                    ServiceDefault.writeDefault.writeDate();
                    ServiceDefault.writeDefault.writeDissatisfaction();
                }
                else if(mode == 1){
                    ServiceStatic.writeStatic.writeDate();
                    ServiceStatic.writeStatic.writeDissatisfaction();

                    Intent intent = new Intent("edu.northwestern.framerate.STATIC_DISSATISFACTION");
                    sendBroadcast(intent);
                }
                else if(mode == 2){
                    ServiceLearning.writeLearning.writeDate();
                    ServiceLearning.writeLearning.writeDissatisfaction();
                }
                else if(mode == 3){
                    ServiceUser.writeUserSpecific.writeDate();
                    ServiceUser.writeUserSpecific.writeDissatisfaction();

                    Intent intent = new Intent("edu.northwestern.framerate.USER_DISSATISFACTION");
                    sendBroadcast(intent);
                }
            }
        });
    }

    public static void changeFPS(int fps){
        try{
            Runtime.getRuntime().exec("adb shell");
            Process su = Runtime.getRuntime().exec("su");
            DataOutputStream oStream = new DataOutputStream(su.getOutputStream());

            oStream.writeBytes("setprop debug.gr.framerate " + fps + "\n");
            oStream.flush();

            oStream.writeBytes("stop\n");
            oStream.flush();

            oStream.writeBytes("start\n");
            oStream.flush();

            oStream.writeBytes("exit\n");
            oStream.flush();

            try{
                su.waitFor();
            }catch(InterruptedException ie){
                Log.e("", "Cannot execute su.waitFor()");
            }
        }catch (IOException io){
            Log.e("", "Cannot execute adb shell command");
        }
    }

}
