package com.example.computers.verify;

/**
 * Created by Aman on 1/11/2015.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
public class hotelsearch extends Activity implements OnItemClickListener {
    private Bitmap bmp;
    HashSet <String> hs = new HashSet <String>();
    int k=0;
    List<RowItem> rowItems;
    ListView mylistview;
    String jstring,getchkin,getchkout;
    JSONArray jarray;
    int lengthJarray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        jstring = intent.getStringExtra("jsonArray");
        getchkin=intent.getStringExtra("chekin");
        getchkout=intent.getStringExtra("chekout");
        //Toast.makeText(this,getchkin+" "+getchkout,Toast.LENGTH_SHORT).show();
        try {
            jarray = new JSONArray(jstring);
            //System.out.println(jarray.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        lengthJarray=jarray.length();
        getActionBar().setTitle("Stayzilla");

        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF4088")));

        getActionBar().setIcon(R.drawable.ic_launcher);

        // Toast.makeText(hotelsearch.this, "No. of hotels :  " + lengthJarray, Toast.LENGTH_SHORT).show();
        setContentView(R.layout.list_result);
        ProgressDialog pd;
        pd= ProgressDialog.show(hotelsearch.this, "", "Hang on. Loading data..", true, false);
        initialize();
        pd.dismiss();
        mylistview = (ListView) findViewById(R.id.list);
        com.example.computers.verify.CustomAdapter adapter = new com.example.computers.verify.CustomAdapter(this, rowItems);
        mylistview.setAdapter(adapter);
        //profile_pics.recycle();
        mylistview.setOnItemClickListener(this);

    }

    @Override
    //intent start karna hai.........
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        //final String member = rowItems.get(position).getDisplayName();
      //  Toast.makeText(hotelsearch.this, "position :  "+ position, Toast.LENGTH_SHORT).show();

        Intent ola = new Intent(hotelsearch.this, HotelDetails.class);

        Bundle bundle =new Bundle();
        try {
            JSONObject d = jarray.getJSONObject(position);
            //Toast.makeText(hotelsearch.this,"locha nhi",Toast.LENGTH_SHORT).show();
            //String cx= d.toString();
            bundle.putString("jsobjstring", d.toString());
        }
        catch(Exception e){
            //Toast.makeText(hotelsearch.this,"locha",Toast.LENGTH_SHORT).show();
        }

        bundle.putString("chekin",getchkin);
        bundle.putString("chekout",getchkout);
        ola.putExtras(bundle);
        startActivity(ola);
    }

    public void initialize() {
        rowItems = new ArrayList<RowItem>();

        for (int i = 0; i < lengthJarray; i++) {
            try {
                JSONObject c = jarray.getJSONObject(i);
                try {

                   // if(!(hs.contains(c.getString("displayName")))) {
                        //Boolean x=hs.contains(c.getString("id"));
                       bmp=null;
                        InputStream in = new URL(c.getString("imageURL")).openStream();

                        bmp = BitmapFactory.decodeStream(in);

                      //  hs.add(c.getString("displayName"));
                    }

                 catch (Exception e) {
                    // log error
                }
                finally{
                   // Toast.makeText(hotelsearch.this, "display NAme :  " + c.getString("displayName"), Toast.LENGTH_LONG).show();

                    RowItem item = new RowItem(c.getString("displayName"),bmp, c.getString("address"), c.getString("price"), c.getString("starRating"));

                    rowItems.add(item);
                }



                //Toast.makeText(hotelsearch.this, "HS :  "+ hs, Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }




        }
    }

}