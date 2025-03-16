package eus.ehu.bummer4;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class Utils {
    public static String readFile(String filename) {
        String content = "";
        try {
            URL fileURL = Utils.class.getResource(filename);
            // toURI() is necessary to handle the ":" symbol in Windows paths
            content = new String (Files.readAllBytes(Paths.get(fileURL.toURI())));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static String query(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
