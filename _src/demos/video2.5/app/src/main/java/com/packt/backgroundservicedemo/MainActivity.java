package com.packt.backgroundservicedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void startBackgroundService(View view) {

		Intent intent = new Intent(this, MyBackgroundService.class);
		startService(intent);
	}

	public void stopBackgroundService(View view) {

		Intent intent = new Intent(this, MyBackgroundService.class);
		stopService(intent);
	}
}
