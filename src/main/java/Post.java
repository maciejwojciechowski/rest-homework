import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Post {

    public final String userId;
    public final String id;
    public final String title;
    public final String body;

    @JsonCreator
    public Post(
            @JsonProperty ("userId") String userId,
            @JsonProperty ("id") String id,
            @JsonProperty ("title") String title,
            @JsonProperty ("body") String body) {

        this.userId = userId;
        this.id = id;
        this.title = title;
        this. body = body;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        final Post post = (Post) o;
        return  Objects.equals(userId, post.userId) &&
                Objects.equals(id, post.id) &&
                Objects.equals(title, post.title) &&
                Objects.equals(body, post.body);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(userId, id, title, body);
    }
}
