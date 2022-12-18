package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyviewHolder> {
    Context context;
    ArrayList<movie>list;
    String parent;


    public void setParent(String parent) {
        this.parent = parent;
    }



    public MyAdapter(Context context, ArrayList<movie> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_movie,parent,false);
        return new MyviewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        movie Movie=list.get(position);
            holder.available.setText(Movie.getAvailable());
            holder.censor.setText(Movie.getCensor());
            holder.synopsis.setText(Movie.getSynopsis());
            holder.duration.setText(Movie.getDuration());
            holder.releaseDate.setText(Movie.getReleaseDate());
            holder.movieName.setText(Movie.getMovieName());
//        Glide.with(holder.itemView.getContext()).load(Movie.getDirector()).into(holder.director);
            Glide.with(holder.itemView.getContext()).load(Movie.getPoster()).into(holder.poster);
        holder.bckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Seats.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder{
        TextView censor,available,synopsis,duration,releaseDate,movieName;
        ImageView director,poster;
        ImageView bckBtn;
        Button book;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            censor =itemView.findViewById(R.id.censor);
            available =itemView.findViewById(R.id.info);
            synopsis =itemView.findViewById(R.id.syntext);
            duration =itemView.findViewById(R.id.duration);
            releaseDate=itemView.findViewById(R.id.release);
            movieName =itemView.findViewById(R.id.moviename);
//            director =itemView.findViewById(R.id.director);
            poster =itemView.findViewById(R.id.movieposter);
            //Back Button

            bckBtn = itemView.findViewById(R.id.backBtn);
            book = itemView.findViewById(R.id.btnBook);



        }
    }
}
