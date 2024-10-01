package HTTP;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class HttpClientExample {

    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/user-agent"))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String responseBody = response.body();
            System.out.println("Ответ сервера: \n" + responseBody);

            JSONObject jsonObject = new JSONObject(responseBody);
            String userAgent = jsonObject.getString("user-agent");

            System.out.println("User-Agent: " + userAgent);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
