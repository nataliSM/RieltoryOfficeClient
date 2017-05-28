package ru.itis.inform.services;

/**
 * Created by Natalia on 25.05.17.
 */
public class ServerException extends Exception {
    public ServerException () {
        super();
    }
    public ServerException (String message, Throwable cause) {
        super(message, cause);
    }
    public ServerException (String message) {
        super(message);
    }
    public ServerException (Throwable cause) {
        super(cause);
    }
}
