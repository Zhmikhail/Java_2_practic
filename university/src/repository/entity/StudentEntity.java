package repository.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "students")
public class StudentEntity {
    private String name;
    private int diplomaNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiplomaNumber() {
        return diplomaNumber;
    }

    public void setDiplomaNumber(int diplomaNumber) {
        this.diplomaNumber = diplomaNumber;
    }
}
