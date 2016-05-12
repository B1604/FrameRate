package edu.northwestern.framerate;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by B on 3/30/2016.
 */
public class DataCollectorAmbientLight {
    private Context context;
    private static double light;

    public DataCollectorAmbientLight(Context context) {
        this.context = context;
        SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        light = 0;

        SensorEventListener sensorListener = new SensorEventListener() {
            @Override
            public void onAccuracyChanged(Sensor arg0, int arg1) {
                // Null
            }

            @Override
            public void onSensorChanged(SensorEvent arg0) {
                if (arg0.sensor.getType() == Sensor.TYPE_LIGHT) {
                    if ((double) arg0.values[0] < 1) {
                        arg0.values[0] = 1;
                    }
                    light =arg0.values[0];
                }
            }
        };
        sensorManager.registerListener(sensorListener, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public double getAmbientLight(){
        return light;
    }
}
