package com.example.clock;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.NumberPicker;
import android.widget.TextView;

/**
 * Created by Rusty on 3/3/2016.
 */
public class calendarActivity extends Activity {

    private NumberPicker daySelector = null;
    private NumberPicker monthSelector = null;
    private TextView dayOfWeek = null;
    private int month = 0;
    private int day = 0;
    private Handler handler = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        daySelector = (NumberPicker) findViewById(R.id.dayOfMonth);
        daySelector.setMaxValue(31);
        daySelector.setMinValue(1);

        monthSelector = (NumberPicker) findViewById(R.id.theMonth);
        monthSelector.setMaxValue(11);
        monthSelector.setMinValue(0);
        monthSelector.setDisplayedValues(new String[]{"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"});



        dayOfWeek = (TextView) findViewById(R.id.dayOfWeek);
        dayOfWeek.setText("Friday"); //Default for January 1
        monthSelector.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                month = newVal;
                dayOfWeek.setText(getDayOfWeek(month,day));
            }
        });

        daySelector.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                day = newVal;
                dayOfWeek.setText(getDayOfWeek(month,day));
            }
        });



    }

    public String getDayOfWeek(int theMonth, int theDay ){
        String wkDay = "";
        int[] array = {31,29,31,30,31,30,31,31,30,31,30,31};
        int numDays = theDay-1;


        if((theMonth == 1 && theDay >29) || (theMonth == 3 && theDay > 30 ) || (theMonth == 5 && theDay >30)
                || (theMonth == 8 && theDay > 30) || (theMonth == 10 && theDay > 30)){
            return "Invalid";
        }
        if(theMonth > 0){
            for(int i=0; i<theMonth; i++){
                numDays += array[i];
            }
        }
        int days = numDays % 7;
        switch (days){
            case 0: wkDay = "Friday";
                break;
            case 1: wkDay = "Saturday";
                break;
            case 2: wkDay = "Sunday";
                break;
            case 3: wkDay = "Monday";
                break;
            case 4: wkDay = "Tuesday";
                break;
            case 5: wkDay = "Wednesday";
                break;
            case 6: wkDay = "Thursday";
        }

        return wkDay;
    }

}
