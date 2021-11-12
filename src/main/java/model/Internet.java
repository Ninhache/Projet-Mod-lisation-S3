package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

public class Internet {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static boolean setupProxy = false;

    public static String sendHttpGETRequest(String url) throws IOException {
    	setupProxy();
        URL obj = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = httpURLConnection.getResponseCode();
        System.out.println(responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();

            return response.toString();

        } else {
            System.out.println("GET request not worked");
        }
        return null;
    }
    
    public static void setupProxy() {

    	if(!Internet.setupProxy) return;
    	System.setProperty("http.proxyHost", "http://cache.univ-lille.fr");
    	System.setProperty("http.proxyPort", "3128");
    	System.setProperty("java.net.useSystemProxies", "true");
    	Internet.setupProxy = true;
    }
}
