package com.example.clock;

import android.app.Activity;
import android.os.Bundle;

/**
 * displayOffActivity simulates the device being off without stopping
 * the update process because it simply displays a black screen when called.
 * When physically turned the device off, the process would stop and clock
 * would stop updating the time.
 */
public class displayOffActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayoff);
    }
}