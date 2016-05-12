package edu.northwestern.framerate;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by B on 3/30/2016.
 */
public class DataCollectorCPU {
    Context context;
    public static final String FREQ_FILE = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq";
    public static final String STAT_FILE = "/proc/stat";
    private long mUser, mSystem, mTotal;

    public DataCollectorCPU(Context c){context = c;}

    public double getFreq(){
        String freq_value = "";
        try{
            RandomAccessFile reader = new RandomAccessFile(FREQ_FILE, "r");
            freq_value = reader.readLine();
            reader.close();
        }catch(IOException e){
            //
        }
        return Double.parseDouble(freq_value);
    }

    public double getUtilization(){
        String[] segs;
        double percentage =0;
        try{
            FileReader fstream = new FileReader(STAT_FILE);
            BufferedReader in = new BufferedReader(fstream, 500);
            String line;
            try{
                while((line = in.readLine())!= null){
                    if(line.startsWith("cpu")){
                        segs = line.trim().split("[ ]+");
                        Long user = Long.parseLong(segs[1]) + Long.parseLong(segs[2]);
                        Long system = Long.parseLong(segs[3])+ Long.parseLong(segs[6]) + Long.parseLong(segs[7]);
                        Long total = user + system + Long.parseLong(segs[4]) + Long.parseLong(segs[5]);

                        if(mTotal != 0 || total >= mTotal){
                            Long duser = user - mUser;
                            Long dsystem = system - mSystem;
                            Long dtotal = total - mTotal;

                            percentage = (double)(duser + dsystem)* 100.0 /dtotal;
                            mUser = duser;
                            mSystem = dsystem;
                            mTotal = dtotal;
                        }
                    }
                }
            }catch(IOException e){}
        }catch(FileNotFoundException e){}
        return percentage;
    }
}
//1 minute: web browser & game & readability
//TODO: every frame level will run twice.
