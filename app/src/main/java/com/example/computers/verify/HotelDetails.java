package com.example.computers.verify;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by SaurabhAgrawal on 2/1/2015.
 */
public class HotelDetails extends Activity {
    int ratingValue;
    String jstring,simage,saddress,sprice,chekin,chekout,srating,htmsg,htmsg2,shotelname,ch,ch2,chint,choutt;
    JSONObject jobj,obj2,obj3,obj4;
    Bitmap cvpic;
    InputStream in;
    ImageView iv;
    TextView setaddress,setprice,tchekin,tchekout,htmlTextView;
    RatingBar txtrating ;

    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        setContentView(R.layout.hoteldetail);



        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF4088")));

        getActionBar().setIcon(R.drawable.ic_launcher);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        jstring = intent.getStringExtra("jsobjstring");
        chekin=intent.getStringExtra("chekin");
        chekout=intent.getStringExtra("chekout");
        //Toast.makeText(this,chekin+" "+chekout,Toast.LENGTH_SHORT).show();
        initialize();
        getActionBar().setTitle(shotelname);

    }
    public void details(View v){
        Intent i = new Intent(HotelDetails.this, Details.class);
        Bundle b = new Bundle();
        b.putString("jsobjstring", jstring);
        i.putExtras(b);
        startActivity(i);
    }
    public void room(View v){
        Intent i = new Intent(HotelDetails.this, Rooms.class);
        Bundle b = new Bundle();
        b.putString("jsobjstring", jstring);
        i.putExtras(b);
        startActivity(i);
    }
    public void location(View v){
        Intent i = new Intent(HotelDetails.this, com.example.computers.verify.Location.class);
        Bundle b = new Bundle();
        b.putString("jsobjstring", jstring);
        i.putExtras(b);
       // Toast.makeText(this,"location me",Toast.LENGTH_SHORT).show();
        startActivity(i);
    }
    public  void initialize(){
        try {
            jobj = new JSONObject(jstring);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Toast.makeText(HotelDetails.this, jstring, Toast.LENGTH_LONG).show();
        try {
            saddress = jobj.getString("address");
            simage = jobj.getString("imageURL");
            sprice= jobj.getString("price");
            srating= jobj.getString("starRating");
            htmsg= jobj.getString("info");

            obj2 = new JSONObject(htmsg);

            htmsg2=obj2.getString("incl");
            ch=obj2.getString("chkin");
            ch2=obj2.getString("chkout");
            obj3= new JSONObject(ch);
            obj4= new JSONObject(ch2);
            chint=obj3.getString("h")+" : "+obj3.getString("m");
            choutt=obj4.getString("h")+" : "+obj4.getString("m");
            ratingValue = Integer.parseInt(srating);
            shotelname= jobj.getString("displayName");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
             in = new URL(simage).openStream();
            cvpic = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            // log error
        }
        iv=(ImageView)findViewById(R.id.ihotel);
        iv.setImageBitmap(cvpic);
        //Toast.makeText(HotelDetails.this, "displayName : " + htmsg2, Toast.LENGTH_LONG).show();

        setaddress=(TextView)findViewById(R.id.taddress);
        setaddress.setText(saddress);
        setprice=(TextView)findViewById(R.id.tprice);
        setprice.setText(sprice);
        tchekin=(TextView)findViewById(R.id.idchekin);
        tchekin.setText(chint);
        tchekout=(TextView)findViewById(R.id.idchekout);
        tchekout.setText(choutt);
        txtrating = (RatingBar)findViewById(R.id.tbook);
        txtrating.setRating(ratingValue);
        LayerDrawable stars = (LayerDrawable) txtrating.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        htmlTextView = (TextView)findViewById(R.id.idhtmsg);
        htmlTextView.setText(Html.fromHtml(htmsg2));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
