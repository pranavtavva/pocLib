package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Admin extends AppCompatActivity {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        CardView c1=(CardView) findViewById(R.id.c166);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.black));

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //adding a new user in db
                Intent i1=new Intent(getApplicationContext(),Add_user.class);
                startActivity(i1);

            }
        });
        CardView c2=(CardView) findViewById(R.id.c167);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Remove user from db
                Intent i2=new Intent(getApplicationContext(),Remove_user.class);
                startActivity(i2);

            }
        });

        CardView c3=(CardView) findViewById(R.id.c168);
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add book
                Intent i3=new Intent(getApplicationContext(),Add_Book.class);
                startActivity(i3);

            }
        });

        CardView c4=(CardView) findViewById(R.id.c169);
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //remove book
                Intent i3=new Intent(getApplicationContext(),removebook.class);
                startActivity(i3);

            }
        });

        CardView c5=(CardView) findViewById(R.id.c170);
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //update book details
                Intent i=new Intent(getApplicationContext(),updatebook.class);
                startActivity(i);

            }
        });

        CardView c6=findViewById(R.id.c171);
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //response to feedback and support--assistance
            }
        });
    }
}