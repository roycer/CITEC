package com.citec.roycercordova.anga;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FSearch.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FSearch extends Fragment implements AdapterCallback  {

    private EditText inputSearch;
    private RecyclerView recycler_Search;
    Button botonSearch;
    View view;
    String URL="https://doodle-manga-scraper.p.mashape.com/mangareader.net/";
    private OnFragmentInteractionListener mListener;

    public FSearch() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.f_search, container, false);

        inputSearch = (EditText) view.findViewById(R.id.input_search);
        recycler_Search = (RecyclerView) view.findViewById(R.id.recycler_mysearch);
        LinearLayoutManager linLayManager = new LinearLayoutManager(view.getContext());
        recycler_Search.setLayoutManager(linLayManager);
        recycler_Search.setHasFixedSize(true);

        botonSearch = (Button) view.findViewById(R.id.buttonSearch);
        botonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = inputSearch.getText().toString();
                titulo = titulo.replaceAll(" +","%20");
                if (!TextUtils.isEmpty(titulo)) {
                    String url=URL+"search?q="+titulo;
                    new cargarMangas().execute(url);
                }
            }
        });

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
    public void onMethodCallback(String idManga) {
        Bundle bundle = new Bundle();
        bundle.putString("idMangaBundle", idManga);
        FManga fManga  = new FManga();
        fManga.setArguments(bundle);
        String tag = "tag2";
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fManga,tag).addToBackStack(tag).commit();
    }

    @Override
    public void onMethodCallback(int idChapter) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class cargarMangas extends AsyncTask<String, Long, String> {
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
            List<mySearch> mangas = getMangas(response);
            if(mangas!=null){
                mostrarMangas(mangas);
            }
        }
        private void mostrarMangas(List<mySearch> busqueda) {
            List<mySearch> mangas =busqueda;
            adapterSearch adapter = new adapterSearch(mangas,FSearch.this);
            recycler_Search.setAdapter(adapter);
        }
        private List<mySearch> getMangas(String json) {
            Gson gson = new Gson();
            List<mySearch> mangas = null;
            try{
                mangas = gson.fromJson(json, new TypeToken<List<mySearch>>(){}.getType());
            }catch (Exception e){}

            return mangas;
        }
    }
}
