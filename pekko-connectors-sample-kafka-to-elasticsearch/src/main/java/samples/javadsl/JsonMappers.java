package samples.javadsl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

class JsonMappers {

  // Jackson's conversion setup (3)
  public static final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
  public static final ObjectWriter movieWriter = mapper.writerFor(Movie.class);
  public static final ObjectReader movieReader = mapper.readerFor(Movie.class);
}
