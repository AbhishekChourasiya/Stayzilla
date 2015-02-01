package com.example.computers.verify;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by computers on 1/31/2015.
 */
public class SearchResult  extends Activity{
    String s=null;
    String D[]=new String[5];
    HttpClient http;
    JSONObject ob,c;
    TextView t;
    static String json = "";
    static JSONObject jObj = null;
    //String a="";
    JSONArray user = null;
    private Handler handler;
    String zy,id,xx;

 //   private String URL_MAIN = "http://180.92.168.7/hotels'%20data%20'%20location=";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        getActionBar().setTitle("Stayzilla");

        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF4088")));

        getActionBar().setIcon(R.drawable.ic_launcher);



        setContentView(R.layout.res);
        Bundle b=getIntent().getExtras();
        D=b.getStringArray("data");
       // Toast.makeText(SearchResult.this,"D[0] : "+D[0],Toast.LENGTH_SHORT).show();
       // Toast.makeText(SearchResult.this,"D[1] : "+D[1],Toast.LENGTH_SHORT).show();
      //  Toast.makeText(SearchResult.this,"D[4] : "+D[4],Toast.LENGTH_SHORT).show();
        t=(TextView)findViewById(R.id.tres);

        http=new DefaultHttpClient();
        handler = new Handler();
        new Read().execute();
    }
    public JSONObject retrieve() throws ClientProtocolException, IOException, JSONException {
//Ooty%20&checkin=06/02/2015&checkout=09/02/2015&property_type=Hotels
       // StringBuilder URL=new StringBuilder(URL_MAIN);
        //   String r="&mode=xml&units=metric&cnt=7";
   //     URL.append(D[0]+"%20&checkin="+D[1]+"&checkout="+D[2]+"&property_type=Hotels");
     //   String web=URL.toString();
        // web="http://180.92.168.7/hotels'%20data%20'%20location=Ooty%20&checkin=06/02/2015&checkout=09/02/2015&property_type=Hotels";
        //HttpGet get=new HttpGet(web);
        //HttpPost get=new HttpPost(web);
        //HttpResponse r=http.execute(get);


        //Toast.makeText(SearchResult.this,"inside retrieve",Toast.LENGTH_SHORT).show();
        try {
            HttpPost post = new HttpPost("http://180.92.168.7/hotels");
            //Toast.makeText(SearchResult.this,"posting",Toast.LENGTH_SHORT).show();
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
            nameValuePairs.add(new BasicNameValuePair("location",D[2]));
            nameValuePairs
                    .add(new BasicNameValuePair("checkin", D[0]));
            nameValuePairs.add(new BasicNameValuePair("checkout", D[1]));
            nameValuePairs.add(new BasicNameValuePair("property_type",
                    "Hotels"));
           // Toast.makeText(SearchResult.this,"abc",Toast.LENGTH_SHORT).show();
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = http.execute(post);
           // Toast.makeText(SearchResult.this,"xyz",Toast.LENGTH_SHORT).show();

            int status=response.getStatusLine().getStatusCode();
            if(status == 200)
            {
                HttpEntity e=response.getEntity();
               // String data= EntityUtils.toString(e);
                //Toast.makeText(SearchResult.this,data,Toast.LENGTH_SHORT).show();
                //JSONArray time=new JSONArray("hotels");
                //JSONObject l=time.getJSONObject(1);
               // Toast.makeText(SearchResult.this,data,Toast.LENGTH_SHORT).show();

                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                StringBuilder sb = new StringBuilder();
                while ((line = rd.readLine()) != null) {
                    sb.append(line + "n");
                }
                //xx=sb.toString();
                json = sb.toString();

                 jObj = new JSONObject(json);

                return jObj;
            }
            else
            {
                //Toast.makeText(SearchResult.this,"Error",Toast.LENGTH_SHORT).show();
                return null;
            }

        }
        catch (IOException e) {
            //Toast.makeText(SearchResult.this,"ErrorCatch",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return null;
    }

    private JSONObject retrieve1() throws ClientProtocolException, IOException, JSONException {
//Ooty%20&checkin=06/02/2015&checkout=09/02/2015&property_type=Hotels
        // StringBuilder URL=new StringBuilder(URL_MAIN);
        //   String r="&mode=xml&units=metric&cnt=7";
        //     URL.append(D[0]+"%20&checkin="+D[1]+"&checkout="+D[2]+"&property_type=Hotels");
        //   String web=URL.toString();
        // web="http://180.92.168.7/hotels'%20data%20'%20location=Ooty%20&checkin=06/02/2015&checkout=09/02/2015&property_type=Hotels";
        //HttpGet get=new HttpGet(web);
        //HttpPost get=new HttpPost(web);
        //HttpResponse r=http.execute(get);


        //Toast.makeText(SearchResult.this,"inside retrieve",Toast.LENGTH_SHORT).show();
        try {
            HttpPost post = new HttpPost("http://180.92.168.7/hotels");
            //Toast.makeText(SearchResult.this,"posting",Toast.LENGTH_SHORT).show();
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);
            nameValuePairs
                    .add(new BasicNameValuePair("checkin", D[0]));
            nameValuePairs.add(new BasicNameValuePair("checkout", D[1]));
            nameValuePairs.add(new BasicNameValuePair("property_type",
                    "Hotels"));
            nameValuePairs.add(new BasicNameValuePair("lat",D[3]));
            nameValuePairs.add(new BasicNameValuePair("lng",D[2]));
            // Toast.makeText(SearchResult.this,"abc",Toast.LENGTH_SHORT).show();
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = http.execute(post);
            // Toast.makeText(SearchResult.this,"xyz",Toast.LENGTH_SHORT).show();

            int status=response.getStatusLine().getStatusCode();
            if(status == 200)
            {
                HttpEntity e=response.getEntity();
                // String data= EntityUtils.toString(e);
                //Toast.makeText(SearchResult.this,data,Toast.LENGTH_SHORT).show();
                //JSONArray time=new JSONArray("hotels");
                //JSONObject l=time.getJSONObject(1);
                // Toast.makeText(SearchResult.this,data,Toast.LENGTH_SHORT).show();

                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                StringBuilder sb = new StringBuilder();
                while ((line = rd.readLine()) != null) {
                    sb.append(line + "n");
                }
                //xx=sb.toString();
                json = sb.toString();

                jObj = new JSONObject(json);

                return jObj;
            }
            else
            {
                //Toast.makeText(SearchResult.this,"Error",Toast.LENGTH_SHORT).show();
                return null;
            }

        }
        catch (IOException e) {
            //Toast.makeText(SearchResult.this,"ErrorCatch",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return null;

    }


    class Read extends AsyncTask<Void, Void, Void> {
        ProgressDialog pd;  //use asynctask because retrieving data takes some time

        //implements methods of asynctask
        @Override
        protected void onPreExecute() {
            //Toast.makeText(SearchResult.this, "Starting", Toast.LENGTH_SHORT).show();
            pd=ProgressDialog.show(SearchResult.this,"","Hang on. Loading data..",true,false);
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            try {
                if(D[4].contentEquals("name")) {
                    ob = retrieve();
                }
                else
                {
                    ob = retrieve1();
                }

//               // Toast.makeText(SearchResult.this,"retrieved",Toast.LENGTH_SHORT).show();
                    //s=ob.getString("city");

                    user = ob.getJSONArray("hotels");
                    c = user.getJSONObject(1);
                    // Storing  JSON item in a Variable
                    //id = user.getString("noOfHotels");
                    zy = c.getString("displayName");



            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //Toast.makeText(SearchResult.this,"retrieved " + zy,Toast.LENGTH_SHORT).show();
            pd.dismiss();
            //t.setText(zy);
            Bundle b=new Bundle();
            Intent intent = new Intent(SearchResult.this, hotelsearch.class);
            b.putString("jsonArray", user.toString());


            b.putString("chekin",D[0]);
            b.putString("chekout",D[1]);
            intent.putExtras(b);
            startActivity(intent);
        }
    }

}