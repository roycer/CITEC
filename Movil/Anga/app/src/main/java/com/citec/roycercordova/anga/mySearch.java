package com.citec.roycercordova.anga;

import java.util.List;

/**
 * Created by Roycer on 2/2/2016.
 */
public class mySearch {

    String mangaId;
    String name;
    List <String> genres;

    public String getMangaId() {
        return mangaId;
    }

    public void setMangaId(String mangaId) {
        this.mangaId = mangaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}
