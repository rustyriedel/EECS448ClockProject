
package com.example.clock;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity; // Base class for android activities

import android.os.Bundle; // Class that maps string values

import android.os.Handler; // Handler class is used to run the timer in a seperate thread from the main thread

import android.util.Log; // Class imported for debugging messages

import android.util.TypedValue;
import android.view.Menu; // Default import for android menus
import android.view.MenuItem; // Default import for android menus

import android.view.View; // Class that is the base block for the user interface

import android.widget.NumberPicker; // Class used for swipable number pickers
import android.widget.RadioGroup;
import android.widget.TextView; // Class used for text view of timer
import android.widget.RadioButton; // Class used for radio buttons

/**
 * Main Activity  is the activity in which a timer is displayed, swipers allow the user to set the time, and radio buttons allow the user to swtich between 12/24 hour mdoe and AM/PM.
 * @author     Cesar Avalos
 * @author     John Russel
 * @author     Yarden Tamir
 * @author     Alec Knutsen
 * @version    First Deployment
 * @since       1.0
 */
public class MainActivity extends AppCompatActivity {

   // Boolean flag to keep the state of the zoomable text size.
   private boolean zoomFlag = false;

   private String class_name; // String for class name for debugging purposes

   private TextView showtime = null; // Textview used to actually display the time

   private NumberPicker hourPicker = null; // Swipable interface for setting the hours
   private NumberPicker minutePicker = null; // Swipable interface for setting the minutes
   private NumberPicker secondPicker = null; // Swipable interface for setting the seconds

   private RadioGroup am_pm = null; // Radio group to select am or pm
   private RadioGroup  twelve_twentyfour = null; // Radio group to select time mode
   private RadioButton radio_12 = null;
   private RadioButton radio_24 = null;
   private RadioButton radio_AM = null;
   private RadioButton radio_PM = null;

   private Timer my_time = null; // Decleration of a variable with type Timer (see Timer class)

   /*
    * Handler is used to update the timer display in real time in a seperate thread then the main thread.
    * Handler is used so that the main thread does not become blocked and unresponsive.
    */
   private Handler handler =null;

   // Calendar data
   private NumberPicker daySelector = null;
   private NumberPicker monthSelector = null;
   private TextView dayOfWeek = null;
   private int month = 0;
   private int day = 0;

   /**
    * This class implements the run method from the Runnable interface. This class allows the Handler class to queue calls to the run method, so that the timer display can be updated in a seperate thread.
    * @version    First Deployment
    * @since       1.0
    */
   class updateTimer implements Runnable {


      /**
       * Implemnts the run method of the runnable interface
       * Sets the time in a textbox and on teh swipable hours, minutes, and seconds UI features. Then, delays one second.
       * Uses methods from Timer.java to display time and update time
       * @see             Timer.java
       * @since           1.0
       */
      public void run() {

         showtime.setText(my_time.display()); // Set the text display by using the display method of the timer class
         dayOfWeek.setText(my_time.getDayOfWeek());
         hourPicker.setValue(my_time.getHour()); // Set the swipable hourPicker to the current hour
         minutePicker.setValue(my_time.getMinute()); // Set the swipable hourPicker to the current minute
         secondPicker.setValue(my_time.getSecond()); // Set the swipable hourPicker to the current second
         monthSelector.setValue(my_time.getMonth());
         daySelector.setValue(my_time.getDay());
         if(my_time.getMonth() == 3 ||my_time.getMonth() == 5||my_time.getMonth() == 8||my_time.getMonth() == 10){
            daySelector.setMaxValue(30);
         }
         else if(my_time.getMonth() == 1){
            daySelector.setMaxValue(29);
         }
         else{
            daySelector.setMaxValue(31);
         }

         if(handler != null) { // If the handler is not null we delay one full second and then update the time again

            handler.postDelayed(this, 1000);

         }

      }
   }

   private updateTimer my_update = null; // Instace of the updateTimer class



   /**
    * Creates an instance of the Hander Class and updateTimer class.
    * Queues calls to the run method from the updateTimer clsas every one second.
    * @see             updateTimer
    * @since           1.0
    */
   public void callHandler() {

      handler = new Handler(); // Create a new instance of the handler

      my_update = new updateTimer(); // Create a new instance of the update_timer

      handler.postDelayed(my_update,1000); // Queue calls to the run method every 1 second
   }

   /**
    * Destroys all calls to the run method in the Handler Queue. Resets the Handler class to point at null.
    * @see             updateTimer
    * @since           1.0
    */
   public void removeHandler() {

      if(handler != null) { // If the handler is not null

         handler.removeCallbacks(my_update); // Remove any queued calls to run
         handler =null; // Set the handler object to null
      }
   }


   /**
    * Constructor used to store the class name
    * @since           1.0
    */
   public MainActivity() {

      class_name = getClass().getName(); // Store teh class name

   }

   /**
    * This method checks if one of the AM/PM radio buttons is clicked. It uses the setAMPM method of the timer class to update the timer accordingly
    *
    * @param view      Represents the view of the radio button
    * @see Timer
    * @since           1.0
    */
   public void onRadioButtonClicked2(View view) {
      // Is the button now checked?
      boolean checked = ((RadioButton) view).isChecked(); // Boolean that stores if one of the radio button's is checked

      // Check which radio button was clicked
      switch (view.getId()) {

         case R.id.radio_PM:
            if (checked) { // If the pm button is checked

               if (my_time.getAMPM().equals("AM")) { // Check if the previous mode is AM

                  my_time.setAMPM("PM"); // Switch the mode

               }
            }
            break;

         case R.id.radio_AM:
            if (checked) { // If the am button is checked

               if (my_time.getAMPM().equals("PM")) { // Check if the previous mode is PM

                  my_time.setAMPM("AM"); // Switch to Am

               }

            }
            break;
      }
   }

   /**
    * This method checks if one of the AM/PM radio buttons is clicked. It uses getters, setters from the Timer class to appropriately to convert the time between the two modes
    *
    * @param view      Represents the view of the radio button
    * @see Timer
    * @since           1.0
    */
   public void onRadioButtonClicked(View view) {

      boolean checked = ((RadioButton) view).isChecked(); // Boolean that stores if one of the buttons is checked

      // Check which radio button was clicked
      switch(view.getId()) {

         case R.id.radio_12:

            if (checked) // If 12 hour mode is checked

               //Set the max min value of the hour selector to be between 1-12
               hourPicker.setMaxValue(12);
               hourPicker.setMinValue(1);

               //12 PM corresponds to 12 military time
               if(my_time.getHour() == 12)
               {
                  my_time.setAMPM("PM");
               }

               //Anytime greater than 13 in military time, is 13-12 PM in regular time
               else if(my_time.getHour() >= 13) {

                  my_time.setHour(my_time.getHour()-12); // Set hour
                  my_time.setAMPM("PM"); // Set to PM

               }

               //Anything below 13 corresponds to the same hour in regular time except when hours is 0
               else {

                  // If the hours is 0, set the time to 12 AM
                  if(my_time.getHour()==0) {

                     my_time.setHour(12); // Set hour to 12

                  }

                  my_time.setAMPM("AM"); // Set to AM
                  
                  
               }

            my_time.setMode(true); // Set mode to 12 hour, so that the timer display can be updated properly

            break;

         case R.id.radio_24:

            if (checked) // If the 24 hour button is checked

               //Change the hour selector, so that the user can select between 0 and 23 hours
               hourPicker.setMaxValue(23);
               hourPicker.setMinValue(0);

               // If we are in PM mode in 24 hour we convert the time appropriately
               if(my_time.getAMPM()=="PM") {

                  //12 PM in regular time converts to 12 in military time
                  if(my_time.getHour() ==12) {

                     my_time.setHour(12); // Set hours to 12

                  }

                  //Any other PM time in regular time besides 12 converts to the number of hours +12 in military time
                  else {

                     my_time.setHour(my_time.getHour() + 12); // Set hours to current hours + 12

                  }

                  my_time.setAMPM("PM"); // Set the current mode to PM so we know what mode we are in

               }

            //If the current 12hour mode is AM
            if(my_time.getAMPM()=="AM") {

               // 12 AM corrspoends to 0 military time

               if(my_time.getHour() == 12) {

                  my_time.setHour(0); // Set hours to 0

               }

               my_time.setAMPM("AM"); // Set mode to AM
            }

            my_time.setMode(false); // Set mode so that we are in 24 hour mode

            break;


      }

   }




   /**
    * onCreate is called when the app is first loaded. We initialize all variables and start the Handler.
    *
    * @param savedInstanceState     Default parameter set by android
    * @see
    * @since           1.0
    */
   @Override
   protected void onCreate(Bundle savedInstanceState) {

      //Default android calls to parent class
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      //Initialize radio groups
      am_pm = (RadioGroup) findViewById(R.id.radioGroup2);
      twelve_twentyfour = (RadioGroup) findViewById(R.id.radioGroup);

      //Set radio groups so that we start in AM and 12 hour mode
      am_pm.check(R.id.radio_AM);
      twelve_twentyfour.check(R.id.radio_12);
      radio_12 = (RadioButton) findViewById(R.id.radio_12);
      radio_24 = (RadioButton) findViewById(R.id.radio_24);
      radio_AM = (RadioButton) findViewById(R.id.radio_AM);
      radio_PM = (RadioButton) findViewById(R.id.radio_PM);

      // Initialize swipable hour picker
      hourPicker = (NumberPicker) findViewById(R.id.numberPickerHour);
      hourPicker.setMaxValue(12);
      hourPicker.setMinValue(1);

      // Initialize swipable minute picker
      minutePicker = (NumberPicker) findViewById(R.id.numberPickerMin);
      minutePicker.setMaxValue(59);

      // Initialize swipable second picker
      secondPicker = (NumberPicker) findViewById(R.id.numberPickerSec);
      secondPicker.setMaxValue(59);

      // Initialize swipable day picker
      daySelector = (NumberPicker) findViewById(R.id.dayOfMonth);
      daySelector.setMaxValue(31);
      daySelector.setMinValue(1);

      // Initialize swipable month picker
      monthSelector = (NumberPicker) findViewById(R.id.theMonth);
      monthSelector.setMaxValue(11);
      monthSelector.setMinValue(0);
      monthSelector.setDisplayedValues(new String[]{"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"});

      // Initialize dayOfWeek textview
      dayOfWeek = (TextView) findViewById(R.id.dayOfWeek);
      dayOfWeek.setText("Friday"); //Default for January 1

      // This sets the month
      monthSelector.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
         @Override
         public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            my_time.setMonth(newVal);
            if(newVal == 3 ||newVal == 5||newVal == 8||newVal == 10){
               daySelector.setMaxValue(30);
            }
            else if(newVal == 1){
               daySelector.setMaxValue(29);
            }
            else{
               daySelector.setMaxValue(31);
            }
         }
      });

      // This sets the day
      daySelector.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
         @Override
         public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            my_time.setDay(newVal);
         }
      });

      // This sets the hours using the setHour method of the Timer class when the user swipes to a new hour
      hourPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
         @Override
         public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            my_time.setHour(newVal);
         }
      });

      // This sets the minutes using the setMinutes method of the Timer class when the user swipes to a new minute
      minutePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
         @Override
         public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

            my_time.setMinute(newVal);

         }
      });

      // This sets the seconds using the setSeconds method of the Timer class when the user swipes to a new second
      secondPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
         @Override
         public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

            my_time.setSecond(newVal);

         }
      });

      // Initalize timer clock
      showtime = (TextView) findViewById(R.id.timer);

      //Set initial time to be 12 AM
      my_time = new Timer(0,0,12);

      // This starts queing call to the run method which runs until removeHandler is called in onDestroy
      callHandler();


      //Debug message
      Log.d(class_name, "Creating Main Activity");

   }


   /**
    * Method created automatically by android
    *
    * @param menu    Default parameter set by android
    * @since           1.0
    */
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }

   /**
    * Method created automatically by android
    *
    * @param item    Default parameter set by android
    * @since           1.0
    */
   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      // Handle action bar item clicks here. The action bar will
      // automatically handle clicks on the Home/Up button, so long
      // as you specify a parent activity in AndroidManifest.xml.

      // The selected menu item will call its respective helper function
      // to start a new activity.
      switch(item.getItemId()){
         case R.id.timerButton:
            startTimerActivity();
            break;
         case R.id.stopwatchButton:
            startStopwatchActivity();
            break;
         case R.id.displayOffButton:
            startDisplayOffActivity();
            break;
         case R.id.zoomButton:
            zoomText();
            break;
      }
      return super.onOptionsItemSelected(item);
   }

   // Helper function for starting the timer activity
   private void startTimerActivity(){
      Intent timerIntent = new Intent(".timerActivity");
      startActivity(timerIntent);
   }

   // Helper function for starting the stopwatch activity
   private void startStopwatchActivity(){
      Intent stopwatchIntent = new Intent(".stopwatchActivity");
      startActivity(stopwatchIntent);
   }

   // Helper function for starting the display off activity
   private void startDisplayOffActivity(){
      Intent displayOffIntent = new Intent(".displayOffActivity");
      startActivity(displayOffIntent);
   }

   // Increases the font size of the clock
   private void zoomText(){
      if(zoomFlag == false){
         showtime.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
         dayOfWeek.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
         radio_12.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
         radio_24.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
         radio_AM.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
         radio_PM.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
         zoomFlag = true;
      }
      else{
         showtime.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
         dayOfWeek.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
         radio_12.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
         radio_24.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
         radio_AM.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
         radio_PM.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
         zoomFlag = false;
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

      Log.d(class_name, "Starting Main Activity"); // Log debug message
   }


   /**
    * Method created automatically by android
    *
    * @since           1.0
    */
   @Override
   protected void onResume() {

      super.onResume(); // Call code of parent class

      Log.d(class_name, "Resuming Main Activity"); // Log debug message

   }

   /**
    * Method created automatically by android
    *
    * @since           1.0
    */
   @Override
   protected void onPause() {

      super.onPause(); // Call parent code 

      Log.d(class_name, "Pausing Main Activity"); // Log debug message

   }


   /**
    * Method created automatically by android
    *
    * @since           1.0
    */
   protected void onStop() {

      super.onStop(); // Call parent code 

      Log.d(class_name, "Stopping Main Activity"); // Log debug message

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

      Log.d(class_name, "Destroying Main Activity "); // Log debug message

   }

}
