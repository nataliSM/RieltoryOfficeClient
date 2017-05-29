package ru.itis.inform.services;

/**
 * Created by Natalia on 25.05.17.
 */
public interface Result<T> {

    void successful(T result);

    void failure(ServerException e);



}
