package repository.entity;

import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
public class UniversityEntity {
    private static final Logger logger = Logger.getLogger(UniversityEntity.class.getName());
    private int id;
    private String university;
    private List<TrendEntity> trends;

    public int getId() {
        logger.log(Level.INFO, "Starting myMethod1");
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
