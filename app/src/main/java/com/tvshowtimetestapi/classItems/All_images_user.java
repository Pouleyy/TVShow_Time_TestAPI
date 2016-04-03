package com.tvshowtimetestapi.classItems;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kévin on 02/04/2016.
 */
public class All_images_user {
    @SerializedName("1")
    private String firstPic;
    @SerializedName("2")
    private String secondPic;
    private String square;

    public All_images_user(String firstPic, String secondPic, String square) {
        this.firstPic = firstPic;
        this.secondPic = secondPic;
        this.square = square;
    }

    public String getFirstPic() {
        return firstPic;
    }

    public String getSecondPic() {
        return secondPic;
    }

    public String getThirdPic() {
        return square;
    }
}
