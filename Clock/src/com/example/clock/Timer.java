package com.example.clock;


import android.util.Log;

public class Timer {

	private String class_name;

	protected int starting_hour;
	protected int starting_minute;
	protected int starting_second;

	protected int current_hour;
	protected int current_minute;
	protected int current_second;

	protected int num_times_display =0;

	protected boolean mode12hour =true;

	protected String am_pm = "AM";






	public Timer(int second, int minute, int hour) {

		class_name = getClass().getName();

		starting_second = second;
		starting_minute = minute;
		starting_hour = hour;

		current_second = second;
		current_minute = minute;
		current_hour = hour;

	}


	public int[] updateTime(int previous_seconds, int previous_minutes, int previous_hour) {

		int[] my_time_array = new int[3];

		int next_seconds = previous_seconds;

		int next_minutes = previous_minutes;

		int next_hour = previous_hour;

		if(previous_seconds!= 59) {

			next_seconds = previous_seconds +1;
		}

		else if (previous_seconds ==59) {

			next_seconds = 0;

			if(previous_minutes!= 59) {
				next_minutes = previous_minutes +1;
			}

			else if(previous_minutes == 59) {
				next_minutes =0;

				if(mode12hour == true) {

					if(previous_hour!=12) {

						if (previous_hour ==11) {
							if(am_pm =="AM") {

								am_pm = "PM";
							}

							else if (am_pm == "PM") {
								am_pm = "AM";
							}
							next_hour = previous_hour +1;
						}

						else if (previous_hour ==12) {




							next_hour = 1;
						}



					}

				}


				else if (mode12hour ==false) {

					if(previous_hour!=23) {

						next_hour = previous_hour +1;
					}

					else if (previous_hour ==23) {

						next_hour = 0;
					}
				}


			}

		}

		my_time_array[0] = next_seconds;
		my_time_array[1] = next_minutes;
		my_time_array[2] = next_hour;

		return(my_time_array);


	}

	//Getters

	public String getAMPM() {
		return(am_pm);
	}

	public int getHour()
	{
		return current_hour;
	}

	public int getMinute()
	{
		return current_minute;
	}

	public int getSecond()
	{
		return current_second;
	}
	public boolean getMode() {

		return(mode12hour);
	}



	public void setMode(boolean mode)
	{
		mode12hour = mode;
	}


	//Setters
	public void setHour(int hour)
	{
		current_hour = hour;
	}

	public void setMinute(int min)
	{
		current_minute = min;
	}

	public void setSecond(int sec)
	{
		current_second = sec;
	}

	public void setAMPM(String x) {
		Log.d(class_name, "Set AM PM");
		am_pm = x;
	}



	public String display()  {

		String display;



		if(num_times_display == 0) {

			if(mode12hour == true) {
				display = String.format("%d", current_hour) + ":" + String.format("%02d", current_minute) + ":" + String.format("%02d", current_second) +" " + getAMPM();
				num_times_display =1;
			}

			else {

				display = String.format("%d", current_hour) + ":" + String.format("%02d", current_minute) + ":" + String.format("%02d", current_second);
				num_times_display =1;

			}



		}

		else {

			int[] my_time_array = new int[3];

			my_time_array = updateTime(current_second,current_minute,current_hour);

			current_second = my_time_array[0];
			current_minute = my_time_array[1];
			current_hour = my_time_array[2];

			if(mode12hour==true) {
				display = String.format("%d", current_hour) + ":" + String.format("%02d", current_minute) + ":" + String.format("%02d", current_second) +" " + getAMPM();

			}

			else {
				display = String.format("%d", current_hour) + ":" + String.format("%02d", current_minute) + ":" + String.format("%02d", current_second);

			}


		}







		return(display);


	}


}