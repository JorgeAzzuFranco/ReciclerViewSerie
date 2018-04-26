package com.jorgeazzufranco.reciclerview;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by uca on 04-18-18.
 */

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>{

    private ArrayList<Serie> series;
    private Context mContext;
    private static boolean fav = false;

    public SeriesAdapter(ArrayList<Serie> series, Context cont) {
        this.series = series;
        this.mContext = cont;
    }


    public class SeriesViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView name;
        ImageView img;
        ImageButton ib;

        public SeriesViewHolder(View itemview) {
            super(itemview);
            card = itemview.findViewById(R.id.card_view);
            name = itemview.findViewById(R.id.name);
            img = itemview.findViewById(R.id.img);
            ib =  itemview.findViewById(R.id.fav_vacio);
        }
    }
    @Override
    public SeriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.actvity_cardview,parent,false);
        return (new SeriesViewHolder(v));
    }

    @Override
    public void onBindViewHolder(final SeriesViewHolder holder, final int position) {
        holder.name.setText(series.get(position).getName());
        holder.img.setImageResource(series.get(position).getImg());

        if(series.get(position).yesorno()){
            holder.ib.setImageResource(R.drawable.yellow);
        }
        else{
            holder.ib.setImageResource(R.drawable.star);
        }
        //boton para agregar y quitar
        holder.ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (series(position)){
                    holder.ib.setImageResource(R.drawable.yellow);
                    ((MainActivity)mContext).addBookmark(series.get(position));
                }
                else {
                   holder.ib.setImageResource(R.drawable.star);
                    ((MainActivity)mContext).quitar(series.get(position).getName());
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        if (series.size() != 0){
            return series.size();
        }
        return 0;
    }

    public boolean series(int position){
        series.get(position).set(!series.get(position).yesorno());
        return series.get(position).yesorno();
    }

    public void setTrue(){
        fav=true;
    }

    public void setFalse(){
        fav=false;
    }

    public boolean isOnBookmark() {
        return fav;
    }
}
