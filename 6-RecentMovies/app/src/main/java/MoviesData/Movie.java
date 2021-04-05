package MoviesData;

public class Movie {

    private String title;
    private String section;
    private String author;
    private String rate;
    private String poster;
    private String reviewLink;

    public Movie(String title, String section, String author,
                 String rate, String poster, String reviewLink) {
        this.title = extractTitle(title);
        this.section = section;
        this.author = author;
        this.rate = rate;
        this.poster = poster;
        this.reviewLink = reviewLink;
    }

    private String extractTitle(String tempTitle) {
        int lastLetter = tempTitle.indexOf("review") - 1;
        if (lastLetter > 0)
            return tempTitle.substring(0, lastLetter);
        else
            return tempTitle;
    }

    public String getTitle() {
        return title;
    }

    public String getSection() {
        return section;
    }

    public String getAuthor() {
        return author;
    }

    public String getRate() {
        return rate;
    }

    public String getPoster() {
        return poster;
    }

    public String getReviewLink() {
        return reviewLink;
    }
}
