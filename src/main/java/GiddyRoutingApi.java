import static spark.Spark.*;

/**
 * Created by rik on 8/26/16.
 */
public class GiddyRoutingApi {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");
    }
}
