package edu.northwestern.framerate;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by B on 3/30/2016.
 */
public class DataCollectorGPU {
    Context context;
    public static final String GPU_CLK_FILE = "/sys/class/kgsl/kgsl-3d0/gpuclk";

    public DataCollectorGPU(Context m){context = m;}

    public double getGPUclk(){
        String clk_value ="";
        try{
            FileReader fstream = new FileReader(GPU_CLK_FILE);
            BufferedReader bf = new BufferedReader(fstream, 500);

            clk_value = bf.readLine();
        }catch(Exception e){
            //
        }
        return Double.parseDouble(clk_value);
    }

}
