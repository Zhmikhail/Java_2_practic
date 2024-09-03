package repository.entity;

public class StudentEntity {
    private String id;
    private String name;
    private int age;
    private String university;
    private String specialtyCode;
    private int diplomaNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "StudentEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", university='" + university + '\'' +
                ", specialtyCode='" + specialtyCode + '\'' +
                ", diplomaNumber=" + diplomaNumber +
                '}';
    }
}
