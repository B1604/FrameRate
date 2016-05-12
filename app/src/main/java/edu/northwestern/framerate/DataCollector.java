package edu.northwestern.framerate;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by B on 3/30/2016.
 */
public class DataCollector {
    private DataCollectorCPU dcCPU;
    private DataCollectorGPU dcGPU;
    private DataCollectorAccelerometer dcAccel;
    private DataCollectorAmbientLight dcAmbient;
    private DataCollectorBattery dcBatt;

    private Context context;
    //Location and Sunlight data collectors are removed.
    //Display Contents also removed.
    //Screen State also removed.
    //Context also removed.

    public float accel;
    public double ambientLight, battery, cpuFreq, cpuUtil, gpuCLK;
    public int frame_rate;

    public SharedPreferences sharedPreferences;

    public DataCollector(Context c){
        context = c;
        dcAccel = new DataCollectorAccelerometer(context);
        dcAmbient = new DataCollectorAmbientLight(context);
        dcCPU = new DataCollectorCPU(context);
        dcGPU = new DataCollectorGPU(context);
        dcBatt = new DataCollectorBattery(context);
        accel = 0;
        ambientLight = 0;
        battery = 0;
        cpuFreq = 0;
        cpuUtil = 0;
        gpuCLK = 0;
        frame_rate = 0;
        sharedPreferences = c.getSharedPreferences(MainActivity.MYPREFS, Context.MODE_PRIVATE);
    }

    public void collectAll(){
        accel = dcAccel.getAcceleration();

        ambientLight = dcAmbient.getAmbientLight();
        battery = dcBatt.getBatteryLevel();
        cpuFreq = dcCPU.getFreq();
        cpuUtil = dcCPU.getUtilization();
        gpuCLK = dcGPU.getGPUclk();
        frame_rate = sharedPreferences.getInt("FPS", 0);

    }
}
