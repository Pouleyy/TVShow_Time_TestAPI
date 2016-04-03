package com.tvshowtimetestapi.classItems;

import java.util.ArrayList;

/**
 * Created by Kévin on 02/04/2016.
 */
public class ResultExplore {
    private String result;
    private ArrayList<Show> shows;

    public ResultExplore(String result, ArrayList<Show> shows) {
        this.result = result;
        this.shows = shows;
    }

    public String getResult() {
        return result;
    }

    public ArrayList<Show> getShows() {
        return shows;
    }
}
