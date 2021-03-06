package com.inforica.booker.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.andexert.library.RippleView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.SupportMapFragment;
import com.inforica.booker.R;
import com.inforica.booker.utils.SuperclassActivity;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by user on 4/27/2016.
 */
public class BookerDetailsActivity extends FragmentActivity implements LocationListener, View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = "LocationActivity";
    private static final long INTERVAL = 1000 * 10;
    private static final long FASTEST_INTERVAL = 1000 * 5;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    TextView screen_name;
    Location mCurrentLocation;
    String mLastUpdateTime;
    GoogleMap googleMap;
    LatLng currentlocation;
    Marker currentlocmarker;
    RippleView back_button,edit_button;
    TextView service_name, service_location, date, start_time, end_time, customer_name, customer_desc, booking_notes, price, days_before, mail_desc, staff_name;
    String StartTime, EndTime, CustomerName, StaffName, Service_Location, Service_Name;
    ImageView staffImage;

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate ...............................");
        //show error dialog if GoolglePlayServices not available
        if (!isGooglePlayServicesAvailable()) {
            finish();
        }
        createLocationRequest();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.booker_details_activity);
        if (googleMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            googleMap.setMyLocationEnabled(true);
        }
        screen_name = (TextView) findViewById(R.id.screen_name);
        screen_name.setText("BOOKING DETAILS");
        edit_button = (RippleView) findViewById(R.id.edit_button);
        edit_button.setVisibility(View.VISIBLE);
        back_button = (RippleView) findViewById(R.id.back_button);
        back_button.setVisibility(View.VISIBLE);
        back_button.setOnClickListener(this);


        date = (TextView) findViewById(R.id.date);
        start_time = (TextView) findViewById(R.id.start_time);
        end_time = (TextView) findViewById(R.id.end_time);
        customer_name = (TextView) findViewById(R.id.customer_name);
        service_name = (TextView) findViewById(R.id.service_name);
        service_location = (TextView) findViewById(R.id.service_location);
   /*     customer_desc = (TextView) findViewById(R.id.customer_desc);
        booking_notes = (TextView) findViewById(R.id.bookinf_notes);
        price = (TextView) findViewById(R.id.price);
        days_before = (TextView) findViewById(R.id.days_before);
        mail_desc = (TextView) findViewById(R.id.mail_desc);
        staff_name = (TextView) findViewById(R.id.staff_Name);*/
        staffImage = (ImageView) findViewById(R.id.staff_image);

        Intent intent = getIntent();
        StartTime = intent.getExtras().getString("Start_Time");
        EndTime = intent.getExtras().getString("End_Time");
        CustomerName = intent.getExtras().getString("Customer_Name");

        Service_Name = intent.getExtras().getString("Service_Name");
        Service_Location = intent.getExtras().getString("Service_Location");
//        StaffName = intent.getExtras().getString("staff_Name");


    /*    String Staffname = "";
        Staffname = Staffname + StaffName.charAt(0);
        for (int i = 0; i < StaffName.length(); i++) {
            if (Character.isWhitespace(StaffName.charAt(i))) {
                Staffname = Staffname + StaffName.charAt(i + 1);
            }
        }
        Log.v("Tag", "FN" + Staffname);*/
        //    String firstLetter = String.valueOf(calenderAgendaviewlist.get(position).getStaff_name().charAt(0));
/*        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
        // generate random color
        //  int color = generator.getColor(calenderAgendaviewlist.get(position));
        int color = generator.getRandomColor();

        TextDrawable drawable = TextDrawable.builder()
                .buildRound(Staffname, color); // radius in px

        staffImage.setImageDrawable(drawable);*/
        start_time.setText(StartTime);
        end_time.setText(EndTime);
        customer_name.setText(CustomerName);
//        staff_name.setText(StaffName);
        service_name.setText(Service_Name);
        service_location.setText(Service_Location);

    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart fired ..............");
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop fired ..............");
        mGoogleApiClient.disconnect();
        Log.d(TAG, "isConnected ...............: " + mGoogleApiClient.isConnected());
    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "onConnected - isConnected ...............: " + mGoogleApiClient.isConnected());
        startLocationUpdates();
    }

    protected void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
        Log.d(TAG, "Location update started ..............: ");
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "Firing onLocationChanged..............................................");
        mCurrentLocation = location;
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        makeMyNewPin(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());

        updateUI();
    }

    private void updateUI() {
        Log.d(TAG, "UI update initiated .............");
        if (null != mCurrentLocation) {
            String lat = String.valueOf(mCurrentLocation.getLatitude());
            String lng = String.valueOf(mCurrentLocation.getLongitude());
           /* tvLocation.setText("At Time: " + mLastUpdateTime + "\n" +
                    "Latitude: " + lat + "\n" +
                    "Longitude: " + lng + "\n" +
                    "Accuracy: " + mCurrentLocation.getAccuracy() + "\n" +
                    "Provider: " + mCurrentLocation.getProvider());*/


        } else {
            Log.d(TAG, "location is null ...............");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                onBackPressed();
                break;
        }

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
        Log.d(TAG, "Location update stopped .......................");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mGoogleApiClient.isConnected()) {
            startLocationUpdates();
            Log.d(TAG, "Location update resumed .....................");
        }
    }

    void makeMyNewPin(double userLat1, double userLng1) {


        currentlocation = new LatLng(userLat1, userLng1);

        Log.v("TAG", "latLngsource" + currentlocation);

        Geocoder geoCoder1 = new Geocoder(BookerDetailsActivity.this,
                Locale.getDefault());
        try {
            /**
             * Reverse GeoCoding
             */
            List<Address> addresses1 = geoCoder1.getFromLocation(userLat1,
                    userLng1, 1);
            StringBuilder sb = new StringBuilder();
            if (addresses1.size() > 0) {
                Address address = addresses1.get(0);
                for (int index = 0; index < address.getMaxAddressLineIndex(); index++)
                    sb.append(address.getAddressLine(index)).append("\n");
                sb.append(address.getCountryName());
            }
            /*currentAddressString1 = sb.toString();
            Log.v("tag", "currentAddressString1 : " + currentAddressString1);*/

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (userLat1 != 0.0 && userLng1 != 0.0) {

            //LatLng currentlocation2 = new LatLng(userLat1, userLng1);

                /*googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        latLngsource, 10));*/
            currentlocationmarker();
        }
    }

    public void currentlocationmarker() {
        googleMap.clear();
        currentlocmarker = googleMap.addMarker(new MarkerOptions()
                .position(currentlocation)
                .title("currentlocation")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.userpin))
                .snippet("cureentaddress"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                currentlocation, 17));
    }
}
