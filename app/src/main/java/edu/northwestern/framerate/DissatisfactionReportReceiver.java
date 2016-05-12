package edu.northwestern.framerate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by B on 5/6/2016.
 */
public class DissatisfactionReportReceiver extends BroadcastReceiver {
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    @Override
    public void onReceive(Context context, Intent intent) {
        sharedPreferences = context.getSharedPreferences(MainActivity.MYPREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        int mode = sharedPreferences.getInt("MODE", 0);

        if(mode == 1){
            ServiceStatic.writeStatic.writeDate();
            ServiceStatic.writeStatic.writeString("FPS: "+ MainActivity.DEFAULT_FPS + "\n");

            editor.putInt("FPS", MainActivity.DEFAULT_FPS);
            editor.commit();
            MainActivity.changeFPS(MainActivity.DEFAULT_FPS);
        }
        else if(mode == 3){
            ServiceUser.writeUserSpecific.writeDate();
            ServiceUser.writeUserSpecific.writeString("FPS: "+ MainActivity.DEFAULT_FPS + "\n");

            editor.putInt("FPS", MainActivity.DEFAULT_FPS);
            editor.commit();
            MainActivity.changeFPS(MainActivity.DEFAULT_FPS);
        }

    }
}
