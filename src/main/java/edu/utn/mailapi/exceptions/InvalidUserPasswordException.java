package edu.utn.mailapi.exceptions;

public class InvalidUserPasswordException extends Throwable {

    @Override
    public String getMessage() {
        return "User and password combination does not exists";
    }
}
