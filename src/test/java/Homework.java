import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThan;

public class Homework extends ApiUrl {

    @Test
    public void userShouldBeAbleToBrowsePost() {
        when()
            .get(path("/posts")).
        then()
            .assertThat()
            .statusCode(200)
            .header("Content-Encoding","gzip")
            .body("findAll { it.id }.size()", equalTo(100));
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
    public void userShouldBeAbleToBrowseCommentsForOneSpecificPostUsingQuery() {
        when()
            .get(path("/comments?postId=2")).
        then()
            .assertThat()
            .statusCode(200)
            .body("findAll { it.id }.size()", greaterThan(0));
    }

    @Test
    public void userShouldBeAbleToCreatePost(){
        Post postToAdd = new Post("1", "102", "wojciecm test", "olaboga co ja robie");

        Post newPost =
            given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .body(postToAdd).
            when()
                .post(path("/posts"))
                .as(Post.class);

        assertThat(newPost, equalTo(postToAdd));
    }

    @Test
    public void userShouldBeAbleToDeletePost(){
        when()
            .delete(path("/posts/1")).
        then()
            .assertThat()
            .statusCode(200);
    }

    @Test
    public void verifyThereIsUserWithZipCode(){
        when()
            .get(path("/users")).
        then()
            .assertThat()
            .statusCode(200)
            .body("find {it.address.zipcode=='23505-1337'}.id", notNullValue());
    }

    @Test
    public void verifyNumberOfUsersWithOrgWebsite(){
        when()
            .get(path("/users")).
        then()
            .assertThat()
            .statusCode(200)
            .body("findAll { it.website.endsWith('.org') }.size()", equalTo(2));
    }
}