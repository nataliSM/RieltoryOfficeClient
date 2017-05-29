package ru.itis.inform.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import ru.itis.inform.models.Offer;
import ru.itis.inform.models.User;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Natalia on 25.05.17.
 */
public class LoginServiceImpl implements LoginService {
    final String BASE_URL = "http://localhost:8080/users/";

    @Override
    public void login(String username, String password, Result<User> result) {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        try {
            HttpResponse<JsonNode> response = Unirest.post(BASE_URL+"login")
                    .header("username", username)
                    .header("password", password).asJson();

                int code = response.getStatus();
                if (code == 404) {


                    result.failure(new ServerException("Wrong password or login"));
                    return;
                }
                Headers headers = response.getHeaders();
                List<String> token = headers.get("Authorization");
                User user = new User();
                if (token.isEmpty()) {

                    result.failure(new ServerException("Internal Server Error"));
                    return;
                }
                user.setToken(token.get(0));

                result.successful(user);
        } catch (UnirestException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getCause() + e.getMessage());
            result.failure(new ServerException("Internal Server Error"));

        }
    }
}
