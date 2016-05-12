package edu.northwestern.framerate;

/**
 * Created by B on 5/5/2016.
 */
public class PredictionStatic {
    public static int staticPredictor(DataCollector dc){
        if(dc.frame_rate <=40){
            if(dc.battery <= 0.42)
                return 1;
            else{
                if(dc.battery <= 0.53)
                    return 0;
                else{
                    if(dc.accel <= -0.618256){
                        if(dc.frame_rate <= 30)
                            return 0;
                        else{
                            if(dc.battery <= 0.9){
                                if(dc.battery <= 0.88){
                                    if(dc.ambientLight <= 27){
                                        if(dc.battery <= 0.78){
                                            if(dc.frame_rate <= 35)
                                                return 1;
                                            else
                                                return 0;
                                        }
                                        else{
                                            if(dc.ambientLight <= 20)
                                                return 0;
                                            else{
                                                if(dc.frame_rate <=35)
                                                    return 0;
                                                else
                                                    return 1;
                                            }
                                        }
                                    }
                                    else
                                        return 0;
                                }
                                else
                                    return 1;
                            }
                            else
                                return 0;
                        }
                    }
                    else{
                        if(dc.ambientLight <= 149){
                            if(dc.ambientLight <= 33){
                                if(dc.battery <= 0.94){
                                    if(dc.battery <=0.93){
                                        if(dc.battery <= 0.90){
                                            if(dc.battery <= 0.56){
                                                if(dc.accel <= 2.298889)
                                                    return 0;
                                                else
                                                    return 1;
                                            }
                                            else{
                                                if(dc.battery <= 0.59)
                                                    return 0;
                                                else{
                                                    if(dc.battery <= 0.61){
                                                        if(dc.frame_rate <= 35){
                                                            if(dc.ambientLight <= 3)
                                                                return 1;
                                                            else
                                                                return 0;
                                                        }
                                                        else
                                                            return 1;
                                                    }
                                                    else{
                                                        if(dc.frame_rate <= 35){
                                                            if(dc.battery <= 0.64)
                                                                return 0;
                                                            else{
                                                                if(dc.battery <= 0.67)
                                                                    return 1;
                                                                else{
                                                                    if(dc.battery <= 0.89){
                                                                        if(dc.accel <= 6.956085){
                                                                            if(dc.accel <= 6.554993){
                                                                                if(dc.battery <= 0.8){
                                                                                    if(dc.battery <= 0.7)
                                                                                        return 0;
                                                                                    else{
                                                                                        if(dc.ambientLight <= 4){
                                                                                            if(dc.cpuFreq <= 1188000){
                                                                                                if(dc.frame_rate <= 30)
                                                                                                    return 0;
                                                                                                else{
                                                                                                    if(dc.cpuUtil <= 34.58445)
                                                                                                        return 1;
                                                                                                    else
                                                                                                        return 0;
                                                                                                }
                                                                                            }
                                                                                            else
                                                                                                return 1;
                                                                                        }
                                                                                        else
                                                                                            return 1;
                                                                                    }
                                                                                }
                                                                                else{
                                                                                    if(dc.ambientLight <= 22){
                                                                                        if(dc.cpuUtil <= 11.927044){
                                                                                            if(dc.ambientLight <= 9)
                                                                                                return 0;
                                                                                            else{
                                                                                                if(dc.battery <= 0.82)
                                                                                                    return 0;
                                                                                                else{
                                                                                                    if(dc.accel <= 6.15509){
                                                                                                        if(dc.ambientLight <= 21){
                                                                                                            if(dc.cpuFreq <= 1026000)
                                                                                                                return 1;
                                                                                                            else{
                                                                                                                if(dc.cpuUtil <= 2.712351){
                                                                                                                    if(dc.cpuUtil <= -0.008477){
                                                                                                                        if(dc.frame_rate <= 30)
                                                                                                                            return 1;
                                                                                                                        else{
                                                                                                                            if(dc.ambientLight <= 18)
                                                                                                                                return 0;
                                                                                                                            else
                                                                                                                                return 1;
                                                                                                                        }
                                                                                                                    }
                                                                                                                    else
                                                                                                                        return 0;
                                                                                                                }
                                                                                                                else
                                                                                                                    return 1;
                                                                                                            }
                                                                                                        }
                                                                                                        else{
                                                                                                            if(dc.frame_rate <= 30)
                                                                                                                return 0;
                                                                                                            else
                                                                                                                return 1;
                                                                                                        }
                                                                                                    }
                                                                                                    else{
                                                                                                        if(dc.frame_rate <= 30)
                                                                                                            return 1;
                                                                                                        else
                                                                                                            return 0;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                        else{
                                                                                            if(dc.frame_rate <= 30){
                                                                                                if(dc.ambientLight <= 2){
                                                                                                    if(dc.cpuFreq <= 1134000)
                                                                                                        return 0;
                                                                                                    else
                                                                                                        return 1;
                                                                                                }
                                                                                                else
                                                                                                    return 0;
                                                                                            }
                                                                                            else{
                                                                                                if(dc.ambientLight <= 19)
                                                                                                    return 0;
                                                                                                else
                                                                                                    return 1;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                    else
                                                                                        return 0;
                                                                                }
                                                                            }
                                                                            else{
                                                                                if(dc.ambientLight <= 13)
                                                                                    return 1;
                                                                                else
                                                                                    return 0;
                                                                            }
                                                                        }
                                                                        else{
                                                                            if(dc.frame_rate <= 30){
                                                                                if(dc.battery <= 0.77)
                                                                                    return 0;
                                                                                else
                                                                                    return 1;
                                                                            }
                                                                            else
                                                                                return 0;
                                                                        }
                                                                    }
                                                                    else
                                                                        return 1;
                                                                }
                                                            }
                                                        }
                                                        else{
                                                            if(dc.battery <= 0.83)
                                                                return 0;
                                                            else{
                                                                if(dc.battery<= 0.88)
                                                                    return 1;
                                                                else
                                                                    return 0;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        else{
                                            if(dc.frame_rate <= 35){
                                                if(dc.accel <= 5.116058)
                                                    return 1;
                                                else{
                                                    if(dc.battery <= 0.92)
                                                        return 1;
                                                    else
                                                        return 0;
                                                }
                                            }
                                            else{
                                                if(dc.ambientLight <= 19)
                                                    return 0;
                                                else
                                                    return 1;
                                            }
                                        }
                                    }
                                    else
                                        return 0;
                                }
                                else{
                                    if(dc.frame_rate <= 35)
                                        return 0;
                                    else{
                                        if(dc.ambientLight <= 5)
                                            return 0;
                                        else
                                            return 1;
                                    }
                                }
                            }
                            else{
                                if(dc.battery <= 0.92){
                                    if(dc.battery <= 0.89){
                                        if(dc.accel <= 0.268433){
                                            if(dc.frame_rate <= 35){
                                                if(dc.accel <= 0.156555)
                                                    return 0;
                                                else{
                                                    if(dc.ambientLight <= 143){
                                                        if(dc.frame_rate <= 30){
                                                            if(dc.battery <= 0.71){
                                                                if(dc.accel <= 0.206543){
                                                                    if(dc.battery <= 0.58)
                                                                        return 0;
                                                                    else{
                                                                        if(dc.battery <= 0.65)
                                                                            return 1;
                                                                        else
                                                                            return 0;
                                                                    }
                                                                }
                                                                else
                                                                    return 0;
                                                            }
                                                            else{
                                                                if(dc.battery <= 0.88){
                                                                    if(dc.battery <= 0.76)
                                                                        return 1;
                                                                    else{
                                                                        if(dc.battery <= 0.84)
                                                                            return 0;
                                                                        else
                                                                            return 1;
                                                                    }
                                                                }
                                                                else
                                                                    return 0;
                                                            }
                                                        }
                                                        else{ //FRAMERATE >30
                                                            if(dc.battery <= 0.84){
                                                                if(dc.battery <= 0.78){
                                                                    if(dc.ambientLight <= 131)
                                                                        return 1;
                                                                    else{
                                                                        if(dc.cpuFreq <= 1242000)
                                                                            return 1;
                                                                        else
                                                                            return 0;
                                                                    }
                                                                }
                                                                else
                                                                    return 0;
                                                            }
                                                            else
                                                                return 1;
                                                        }
                                                    }
                                                    else{
                                                        if(dc.battery <= 0.59){
                                                            if(dc.accel <= 0.231537)
                                                                return 0;
                                                            else{
                                                                if(dc.cpuFreq <= 702000)
                                                                    return 1;
                                                                else
                                                                    return 0;
                                                            }
                                                        }
                                                        else{
                                                            if(dc.battery <= 0.67)
                                                                return 1;
                                                            else
                                                                return 0;
                                                        }
                                                    }
                                                }
                                            }
                                            else{
                                                if(dc.gpuCLK <= 200000000){
                                                    if(dc.battery <= 0.65){
                                                        if(dc.battery <= 0.63)
                                                            return 0;
                                                        else
                                                            return 1;
                                                    }
                                                    else
                                                        return 0;
                                                }
                                                else
                                                    return 0;
                                            }
                                        }
                                        else
                                            return 0;
                                    }
                                    else
                                        return 1;
                                }
                                else
                                    return 0;
                            }
                        }
                        else{
                            if(dc.frame_rate <= 35){
                                if(dc.accel <= 0.166077){
                                    if(dc.gpuCLK <= 200000000)
                                        return 0;
                                    else{
                                        if(dc.battery <= 0.88)
                                            return 1;
                                        else
                                            return 0;
                                    }
                                }
                                else{
                                    if(dc.frame_rate <= 30){
                                        if(dc.accel <= 0.199402)
                                            return 1;
                                        else{
                                            if(dc.battery <= 0.65){
                                                if(dc.ambientLight <= 221){
                                                    if(dc.ambientLight<= 198)
                                                        return 0;
                                                    else
                                                        return 1;
                                                }
                                                else
                                                    return 0;
                                            }
                                            else
                                                return 0;
                                        }
                                    }
                                    else
                                        return 1;
                                }
                            }
                            else{
                                if(dc.battery <= 0.67){
                                    if(dc.battery <= 0.63)
                                        return 0;
                                    else
                                        return 1;
                                }
                                else
                                    return 0;
                            }
                        }
                    }
                }
            }
        }
        else{
            if(dc.accel <= 0.230347)
                return 0;
            else{
                if(dc.battery <= 0.8){
                    if(dc.accel <= 8.146271)
                        return 0;
                    else{
                        if(dc.frame_rate <= 45){
                            if(dc.battery <= 0.72)
                                return 1;
                            else
                                return 0;
                        }
                        else
                            return 0;
                    }
                }
                else{
                    if(dc.accel <= 0.270813){
                        if(dc.ambientLight<= 25)
                            return 0;
                        else
                            return 1;
                    }
                    else{
                        if(dc.battery <= 0.85){
                            if(dc.ambientLight<= 6){
                                if(dc.ambientLight <= 5){
                                    if(dc.accel<= 5.464783)
                                        return 1;
                                    else
                                        return 0;
                                }
                                else{
                                    if(dc.frame_rate <= 50){
                                        if(dc.accel <= 4.405518)
                                            return 1;
                                        else{
                                            if(dc.accel <= 6.703766)
                                                return 1;
                                            else
                                                return 0;
                                        }
                                    }
                                    else{
                                        if(dc.battery <= 0.84)
                                            return 1;
                                        else
                                            return 0;
                                    }
                                }
                            }
                            else{
                                if(dc.accel <= 6.458588)
                                    return 0;
                                else{
                                    if(dc.frame_rate <= 45)
                                        return 0;
                                    else{
                                        if(dc.ambientLight <= 11)
                                            return 1;
                                        else{
                                            if(dc.frame_rate <= 50)
                                                return 1;
                                            else
                                                return 0;
                                        }
                                    }
                                }
                            }
                        }
                        else
                            return 0;
                    }
                }
            }
        }
    }
}
