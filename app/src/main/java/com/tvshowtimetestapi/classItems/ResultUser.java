package com.tvshowtimetestapi.classItems;

/**
 * Created by Kévin on 02/04/2016.
 */
public class ResultUser {
    private String result;
    private User user;

    public ResultUser(String result, User user) {
        this.result = result;
        this.user = user;
    }

    public String getMessage() {
        return result;
    }

    public User getUser() {
        return user;
    }
}


