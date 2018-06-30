package me.juliasson.flixter.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Movie {
    //instance values from API
    String title;
    String synopsis;
    String posterPath; //only the path, not full URL
    String backdropPath;
    Double voteAverage;
    Integer id;
    String key;

    //default constructor, empty
    public Movie() {}

    //initialize from JSON data
    public Movie(JSONObject object) throws JSONException {
        title = object.getString("title");
        synopsis = object.getString("overview");
        posterPath = object.getString("poster_path");
        backdropPath = object.getString("backdrop_path");
        voteAverage = object.getDouble("vote_average");
        id = object.getInt("id");
        key = String.valueOf(object.getJSONArray("results").get(3));
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

    public Double getVoteAverage() {
        return voteAverage;
    }

    public Integer getId() {
        return id;
    }

    public String getKey() { return key; }
}
