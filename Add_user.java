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

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Add_user extends AppCompatActivity {
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
                Toast.makeText(getApplicationContext(),"Logout Selected",Toast.LENGTH_LONG).show();
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        EditText u=(EditText) findViewById(R.id.u);
        EditText p=(EditText) findViewById(R.id.p);
        Button add=(Button) findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,Object> user=new HashMap<>();
                user.put("username",u.getText().toString());
                user.put("password",p.getText().toString());
                user.put("dates","");
                user.put("books","");


                FirebaseDatabase.getInstance().getReference().child("Users").push().setValue(user);
                Toast.makeText(Add_user.this, "User Added Successfully", Toast.LENGTH_SHORT).show();
                u.setText("");
                p.setText("");
            }
        });


    }
}