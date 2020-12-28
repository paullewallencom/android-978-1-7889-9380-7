package com.packt.backgroundservicedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	private TextView txvResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txvResult = (TextView) findViewById(R.id.txvResults);
	}

	public void startBackgroundService(View view) {

		Intent intent = new Intent(this, MyBackgroundService.class);
		intent.putExtra("key", "value");
		startService(intent);
	}

	public void stopBackgroundService(View view) {

		Intent intent = new Intent(this, MyBackgroundService.class);
		stopService(intent);
	}

	public void startIntentService(View view) {

		Intent intent = new Intent(this, MyIntentService.class);
		intent.putExtra("sleepTime", 12);
		startService(intent);
	}

	@Override
	protected void onResume() {
		super.onResume();

		IntentFilter intentFilter = new IntentFilter("my.own.broadcast");
		LocalBroadcastManager.getInstance(this).registerReceiver(myLocalBroadcastReceiver, intentFilter);
	}

	private BroadcastReceiver myLocalBroadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {

			int result = intent.getIntExtra("result", -1);
			txvResult.setText("Task executed in " + result + " seconds");
		}
	};

	@Override
	protected void onPause() {
		super.onPause();
		LocalBroadcastManager.getInstance(this).unregisterReceiver(myLocalBroadcastReceiver);
	}

	public void startJobIntentService(View view) {

		Intent i = new Intent(this, MyJobIntentService.class);
		i.putExtra("sleepTime", 12);
		MyJobIntentService.enqueueWork(this, i);
	}
}
