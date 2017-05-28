package ru.itis.inform.services;

import ru.itis.inform.models.Offer;
import ru.itis.inform.models.User;

import java.util.List;

/**
 * Created by Natalia on 26.05.17.
 */
public interface OffersServices {

    public void allOffers(Result<List<Offer>> result, User user);
}
