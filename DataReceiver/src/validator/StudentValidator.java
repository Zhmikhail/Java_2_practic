package validator;

import exception.ValidationException;

import java.util.regex.Pattern;

public class StudentValidator {
    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z\\s-]+$");
    private static final Pattern SPECIALTY_CODE_PATTERN = Pattern.compile("^\\d{2}\\.\\d{2}\\.\\d{2}$");

    public void validateAge(int age) throws ValidationException {
        if (age < 18 || age > 100) {
            throw new ValidationException("Age must be between 18 and 100");
        }
    }

    public void validateName(String name) throws ValidationException {
        if (name == null || !NAME_PATTERN.matcher(name).matches()) {
            throw new ValidationException("Name must contain only letters, spaces, and hyphens");
        }
    }

    public void validateDiplomaNumber(int diplomaNumber) throws ValidationException {
        if (diplomaNumber < 0) {
            throw new ValidationException("Diploma number must contain only positive numbers");
        }
    }

    public void validateSpecialtyCode(String specialtyCode) throws ValidationException {
        if (specialtyCode == null || !SPECIALTY_CODE_PATTERN.matcher(specialtyCode).matches()) {
            throw new ValidationException("Specialty code must be in format XX.XX.XX");
        }
    }

    public void validateStudentData(String name, int age, String specialtyCode, int diplomaNumber) throws ValidationException {
        validateAge(age);
        validateName(name);
        validateSpecialtyCode(specialtyCode);
        validateDiplomaNumber(diplomaNumber);
    }
}
