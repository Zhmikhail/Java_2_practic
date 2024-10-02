package repository.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "students")
public class StudentEntity {

    @Id
    private String id;
    private String name;
    private int age;
    private String university;
    private String specialtyCode;
    private int diplomaNumber;

    public StudentEntity(String name, int age, String university, String specialtyCode, int diplomaNumber) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.age = age;
        this.university = university;
        this.specialtyCode = specialtyCode;
        this.diplomaNumber = diplomaNumber;
    }

    public StudentEntity() {}

    // Геттеры для всех полей
    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getUniversity() { return university; }
    public String getSpecialtyCode() { return specialtyCode; }
    public int getDiplomaNumber() { return diplomaNumber; }
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setSpecialtyCode(String specialtyCode) {
        this.specialtyCode = specialtyCode;
    }

    public void setDiplomaNumber(int diplomaNumber) {
        this.diplomaNumber = diplomaNumber;
    }

    @Override
    public String toString() {
        return String.format("StudentEntity{id='%s', name='%s', age=%d, university='%s', specialtyCode='%s', diplomaNumber=%d}",
                id, name, age, university, specialtyCode, diplomaNumber);
    }
}
