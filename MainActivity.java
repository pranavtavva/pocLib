package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//import java.util.*;

public class MainActivity extends AppCompatActivity {
    public static boolean onlyDigits(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.black));
        EditText s=(EditText) findViewById(R.id.username);
        EditText p=(EditText) findViewById(R.id.password);
        Button login=(Button) findViewById(R.id.login);
        final boolean[] f = new boolean[1];
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(s.getText().toString().equals("admin") ){
                    if(p.getText().toString().equals("vitap22")) {
                        Log.d("MainActivity", "onClick: s1");
                        Intent admin = new Intent(getApplicationContext(), Admin.class);

                        startActivity(admin);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                    }
                }

                else if(!TextUtils.isEmpty(s.getText().toString())&&onlyDigits(s.getText().toString())){

                    if(!TextUtils.isEmpty(p.getText().toString())) {
                        final int[] i1 = {0};
                        FirebaseDatabase.getInstance().getReference().child("Users").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                        Users i = snapshot1.getValue(Users.class);
                                        if (i.getUsername().equals(s.getText().toString()) && i.getPassword().equals(p.getText().toString())) {
                                            i1[0] =1;
                                            break;
                                        }
                                    }
                                }

                                if(i1[0] ==1){
                                    Intent faculty = new Intent(getApplicationContext(), Faculty.class);
                                    faculty.putExtra("id22",s.getText().toString());

                                    startActivity(faculty);
                                }
                                else{
                                    Toast.makeText(MainActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
               else{
                   if(!s.getText().toString().isEmpty()) {
                       final int[] i1 = {0};
                       FirebaseDatabase.getInstance().getReference().child("Users").addValueEventListener(new ValueEventListener() {
                           @Override
                           public void onDataChange(@NonNull DataSnapshot snapshot) {
                               if (snapshot.exists()) {
                                   for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                       Users i = snapshot1.getValue(Users.class);
                                       if (i.getUsername().equals(s.getText().toString()) && i.getPassword().equals(p.getText().toString())) {
                                           i1[0] =1;
                                           break;
                                       }
                                   }
                               }
                               if(i1[0]==1) {
                                   Intent student = new Intent(getApplicationContext(), Student.class);
                                   student.putExtra("id22",s.getText().toString());
//                                   student.putExtra()
                                   startActivity(student);
                               }
                               else{
                                   Toast.makeText(MainActivity.this, "Invalid UserName or Password", Toast.LENGTH_SHORT).show();
                               }
                           }

                           @Override
                           public void onCancelled(@NonNull DatabaseError error) {

                           }
                       });

                   }
//
                }
            }
        });


    }
}