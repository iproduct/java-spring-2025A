package course.spring.exception;

import java.util.List;

public class InvalidEntityDataException extends RuntimeException {
    private List<String> violations;

    public InvalidEntityDataException() {
    }

    public InvalidEntityDataException(String message) {
        super(message);
    }

    public InvalidEntityDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidEntityDataException(Throwable cause) {
        super(cause);
    }

    public InvalidEntityDataException(List<String> violations) {
        this.violations = violations;
    }

    public InvalidEntityDataException(String message, List<String> violations) {
        super(message);
        this.violations = violations;
    }

    public InvalidEntityDataException(String message, Throwable cause, List<String> violations) {
        super(message, cause);
        this.violations = violations;
    }

    public InvalidEntityDataException(Throwable cause, List<String> violations) {
        super(cause);
        this.violations = violations;
    }

    public List<String> getViolations() {
        return violations;
    }

    @Override
    public String toString() {
        return "InvalidEntityDataException{" +
                "violations=" + violations +
                "} " + super.toString();
    }
}
