package mapper;

import transport.dto.response.ValidationResponseDto;

public class ExternalDataMapper {
    //FIXME: не совсем верное использование шаблона адаптер (маппер)
    public static ValidationResponseDto toValidationResponse(boolean isValid, String message) {
        return new ValidationResponseDto(isValid, message);
    }


}
