package com.bwfsurvey.bwfsurveybeta.utils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import static androidx.core.content.ContextCompat.getSystemService;

public class PhoneLocation {
    LocationManager locationManager;
    private static final int REQUEST_LOCATION = 1;
    private AppCompatActivity context;

    public PhoneLocation(AppCompatActivity context) {
        this.context = context;
    }

    public String[] getLocation(){
        LocationManager locationManager = (LocationManager) this.context.getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            this.context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        }
        if (ActivityCompat.checkSelfPermission(
                this.context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this.context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
                String latitude = String.valueOf(lat);
                String longitude = String.valueOf(longi);

                String[] result = {latitude,longitude};
                return result;
            }else{
                Log.i("Tutorials", "could not get"  );
            }
        }
        return null;
    }
}
