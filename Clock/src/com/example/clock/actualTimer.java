package com.example.clock;

/**
 * Created by Sierra on 3/2/2016.
 */
public class actualTimer {

    private int current_hour;
    private int current_minute;
    private int current_second;
    private boolean run;

    public actualTimer(int hours, int minutes, int seconds){

        current_hour = 0;
        current_minute = 0;
        current_second = 0;
        run=false;
    }

    public int getHour() {
        return current_hour;
    }

    public int getMinute() {
        return current_minute;
    }

    public int getSecond() {
        return current_second;
    }

    public boolean getRun(){
        return run;
    }

    public void setHour(int h){
        current_hour = h;

    }

    public void setMinute(int m){
        current_minute = m;
    }

    public void setSecond(int s){
        current_second = s;
    }
    public void setRun(boolean r){
        run = r;
    }

    public void reset() {
        current_hour = 0;
        current_minute = 0;
        current_second = 0;
        run = false;
    }

    public int[] updateTimer(int previousHour, int previousMinute, int previousSecond) {
        int[] timerValue = new int[3];
        int nextHour;
        int nextMinute;
        int nextSecond;

        if (previousHour == 0 && previousMinute == 0 && previousSecond == 0) {
            run = false;
        } else {
            run = true;
        }

        if (run == true) {


            nextHour = previousHour;
            nextMinute = previousMinute;
            nextSecond = previousSecond;

            if (previousSecond != 0) {
                nextSecond = previousSecond-1;
            } else if (previousSecond == 0) {
                nextSecond = 59;

                if(previousMinute!=0){
                    nextMinute=previousMinute-1;
                }
                else if(previousMinute==0){
                    nextMinute=59;

                    nextHour=previousHour-1;
                }

            }



        }
        else{
            nextHour=0;
            nextMinute=0;
            nextSecond=0;
        }
        timerValue[0]=nextHour;
        timerValue[1]=nextMinute;
        timerValue[2]=nextSecond;
        return(timerValue);


    }

    public String timerDisplay(){
        String display;
        int[] timerValue = updateTimer(current_hour, current_minute, current_second);

        current_hour=timerValue[0];
        current_minute=timerValue[1];
        current_second=timerValue[2];

        display= String.format("%d", current_hour) + ":" + String.format("%02d", current_minute) + ":" + String.format("%02d", current_second);
        return(display);

    }
}
