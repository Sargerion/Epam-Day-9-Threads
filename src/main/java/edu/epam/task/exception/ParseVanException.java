package edu.epam.task.exception;

public class ParseVanException extends Exception {

    public ParseVanException() {
    }

    public ParseVanException(String message) {
        super(message);
    }

    public ParseVanException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseVanException(Throwable cause) {
        super(cause);
    }
}