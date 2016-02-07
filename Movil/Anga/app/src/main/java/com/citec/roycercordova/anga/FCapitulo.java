package com.citec.roycercordova.anga;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FCapitulo.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FCapitulo extends Fragment {
    String URL="https://doodle-manga-scraper.p.mashape.com/mangareader.net/manga/";
    View view;
    private RecyclerView recycler_capitulo;

    private OnFragmentInteractionListener mListener;

    public FCapitulo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view  = inflater.inflate(R.layout.f_capitulo, container, false);
        recycler_capitulo = (RecyclerView) view.findViewById(R.id.recycler_mycapitulo);
        LinearLayoutManager linLayManager = new LinearLayoutManager(view.getContext());
        recycler_capitulo.setLayoutManager(linLayManager);
        recycler_capitulo.setHasFixedSize(true);


        Bundle bundle = this.getArguments();
        String mangaId = bundle.getString("mangaId");
        int chapterId = bundle.getInt("chapterId");
        String url=URL+mangaId+"/"+chapterId;
        new cargarCapitulo().execute(url);

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


    private class cargarCapitulo extends AsyncTask<String, Long, String> {
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
            Paginas mpaginas = getPaginas(response);
            if(mpaginas!=null){
                mostrarPaginas(mpaginas);
            }
        }
        private void mostrarPaginas(Paginas paginas) {
            List<Pagina> myPaginas = paginas.getPages();
            adapterCapitulo adapter = new adapterCapitulo(myPaginas,FCapitulo.this);
            recycler_capitulo.setAdapter(adapter);
        }
        private Paginas getPaginas(String json) {
            Gson gson = new Gson();
            Paginas paginas = gson.fromJson(json, Paginas.class);
            return paginas;
        }
    }
}
