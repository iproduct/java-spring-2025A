package course.spring.dto;

import java.util.List;

public record ErrorResponse(
        int statusCode,
        String errorMessage,
        List<String> violations
) {
}
