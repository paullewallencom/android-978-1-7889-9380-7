package com.packt.backgroundservicedemo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.util.Log;
import android.widget.Toast;

public class MyJobIntentService extends JobIntentService {

	private static final String TAG = MyJobIntentService.class.getSimpleName();

	public static void enqueueWork(Context context, Intent intent) {
		enqueueWork(context, MyJobIntentService.class, 17, intent);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Toast.makeText(this, "Task Execution Started", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onHandleWork(@NonNull Intent intent) {

		Log.i(TAG, "onHandleWork, Thread name: " + Thread.currentThread().getName());
		// Write code here.. Perform Long operation here

		int duration = intent.getIntExtra("sleepTime", -1);

		int ctr = 1;

		// Dummy long operation: Download/Upload of file
		while (ctr <= duration) {
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
		Toast.makeText(this, "Task Execution Finishes", Toast.LENGTH_SHORT).show();
	}
}
