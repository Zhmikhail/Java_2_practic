package mapper;

import repository.entity.UniversityEntity;
import transport.dto.request.StudentRequest;
import transport.dto.response.ValidationResponse;
import java.util.logging.Logger;
import java.util.logging.Level;

public class InternalDataMapper {
    private static final Logger logger = Logger.getLogger(InternalDataMapper.class.getName());
    public static ValidationResponse toValidationResponse(boolean isValid, String message) {
        logger.log(Level.INFO, "Starting myMethod1");
//        ValidationResponse response = new ValidationResponse(isValid, message);
////        response.setValid(isValid);
////        response.setMessage(message);
        return new ValidationResponse(isValid, message);
    }
}
