package repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import repository.StudentRepository;
import repository.entity.StudentEntity;

import java.util.List;
import java.util.Optional;

public class StudentRepositoryImpl implements StudentRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public StudentRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void save(StudentEntity student) {
        mongoTemplate.save(student);
    }

    @Override
    public List<StudentEntity> findAll() {
        return mongoTemplate.findAll(StudentEntity.class);
    }

    @Override
    public Optional<StudentEntity> findByDetails(String name, int age, String university, String specialtyCode, int diplomaNumber) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name)
                .and("age").is(age)
                .and("university").is(university)
                .and("specialtyCode").is(specialtyCode)
                .and("diplomaNumber").is(diplomaNumber));

        StudentEntity student = mongoTemplate.findOne(query, StudentEntity.class);
        return Optional.ofNullable(student);
    }

    @Override
    public void update(StudentEntity student) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(student.getId()));

        mongoTemplate.findAndReplace(query, student);
    }

    @Override
    public void delete(StudentEntity student) {
        mongoTemplate.remove(student);
    }
}
