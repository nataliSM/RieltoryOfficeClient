package ru.itis.inform.services;

import ru.itis.inform.models.User;

/**
 * Created by Natalia on 25.05.17.
 */
public interface LoginService {
    public void login(String username, String password, Result<User> result);
}
