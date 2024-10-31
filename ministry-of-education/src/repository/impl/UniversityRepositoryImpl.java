package repository.impl;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import repository.entity.TrendEntity;
import repository.entity.UniversityEntity;

import java.util.Optional;


public interface UniversityRepositoryImpl extends MongoRepository<UniversityEntity, Integer> {
    Optional<UniversityEntity> findByUniversity(String university);
}
