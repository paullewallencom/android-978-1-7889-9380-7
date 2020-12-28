package com.packt.backgroundservicedemo;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyBackgroundService extends Service {

	private static final String TAG = MyBackgroundService.class.getSimpleName();

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "onCreate, Thread name: " + Thread.currentThread().getName());
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(TAG, "onStartCommand, Thread name: " + Thread.currentThread().getName());

		// Perform tasks: Dummy long operation
//		try {
//			Thread.sleep(12000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		return START_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.i(TAG, "onBind, Thread name: " + Thread.currentThread().getName());
		return null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "onDestroy, Thread name: " + Thread.currentThread().getName());
	}
}
