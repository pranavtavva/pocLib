package com.example.myapplication;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
//

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class fine extends AppCompatActivity {
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.item211:
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                Toast.makeText(getApplicationContext(),"Logged out",Toast.LENGTH_LONG).show();
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

        static long find(String join_date, String leave_date) {
        SimpleDateFormat obj = new SimpleDateFormat("yyyy-MM-dd");


        try {
            Date date1 = obj.parse(join_date);
            Date date2 = obj.parse(leave_date);
            long time_difference = date2.getTime() - date1.getTime();
            long days_difference = (time_difference / (1000*60*60*24)) % 365;
            return days_difference;
        }
        catch (ParseException excep) {
            excep.printStackTrace();
        }
        return 0;
        }
    private long findfine(String[] arr,ArrayList<Integer> pos) {
        long diff=0;
        long fine=0;
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        String today=date.toString();
//        for (String s : arr) {
        for(int i=0;i< arr.length;i++){
            String s=arr[i];
            diff= find(s, today);
            if(diff>15){
                pos.add(i);
                fine+=10*(diff-15);
            }
        }
        return fine;
    }
//    private long findfine(String[] arr) {
//        long diff=0;
//        long fine=0;
//        long millis = System.currentTimeMillis();
//        java.sql.Date date = new java.sql.Date(millis);
//        String today=date.toString();
//        for (String s : arr) {
//            diff= find(s, today);
//            if(diff>15){
//                fine+=10*(diff-15);
//            }
//        }
//        return fine;
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fine);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();
        String dates = i.getStringExtra("dates");
        String bookids = i.getStringExtra("bookids");
        String arr[]=dates.split(",");
        String boo[]=bookids.split(",");
        if (dates == null) {
            Toast.makeText(this, "No Fines", Toast.LENGTH_SHORT).show();
        }
        else {
            ArrayList<Integer> pos=new ArrayList<>();
            long res = findfine(arr,pos);
            TextView t = (TextView) findViewById(R.id.textView3);
            String ans="";
            for(int j=0;j<pos.size();j++){
                if(j!=pos.size()-1) {
                    ans += boo[pos.get(j)];
                    ans+=",";
                }
                else{
                    ans += boo[pos.get(j)];
                }
            }
            if(!ans.isEmpty())
            t.setText("This books hasn't returned yet- "+ans+" So, Fine = Rs."+String.valueOf(res));
            else{
                t.setText("Nothing");
            }
        }
    }
}