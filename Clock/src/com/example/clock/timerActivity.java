package com.example.clock;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

/**
 * timerActivity is the activity that gives a interfaces for using the
 * actualTimer class written by Sierra Seacat.
 */
public class timerActivity extends Activity {

    private String class_name; // String for class name for debugging purposes
    private TextView showtime = null; // Textview used to actually display the time
    private actualTimer my_time = null; // Decleration of a variable with type Timer (see Timer class)
    private NumberPicker hourPicker = null; // Swipable interface for setting the hours
    private NumberPicker minutePicker = null; // Swipable interface for setting the minutes
    private NumberPicker secondPicker = null; // Swipable interface for setting the seconds
    private Button startButton = null; // Button for starting the timer
    private Button pauseButton = null; // Button for pausing the timer
    private Button resetButton = null; // Button for resetting the timer
    private Button zoomButton = null; // Button for zooming the timer display
    private Button displayButton = null; // Button for turning off the timer display
    private boolean zoomFlag = false; // Boolean flag that holds the status of the display zoom

    /*
    * Handler is used to update the timer display in real time in a seperate thread then the main thread.
    * Handler is used so that the main thread does not become blocked and unresponsive.
    */
    private Handler handler =null;

    /**
     * onCreate is called when the activity is first loaded. We initialize all variables and start the Handler.
     *
     * @param savedInstanceState     Default parameter set by android
     * @since           1.0
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        //instance of the stopwatch
        my_time = new actualTimer();

        //set up buttons
        startButton = (Button)findViewById(R.id.startTimerButton);
        resetButton = (Button)findViewById(R.id.resetTimerButton);
        pauseButton = (Button)findViewById(R.id.pauseTimerButton);
        zoomButton = (Button)findViewById(R.id.zoomTimerButton);
        displayButton = (Button)findViewById(R.id.displayTimerButton);

        //set up text to display time
        showtime = (TextView) findViewById(R.id.realTimer);

        // Initialize swipable hour picker
        hourPicker = (NumberPicker) findViewById(R.id.numberPickerTimerHour);
        hourPicker.setMaxValue(24);

        // Initialize swipable minute picker
        minutePicker = (NumberPicker) findViewById(R.id.numberPickerTimerMin);
        minutePicker.setMaxValue(59);

        // Initialize swipable second picker
        secondPicker = (NumberPicker) findViewById(R.id.numberPickerTimerSec);
        secondPicker.setMaxValue(59);

        // This sets the hours using the setHour method of the actualTimer class when the user swipes to a new hour
        hourPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                my_time.setHour(newVal);
            }
        });

        // This sets the minutes using the setMinutes method of the actualTimer class when the user swipes to a new minute
        minutePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                my_time.setMinute(newVal);

            }
        });

        // This sets the seconds using the setSeconds method of the actualTimer class when the user swipes to a new second
        secondPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                my_time.setSecond(newVal);

            }
        });


        // This starts queing call to the run method which runs until removeHandler is called in onDestroy
        callHandler();


        //Debug message
        Log.d(class_name, "Creating Timer Activity");

    }

    /**
     * This class implements the run method from the Runnable interface. This class allows the Handler class to queue calls to the run method, so that the timer display can be updated in a seperate thread.
     * @version    First Deployment
     * @since       1.0
     */
    class updateActualTimer implements Runnable {

        /**
         * Implements the run method of the runnable interface
         * Sets the time in a textbox and on teh swipable hours, minutes, and seconds UI features. Then, delays one second.
         * Uses methods from Timer.java to display time and update time
         * @see             Timer.java
         * @since           1.0
         */
        public void run() {
            showtime.setText(my_time.timerDisplay()); // Set the text display by using the display method of the Stopwatch class
            hourPicker.setValue(my_time.getHour()); // Set the swipable hourPicker to the current hour
            minutePicker.setValue(my_time.getMinute()); // Set the swipable hourPicker to the current minute
            secondPicker.setValue(my_time.getSecond()); // Set the swipable hourPicker to the current second
            if(my_time.getHour()==0&&my_time.getMinute()==0&&my_time.getSecond()==0){
                showtime.setTextColor(Color.RED);
            }
            else{
                showtime.setTextColor(Color.BLACK);
            }

            if(handler != null) { // If the handler is not null we delay one full second and then update the time again
                handler.postDelayed(this, 1000);
            }
        }
    }

    private updateActualTimer my_update = null; // Instace of the updateTimer class

    /**
     * Creates an instance of the Handler Class and updateTimer class.
     * Queues calls to the run method from the updateTimer class every one second.
     * @see             updateTimer
     * @since           1.0
     */
    public void callHandler() {
        handler = new Handler(); // Create a new instance of the handler
        my_update = new updateActualTimer(); // Create a new instance of the update_timer
        handler.postDelayed(my_update,1000); // Queue calls to the run method every 1 second
    }

    /**
     * Destroys all calls to the run method in the Handler Queue. Resets the Handler class to point at null.
     * @see             updateTimer
     * @since           1.0
     */
    public void removeHandler() {
        if(handler != null) { // If the handler is not null
            handler.removeCallbacks(my_update); // Remove any queued calls to run //TODO this is broken.
            handler =null; // Set the handler object to null
        }
    }


    // Sets the button callbacks.
    public void startTimerClicked(View view){
        my_time.setRun(true);
    }
    public void pauseTimerClicked(View view){
        my_time.setRun(false);
    }
    public void resetTimerClicked(View view){
        my_time.reset();
    }

    /**
     * Method created automatically by android
     *
     * @since           1.0
     */
    @Override
    protected void onStart() {
        super.onStart(); // Make sure the code in the parent class is used
        Log.d(class_name, "Starting timer Activity"); // Log debug message
    }

    /**
     * Method created automatically by android
     *
     * @since           1.0
     */
    @Override
    protected void onResume() {
        super.onResume(); // Call code of parent class
        Log.d(class_name, "Resuming timer Activity"); // Log debug message
    }

    /**
     * Method created automatically by android
     *
     * @since           1.0
     */
    @Override
    protected void onPause() {
        super.onPause(); // Call parent code
        Log.d(class_name, "Pausing timer Activity"); // Log debug message
    }

    /**
     * Method created automatically by android
     *
     * @since           1.0
     */
    protected void onStop() {
        super.onStop(); // Call parent code
        Log.d(class_name, "Stopping timer Activity"); // Log debug message
    }

    /**
     * Method created automatically by android. Call removeHandler() to destroy the Handler
     *
     * @see removeHandler()
     * @since           1.0
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeHandler(); // Destroy handler and any remaining calls to run when the activity is destroyed
        Log.d(class_name, "Destroying timer Activity "); // Log debug message
    }

    /**
     * Constructor used to store the class name
     * @since           1.0
     */
    public timerActivity() {

        class_name = getClass().getName(); // Store the class name

    }

    /**
     * Creates an intent for the displayOffActivity, and then requests
     * that android start the activity by calling startActivity with the
     * intent as its parameter.
     *
     * @param view
     */
    public void startDisplayOffActivity(View view){
        Intent displayOffIntent = new Intent(".displayOffActivity");
        startActivity(displayOffIntent);
    }

    /**
     * Uses setTextSize to change the font size of each element in the
     * layout individually. if the zoomFlag is true, it is in the zoom
     * state, false is non-zoomed state.
     *
     * @param view
     */
    public void zoomText(View view){
        if(zoomFlag == false){
            showtime.setTextSize(TypedValue.COMPLEX_UNIT_SP, 85);
            startButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
            pauseButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
            resetButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
            zoomButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
            displayButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
            zoomFlag = true;
        }
        else{
            showtime.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
            startButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            pauseButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            resetButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            zoomButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            displayButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            zoomFlag = false;
        }
    }

}
