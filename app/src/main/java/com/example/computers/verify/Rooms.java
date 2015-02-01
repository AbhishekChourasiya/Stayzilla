package com.example.computers.verify;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Rooms extends ListActivity {
    Button bk;
    String jstring;
    JSONObject jobj;
    JSONArray roomsarr;
    String rid, rtype, rtariffDisp, rdiscount, rdiscountDisp, rdiscountPriceWithTax, roccupants, totalnoofrooms, isExtraBedType, withTax;
    int nrooms;
    int k = 0;
    String veg[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        getActionBar().setTitle("Stayzilla");

        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF4088")));

        getActionBar().setIcon(R.drawable.ic_launcher);
        jstring = intent.getStringExtra("jsobjstring");
        //Toast.makeText(Details.this, jstring, Toast.LENGTH_SHORT).show();
        try {
            jobj = new JSONObject(jstring);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            roomsarr = jobj.getJSONArray("rooms");
            nrooms = roomsarr.length();
            Toast.makeText(Rooms.this, "Type of rooms : " + nrooms, Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        veg=new String[nrooms];
        getActionBar().setTitle("Stayzilla");

        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF4088")));

        getActionBar().setIcon(R.drawable.ic_launcher);
        String s = "";
        Bundle b = getIntent().getExtras();   //to display contents of string array depending on value of s which we got from crop activity
        s = b.getString("selectedcrop");
        setListAdapter(new ArrayAdapter<String>(Rooms.this, android.R.layout.simple_list_item_1, veg));
        try {
            initialize();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void initialize() throws JSONException {

        for (int i = 0; i < nrooms; i++) {
            try {
                JSONObject c = roomsarr.getJSONObject(i);
                veg[i] = c.getString("rtype").toString();


            } catch (Exception e) {

            }
        }
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        super.onListItemClick(l, v, position, id);
        String label = veg[position];    //depending on value of k we send crop value to display2 activity
        Bundle b = new Bundle();

        try {
            b.putString("roomdet", roomsarr.getJSONObject(position).toString());
            b.putString("jsobjstring",jstring);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent i = new Intent(Rooms.this,RoomDetails.class);
        i.putExtras(b);
        startActivity(i);
    }
}
