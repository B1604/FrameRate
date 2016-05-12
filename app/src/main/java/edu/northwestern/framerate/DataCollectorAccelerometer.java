package edu.northwestern.framerate;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by B on 3/30/2016.
 */
public class DataCollectorAccelerometer {
    public Context context;
    private float acceleration;

    public DataCollectorAccelerometer(Context context) {
        this.context = context;
        startSensor();
    }

    private void startSensor() {
        SensorManager sensorManager =(SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
                    acceleration = event.values[0];
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                //
            }
        };
        sensorManager.registerListener(sensorEventListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    public float getAcceleration(){
        return acceleration;
    }
}
