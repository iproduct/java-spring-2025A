package course.spring.exception;

public class CustomDBExcetion extends Exception {
    public CustomDBExcetion() {
    }

    public CustomDBExcetion(String message) {
        super(message);
    }

    public CustomDBExcetion(Throwable cause) {
        super(cause);
    }

    public CustomDBExcetion(String message, Throwable cause) {
        super(message, cause);
    }
}
