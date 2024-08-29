package repository.entity;

import java.util.List;
public class UniversityEntity {
    private int id;
    private String university;
    private List<TrendEntity> trends;

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

    public List<TrendEntity> getTrends() {
        return trends;
    }

    public void setTrends(List<TrendEntity> trends) {
        this.trends = trends;
    }
}
