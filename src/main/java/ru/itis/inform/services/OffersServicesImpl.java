package ru.itis.inform.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import ru.itis.inform.models.Offer;
import ru.itis.inform.models.User;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Natalia on 26.05.17.
 */
public class OffersServicesImpl implements OffersServices {
    final String BASE_URL = "http://localhost:8080/offers/";
    public void allOffers(Result<List<Offer>> result, User user) {

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
        Unirest.get(BASE_URL).header("Authorization", "Bearer" + user.getToken());
        try {
            HttpResponse<Offer[]> offerHttpResponse = Unirest.get(BASE_URL).header("Authorization", "Bearer " + user.getToken()).asObject(Offer[].class);
            Offer[] offers =  offerHttpResponse.getBody();
            result.successful(Arrays.asList(offers));

        } catch (UnirestException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getCause() + e.getMessage());
            result.failure(new ServerException("Internal Server Error"));
        }


    }

    public void update(Offer offer, User user, Result<Offer> result) {

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

        Unirest.put(BASE_URL + offer.getId()).header("Authorization", "Bearer" + user.getToken());
        try {
            HttpResponse<JsonNode> offerHttpResponse = Unirest.put(BASE_URL)
                    .header("Authorization", "Bearer " + user.getToken())
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(offer)
                    .asJson();
            offerHttpResponse.getBody();
            result.successful(null);

        } catch (UnirestException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getCause() + e.getMessage());
            result.failure(new ServerException("Internal Server Error"));
        }
    }

}
