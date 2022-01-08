package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Util class to simplify http request
 *
 * @author Néo ALMEIDA
 * @version %I%, %G%
 */
public class InternetUtil {

    private static final String USER_AGENT = "Mozilla/5.0";
    
    public static String sendHttpGETRequest(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();

            return response.toString();

        } else {
            System.out.println("GET request not worked");
        }
        return null;
    }

    public static void setupProperty(String systemProperty, String value) {
        System.setProperty(systemProperty, value);
    }
}
