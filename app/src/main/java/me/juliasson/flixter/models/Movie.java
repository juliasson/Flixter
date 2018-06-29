package me.juliasson.flixter.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie {
    //instance values from API
    private String title;
    private String synopsis;
    private String posterPath; //only the path, not full URL
    private String backdropPath;

    //initialize from JSON data
    public Movie(JSONObject object) throws JSONException {
        title = object.getString("title");
        synopsis = object.getString("overview");
        posterPath = object.getString("poster_path");
        backdropPath = object.getString("backdrop_path");
    }

    //getters

    public String getTitle() {
        return title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }
}
