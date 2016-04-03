package com.tvshowtimetestapi.classItems;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kévin on 02/04/2016.
 */
public class Fanart {
    @SerializedName("0")
    private String fanartZero;
    @SerializedName("1")
    private String fanartOne;
    @SerializedName("2")
    private String fanartTwo;
    @SerializedName("3")
    private String fanartThree;
    @SerializedName("7")
    private String fanartSeven;
    @SerializedName("2.5")
    private String fanartTwoFive;

    public Fanart(String fanartZero, String fanartOne, String fanartTwo, String fanartThree,
                  String fanartSeven, String fanartTwoFive) {
        this.fanartZero = fanartZero;
        this.fanartOne = fanartOne;
        this.fanartTwo = fanartTwo;
        this.fanartThree = fanartThree;
        this.fanartSeven = fanartSeven;
        this.fanartTwoFive = fanartTwoFive;
    }

    public String getFanartZero() {
        return fanartZero;
    }

    public String getFanartOne() {
        return fanartOne;
    }

    public String getFanartTwo() {
        return fanartTwo;
    }

    public String getFanartThree() {
        return fanartThree;
    }

    public String getFanartSeven() {
        return fanartSeven;
    }

    public String getFanartTwoFive() {
        return fanartTwoFive;
    }
}
