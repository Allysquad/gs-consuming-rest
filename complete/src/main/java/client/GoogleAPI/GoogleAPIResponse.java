package client.GoogleAPI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;


class GoogleAPIResponse {

    static String main(String coinName) throws IOException {
        String dashlessCoinName = coinName.replaceAll(" ", "%20");

        try (InputStream inputStream = new URL("https://www.googleapis.com/customsearch/v1?key=AIzaSyCIpg1LmOxtxuv2YcljwwFXw1t1lmRlqTw&cx=010827125214111693049:sb7ftpqlteu&q=" + dashlessCoinName).openStream()) {
            Reader reader = new InputStreamReader(inputStream);
            Gson gson = new GsonBuilder().create();
            MappedGoogleResponse mappedGoogleResponse = gson.fromJson(reader, MappedGoogleResponse.class);
            System.out.println(mappedGoogleResponse.toString());
            return mappedGoogleResponse.toString();
        }
//        return "335000000"; for testing response from API.
    }
}


