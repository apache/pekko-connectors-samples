package samples.javadsl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

// Type in Elasticsearch (2)
public class Movie {

  public final int id;
  public final String title;

  @JsonCreator
  public Movie(@JsonProperty("id") int id, @JsonProperty("title") String title) {
    this.id = id;
    this.title = title;
  }

  @Override
  public String toString() {
    return "Movie(" + id + ", title=" + title + ")";
  }
}

