package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Faculty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);
        Intent i=getIntent();
        getWindow().setNavigationBarColor(getResources().getColor(R.color.black));

        String curid=i.getStringExtra("id22");
//        TextView t=(TextView) findViewById(R.id.textView2);

        CardView c11=(CardView) findViewById(R.id.c166);
        c11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //display books in recycler view
                Intent b1=new Intent(getApplicationContext(),books.class);
                b1.putExtra("id4321",curid);
                startActivity(b1);

            }
        });
//        Toast.makeText(this, "id"+curid, Toast.LENGTH_SHORT).show();
        CardView c12=(CardView) findViewById(R.id.c167);
        c12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Renew book
                Intent i2=new Intent(getApplicationContext(),renew.class);
                i2.putExtra("currid",curid);
                startActivity(i2);

            }
        });

        CardView c13=(CardView) findViewById(R.id.c169);
        c13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Calculate Fine
                FirebaseDatabase.getInstance().getReference().child("Users").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot snapshot1:snapshot.getChildren()){
                            Users u=snapshot1.getValue(Users.class);
                            if(u.getUsername().equals(curid)){
                                Intent b3=new Intent(getApplicationContext(),fine.class);
                                b3.putExtra("bookids",u.getBooks());
                                b3.putExtra("dates",u.getDates());
                                startActivity(b3);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

            }
        });

        CardView c14=(CardView) findViewById(R.id.c171);
        c14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Suggestions and Feedback
                Intent b4=new Intent(getApplicationContext(),student_suggestion.class);
                startActivity(b4);

            }
        });
        CardView c221=(CardView) findViewById(R.id.c170);
        c221.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Premium content
                Intent b4=new Intent(getApplicationContext(),premiumbooks.class);
                startActivity(b4);
            }
        });

        CardView c222=(CardView) findViewById(R.id.c168);
        c222.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mybooks
                FirebaseDatabase.getInstance().getReference().child("Users").addValueEventListener(new ValueEventListener() {
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()) {
                            for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                Users u = snapshot1.getValue(Users.class);
                                if(u.getUsername().equals(curid)){
                                    Intent i=new Intent(getApplicationContext(),mybooksdisp.class);
                                    i.putExtra("bookids",u.getBooks());
                                    startActivity(i);
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }
}