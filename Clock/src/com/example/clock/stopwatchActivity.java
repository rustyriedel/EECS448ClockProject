package com.example.clock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * The stopwatchActivity is an activity that gives you an interface for using
 * the Stopwatch class written by Dillon Fruhwirth.
 */
public class stopwatchActivity extends Activity {

    //privata data memebers
    private String class_name; // String for class name for debugging purposes
    private TextView showtime = null; // Textview used to actually display the time
    private Stopwatch my_time = null; // Decleration of a variable with type Timer (see Timer class)
    private boolean zoomFlag = false; // Boolean flag to hold zoom status
    private Button startButton = null; // Button for starting the stopwatch
    private Button pauseButton = null; // Button for pausing the stopwatch
    private Button resetButton = null; // Button for resetting the stopwatch
    private Button zoomButton = null; // Button for zooming the stopwatch
    private Button displayButton = null; // Button for turning the display off


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
        setContentView(R.layout.activity_stopwatch);

        //instance of the stopwatch
        my_time = new Stopwatch();

        //set up buttons
        startButton = (Button)findViewById(R.id.startButton);
        resetButton = (Button)findViewById(R.id.resetButton);
        pauseButton = (Button)findViewById(R.id.pauseButton);
        zoomButton = (Button)findViewById(R.id.zoomStopwatchButton);
        displayButton = (Button)findViewById(R.id.displayStopwatchButton);

        //set up text to display time
        showtime = (TextView) findViewById(R.id.stopwatchTime);


        // This starts queing call to the run method which runs until removeHandler is called in onDestroy
        callHandler();


        //Debug message
        Log.d(class_name, "Creating stopwatch Activity");

    }

    /**
     * This class implements the run method from the Runnable interface. This class allows the Handler class to queue calls to the run method, so that the timer display can be updated in a seperate thread.
     * @version    First Deployment
     * @since       1.0
     */
    class updateTimer implements Runnable {

        /**
         * Implements the run method of the runnable interface
         * Sets the time in a textbox and on teh swipable hours, minutes, and seconds UI features. Then, delays one second.
         * Uses methods from Timer.java to display time and update time
         * @see             Timer.java
         * @since           1.0
         */
        public void run() {
            showtime.setText(my_time.display()); // Set the text display by using the display method of the Stopwatch class

            if(handler != null) { // If the handler is not null we delay one full second and then update the time again
                handler.postDelayed(this, 38);
            }
        }
    }

    private updateTimer my_update = null; // Instace of the updateTimer class

    /**
     * Creates an instance of the Handler Class and updateTimer class.
     * Queues calls to the run method from the updateTimer class every one second.
     * @see             updateTimer
     * @since           1.0
     */
    public void callHandler() {
        handler = new Handler(); // Create a new instance of the handler
        my_update = new updateTimer(); // Create a new instance of the update_timer
        handler.postDelayed(my_update,38); // Queue calls to the run method every 1 second
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

    // Set up Button callbacks.
    public void startClicked(View view){
        my_time.setUpdate(true);
    }
    public void pauseClicked(View view){
        my_time.setUpdate(false);
    }
    public void resetClicked(View view){
        my_time.Reset();
    }

    /**
     * Method created automatically by android
     *
     * @since           1.0
     */
    @Override
    protected void onStart() {
        super.onStart(); // Make sure the code in the parent class is used
        Log.d(class_name, "Starting stopwatch Activity"); // Log debug message
    }

    /**
     * Method created automatically by android
     *
     * @since           1.0
     */
    @Override
    protected void onResume() {
        super.onResume(); // Call code of parent class
        Log.d(class_name, "Resuming stopwatch Activity"); // Log debug message
    }

    /**
     * Method created automatically by android
     *
     * @since           1.0
     */
    @Override
    protected void onPause() {
        super.onPause(); // Call parent code
        Log.d(class_name, "Pausing stopwatch Activity"); // Log debug message
    }

    /**
     * Method created automatically by android
     *
     * @since           1.0
     */
    protected void onStop() {
        super.onStop(); // Call parent code
        Log.d(class_name, "Stopping stopwatch Activity"); // Log debug message
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
        Log.d(class_name, "Destroying stopwatch Activity "); // Log debug message
    }

    /**
     * Constructor used to store the class name
     * @since           1.0
     */
    public stopwatchActivity() {

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
            startButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
            pauseButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
            resetButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
            zoomButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
            displayButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
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
