package MoviesData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recentmovies.R;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private Context context;
    private ArrayList<Movie> movieArrayList;
    private MovieClickListener movieClickListener;

    public MoviesAdapter(Context context, ArrayList<Movie> movieArrayList,
                         MovieClickListener movieClickListener) {
        this.context = context;
        this.movieArrayList = movieArrayList;
        this.movieClickListener = movieClickListener;
    }

    public void clearData() {
        movieArrayList.clear();
    }

    public void setData(ArrayList<Movie> movies) {
        movieArrayList = movies;
    }

    public String getReviewLink(int position) {
        return movieArrayList.get(position).getReviewLink();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.movies_list_item, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.titleTextView.setText(movieArrayList.get(position).getTitle());
        holder.sectionTextView.setText(movieArrayList.get(position).getSection());
        holder.authorTextView.setText(movieArrayList.get(position).getAuthor());
        holder.rateTextView.setText(movieArrayList.get(position).getRate());
        Glide.with(context).load(movieArrayList.get(position).getPoster())
                .into(holder.posterImageView);
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView titleTextView;
        private TextView sectionTextView;
        private TextView authorTextView;
        private TextView rateTextView;
        private ImageView posterImageView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_text_view);
            sectionTextView = itemView.findViewById(R.id.section_text_view);
            authorTextView = itemView.findViewById(R.id.author_text_view);
            rateTextView = itemView.findViewById(R.id.rate_text_view);
            posterImageView = itemView.findViewById(R.id.poster_image_view);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            movieClickListener.onMovieClick(getAdapterPosition());
        }
    }

    public interface MovieClickListener {
        void onMovieClick(int position);
    }

}
