package com.example.clock;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.RadioButton;

import java.lang.reflect.Array;


public class MainActivity extends AppCompatActivity {

	private String class_name;

	public static boolean my_bool;


	public TextView showtime;

	public String hours;
	public String minutes;
	public String seconds;
	public String mode;

	public Spinner changehour;
	public Spinner changeminutes;
	public Spinner changeseconds;
	public Spinner changemode;

	public NumberPicker hourPicker;
	public NumberPicker minutePicker;
	public NumberPicker secondPicker;


	protected Timer my_time;



	protected Handler handler =null;

	protected updateTimer my_update;

	class updateTimer implements Runnable {

		public void run() {

			showtime.setText(my_time.display());

			if(handler != null) {
				handler.postDelayed(this, 1000);
				hourPicker.setValue(my_time.getHour());
				minutePicker.setValue(my_time.getMinute());
				secondPicker.setValue(my_time.getSecond());
			}

		}
	}


	public void callHandler() {

		handler = new Handler();

		my_update = new updateTimer();

		handler.postDelayed(my_update,1000);
	}

	public void removeHandler() {

		if(handler == null) {

		}

		else {
			handler.removeCallbacks(my_update);
			handler =null;
		}
	}


	/*
	 * Constructor the stores the Class_Name for debugging purposes 
	 */
	public MainActivity() {

		class_name = getClass().getName();

	}

	public void onRadioButtonClicked2(View view) {
		// Is the button now checked?
		boolean checked = ((RadioButton) view).isChecked();
		removeHandler();
		// Check which radio button was clicked
		switch (view.getId()) {

			case R.id.radio_PM:
				if (checked) {

					if (my_time.getAMPM().equals("AM")) {
						removeHandler();

						my_time.setAMPM("PM");
						callHandler();

					}
				}
				break;
			case R.id.radio_AM:
				if (checked) {
					removeHandler();
					if (my_time.getAMPM().equals("PM")) {

						my_time.setAMPM("AM");

					}
					callHandler();
				}
				break;
		}
	}

	public void onRadioButtonClicked(View view) {
		// Is the button now checked?
		boolean checked = ((RadioButton) view).isChecked();
		removeHandler();
		// Check which radio button was clicked
		switch(view.getId()) {

			case R.id.radio_12:

				hourPicker.setMaxValue(12);
				hourPicker.setMinValue(1);
				if (checked)
					
					if(my_time.getHour() == 12)
					{
						my_time.setAMPM("PM");
					}
				
					else if(my_time.getHour() >= 13) {
						my_time.setHour(my_time.getHour()-12);
						my_time.setAMPM("PM");
					}

					else {
						if(my_time.getHour()==0) {
							my_time.setHour(12);
						}

						my_time.setAMPM("AM");
						
						
					}
				my_time.setMode(true);

				break;
			case R.id.radio_24:
				hourPicker.setMaxValue(23);
				hourPicker.setMinValue(0);
				if (checked)
					if(my_time.getAMPM()=="PM") {
						if(my_time.getHour() ==12) {
							my_time.setHour(12);
						}
						else {
							my_time.setHour(my_time.getHour() + 12);

						}

						my_time.setAMPM("PM");

					}
				if(my_time.getAMPM()=="AM") {
					if(my_time.getHour() == 12) {
						my_time.setHour(0);
					}

					my_time.setAMPM("AM");
				}
				my_time.setMode(false);
				break;


		}
		callHandler();
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.d(class_name, "Creating Main Activity");




		showtime = (TextView) findViewById(R.id.timer);

		my_time = new Timer(0,0,12);

		hourPicker = (NumberPicker) findViewById(R.id.numberPickerHour);
		hourPicker.setMaxValue(12);
		hourPicker.setMinValue(1);

		minutePicker = (NumberPicker) findViewById(R.id.numberPickerMin);
		minutePicker.setMaxValue(59);

		secondPicker = (NumberPicker) findViewById(R.id.numberPickerSec);
		secondPicker.setMaxValue(59);

		hourPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				removeHandler();

				my_time.setHour(newVal);

				callHandler();

			}
		});

		minutePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				removeHandler();

				my_time.setMinute(newVal);

				callHandler();
			}
		});

		secondPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				removeHandler();
				my_time.setSecond(newVal);
				callHandler();
			}
		});



	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onStart() {


		super.onStart(); // Make sure the code in the parent class is used



		Log.d(class_name, "Starting Main Activity");


	}


	@Override
	protected void onResume() {


		super.onResume(); // Call code of parent class

		Log.d(class_name, "Resuming Main Activity");

	}


	@Override
	protected void onPause() {


		super.onPause(); // Call parent code 

		Log.d(class_name, "Pausing Main Activity");



	}


	protected void onStop() {


		super.onStop(); // Call parent code 


		Log.d(class_name, "Stopping Main Activity");



	}


	@Override
	protected void onDestroy() {


		super.onDestroy();

		removeHandler();

		Log.d(class_name, "Destroying Main Activity ");

	}

}
