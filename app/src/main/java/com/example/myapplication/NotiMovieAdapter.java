package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.core.Context;

import java.util.ArrayList;
import java.util.List;

public class NotiMovieAdapter extends FirebaseRecyclerAdapter<NotiMovieModel, NotiMovieAdapter.MyViewHolder> {

    android.content.Context context;


    ArrayList<NotiMovieModel> lists;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public NotiMovieAdapter(@NonNull FirebaseRecyclerOptions<NotiMovieModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull NotiMovieModel model) {
        holder.Loc.setText(model.getLoc());
        holder.Time.setText(model.getTime());
        holder.Date.setText(model.getDate());
        holder.movName.setText(model.getName());
        holder.Tickets.setText(model.getTickets());
        Glide.with(holder.posterI.getContext()).load(model.getPoster()).into(holder.posterI);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item,parent,false);
        return new NotiMovieAdapter.MyViewHolder(view);
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView posterI;
        TextView movName,Date,Time,Tickets,Loc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            posterI = itemView.findViewById(R.id.movie_poster);
            movName = itemView.findViewById(R.id.movie_name);
            Date = itemView.findViewById(R.id.movie_date);
            Time = itemView.findViewById(R.id.movie_time);
            Tickets = itemView.findViewById(R.id.movie_ticket);
            Loc = itemView.findViewById(R.id.movie_loc);

        }
    }

}
