package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class getbook extends AppCompatActivity {
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
                Toast.makeText(getApplicationContext(),"Logged Out",Toast.LENGTH_LONG).show();
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
public void execute(String key[],HashMap<String,Object> h){
    FirebaseDatabase.getInstance().getReference().child("Users").child(key[0]).updateChildren(h).addOnSuccessListener(new OnSuccessListener<Void>() {
        @Override
        public void onSuccess(Void unused) {
            Toast.makeText(getbook.this, "Success", Toast.LENGTH_SHORT).show();
        }
    });
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getbook);
        Intent i=getIntent();
        String userid=i.getStringExtra("id4321");
        EditText t=(EditText) findViewById(R.id.bookid22);
        Button b=(Button) findViewById(R.id.button4);
        HashMap<String,Object> h=new HashMap<>();
        final String[] key = {""};
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.black));
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("Users").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot snapshot1:snapshot.getChildren()){
                            Users u=snapshot1.getValue(Users.class);
                            if(u.getUsername().equals(userid)){
                                String books=u.getBooks();
                                books+=","+t.getText().toString();
                                String date1=u.getDates();
                                long millis = System.currentTimeMillis();   //today
                                java.sql.Date date = new java.sql.Date(millis);
                                String today=date.toString();
                                date1+=","+today;
                                h.put("books",books);
                                h.put("dates",date1);
                                h.put("username",userid);
                                h.put("password",u.getPassword());
                                key[0] =snapshot1.getKey();
//                                execute(key,h);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

//                try {
//                    Thread.sleep(15000);
//                    FirebaseDatabase.getInstance().getReference().child("Users").child(key[0]).updateChildren(h).addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void unused) {
//                            Toast.makeText(getbook.this, "Success", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                    FirebaseDatabase.getInstance().getReference().child("Users").child(key[0]).updateChildren(h).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getbook.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                    });
            }
        });
    }
}