package repository.impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import repository.entity.StudentEntity;
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




@Repository
public interface UniversityRepositoryImpl extends MongoRepository<UniversityEntity, Integer> {

    Optional<UniversityEntity> findByUniversity(String university);
}
