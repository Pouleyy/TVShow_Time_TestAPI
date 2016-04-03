package com.tvshowtimetestapi.classItems;

/**
 * Created by Kévin on 02/04/2016.
 */
public class Next_aired {
    private Integer id;
    private String air_date;
    private Integer number;
    private Integer season_number;
    private String network;

    public Next_aired(Integer id, String air_date, Integer number, Integer season_number, String network) {
        this.id = id;
        this.air_date = air_date;
        this.number = number;
        this.season_number = season_number;
        this.network = network;
    }

    public Integer getId() {
        return id;
    }

    public String getAir_date() {
        return air_date;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getSeason_number() {
        return season_number;
    }

    public String getNetwork() {
        return network;
    }
}
