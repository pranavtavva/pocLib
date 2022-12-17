package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class mybooksdisp extends AppCompatActivity {
    //array-hashset

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybooksdisp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();
        String s = i.getStringExtra("bookids");
        String d=i.getStringExtra("dates2121");
        if (s == null) {
            Toast.makeText(this, "No books", Toast.LENGTH_SHORT).show();
        }
        else {
            String[] ids = s.split(",");
            String [] dates=d.split(",");
            HashMap<String,String> h=new HashMap<>();

            for(int j=0;j<ids.length;j++){
                h.put(ids[j],dates[j]);
            }

            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.mybooks);

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            ArrayList<bookd> l = new ArrayList<>();
            MyListAdapter adapter = new MyListAdapter(this, l);
            recyclerView.setAdapter(adapter);

            FirebaseDatabase.getInstance().getReference().child("books").addValueEventListener(new ValueEventListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        bookd b = dataSnapshot.getValue(bookd.class);
                        if (h.containsKey(b.getBook_id())) {
                            b.setDates(h.get(b.getBook_id()));
                            l.add(b);
                        }
                    }

                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
    }
}