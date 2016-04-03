package com.tvshowtimetestapi.classItems;

/**
 * Created by Kévin on 02/04/2016.
 */
public class Last_aired {

    private Integer number;
    private Integer season_number;

    public Last_aired(Integer number, Integer season_number) {
        this.number = number;
        this.season_number = season_number;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getSeason_number() {
        return season_number;
    }
}
