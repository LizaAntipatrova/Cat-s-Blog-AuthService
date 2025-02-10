package ru.meow.meowauth.exceptions;

public class ExistingUserWithThatUsernameException extends RuntimeException {
    public ExistingUserWithThatUsernameException() {
        super("A user with that username already exists");
    }
}
