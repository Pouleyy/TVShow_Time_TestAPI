package com.tvshowtimetestapi.classItems;

/**
 * Created by Kévin on 02/04/2016.
 */
public class Show {
    private Integer id;
    private String name;
    private String overview;
    private String last_seen;
    private Last_aired last_aired;
    private Next_aired next_aired;
    private Integer nb_followers;
    private Boolean followed;
    private String archived;
    private String status;
    private All_images_show all_images;
    private Integer aired_episode;
    private Integer seen_episodes;
    private String hashtag;
    private Integer number_of_seasons;
    private Integer runtime;

    public Show(Integer id, String name, String overview, String last_seen, Last_aired last_aired,
                Next_aired next_aired, Integer nb_followers, Boolean followed, String archived,
                String status, All_images_show all_images, Integer aired_episode, Integer seen_episodes,
                String hashtag, Integer number_of_seasons, Integer runtime) {
        this.id = id;
        this.name = name;
        this.overview = overview;
        this.last_seen = last_seen;
        this.last_aired = last_aired;
        this.next_aired = next_aired;
        this.nb_followers = nb_followers;
        this.followed = followed;
        this.archived = archived;
        this.status = status;
        this.all_images = all_images;
        this.aired_episode = aired_episode;
        this.seen_episodes = seen_episodes;
        this.hashtag = hashtag;
        this.number_of_seasons = number_of_seasons;
        this.runtime = runtime;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public String getLast_seen() {
        return last_seen;
    }

    public Last_aired getLast_aired() {
        return last_aired;
    }

    public Next_aired getNext_aired() {
        return next_aired;
    }

    public Integer getNb_followers() {
        return nb_followers;
    }

    public Boolean getFollowed() {
        return followed;
    }

    public String getArchived() {
        return archived;
    }

    public String getStatus() {
        return status;
    }

    public All_images_show getAll_images() {
        return all_images;
    }

    public Integer getAired_episode() {
        return aired_episode;
    }

    public Integer getSeen_episodes() {
        return seen_episodes;
    }

    public String getHashtag() {
        return hashtag;
    }

    public Integer getNumber_of_seasons() {
        return number_of_seasons;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public String getImage() {
        return all_images.getFirstPic();
    }
}
