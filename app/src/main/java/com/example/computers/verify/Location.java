package com.example.computers.verify;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

 public class Location extends Activity {

    JSONObject jobj, amenities, amenities1;
    String jstring;
    TextView taname, tadis, trname, trdis, tbname, tbdis;
    Double d1, d2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);
        getActionBar().setTitle("Stayzilla");

        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF4088")));

        getActionBar().setIcon(R.drawable.ic_launcher);
        taname = (TextView) findViewById(R.id.tloc);
        trname = (TextView) findViewById(R.id.tloc1);
        tbname = (TextView) findViewById(R.id.tloc2);
        tadis = (TextView) findViewById(R.id.tdis);
        trdis = (TextView) findViewById(R.id.tdis1);
        tbdis = (TextView) findViewById(R.id.tdis2);
        Intent intent = getIntent();
        jstring = intent.getStringExtra("jsobjstring");
        //Toast.makeText(Details.this, jstring, Toast.LENGTH_SHORT).show();
        try {
            jobj = new JSONObject(jstring);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            amenities = jobj.getJSONObject("distanceFrom");
            amenities1 = jobj.getJSONObject("geoCoordinates");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            String aname, adis, bname, bdis, rname, rdis;
            adis = amenities.getString("airport");
            aname = amenities.getString("airportName");
            bdis = amenities.getString("busStand");
            bname = amenities.getString("busStandName");
            rdis = amenities.getString("railway");
            rname = amenities.getString("railwayStnName");
            taname.setText(aname);
            tadis.setText(adis + "Km");
            trname.setText(rname);
            trdis.setText(rdis + "Km");
            tbname.setText(bname);
            tbdis.setText(bdis + "Km");
            rname = amenities1.getString("lat");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rooms(View v) {
        Intent i = new Intent(Location.this, Rooms.class);
        Bundle b = new Bundle();
        b.putString("jsobjstring", jstring);
        i.putExtras(b);
        startActivity(i);
    }

    public void overview(View v) {
        Intent i = new Intent(Location.this, HotelDetails.class);
        Bundle b = new Bundle();
        b.putString("jsobjstring", jstring);
        i.putExtras(b);
        startActivity(i);
    }

    public void details(View v) {

        Intent i = new Intent(Location.this, Details.class);
        Bundle b = new Bundle();
        b.putString("jsobjstring", jstring);
        i.putExtras(b);
        startActivity(i);
    }


}