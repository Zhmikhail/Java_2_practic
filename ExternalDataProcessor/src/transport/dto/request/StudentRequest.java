package transport.dto.request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class StudentRequest implements Serializable {
    private String university;
    private String specialty;
    private int diplomaNumber;
    private String student;

    public static StudentRequest fromByteArray(byte[] bytes) throws IOException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            return (StudentRequest) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Unable to deserialize request", e);
        }
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public int getDiplomaNumber() {
        return diplomaNumber;
    }

    public void setDiplomaNumber(int diplomaNumber) {
        this.diplomaNumber = diplomaNumber;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }
}
