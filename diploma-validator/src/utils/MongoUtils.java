package utils;

import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import repository.entity.StudentEntity;

import java.util.List;

@Component
public class MongoUtils {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveStudent(StudentEntity student) {
        mongoTemplate.save(student);
    }

    public List<StudentEntity> getAllStudents() {
        return mongoTemplate.findAll(StudentEntity.class);
    }

    public void updateStudent(StudentEntity student) {
        mongoTemplate.save(student); // MongoDB will update if the record exists
    }

    public void deleteStudent(StudentEntity student) {
        DeleteResult result = mongoTemplate.remove(student);
        if (result.getDeletedCount() > 0) {
            System.out.println("Student deleted successfully");
        } else {
            System.out.println("Student not found");
        }
    }
}

