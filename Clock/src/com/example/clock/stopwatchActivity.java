package com.example.clock;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Rusty on 3/3/2016.
 */
public class stopwatchActivity extends Activity {

    private String class_name; // String for class name for debugging purposes
    private TextView showtime = null; // Textview used to actually display the time
    private Stopwatch my_time = null; // Decleration of a variable with type Timer (see Timer class)

    /*
	 * Handler is used to update the timer display in real time in a seperate thread then the main thread.
	 * Handler is used so that the main thread does not become blocked and unresponsive.
	 */
    private Handler handler =null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        //instance of the stopwatch
        final Stopwatch my_time = new Stopwatch();

        //set up buttons
        Button startButton = (Button)findViewById(R.id.startButton);
        Button resetButton = (Button)findViewById(R.id.resetButton);

        //set up text to display time
        TextView showtime = (TextView) findViewById(R.id.stopwatchTime);

        //set up startButton listener
        startButton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        my_time.setUpdate(true);
                        // This starts queuing call to the run method which runs until removeHandler is called in onDestroy
                        callHandler();
                    }
                }
        );

        //set up resetButton listener
        resetButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        my_time.Reset();
                    }
                }
        );



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
                handler.postDelayed(this, 1000);
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
        updateTimer my_update = new updateTimer(); // Create a new instance of the update_timer
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

}