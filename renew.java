package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class renew extends AppCompatActivity {
    private DatabaseReference mDatabase;
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
        setContentView(R.layout.activity_renew);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        EditText t = (EditText) findViewById(R.id.re);
        final String[] e = {""};
        final int[] i = {0};
        Intent i1 = getIntent();
        String s = i1.getStringExtra("currid");

        //today's date
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        String today=date.toString();

        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase.getInstance().getReference().child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            Users u = snapshot1.getValue(Users.class);
                            if (u.getUsername().equals(s)) {
                                String s = u.getBooks();
                                String arr[] = s.split(",");
                                for (int j = 0; j < arr.length; j++) {
                                    if (t.getText().toString().equals(arr[j])) {
                                        i[0] = j;
                                        break;
                                    }
                                }
                                e[0] = u.getDates();
                                String ar[] = e[0].split(",");
                                ar[i[0]] = today;
                                StringBuilder rs = new StringBuilder();
                                for (int i = 0; i < ar.length; i++) {
                                    rs.append(ar[i]);
                                    if (i != ar.length - 1)
                                        rs.append(",");
                                }

                                HashMap<String, Object> h = new HashMap<>();
                                h.put("dates", rs.toString());

                        FirebaseDatabase.getInstance().getReference().child("Users").child(snapshot1.getKey()).updateChildren(h).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                            }
                        });
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        //------------------------------------updatechildren--------------------------------------
//        HashMap<String, Object> h = new HashMap<>();
//        h.put("password","nithin12");
//        FirebaseDatabase.getInstance().getReference().child("Users").child("-NGbn72dktDiwwKCxNsN").updateChildren(h).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//                Toast.makeText(renew.this, "sdhfa", Toast.LENGTH_SHORT).show();
//            }
//        });

    }
}
