package com.tvshowtimetestapi.classItems;

import java.util.ArrayList;

/**
 * Created by Kévin on 02/04/2016.
 */
public class All_images_show {
    private Poster poster;
    private ArrayList<String> banner;
    private Fanart fanart;

    public All_images_show(Poster poster, ArrayList<String> banner, Fanart fanart) {
        this.poster = poster;
        this.banner = banner;
        this.fanart = fanart;
    }

    public Poster getPoster() {
        return poster;
    }

    public ArrayList<String> getBanner() {
        return banner;
    }

    public Fanart getFanart() {
        return fanart;
    }
    public String getFirstPic() {
        return poster.getPosterZero();
    }
}
