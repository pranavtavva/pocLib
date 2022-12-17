package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
    Context context;
    ArrayList<bookd> l;

    public MyListAdapter(Context context, ArrayList<bookd> l){
        this.l=l;
        this.context=context;
    }

        @NonNull
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            View v=LayoutInflater.from(context).inflate(R.layout.books_view,parent,false);
            return new ViewHolder(v);
        }
        public void onBindViewHolder(ViewHolder holder, int position) {
            bookd b=l.get(position);
            holder.name.setText(b.getBook_name());
            holder.id.setText("Book id: "+b.getBook_id());
            holder.author.setText("Author: "+b.getAuthor());
            holder.copies.setText("Copies Available: "+b.getCopies());
//            Toast.makeText(context, "date"+b.getDates(), Toast.LENGTH_SHORT).show();
//            holder.relativeLayout.
            //
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
//            String today=date.toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


            Calendar cal = Calendar.getInstance();
            try{
                cal.setTime(sdf.parse(b.getDates()));
            }catch(ParseException e){
                e.printStackTrace();
            }
            cal.add(Calendar.DAY_OF_MONTH, 15);     //adding 15days to the date

            String dateAfter = sdf.format(cal.getTime());
            if(b.getDates().isEmpty()){
                holder.estimate.setText("");

            }
            else
            holder.estimate.setText("Due date: "+dateAfter);

        }




    public int getItemCount() {
        return  l.size() ;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView id;
        public TextView author;
        public TextView copies;
        public TextView estimate;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            name=  itemView.findViewById(R.id.name);
            id= itemView.findViewById(R.id.id);
            author= itemView.findViewById(R.id.Author1);
            copies=  itemView.findViewById(R.id.copies);
            estimate=  itemView.findViewById(R.id.estimateddate);
            relativeLayout =itemView.findViewById(R.id.view);
        }
    }
}