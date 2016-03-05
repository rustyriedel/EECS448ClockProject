package com.example.clock;


/**
 * Timer is a helper class for main activity. All class variables have getter, setter methods to update timing variables.
 * Furthermore, this class containts two methods which update the time and return a string which represents the curren time.
 * @author     Cesar Avalos
 * @author     John Russel
 * @author     Yarden Tamir
 * @author     Alec Knutsen
 * @version    First Deployment
 * @since       1.0
 */
public class Timer {

   //Stores time values for hours, seconds, and minutes
   private int current_hour;
   private int current_minute;
   private int current_second;
   private int current_month;
   private int current_day;

   //Boolean that stores value for 12/24 mode. True = 12 hour mode, False = 24 hour mode
   private boolean mode12hour =true;

   //String that stores either AM/PM
   private String am_pm = "AM";



   /**
    * This constructor initalizes the class variables <code>current_second, current hour, current minute</code>.
    * This constructor will be called in onCreate() of the MainActivity
    * @param second - Represents initial seconds when the program is first called
    * @param minute - Represents initial minutes when the program is first called
    * @param hour - Represents initial hours when the pogram is first called
    * @since       1.0
    */
   public Timer(int second, int minute, int hour) {

      // Initalize starting time values to those past in to constructor
      current_second = second;
      current_minute = minute;
      current_hour = hour;
      current_month = 0;
      current_day = 1;

   }

   //Getters


   /**
    * Get method for class variable am_pm
    * @return am_pm - Returns class variable am_pm
    * @since       1.0
    */
   public String getAMPM() {return(am_pm);}

   /**
    * Get method for class variable current_hour
    * @return am_pm - Returns class variable current_hour
    * @since       1.0
    */
   public int getHour()
   {
      return current_hour;
   }

   /**
    * Get method for class variable current_minute
    * @return am_pm - Returns class variable current_minute
    * @since       1.0
    */
   public int getMinute()
   {
      return current_minute;
   }

   /**
    * Get method for class variable current_second
    * @return am_pm - Returns class variable current_second
    * @since       1.0
    */
   public int getSecond()
   {
      return current_second;
   }


   /**
    * Get method for class variable mode12hour
    * @return am_pm - Returns class variable mode12hour
    * @since       1.0
    */
   public boolean getMode() {return(mode12hour);}

   public int getMonth()
   {
      return current_month;
   }
   public int getDay()
   {
      return current_day;
   }

   //Setters


   /**
    * Set method for class variable mode12hour
    * @param  mode - Class variable mode12hour is set to mode
    * @since       1.0
    */
   public void setMode(boolean mode) {mode12hour = mode;}

   /**
    * Set method for class variable current_hour
    * @param hour - Class variable current_hour is set to hour
    * @since       1.0
    */
   public void setHour(int hour)
   {
      current_hour = hour;
   }

   /**
    * Set method for class variable current_minute
    * @param min - Class variable current_minute is set to min
    * @since       1.0
    */
   public void setMinute(int min)
   {
      current_minute = min;
   }

   /**
    * Set method for class variable current_second
    * @param sec - Class variable current_second is set to sec
    * @since       1.0
    */
   public void setSecond(int sec)
   {
      current_second = sec;
   }

   /**
    * Set method for class variable am_pm
    * @param x - Class variable am_pm is set to x
    * @since       1.0
    */
   public void setAMPM(String x) {am_pm = x; }
   public void setDay(int day)
   {
      current_day = day;
   }
   public void setMonth(int month)
   {
      current_month = month;
   }


   /**
    * This method is a manual program of a clock. It takes in the previous hours, minutes, seconds and updates them accordingly
    * @param previous_seconds - Represents the clocks current seconds
    * @param previous_minutes - Represents the clocks current minutes
    * @param previous_hour - Represents the clocks current hour
    * @return int[] - This method returns the updated hours,minutes, seconds and stores them in an int arrays
    * @since       1.0
    */
   public int[] updateTime(int previous_seconds, int previous_minutes, int previous_hour) {

      int[] my_time_array = new int[3]; // Time array to store updated time values

      //Variables that will temporarily store updated time values
      int next_seconds = previous_seconds;

      int next_minutes = previous_minutes;

      int next_hour = previous_hour;

      //If we are not at 59 seconds, just update the seconds +1
      if(previous_seconds!= 59) {

         next_seconds = previous_seconds +1;

      }

      //If the seconds is 59, go into this else if statement
      else if (previous_seconds ==59) {

         next_seconds = 0; // Reset the seconds to 0

         //If we are not at 59 minutes,update the minutes +1
         if(previous_minutes!= 59) {

            next_minutes = previous_minutes +1;

         }

         //If we are at 59 minutes, go into this elseif statement
         else if(previous_minutes == 59) {

            next_minutes =0; // Reset the minutes to 0

            //If we are in 12 hour mode, go into this if statement
            if(mode12hour) {

               if(previous_hour != 12) { // If the previous hour is not 12, go into this if statement

                  if (previous_hour == 11) { // If the previous hour is 11, switch from either flip AM and PM

                     if(am_pm == "AM") { // If we are at AM, reset to PM

                        am_pm = "PM";

                     }

                     else if (am_pm == "PM") { // If we are at PM, reset to AM
                        am_pm = "AM";
                        if((current_month == 1 && current_day ==29) || (current_month == 3 && current_day == 30 ) || (current_month == 5 && current_day ==30)
                              || (current_month == 8 && current_day == 30) || (current_month == 10 && current_day == 30)){
                           current_month = current_month + 1;
                           current_day = 1;
                        }
                        else if(current_month == 11 && current_day == 31){
                           current_month = 0;
                           current_day =1;
                        }
                        else if(current_day == 31){
                           current_month = current_month + 1;
                           current_day = 1;
                        }
                        else{
                           current_day = current_day + 1;
                        }

                     }

                     next_hour = previous_hour +1; // Update next hour +1

                  }
                  else
                  {
                     next_hour = previous_hour +1;
                  }
               }
               else if (previous_hour == 12) { // If the previous hour is at 12, reset hour to 1


                  next_hour = 1;

               }

            }


            else if (!mode12hour) { // If we are in 24 hour mode, go into this else if block

               if(previous_hour != 23) { // If we are not at hour 23, update hour +1

                  next_hour = previous_hour + 1;

               }


               else if (previous_hour == 23) { // If we are at hour 23, reset hour to 0

                  next_hour = 0;
                  if((current_month == 1 && current_day ==29) || (current_month == 3 && current_day == 30 ) || (current_month == 5 && current_day ==30)
                        || (current_month == 8 && current_day == 30) || (current_month == 10 && current_day == 30)){
                     current_month = current_month + 1;
                     current_day = 1;
                  }
                  else if(current_month == 11 && current_day == 31){
                     current_month = 0;
                     current_day =1;
                  }
                  else if(current_day == 31){
                     current_month = current_month + 1;
                     current_day = 1;
                  }
                  else{
                     current_day = current_day + 1;
                  }
               }

            }

         }

      }

      //Store updated time values in array
      my_time_array[0] = next_seconds;
      my_time_array[1] = next_minutes;
      my_time_array[2] = next_hour;

      //Return array
      return(my_time_array);

   }


   /**
    * This method calls the updateTime() method to get the current time, then returns a String with the displayed time.
    * @return String - Returns the time display represented as a string
    * @see updateTime()
    * @since       1.0
    */
   public String display()  {

      String display; // String that will be time display

      int[] my_time_array = updateTime(current_second,current_minute,current_hour); // Call update time method to update time

      //Store newly updated time
      current_second = my_time_array[0];
      current_minute = my_time_array[1];
      current_hour = my_time_array[2];

      //Display time with AM/PM since mode is 12 hour
      if(mode12hour==true) {
         display = String.format("%d", current_hour) + ":" + String.format("%02d", current_minute) + ":" + String.format("%02d", current_second) +" " + getAMPM();

      }

      //Display time without AM/PM since mode is 24 hour
      else {
         display = String.format("%d", current_hour) + ":" + String.format("%02d", current_minute) + ":" + String.format("%02d", current_second);

      }


      return(display); // Return the string to display

   }
   // calculates the day of the week
   public String getDayOfWeek(){
      String wkDay = "";
      int[] array = {31,29,31,30,31,30,31,31,30,31,30,31};
      int numDays = current_day-1;

      if(current_month > 0){
         for(int i=0; i<current_month; i++){
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
