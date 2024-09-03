package mapper;

import transport.dto.response.ValidationResponseDto;

public class InternalDataMapper {
    public static ValidationResponseDto toValidationResponse(boolean isValid, String message) {
        return new ValidationResponseDto(isValid, message);
    }
}
