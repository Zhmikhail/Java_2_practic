package repository.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "students")
public class StudentEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    private String university;
    @Column(nullable = false)
    private String specialtyCode;
    @Column(nullable = false)
    private int diplomaNumber;

    public StudentEntity(String name, int age, String university, String specialtyCode, int diplomaNumber) {
        this.name = name;
        this.age = age;
        this.university = university;
        this.specialtyCode = specialtyCode;
        this.diplomaNumber = diplomaNumber;
    }

    public StudentEntity() {}

    public StudentEntity(int id, String name, int age, String university, String specialtyCode, int diplomaNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.university = university;
        this.specialtyCode = specialtyCode;
        this.diplomaNumber = diplomaNumber;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getUniversity() { return university; }
    public String getSpecialtyCode() { return specialtyCode; }
    public int getDiplomaNumber() { return diplomaNumber; }

    public void setId(Integer id) {
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
