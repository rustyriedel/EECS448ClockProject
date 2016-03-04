package com.example.clock;

/**
 * actualTimer is the helper class for the timer function.
 * @author Sierra Seacat
 * @version first deployment
 * @since 1.0
 */
public class actualTimer {

    //stores time values
    private int current_hour;
    private int current_minute;
    private int current_second;

    //controls whether the timer is running or not true = run, false = paused
    private boolean run;


    /**
     * This constructor initalizes the class variables <code>current_second, current hour, current minute</code> to 0.
     * This constructor will be called in onCreate() of the timer activity
     * @since       1.0
     */
    public actualTimer(){

        current_hour = 0;
        current_minute = 0;
        current_second = 0;
        run=false;
    }
    //getters
    /**
     *
     * @return int for current_hour
     */
    public int getHour() {
        return current_hour;
    }

    /**
     *
     * @return int for minutes
     */
    public int getMinute() {
        return current_minute;
    }

    /**
     *
     * @return int for seconds
     */
    public int getSecond() {
        return current_second;
    }

    /**
     *
     * @return boolean run
     */
    public boolean getRun(){
        return run;
    }

    //setters

    /**
     *
     * @param h int, the hour the user sets current_hour.
     */
    public void setHour(int h){
        current_hour = h;

    }

    /**
     *
     * @param m int, the minute value the user sets current_minute.
     */
    public void setMinute(int m){
        current_minute = m;
    }


    /**
     *
     * @param s int, the second value the user sets current_second to.
     */
    public void setSecond(int s){
        current_second = s;
    }

    /**
     *
     * @param r boolean, controls whether the clock runs or not. true = run, false = pause
     */
    public void setRun(boolean r){
        run = r;
    }

    /**
     * resets all of the time values to zero and run to false
     */
    public void reset() {
        current_hour = 0;
        current_minute = 0;
        current_second = 0;
        run = false;
    }

    /**
     * updates the timer, decreasing by 1 s, every 1000 ms
     * @param previousHour int the value for the current hour which will be incremented
     * @param previousMinute int the value for the current minute,
     * @param previousSecond int the value for the current second
     * @return timerValue int[] an array that contains the updated hour, minutes, and seconds values
     */
    public int[] updateTimer(int previousHour, int previousMinute, int previousSecond) {
        int[] timerValue = new int[3];
        int nextHour;
        int nextMinute;
        int nextSecond;

        nextHour = previousHour;
        nextMinute = previousMinute;
        nextSecond = previousSecond;

        //if the timer is at zero, don't run anymore
        if (previousHour == 0 && previousMinute == 0 && previousSecond == 0) {
            run = false;
        }
        //increment timer if run is true
        if (run == true) {

            //decrease the second value by 1, if it's at 0, loop to 59
            if (previousSecond != 0) {
                nextSecond = previousSecond-1;
            } else if (previousSecond == 0) {
                nextSecond = 59;

                //decrease minute value by 1, if it's at 0, loop to 59
                if(previousMinute!=0){
                    nextMinute=previousMinute-1;
                }
                else if(previousMinute==0){
                    nextMinute=59;

                    //decrease hour value by 1
                    nextHour=previousHour-1;
                }

            }



        }

        //store new values in the array
        timerValue[0]=nextHour;
        timerValue[1]=nextMinute;
        timerValue[2]=nextSecond;
        return(timerValue);


    }

    /**
     *
     * @return String dispalying the formated time
     */
    public String timerDisplay(){
        String display;

        //calls updateTimer to increment the time
        int[] timerValue = updateTimer(current_hour, current_minute, current_second);

        //sets current values to the updated values
        current_hour=timerValue[0];
        current_minute=timerValue[1];
        current_second=timerValue[2];

        //formats the display string
        display= String.format("%d", current_hour) + ":" + String.format("%02d", current_minute) + ":" + String.format("%02d", current_second);
        return(display);

    }
}
