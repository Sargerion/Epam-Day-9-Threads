package edu.epam.task.exception;

public class VanReaderException extends Exception{

    public VanReaderException() {
    }

    public VanReaderException(String message) {
        super(message);
    }

    public VanReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public VanReaderException(Throwable cause) {
        super(cause);
    }
}
