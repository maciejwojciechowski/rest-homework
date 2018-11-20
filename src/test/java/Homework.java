import org.junit.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.number.OrderingComparison.greaterThan;

public class Homework extends ApiUrl {

    @Test
    public void userShouldBeAbleToBrowsePost() {
        when()
            .get(path("/posts")).
        then()
            .assertThat()
            .statusCode(200)
            .body("findAll { it.id }.size()", greaterThan(0));
    }

    @Test
    public void userShouldBeAbleToBrowseCommentsForOneSpecificPost() {
        when()
            .get(path("/posts/1/comments")).
        then()
            .assertThat()
            .statusCode(200)
            .body("findAll { it.id }.size()", greaterThan(0));
    }

    @Test
    public void userShouldBeAbleToBrowseCommentsForOneSpecificPost2() {
        when()
            .get(path("/comments?postId=2")).
        then()
            .assertThat()
            .statusCode(200)
            .body("findAll { it.id }.size()", greaterThan(0));
    }
}