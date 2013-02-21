package com.apress;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;

public class LocationActivity extends Activity {
	
	Location location;
	LocationManager locationManager;
	
	TextView locationView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationView = new TextView(this);
        setContentView(locationView);
        locationManager = (LocationManager) getSystemService(
        		Context.LOCATION_SERVICE);
    }
    
	@Override
	protected void onResume() {
		super.onResume();
		if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			// Ҫ���û�����GPS
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Location Manager");
			builder.setMessage("We want to use your location, but the GPS is currently disabled. \n" +
					"Would you like to change the settings now?");
			builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					startActivity(intent);
				}
			});
			builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					finish();
				}
			});
			builder.create().show();
		}
		// ������ڵĻ�����ȡ�����λ��
		location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		/* �����ı���ͼ  */
		updateDisplay();
		// ע��λ�ñ仯
		int minTime = 500;
		float minDistance = 0;
		locationManager.requestLocationUpdates(
				LocationManager.GPS_PROVIDER, minTime, minDistance, listener);
	}
	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(listener);
	}
    
	/* �����ı���ͼ  */
	private void updateDisplay() {
		if(location == null) {
			locationView.setText("Determining your Location.......");
		} else {
			locationView.setText(
					String.format("Your Location:\n%.2f, %.2f", location.getLatitude(), location.getLongitude()));
		}
	}

	/*  ����λ�õĻص��¼�  */
    private LocationListener listener = new LocationListener() {
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
		@Override
		public void onProviderEnabled(String provider) {
		}
		@Override
		public void onProviderDisabled(String provider) {
		}
		@Override
		public void onLocationChanged(Location loc) {
			location = loc;
			updateDisplay();
		}
	};
    
}