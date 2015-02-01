package com.example.computers.verify;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends Activity {
    private DatePicker datePicker;
    private Calendar calendar1, calendar2;
    TextView ein, eout, tv;
    EditText aname, lat, lon;
    LinearLayout l1, l2;
    int count = 0;
    Button b;
    String in, out;
    String A[] = new String[5];
    String B[] = new String[5];
    SharedPreferences sharedPreferences, s1, s2, s3;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String name = "nameKey";
    public static final String pass = "passwordKey";
    public static final String id = "userid";
    public static final String name1 = "nameKey1";
    public static final String pass1 = "passwordKey1";
    public static final String id1 = "userid1";
    public static final String id2 = "two";
    private int year, month, day, i = 0, year2, month2, day2;

    //String indate="",outdate="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        getActionBar().setTitle("Stayzilla");

        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF4088")));

        getActionBar().setIcon(R.drawable.ic_launcher);

        aname = (EditText) findViewById(R.id.etcity);
        lat = (EditText) findViewById(R.id.lat);
        lon = (EditText) findViewById(R.id.longi);
        ein = (TextView) findViewById(R.id.in);
        eout = (TextView) findViewById(R.id.out);
        b = (Button) findViewById(R.id.search);
        l1 = (LinearLayout) findViewById(R.id.l1);
        l2 = (LinearLayout) findViewById(R.id.l2);
        tv = (TextView) findViewById(R.id.ch);
        count = 0;
        l2.setVisibility(View.GONE);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = df.format(c.getTime());
        ein.setText(formattedDate);
        eout.setText(formattedDate);
        String[] countries = getResources().
                getStringArray(R.array.city);
        ArrayAdapter adapter = new ArrayAdapter
                (this, android.R.layout.simple_list_item_1, countries);


    }


    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener1, year, month, day);
        } else if (id == 998) {
            return new DatePickerDialog(this, myDateListener2, year2, month2, day2);
        }
        return null;
    }

    public void change(View v) {
       // Toast.makeText(this, "jshfj", Toast.LENGTH_SHORT).show();
        if (count == 0) {
            count = 1;
            tv.setText("Search by city");
            l1.setVisibility(View.GONE);
            l2.setVisibility(View.VISIBLE);
        } else {
            count = 0;
            tv.setText("Search by co-ordinates");
            l1.setVisibility(View.VISIBLE);
            l2.setVisibility(View.GONE);
        }
    }


    private DatePickerDialog.OnDateSetListener myDateListener1
            = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            i = 0;


            showDate(i, arg1, arg2 + 1, arg3);
        }
    };
    private DatePickerDialog.OnDateSetListener myDateListener2
            = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day


            i = 1;
            showDate(i, arg1, arg2 + 1, arg3);
        }
    };


    private void showDate(int i, int year, int month, int day) {
        if (i == 0) {
            ein.setText(new StringBuilder().append(day).append("/")
                    .append(month).append("/").append(year));
            i++;
            // int l1=day.toString().length();
            String indate = day + "/" + month + "/" + year;
            A[0] = indate;
           // Toast.makeText(MainActivity.this, "indate : " + indate, Toast.LENGTH_SHORT).show();

        } else {
            i = 0;

            eout.setText(new StringBuilder().append(day).append("/")
                    .append(month).append("/").append(year));
            String outdate = day + "/" + month + "/" + year;
            A[1] = outdate;
           // Toast.makeText(MainActivity.this, "outdate : " + outdate, Toast.LENGTH_SHORT).show();
        }
    }

    public void search(View v) {
        String ename = aname.getText().toString();
        Bundle b = new Bundle();
        A[2] = ename;

        if (count == 0) {
            A[4] = "name";
            b.putStringArray("data", A);
            Intent i = new Intent(MainActivity.this, SearchResult.class);
            i.putExtras(b);
            startActivity(i);
        } else {
            A[4] = "cor";
            A[2] = lon.getText().toString();
            A[3] = lat.getText().toString();
            b.putStringArray("data", A);
            Intent i = new Intent(MainActivity.this, SearchResult.class);
            i.putExtras(b);
            startActivity(i);
        }
    }

    public void onResume() {
        count = 0;
        super.onResume();
    }

    public void in(View v) {
        calendar1 = Calendar.getInstance();
        year = calendar1.get(Calendar.YEAR);
        month = calendar1.get(Calendar.MONTH);
        day = calendar1.get(Calendar.DAY_OF_MONTH);
        showDialog(999);
        showDate(0, year, month + 1, day);

        //  Bundle b=this.getIntent().getExtras();
        // bname = b.getString("data");
    }

    public void out(View v) {
        calendar2 = Calendar.getInstance();
        year2 = calendar2.get(Calendar.YEAR);
        month2 = calendar2.get(Calendar.MONTH);
        day2 = calendar2.get(Calendar.DAY_OF_MONTH);
        showDialog(998);
        showDate(1, year2, month2 + 1, day2);

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
