package com.example.clock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.RadioButton;

import java.lang.reflect.Array;


public class MainActivity extends AppCompatActivity {
	
	private String class_name;
	
	
	public TextView showtime;

	public Spinner changehour;
	public Spinner changeminutes;
	public Spinner changeseconds;
	public Spinner changemode;

	protected Timer my_time = new Timer(45,59,12);


	
	protected Handler handler;
	
	protected updateTimer my_update;
	
	class updateTimer implements Runnable {
		
		public void run() {
			
			showtime.setText(my_time.display());
			
			if(handler != null) {
				handler.postDelayed(this, 950);
			}
			
		}
	}

	public class SpinnerItemSelectedListener implements AdapterView.OnItemSelectedListener
	{

		public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
		{

		}

		@Override
		public void onNothingSelected(AdapterView<?> arg) {

		}
	}


	/*
	 * Constructor the stores the Class_Name for debugging purposes 
	 */
	public MainActivity() {
		
		class_name = getClass().getName();
		
	}

	public void onRadioButtonClicked(View view) {
		// Is the button now checked?
		boolean checked = ((RadioButton) view).isChecked();

		// Check which radio button was clicked
		switch(view.getId()) {
			case R.id.radio_12:
				if (checked)

					break;
			case R.id.radio_24:
				if (checked)

					break;
		}
	}

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Log.d(class_name, "Creating Main Activity");


		Integer[] hours = new Integer[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24};
		ArrayAdapter<Integer> hourAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item,hours);


		//this is probably retarded
		Integer[] minutes = new Integer[60];
		for(int i = 0; i < 60; i++)
		{
			minutes[i] = i;
		}
		ArrayAdapter<Integer> minuteAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, minutes);

		String[] mode = {"AM","PM"};
		ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mode);


		showtime = (TextView) findViewById(R.id.timer);

        changehour = (Spinner) findViewById(R.id.spinnerHour);
		changehour.setAdapter(hourAdapter);
		changehour.setOnItemSelectedListener(new SpinnerItemSelectedListener());

		changeminutes = (Spinner) findViewById(R.id.spinnerMinutes);
		changeminutes.setAdapter(minuteAdapter);


		changeseconds = (Spinner) findViewById(R.id.spinnerSeconds);
		changeseconds.setAdapter(minuteAdapter);

		changemode = (Spinner) findViewById(R.id.spinnerMode);
		changemode.setAdapter(modeAdapter);

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
		
		handler = new Handler();
		
		my_update = new updateTimer();
		
		handler.postDelayed(my_update,950);
		
	
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
		
		handler = new Handler();
		
		my_update = new updateTimer();
		
		handler.postDelayed(my_update,950);
		
		
		
	}
    
    
    protected void onStop() {
		
		
		super.onStop(); // Call parent code 
		
		
		Log.d(class_name, "Stopping Main Activity");
		
		
		
	}
    
    
    @Override
	protected void onDestroy() {
		
		
		super.onDestroy();
		
		Log.d(class_name, "Destroying Main Activity ");
		
	}

}
