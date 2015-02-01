package com.example.computers.verify;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Aman on 2/1/2015.
 */

public class Details extends Activity {
    ImageView i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16;
    TextView t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16;
    String jstring, rest, bar, coffee, biz, swim, internet, laundary, veg, credit, news, health, pickup, elevator, parking, checkin, checkout;
    JSONObject jobj, amenities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.details);
        getActionBar().setTitle("Stayzilla");

        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF4088")));

        getActionBar().setIcon(R.drawable.ic_launcher);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        i1 = (ImageView) findViewById(R.id.icheckin);
        i2 = (ImageView) findViewById(R.id.icheckout);
        i3 = (ImageView) findViewById(R.id.ibar);
        i4 = (ImageView) findViewById(R.id.ibiz);
        i5 = (ImageView) findViewById(R.id.icoffee);
        i6 = (ImageView) findViewById(R.id.icreditcard);
        i7 = (ImageView) findViewById(R.id.ielevator);
        i8 = (ImageView) findViewById(R.id.inewspaper);
        i9 = (ImageView) findViewById(R.id.ihealth);
        i10 = (ImageView) findViewById(R.id.internet);
        i11 = (ImageView) findViewById(R.id.ilaundary);
        i12 = (ImageView) findViewById(R.id.iparking);
        i13 = (ImageView) findViewById(R.id.ipickup);
        i14 = (ImageView) findViewById(R.id.ipureveg);
        i15 = (ImageView) findViewById(R.id.irest);
        i16 = (ImageView) findViewById(R.id.iswim);

        t1 = (TextView) findViewById(R.id.tcheckin);
        //  t1.setText("CheckIn");
        t2 = (TextView) findViewById(R.id.tcheckout);
        t3 = (TextView) findViewById(R.id.tbar);
        t4 = (TextView) findViewById(R.id.tbiz);
        t5 = (TextView) findViewById(R.id.tcoffee);
        t6 = (TextView) findViewById(R.id.tcreditcard);
        t7 = (TextView) findViewById(R.id.televator);
        t8 = (TextView) findViewById(R.id.tnewspaper);
        t9 = (TextView) findViewById(R.id.thealth);
        t10 = (TextView) findViewById(R.id.tinternet);
        t11 = (TextView) findViewById(R.id.tlaundary);
        t12 = (TextView) findViewById(R.id.tparking);
        t13 = (TextView) findViewById(R.id.tpickup);
        t14 = (TextView) findViewById(R.id.tpureveg);
        t15 = (TextView) findViewById(R.id.trest);
        t16 = (TextView) findViewById(R.id.tswim);


        Intent intent = getIntent();
        jstring = intent.getStringExtra("jsobjstring");
        //Toast.makeText(Details.this, jstring, Toast.LENGTH_SHORT).show();
        try {
            jobj = new JSONObject(jstring);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            amenities = jobj.getJSONObject("amenities");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            rest = amenities.getString("restaurant");
            bar = amenities.getString("bar");
           // Toast.makeText(Details.this, "if" + rest, Toast.LENGTH_SHORT).show();
            coffee = amenities.getString("coffee");
            biz = amenities.getString("biz");
            swim = amenities.getString("swim");
            internet = amenities.getString("internet");
            credit = amenities.getString("creditCard");
            laundary = amenities.getString("laundry");
            pickup = amenities.getString("pickupAndDrop");
            health = amenities.getString("healthClub");
            news = amenities.getString("freeNewspaper");
            elevator = amenities.getString("elevator");
            veg = amenities.getString("pureVeg");
            parking = amenities.getString("parking");
            checkin = amenities.getString("twenty4HourCheckIn");

            checkout = amenities.getString("twenty4HourCheckOut");
            if (rest.contentEquals("true")) {
               // Toast.makeText(Details.this, "if" + rest, Toast.LENGTH_SHORT).show();
                i15.setImageResource(R.drawable.restuarant_dark);
            } else {
               // Toast.makeText(Details.this, "else" + rest, Toast.LENGTH_SHORT).show();
                i15.setImageResource(R.drawable.restuarant_grey);
            }

            if (bar.contentEquals("true")) {
                i3.setImageResource(R.drawable.bar_dark);
            } else {
                i3.setImageResource(R.drawable.bar_grey);
            }
            if (coffee.contentEquals("true")) {
                i5.setImageResource(R.drawable.coffee_dark);
            } else {
                i5.setImageResource(R.drawable.coffee_grey);
            }
            if (biz.contentEquals("true")) {
                i4.setImageResource(R.drawable.biz_dark);
            } else {
                i4.setImageResource(R.drawable.biz_grey);
            }
            if (swim.contentEquals("true")) {
                i16.setImageResource(R.drawable.swim_dark);
            } else {
                i16.setImageResource(R.drawable.swim_grey);
            }
            if (internet.contentEquals("true")) {
                i10.setImageResource(R.drawable.internet_dark);
            } else {
                i10.setImageResource(R.drawable.internet_grey);
            }
            if (credit.contentEquals("true")) {
                i6.setImageResource(R.drawable.credit_card_dark);
            } else {
                i6.setImageResource(R.drawable.credit_card_grey);
            }
            if (laundary.contentEquals("true")) {
                i11.setImageResource(R.drawable.laundary_dark);
            } else {
                i11.setImageResource(R.drawable.laundry_grey);
            }
            if (pickup.contentEquals("true")) {
                i13.setImageResource(R.drawable.pickup_drop_dark);
            } else {
                i13.setImageResource(R.drawable.pickup_drop_grey);
            }
            if (health.contentEquals("true")) {
                i9.setImageResource(R.drawable.health_care_dark);
            } else {
                i9.setImageResource(R.drawable.health_care_grey);
            }
            if (news.contentEquals("true")) {
                i8.setImageResource(R.drawable.free_newspaper_dark);
            } else {
                i8.setImageResource(R.drawable.free_newspaper_grey);
            }
            if (elevator.contentEquals("true")) {
                i7.setImageResource(R.drawable.elevator_dark);
            } else {
                i7.setImageResource(R.drawable.elevator_grey);
            }
            if (veg.contentEquals("true")) {
                i14.setImageResource(R.drawable.pure_veg_dark);
            } else {
                i14.setImageResource(R.drawable.pure_veg_grey);
            }
            if (parking.contentEquals("true")) {
                i12.setImageResource(R.drawable.parking_dark);
            } else {
                i12.setImageResource(R.drawable.parking_grey);
            }
            if (checkin.contentEquals("true")) {
                i1.setImageResource(R.drawable.checkin_dark);
            } else {
                i1.setImageResource(R.drawable.checkin_grey);
            }
            if (checkout.contentEquals("true")) {
                i2.setImageResource(R.drawable.checkout_dark);
            } else {
                i2.setImageResource(R.drawable.checkout_grey);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void overview(View v){
        Intent i = new Intent(Details.this, HotelDetails.class);
        Bundle b = new Bundle();
        b.putString("jsobjstring", jstring);
        i.putExtras(b);
        startActivity(i);
    }
    public void rooms(View v){
        Intent i = new Intent(Details.this, Rooms.class);
        Bundle b = new Bundle();
        b.putString("jsobjstring", jstring);
        i.putExtras(b);
        startActivity(i);
    }
    public void location(View v){
        Intent i = new Intent(Details.this, com.example.computers.verify.Location.class);
        Bundle b = new Bundle();
        b.putString("jsobjstring", jstring);
        i.putExtras(b);
        //Toast.makeText(this,"location me",Toast.LENGTH_SHORT).show();
        startActivity(i);
    }
}