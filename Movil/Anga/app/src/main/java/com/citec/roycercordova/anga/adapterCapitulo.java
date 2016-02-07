package com.citec.roycercordova.anga;

import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Roycer on 2/6/2016.
 */
public class adapterCapitulo extends RecyclerView.Adapter<adapterCapitulo.ViewHolder> {

    List<Pagina> paginas;
    Fragment fragment;

    adapterCapitulo(List<Pagina> paginas, Fragment fragment){
        this.paginas = paginas;
        this.fragment = fragment;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView  imgPagina;

        ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.recycler_mycapitulo);
            imgPagina = (ImageView)itemView.findViewById(R.id.imgPagina);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_capitulo,parent, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (paginas.get(position).getUrl() != null) {
            Picasso.with(fragment.getActivity()).load(paginas.get(position).getUrl()).into(holder.imgPagina);
        }
    }

    @Override
    public int getItemCount() {
        return paginas.size();
    }


}
