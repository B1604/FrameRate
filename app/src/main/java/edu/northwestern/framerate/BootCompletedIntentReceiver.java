package edu.northwestern.framerate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by B on 4/5/2016.
 */
public class BootCompletedIntentReceiver extends BroadcastReceiver {
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    @Override
    public void onReceive(Context context, Intent intent) {

        sharedPreferences = context.getSharedPreferences(MainActivity.MYPREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        int mode = sharedPreferences.getInt("MODE", 0);
        Intent intentService = new Intent();

        if("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())){
            if(mode == 0){
                intentService = new Intent(context, ServiceDefault.class);
            }
            else if(mode == 1){
                intentService = new Intent(context, ServiceStatic.class);
            }
            else if(mode == 2){
                intentService = new Intent(context, ServiceLearning.class);
            }
            else if(mode == 3){
                intentService = new Intent(context, ServiceUser.class);
            }
            context.startService(intentService);
        }
    }
}
