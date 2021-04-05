package Loaders;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.ArrayList;

import MoviesData.Movie;
import Utils.MoviesQueryUtils;

public class MovieLoader extends AsyncTaskLoader<ArrayList<Movie>> {

    private String jsonQueryLink;

    public MovieLoader(@NonNull Context context, String jsonQueryLink) {
        super(context);
        this.jsonQueryLink = jsonQueryLink;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public ArrayList<Movie> loadInBackground() {
        return MoviesQueryUtils.fetchMovies(jsonQueryLink);
    }

}
