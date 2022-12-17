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

public class Add_Book extends AppCompatActivity {
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
        setContentView(R.layout.activity_add_book);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EditText id2=(EditText) findViewById(R.id.bookid);
        EditText b_name=(EditText) findViewById(R.id.bookname);
        EditText author2=(EditText) findViewById(R.id.authorname);
        EditText c_avail=(EditText) findViewById(R.id.copiesavail);
        Button add_book=(Button) findViewById(R.id.addbooks);
        add_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,Object> h=new HashMap<>();
                h.put("book_id",id2.getText().toString());
                h.put("book_name",b_name.getText().toString());
                h.put("author",author2.getText().toString());
                h.put("copies",c_avail.getText().toString());
                h.put("usernames","");
                h.put("dates","");
                FirebaseDatabase.getInstance().getReference().child("books").push().setValue(h);
                Toast.makeText(Add_Book.this, "Book Added Successfully", Toast.LENGTH_SHORT).show();
                id2.setText("");
                b_name.setText("");
                author2.setText("");
                c_avail.setText("");
            }
        });
    }
}