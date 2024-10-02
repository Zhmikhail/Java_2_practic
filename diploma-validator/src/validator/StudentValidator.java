package validator;

import exception.ValidationException;
import java.util.regex.Pattern;

public class StudentValidator {
    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z\\s-]+$");
    private static final Pattern UNIVERSITY_PATTERN = Pattern.compile("^[A-Za-z]+$");  // Университет: только английские буквы
    private static final Pattern SPECIALTY_CODE_PATTERN = Pattern.compile("^\\d{2}\\.\\d{2}\\.\\d{2}$");

    public void validateAge(int age) throws ValidationException {
        if (age < 18 || age > 100) {
            throw new ValidationException("Age must be between 18 and 100");
        }
    }

    public void validateName(String name) throws ValidationException {
        if (name == null || name.trim().isEmpty() || !NAME_PATTERN.matcher(name).matches()) {
            throw new ValidationException("Name must contain only letters, spaces, and hyphens and cannot be empty.");
        }
    }

    public void validateUniversity(String university) throws ValidationException {
        if (university == null || university.trim().isEmpty() || !UNIVERSITY_PATTERN.matcher(university).matches()) {
            throw new ValidationException("University must contain only English letters and cannot be empty.");
        }
    }

    public void validateDiplomaNumber(int diplomaNumber) throws ValidationException {
        if (diplomaNumber < 0) {
            throw new ValidationException("Diploma number must contain only positive numbers.");
        }
    }

    public void validateSpecialtyCode(String specialtyCode) throws ValidationException {
        if (specialtyCode == null || !SPECIALTY_CODE_PATTERN.matcher(specialtyCode).matches()) {
            throw new ValidationException("Specialty code must be in format XX.XX.XX.");
        }
    }

    public void validateStudentData(String name, int age, String university, String specialtyCode, int diplomaNumber) throws ValidationException {
        validateAge(age);
        validateName(name);
        validateUniversity(university);
        validateSpecialtyCode(specialtyCode);
        validateDiplomaNumber(diplomaNumber);
    }
}
