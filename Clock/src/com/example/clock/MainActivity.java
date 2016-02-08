package com.example.clock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity {
	
	private String class_name;
	
	
	public TextView showtime;
	
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
	
	
	/*
	 * Constructor the stores the Class_Name for debugging purposes 
	 */
	public MainActivity() {
		
		class_name = getClass().getName();
		
	}
	
	

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Log.d(class_name, "Creating Main Activity");
        
        
        showtime = (TextView) findViewById(R.id.timer);
        
 
        
      
		
        

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
