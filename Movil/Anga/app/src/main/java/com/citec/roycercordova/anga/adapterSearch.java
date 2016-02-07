package com.citec.roycercordova.anga;


import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Roycer on 2/2/2016.
 */

public class adapterSearch extends RecyclerView.Adapter<adapterSearch.ViewHolder>  {
    List<mySearch> mysearchs;
    AdapterCallback mAdapterCallback;
    String URL="https://doodle-manga-scraper.p.mashape.com/mangareader.net/manga/";
    Fragment fragment;

    adapterSearch(List<mySearch> mysearchs, Fragment fragment ){
        this.mysearchs = mysearchs;
        try {
            this.fragment = fragment;
            this.mAdapterCallback = ((AdapterCallback) fragment);
        } catch (ClassCastException e) {
            throw new ClassCastException("Fragment must implement AdapterCallback.");
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView txtnameManga, fecha;
        ImageView imgPortada;

        ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.recycler_mysearch);
            txtnameManga = (TextView)itemView.findViewById(R.id.name);
            fecha = (TextView)itemView.findViewById(R.id.fecha);
            imgPortada = (ImageView)itemView.findViewById(R.id.portada);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_search,parent, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
        holder.txtnameManga.setText(mysearchs.get(position).getName());

        holder.cv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                mAdapterCallback.onMethodCallback(mysearchs.get(position).getMangaId());
            }
        });

        String myURL = URL+mysearchs.get(position).getMangaId()+"/";
        new cargarDetalles(holder,position).execute(myURL);
    }

    @Override
    public int getItemCount() {
        return mysearchs.size();
    }

    private class cargarDetalles extends AsyncTask<String, Long, String> {
        ViewHolder myholder;
        int myposition;
        cargarDetalles(ViewHolder holder,final int position){
            this.myholder = holder;
            this.myposition = position;
        }
        protected String doInBackground(String... urls) {
            try {
                return HttpRequest.get(urls[0]).accept("application/json")
                        .header("X-Mashape-Key", "LB3W1zrfA0mshda7qZt3RnXhWgvNp1pfiJyjsn5zNpTJcuLio6")
                        .body();
            } catch (HttpRequest.HttpRequestException exception) {
                return null;
            }
        }
        protected void onPostExecute(String response) {
            Gson mgson = new Gson();
            Manga mangas = mgson.fromJson(response,Manga.class);
            myholder.fecha.setText(mangas.getLastUpdate());
            if (mangas.getCover() != null) {
                Picasso.with(fragment.getActivity()).load(mangas.getCover()).into(myholder.imgPortada);
            }
        }
    }






}
