package com.citec.roycercordova.anga;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Roycer on 2/6/2016.
 */
public class adapterManga extends RecyclerView.Adapter<adapterManga.ViewHolder>{
    List<Capitulo> capitulos;
    Fragment fragment;
    AdapterCallback mAdapterCallback;

    adapterManga(List<Capitulo> capitulos, Fragment fragment){
        this.capitulos = capitulos;
        try {
            this.fragment = fragment;
            this.mAdapterCallback = ((AdapterCallback) fragment);
        } catch (ClassCastException e) {
            throw new ClassCastException("Fragment must implement AdapterCallback.");
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_manga,parent, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.txtCapitulo.setText(capitulos.get(position).name);
        holder.txtidManga.setText("Cap "+capitulos.get(position).chapterId+": ");
        holder.cv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                mAdapterCallback.onMethodCallback(capitulos.get(position).chapterId);
            }
        });

    }

    @Override
    public int getItemCount() {
        return capitulos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView txtidManga;
        TextView txtCapitulo;

        ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.recycler_mymanga);
            txtidManga = (TextView)itemView.findViewById(R.id.txtNumCap);
            txtCapitulo = (TextView)itemView.findViewById(R.id.txtCapitulo);
        }
    }
}
