package com.example.clock;


/**
 * Stopwatch is a helper class for main activity. All class variables have getter, setter methods to update timing variables.
 * Furthermore, this class containts two methods which update the time and return a string which represents the time.
 * @author     Dillon Fruhwirth
 * @version    First Deployment
 * @since       1.0
 */
public class Stopwatch {

    //Stores time values for hours, seconds, and minutes
    private int current_hour;
    private int current_minute;
    private int current_second;

    //Boolean tells updateTime() if to update time
    private boolean update;

    /**
     * This constructor initalizes the class variables <code>current_second, current hour, current minute</code> to zero
     * @since       1.0
     */
    public Stopwatch() {

        // Initalize starting time values to 0
        current_second = 0;
        current_minute = 0;
        current_hour = 0;
        update = false;

    }

    //Getters

	/**
     * Get method for class variable current_hour
     * @return Returns class variable current_hour
     * @since       1.0
     */
    public int getHour()
    {
        return current_hour;
    }

    /**
     * Get method for class variable current_minute
     * @return Returns class variable current_minute
     * @since       1.0
     */
    public int getMinute()
    {
        return current_minute;
    }

    /**
     * Get method for class variable current_second
     * @return Returns class variable current_second
     * @since       1.0
     */
    public int getSecond()
    {
        return current_second;
    }

    /**
     * Get method for class variable update
     * @return Returns class variable update
     * @since       1.0
     */
    public boolean getUpdate()
    {
        return update;
    }

	//Setter methods

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
     * Set method for class variable update
     * @param sec - Class variable update is set to up
     * @since       1.0
     */
    public void setUpdate(boolean up)
    {
        update = up;
    }

	/**
     * This method resets variables to 0 and false
     * @since       1.0
     */
    public void Reset()
    {
        // Reset starting time values to 0
        current_second = 0;
        current_minute = 0;
        current_hour = 0;

		//Stop updating time
        update = false;
    }


    /**
     * This method is a manual program of a stopwatch. It takes in the previous hours, minutes, seconds and updates them accordingly
     * @param previous_seconds - Represents the current seconds
     * @param previous_minutes - Represents the current minutes
     * @param previous_hour - Represents the current hour
     * @return int[] - This method returns the updated hours,minutes, seconds and stores them in an int arrays
     * @since       1.0
     */
    public int[] updateTime(int previous_seconds, int previous_minutes, int previous_hour) {

        int[] my_time_array = new int[3]; // Time array to store updated time values

        //Variables that will temporarily store updated time values
        int next_seconds = previous_seconds;

        int next_minutes = previous_minutes;

        int next_hour = previous_hour;

        //If told to update
        if(update) {
            //If we are not at 59 seconds, just update the seconds +1
            if (previous_seconds != 59) {

                next_seconds = previous_seconds + 1;

            }
            //If the seconds is 59, go into this else if statement
            else if (previous_seconds == 59) {

                next_seconds = 0; // Reset the seconds to 0

                //If we are not at 59 minutes,update the minutes +1
                if (previous_minutes != 59) {
                    next_minutes = previous_minutes + 1;

                }

                //If we are at 59 minutes, increase hours by 1
                else if (previous_minutes == 59) {

                    next_minutes = 0; // Reset the minutes to 0

                    next_hour = next_hour + 1;
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

        //Display time
        display = String.format("%d", current_hour) + ":" + String.format("%02d", current_minute) + ":" + String.format("%02d", current_second);


        return(display); // Return the string to display

    }

}
