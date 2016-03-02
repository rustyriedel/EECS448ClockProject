package com.example.clock;

/**
 * Created by Sierra on 3/2/2016.
 */
public class actualTimer {

    private int hour;
    private int minute;
    private int second;
    private boolean run = true;

    public actualTimer(int hours, int minutes, int seconds){

        hour = hours;
        minute = minutes;
        second = seconds;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public boolean getRun(){
        return run;
    }

    public void setHour(int h){
        hour = h;

    }

    public void setMinute(int m){
        minute = m;
    }

    public void setSecond(int s){
        second = s;
    }
    public void setRun(boolean r){
        run = r;
    }

    public void reset() {
        hour = 0;
        minute = 0;
        second = 0;
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

    public String display(){
        String display;
        int[] timerValue = updateTimer(hour, minute, second);

        hour=timerValue[0];
        minute=timerValue[1];
        second=timerValue[2];

        display= String.format("%d", hour) + ":" + String.format("%02d", minute) + ":" + String.format("%02d", second);
        return(display);

    }
}
