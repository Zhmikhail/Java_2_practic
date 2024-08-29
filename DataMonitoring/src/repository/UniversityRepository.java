package repository;

import repository.entity.UniversityEntity;
import java.util.Optional;
import java.util.List;

public interface UniversityRepository {
    Optional<UniversityEntity> findByName(String name);
    List<UniversityEntity> findAll();
}
