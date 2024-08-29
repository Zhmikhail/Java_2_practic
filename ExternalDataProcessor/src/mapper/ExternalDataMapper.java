package mapper;

import transport.dto.response.ValidationResponse;

public class ExternalDataMapper {
    public static ValidationResponse toValidationResponse(boolean isValid, String message) {
        return new ValidationResponse(isValid, message);
    }
}
