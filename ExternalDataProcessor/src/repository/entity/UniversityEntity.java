package repository.entity;

import java.util.List;

public class UniversityEntity {
    private int id;
    private String university;
    private List<SpecialtyEntity> specialties;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public List<SpecialtyEntity> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<SpecialtyEntity> specialties) {
        this.specialties = specialties;
    }
}
