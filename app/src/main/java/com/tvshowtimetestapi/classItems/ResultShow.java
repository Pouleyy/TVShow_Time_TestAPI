package com.tvshowtimetestapi.classItems;

/**
 * Created by Kévin on 03/04/2016.
 */
public class ResultShow {
    private String result;
    private Show show;

    public ResultShow(String result, Show show) {
        this.result = result;
        this.show = show;
    }

    public String getResult() {
        return result;
    }


    public Show getShow() {
        return show;
    }
}
