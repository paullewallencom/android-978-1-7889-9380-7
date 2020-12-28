package com.packt.backgroundservicedemo;


import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

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
		new MyAsyncTask().execute();	// Background Thread

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

	class MyAsyncTask extends AsyncTask<Void, String, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Log.i(TAG, "onPreExecute, Thread name: " + Thread.currentThread().getName());
		}

		@Override // Perform tasks in Background or Worker Thread
		protected Void doInBackground(Void... voids) {
			Log.i(TAG, "doInBackground, Thread name: " + Thread.currentThread().getName());

			int ctr = 1;

			// Dummy long operation: Download/Upload of file
			while (ctr <= 12) {
				publishProgress("Time elapsed: " + ctr + " secs");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ctr++;
			}

			return null;
		}

		@Override
		protected void onProgressUpdate(String... values) {
			super.onProgressUpdate(values);
			Log.i(TAG, "onProgressUpdate, Counter Value" + values[0] +"Thread name: " + Thread.currentThread().getName());
			Toast.makeText(MyBackgroundService.this, values[0], Toast.LENGTH_SHORT).show();
		}

		@Override
		protected void onPostExecute(Void aVoid) {
			Log.i(TAG, "onPostExecute, Thread name: " + Thread.currentThread().getName());
			super.onPostExecute(aVoid);

			stopSelf();
		}
	}
}
