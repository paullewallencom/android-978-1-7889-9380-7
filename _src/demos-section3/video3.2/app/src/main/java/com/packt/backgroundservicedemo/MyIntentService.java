package com.packt.backgroundservicedemo;


import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyIntentService extends IntentService {

	private static final String TAG = MyIntentService.class.getSimpleName();

	public MyIntentService() {
		// Provide name to your Worker or Background Thread
		super("MyBackgroundThread");
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "onCreate, Thread name: " + Thread.currentThread().getName());
	}

	@Override
	protected void onHandleIntent(@Nullable Intent intent) {
		Log.i(TAG, "onHandleIntent, Thread name: " + Thread.currentThread().getName());
		// Write code here.. Perform Long operation here
		int ctr = 1;

		// Dummy long operation: Download/Upload of file
		while (ctr <= 12) {
			Log.i(TAG, "Time elapsed: " + ctr + " secs");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ctr++;
		}

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "onDestroy, Thread name: " + Thread.currentThread().getName());

	}
}
