package com.citec.roycercordova.anga;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FManga.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FManga extends Fragment implements AdapterCallback {
    private RecyclerView recycler_Manga;
    private OnFragmentInteractionListener mListener;
    String URL="https://doodle-manga-scraper.p.mashape.com/mangareader.net/manga/";
    View view;
    String mangaId;

    public FManga() {
        // Required emptyd public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.f_manga, container, false);

        Bundle bundle = this.getArguments();
        mangaId = bundle.getString("idMangaBundle");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(mangaId);

        recycler_Manga = (RecyclerView) view.findViewById(R.id.recycler_mymanga);
        LinearLayoutManager linLayManager = new LinearLayoutManager(view.getContext());
        recycler_Manga.setLayoutManager(linLayManager);
        recycler_Manga.setHasFixedSize(true);

        String url=URL+mangaId;
        new cargarCapitulos().execute(url);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onMethodCallback(String idmanga) {}

    @Override
    public void onMethodCallback(int idChapter) {
        Bundle bundle = new Bundle();
        bundle.putInt("chapterId", idChapter);
        bundle.putString("mangaId", mangaId);
        FCapitulo fCapitulo  = new FCapitulo();
        fCapitulo.setArguments(bundle);
        String tag = "tag3";
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fCapitulo, tag).addToBackStack(tag).commit();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class cargarCapitulos extends AsyncTask<String, Long, String> {
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
            Manga mcapitulos = getCapitulos(response);
            if(mcapitulos!=null){
                mostrarMangas(mcapitulos);
            }
        }
        private void mostrarMangas(Manga manga) {
            List<Capitulo> myCapitulos =manga.getChapters();
            adapterManga adapter = new adapterManga(myCapitulos,FManga.this);
            recycler_Manga.setAdapter(adapter);
        }
        private Manga getCapitulos(String json) {
            Gson gson = new Gson();
            Manga manga = gson.fromJson(json, Manga.class);
            return manga;
        }
    }
}
