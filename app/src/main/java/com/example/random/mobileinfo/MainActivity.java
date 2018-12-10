package com.example.random.mobileinfo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button btn;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    ViewSwitcher realViewSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btnReport);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String date = prefs.getString("Date_","");
        if(date.equals(""))
        {
            Date c = Calendar.getInstance().getTime();

            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            String formattedDate = df.format(c);
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString("Date_", formattedDate);
            editor.apply();
        }

        int[] graphData = {3,5,2,7,4,8,1,5,9};

//        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
//        MyView graphView = new MyView(this);
//        ll.addView(graphView);


        //call this method with every new set of data
//        graphView.drawGraph(graphData);

    }

    public void btnreport(View v){
        Intent i = new Intent(this,report.class);
        startActivity(i);
    }


}
