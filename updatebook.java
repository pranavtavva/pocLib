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

public class updatebook extends AppCompatActivity {
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
        setContentView(R.layout.activity_updatebook);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        EditText t1=(EditText) findViewById(R.id.idu);
        EditText t2=(EditText) findViewById(R.id.bookname_u);
        EditText t3=(EditText) findViewById(R.id.author_u);
        EditText t4=(EditText) findViewById(R.id.copies_u);
        Button update=(Button) findViewById(R.id.update_u);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("books").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot snapshot1:snapshot.getChildren()){
                            bookd b=snapshot1.getValue(bookd.class);
                            if(t1.getText().toString().equals(b.getBook_id())){
                                HashMap<String,Object> h=new HashMap<>();
                                if(!t2.getText().toString().isEmpty())
                                h.put("book_name",t2.getText().toString());
                                if(!t3.getText().toString().isEmpty())
                                    h.put("author",t3.getText().toString());
                                if(!t4.getText().toString().isEmpty())
                                    h.put("copies",t4.getText().toString());
                                FirebaseDatabase.getInstance().getReference().child("books").child(snapshot1.getKey()).updateChildren(h).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(updatebook.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
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
    }
}