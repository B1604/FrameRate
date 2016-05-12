package edu.northwestern.framerate;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by B on 3/31/2016.
 */
public class Writers {
    public static final String LOG_DEFAULT = "Default.txt";
    public static final String LOG_STATIC = "Static.txt";
    public static final String LOG_LEARNING = "Learning.txt";
    public static final String LOG_USER = "UserSpecific.txt";
    public static final String LOG_PATH = "/storage/sdcard0/FrameRate/";
    private File path, file;
    private FileOutputStream fos;
    private static OutputStreamWriter osw;
    private Context context;

    public Writers(Context c, int mode){
        try {
            context = c;
            path = new File(LOG_PATH);

            if(!path.exists()){
                path.mkdirs();
            }

            if(mode == 0){
                file = new File(path, LOG_DEFAULT);
            }
            else if(mode == 1){
                file = new File(path, LOG_STATIC);
            }
            else if(mode == 2){
                file = new File(path, LOG_LEARNING);
            }
            else if(mode == 3){
                file = new File (path, LOG_USER);
            }

            if (!file.exists()){
                file.createNewFile();
            }

            fos = new FileOutputStream(file, true);
            osw = new OutputStreamWriter(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeString(String data){
        try {
            osw.append(data);
            osw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeDate(){
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);
        try {
            osw.append(reportDate + " - ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeDissatisfaction(){
        try {
            osw.append("USER IS DISSATISFIED!!!\n");
            osw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeCollectedData(DataCollector dc){
        String data = dc.accel + " " + dc.ambientLight + " " + dc.battery + " " + dc.cpuFreq + " "
                + dc.cpuUtil + " " + dc.gpuCLK + " " + dc.frame_rate + "\n";
        writeString(data);
    }

    public void close(){
        try {
            osw.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
