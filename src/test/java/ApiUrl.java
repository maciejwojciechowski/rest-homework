public class ApiUrl {

    protected final String apiUrl = "https://jsonplaceholder.typicode.com";

    public String path(String path) {
        return apiUrl.concat(path);
    }
}
