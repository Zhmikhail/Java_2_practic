package repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import repository.entity.UniversityEntity;
import repository.UniversityRepository;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.io.FileInputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import repository.entity.UniversityEntity;

import java.util.List;

@Repository
public class UniversityRepositoryImpl implements UniversityRepository {

    @Autowired
    private MongoTemplate mongoTemplateUniversity;

    @Override
    public List<UniversityEntity> findAll() {
        return mongoTemplateUniversity.findAll(UniversityEntity.class, "students");
    }

    @Override
    public Optional<UniversityEntity> findByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("university").is(name));
        UniversityEntity university = mongoTemplateUniversity.findOne(query, UniversityEntity.class, "students");
        return Optional.ofNullable(university);
    }
}
