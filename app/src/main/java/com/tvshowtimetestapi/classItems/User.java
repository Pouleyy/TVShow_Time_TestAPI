package com.tvshowtimetestapi.classItems;

/**
 * Created by Kévin on 02/04/2016.
 */
public class User {
    
    private Integer id;
    private String name;
    private String image;
    private All_images_user all_images;

    public User(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public User(Integer id, String name, String image, All_images_user all_images) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.all_images = all_images;
    }

    public Integer getIdUser() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public All_images_user getAll_images() {
        return all_images;
    }
}
