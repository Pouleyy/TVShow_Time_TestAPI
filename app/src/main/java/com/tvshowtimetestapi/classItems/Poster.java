package com.tvshowtimetestapi.classItems;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Kévin on 02/04/2016.
 */
public class Poster {
    @SerializedName("0")
    private String posterZero;
    @SerializedName("1")
    private String posterOne;
    @SerializedName("2")
    private String posterTwo;
    @SerializedName("3")
    private String posterThree;
    @SerializedName("4")
    private String posterFour;
    @SerializedName("1,3")
    private String posterOneThree;
    @SerializedName("1,5")
    private String posterOneFive;
    @SerializedName("2,6")
    private String posterTwoSix;

    public Poster(String posterZero, String posterOne, String posterTwo, String posterThree,
                  String posterFour, String posterOneThree, String posterOneFive,
                  String posterTwoSix) {
        this.posterZero = posterZero;
        this.posterOne = posterOne;
        this.posterTwo = posterTwo;
        this.posterThree = posterThree;
        this.posterFour = posterFour;
        this.posterOneThree = posterOneThree;
        this.posterOneFive = posterOneFive;
        this.posterTwoSix = posterTwoSix;
    }

    public String getPosterZero() {
        return posterZero;
    }

    public String getPosterOne() {
        return posterOne;
    }

    public String getPosterTwo() {
        return posterTwo;
    }

    public String getPosterThree() {
        return posterThree;
    }

    public String getPosterFour() {
        return posterFour;
    }

    public String getPosterOneThree() {
        return posterOneThree;
    }

    public String getPosterOneFive() {
        return posterOneFive;
    }

    public String getPosterTwoSix() {
        return posterTwoSix;
    }
}
