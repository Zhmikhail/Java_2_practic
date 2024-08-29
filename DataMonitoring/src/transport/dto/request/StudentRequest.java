package transport.dto.request;

import java.io.Serializable;
import java.util.logging.Logger;
import java.util.logging.Level;

public class StudentRequest implements Serializable {
    private static final Logger logger = Logger.getLogger(StudentRequest.class.getName());
    private static final long serialVersionUID = 1L;  // Добавлен serialVersionUID;
    private String name;
    private int age;
    private String university;
    private String specialtyCode;
    private int diplomaNumber;

//    public StudentRequest(String name, int age, String university, String specialtyCode, int diplomaNumber) {
//        this.name = name;
//        this.age = age;
//        this.university = university;
//        this.specialtyCode = specialtyCode;
//        this.diplomaNumber = diplomaNumber;
//        logger.log(Level.INFO, "Starting myMethod1");
//    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getSpecialtyCode() {
        return specialtyCode;
    }

    public void setSpecialtyCode(String specialtyCode) {
        this.specialtyCode = specialtyCode;
    }

    public int getDiplomaNumber() {
        return diplomaNumber;
    }

    public void setDiplomaNumber(int diplomaNumber) {
        this.diplomaNumber = diplomaNumber;
    }
}
