package ru.meow.meowauth.exceptions;

public class InvalidBasicAuthorizationHeaderException extends RuntimeException {
    public InvalidBasicAuthorizationHeaderException() {
        super("Invalid Basic Authorization Header");
    }
}
