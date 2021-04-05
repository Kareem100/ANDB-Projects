package Utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import MoviesData.Movie;

public final class MoviesQueryUtils {

    private static final String LOG_TAG = MoviesQueryUtils.class.getName();
    private static final int CONNECTION_TIMEOUT = 15000;
    private static final int SUCCESS_RESPONSE_CODE = 200;

    public static ArrayList<Movie> fetchMovies(String queryLink) {
        URL queryUrl = createUrl(queryLink);
        ArrayList<Movie>movies = null;
        InputStream inputStream = null;
        String jsonResponse = "";
        // check if queryUrl is empty or null
        if (ObjectUtils.notValidObject(queryUrl)) return null;
        try {
            HttpURLConnection urlConnection = getHttpRequest(queryUrl);
            if (urlConnection.getResponseCode() == SUCCESS_RESPONSE_CODE) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readStream(inputStream);
                // check if jsonResponse is empty or null
                if (ObjectUtils.notValidObject(jsonResponse)) return null;
                movies = extractMovies(jsonResponse);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "MoviesQueryUtils.fetchMovies: Error Making HTTP Request...", e);
        }
        return movies;
    }

    private static URL createUrl(String queryLink) {
        // check if queryLink is empty or null
        if (ObjectUtils.notValidObject(queryLink)) return null;
        URL queryUrl = null;
        try {
            queryUrl = new URL(queryLink);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "MoviesQueryUtils.createUrl: Error Creating URL", e);
        }
        return queryUrl;
    }

    private static HttpURLConnection getHttpRequest(URL queryUrl) {

        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) queryUrl.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(CONNECTION_TIMEOUT);
            urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            urlConnection.connect();
        } catch (IOException e) {
            Log.e(LOG_TAG,
                    "MoviesQueryUtils.getHttpRequest: Error Establishing URL Connection", e);
        }
        return urlConnection;
    }

    private static String readStream(InputStream inputStream) throws IOException{
        // check if inputStream is empty or null
        if (ObjectUtils.notValidObject(inputStream)) return null;
        StringBuilder returnString = new StringBuilder();
        InputStreamReader inputStreamReader =
                new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String jsonLine = bufferedReader.readLine();
        while (jsonLine != null) {
            returnString.append(jsonLine);
            jsonLine = bufferedReader.readLine();
        }
        return returnString.toString();
    }

    private static ArrayList<Movie> extractMovies(String jsonQuery) {
        ArrayList<Movie> movieArrayList = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(jsonQuery);
            JSONObject response = root.getJSONObject("response");
            JSONArray moviesResults = response.getJSONArray("results");
            for (int i = 0; i < moviesResults.length(); ++i) {
                JSONObject movieFields = moviesResults.getJSONObject(i).getJSONObject("fields");
                JSONArray contributorTags = moviesResults.getJSONObject(i).getJSONArray("tags");
                String title = movieFields.getString("headline");
                String rate = movieFields.getString("starRating");
                String reviewLink = movieFields.getString("shortUrl");
                String poster = movieFields.getString("thumbnail");
                String section = moviesResults.getJSONObject(i).getString("sectionName");
                String author = contributorTags.getJSONObject(0).getString("webTitle");
                movieArrayList.add(new Movie(title, section, author, rate, poster, reviewLink));
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG,
                    "MoviesQueryUtils.extractMovies: " +
                            "Error Parsing The Earthquake JSON Results", e);
        }
        return movieArrayList;
    }
}
