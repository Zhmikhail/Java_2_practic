package repository.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document(collection = "students")
public class UniversityEntity {
    @Id
    private ObjectId id;
    private String university;
    private List<TrendEntity> trends;





//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

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
