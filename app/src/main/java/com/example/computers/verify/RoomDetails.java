package com.example.computers.verify;

/**
 * Created by SaurabhAgrawal on 2/1/2015.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Aman on 2/1/2015.
 */
public class RoomDetails extends Activity {
    TextView mprice,drprice,rcp,tr,eb,rmtype;

    String mprice2,drprice2,rcp2,tr2,eb2,rmtype2;
    String jstring,jstring1;
    JSONObject jobj;
    protected void onCreate(Bundle savedInstanceState) {

       /* requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rooms);
        getActionBar().setTitle("Stayzilla");

        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF4088")));

        getActionBar().setIcon(R.drawable.ic_launcher);
        mprice=(TextView)findViewById(R.id.tp1);
        drprice=(TextView)findViewById(R.id.tp2);
        rcp=(TextView)findViewById(R.id.roccupt);
        tr=(TextView)findViewById(R.id.trooms);
        eb=(TextView)findViewById(R.id.ebed);
rmtype=(TextView)findViewById(R.id.rmtype);
        Intent intent = getIntent();
        jstring = intent.getStringExtra("roomdet");
        jstring1 = intent.getStringExtra("jsobjstring");

        try {
            jobj = new JSONObject(jstring);

            rmtype.setText(jobj.getString("rtariffDisp"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try{

            mprice2=jobj.getString("rtariffDisp");
            mprice.setText(mprice2);
            rmtype2=jobj.getString("rtype");
            rmtype.setText(rmtype2);
            drprice2=jobj.getString("rdiscountDisp");
            tr2=jobj.getString("totalnoofrooms");
            tr.setText(tr2);
            drprice.setText(drprice2);
            rcp2=jobj.getString("roccupants");
            rcp.setText(rcp2);
            if(jobj.getString("isExtraBedType").equalsIgnoreCase("true"))
                    eb.setText("Available");
            else
                    eb.setText("Not available");


        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void details(View v){
        Intent i = new Intent(RoomDetails.this, com.example.computers.verify.Details.class);

        Bundle b = new Bundle();
        b.putString("jsobjstring", jstring1);
        i.putExtras(b);
        startActivity(i);
    }
    public void overview(View v){
        Intent i = new Intent(RoomDetails.this, com.example.computers.verify.HotelDetails.class);
        Bundle b = new Bundle();
        b.putString("jsobjstring", jstring1);
        i.putExtras(b);
        startActivity(i);
    }
    public void location(View v){
        Intent i = new Intent(RoomDetails.this, com.example.computers.verify.Location.class);
        Bundle b = new Bundle();
        b.putString("jsobjstring", jstring1);
        i.putExtras(b);
       // Toast.makeText(this, "location me", Toast.LENGTH_SHORT).show();
        startActivity(i);
    }
}