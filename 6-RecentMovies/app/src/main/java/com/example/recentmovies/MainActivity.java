package com.example.recentmovies;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;
import Loaders.MovieLoader;
import MoviesData.Movie;
import MoviesData.MoviesAdapter;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.MovieClickListener,
        LoaderManager.LoaderCallbacks<ArrayList<Movie>> {

    private static final String JSON_QUERY_LINK = "https://content.guardianapis.com/search";

    private final int MOVIES_LOADER_ID = 0;
    private RecyclerView moviesRecyclerView;
    private MoviesAdapter moviesAdapter;
    private TextView tipText;
    private ProgressBar circularProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        centerTitle();
        setHooks();
        if (!isNetworkConnected()) {
            tipText.setText(R.string.no_network_state_text);
            circularProgress.setVisibility(View.GONE);
            moviesRecyclerView.setVisibility(View.GONE);
            tipText.setVisibility(View.VISIBLE);
        }
        getSupportLoaderManager().initLoader(MOVIES_LOADER_ID, null, this);
    }

    @Override
    public void onMovieClick(int position) {
        // open the review made by the author
        try {
            String reviewLink = moviesAdapter.getReviewLink(position);
            Uri reviewUri = Uri.parse(reviewLink);
            Intent reviewWebsiteIntent = new Intent(Intent.ACTION_VIEW, reviewUri);
            startActivity(reviewWebsiteIntent);
        } catch (Exception e) {
            Log.e(MainActivity.class.getName(), "MainActivity.onMovieClick: " +
                    "Problem Opening The Website.", e);
        }
    }

    private void setHooks() {
        circularProgress = findViewById(R.id.progress_circular);
        tipText = findViewById(R.id.tip_text_view);
        moviesRecyclerView = findViewById(R.id.movies_recycler_view);
        moviesAdapter = new MoviesAdapter(this, new ArrayList<>(), this);
        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        moviesRecyclerView.setAdapter(moviesAdapter);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private void centerTitle() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView titleTextView = (TextView)
                TextView.inflate(this, R.layout.center_title_text_view, null);
        titleTextView.setLayoutParams(new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(titleTextView);
    }

    private String buildUri () {
        Uri baseUri = Uri.parse(JSON_QUERY_LINK);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        String movieTag = getString(R.string.filter_movies_tag);
        String fromDate = getString(R.string.filter_from_date);
        String showedTags = getString(R.string.filter_show_tags);
        String showedFields = getString(R.string.filter_show_fields);
        String orderBy = getString(R.string.filter_order_by);

        uriBuilder.appendQueryParameter("formant", "json");
        uriBuilder.appendQueryParameter("tag", movieTag);
        uriBuilder.appendQueryParameter("from-date", fromDate);
        uriBuilder.appendQueryParameter("show-tags", showedTags);
        uriBuilder.appendQueryParameter("show-fields", showedFields);
        uriBuilder.appendQueryParameter("order-by", orderBy);
        uriBuilder.appendQueryParameter("api-key", BuildConfig.API_KEY);

        return uriBuilder.toString();
    }

    @NonNull
    @Override
    public Loader<ArrayList<Movie>> onCreateLoader(int id, @Nullable Bundle args) {
        String jsonQueryLink = buildUri();
        return new MovieLoader(this, jsonQueryLink);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Movie>> loader, ArrayList<Movie> data) {
        // clear the adapter of previous earthquake data
        moviesAdapter.clearData();

        if (data != null && !data.isEmpty()) {
            moviesAdapter.setData(data);
            moviesAdapter.notifyDataSetChanged();
            circularProgress.setVisibility(View.GONE);
            tipText.setVisibility(View.GONE);
            moviesRecyclerView.setVisibility(View.VISIBLE);
        }
        else {
            tipText.setText(R.string.empty_data_state_text);
            circularProgress.setVisibility(View.GONE);
            tipText.setVisibility(View.VISIBLE);
            moviesRecyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Movie>> loader) {
        moviesAdapter.clearData();
    }
}