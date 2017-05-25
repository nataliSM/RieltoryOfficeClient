package ru.itis.inform.services;

import com.mashape.unirest.http.Headers;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Created by Natalia on 25.05.17.
 */
public class LoginServiceImpl implements LoginService {
    final String BASE_URL = "http://localhost:8080/users/";

    @Override
    public void login(String username, String password) {
        Future<HttpResponse<JsonNode>> future = Unirest.post(BASE_URL+"login")
                .header("username", username)
                .header("password", password).asJsonAsync(new Callback<JsonNode>() {
                    @Override
                    public void completed(HttpResponse<JsonNode> response) {
                        int code = response.getStatus();
                        Headers headers = response.getHeaders();
                        List<String> token = headers.get("Authorization");
                        System.out.print(token);

                    }

                    @Override
                    public void failed(UnirestException e) {

                    }

                    @Override
                    public void cancelled() {

                    }
                });
    }
}
