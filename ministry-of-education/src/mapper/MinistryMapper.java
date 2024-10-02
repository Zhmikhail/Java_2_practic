package mapper;

import transport.dto.response.ValidationResponseDto;

public class MinistryMapper {
    public static ValidationResponseDto toValidationResponse(boolean isValid, String message) {
        return new ValidationResponseDto(isValid, message);
    }
}
