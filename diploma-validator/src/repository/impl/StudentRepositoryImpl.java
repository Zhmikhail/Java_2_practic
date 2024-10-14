package repository.impl;

import enums.StudentColumns;
import enums.StudentSqlQuery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import repository.StudentRepository;
import repository.entity.StudentEntity;
import org.springframework.stereotype.Repository;
import service.StudentService;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private final JdbcTemplate jdbcTemplate;
    private static final Logger logger = Logger.getLogger(StudentService.class.getName());


    public StudentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveStudent(StudentEntity student) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(StudentSqlQuery.INSERT_STUDENT.getQuery(), new String[]{"id"});
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getUniversity());
            ps.setString(4, student.getSpecialtyCode());
            ps.setInt(5, student.getDiplomaNumber());
            return ps;
        }, keyHolder);

        int generatedId = keyHolder.getKey().intValue();
        student.setId(generatedId);  // Присваиваем сгенерированный ID объекту студента

        // Добавьте лог для проверки присвоенного ID
        logger.info("Student saved with ID: " + generatedId);
    }


    @Override
    public List<StudentEntity> getAllStudents() {
        return jdbcTemplate.query(StudentSqlQuery.SELECT_ALL.getQuery(), (rs, rowNum) ->
                new StudentEntity(
                        rs.getString(StudentColumns.NAME.getColumn()),
                        rs.getInt(StudentColumns.AGE.getColumn()),
                        rs.getString(StudentColumns.UNIVERSITY.getColumn()),
                        rs.getString(StudentColumns.SPECIALTY_CODE.getColumn()),
                        rs.getInt(StudentColumns.DIPLOMA_NUMBER.getColumn())
                ));
    }

    @Override
    public void updateStudent(StudentEntity student) {
        jdbcTemplate.update(StudentSqlQuery.UPDATE_STUDENT.getQuery(),
                student.getName(), student.getAge(), student.getUniversity(), student.getSpecialtyCode(), student.getDiplomaNumber(), student.getId());
    }

    @Override
    public void updateStudentByDetails(StudentEntity student) {
        jdbcTemplate.update(StudentSqlQuery.UPDATE_STUDENT.getQuery(),
                student.getName(), student.getAge(), student.getUniversity(), student.getSpecialtyCode(), student.getDiplomaNumber(), student.getId());
    }

    @Override
    public void deleteStudent(StudentEntity student) {
        jdbcTemplate.update(StudentSqlQuery.DELETE_STUDENT.getQuery(), student.getId());
    }

    @Override
    public void deleteStudentByDetails(StudentEntity student) {
        jdbcTemplate.update(StudentSqlQuery.DELETE_STUDENT_BY_DETAILS.getQuery(),
                student.getName(), student.getAge(), student.getUniversity(), student.getSpecialtyCode(), student.getDiplomaNumber());
    }

    @Override
    public Optional<StudentEntity> findByDetails(String name, int age, String university, String specialtyCode, int diplomaNumber) {
        return jdbcTemplate.query(StudentSqlQuery.FIND_BY_DETAILS.getQuery(),
                new Object[]{name, age, university, specialtyCode, diplomaNumber}, rs -> {
                    if (rs.next()) {
                        return Optional.of(new StudentEntity(
                                rs.getInt(StudentColumns.ID.getColumn()),
                                rs.getString(StudentColumns.NAME.getColumn()),
                                rs.getInt(StudentColumns.AGE.getColumn()),
                                rs.getString(StudentColumns.UNIVERSITY.getColumn()),
                                rs.getString(StudentColumns.SPECIALTY_CODE.getColumn()),
                                rs.getInt(StudentColumns.DIPLOMA_NUMBER.getColumn())
                        ));
                    }
                    return Optional.empty();
                });
    }

    @Override
    public Optional<StudentEntity> findById(int id) {
        return jdbcTemplate.query(StudentSqlQuery.FIND_BY_ID.getQuery(), new Object[]{id}, rs -> {
            if (rs.next()) {
                return Optional.of(new StudentEntity(
                        rs.getInt(StudentColumns.ID.getColumn()),
                        rs.getString(StudentColumns.NAME.getColumn()),
                        rs.getInt(StudentColumns.AGE.getColumn()),
                        rs.getString(StudentColumns.UNIVERSITY.getColumn()),
                        rs.getString(StudentColumns.SPECIALTY_CODE.getColumn()),
                        rs.getInt(StudentColumns.DIPLOMA_NUMBER.getColumn())
                ));
            }
            return Optional.empty();
        });
    }

    @Override
    public List<StudentEntity> findAll() {
        return jdbcTemplate.query(StudentSqlQuery.SELECT_ALL.getQuery(), (rs, rowNum) ->
                new StudentEntity(
                        rs.getInt(StudentColumns.ID.getColumn()),
                        rs.getString(StudentColumns.NAME.getColumn()),
                        rs.getInt(StudentColumns.AGE.getColumn()),
                        rs.getString(StudentColumns.UNIVERSITY.getColumn()),
                        rs.getString(StudentColumns.SPECIALTY_CODE.getColumn()),
                        rs.getInt(StudentColumns.DIPLOMA_NUMBER.getColumn())
                ));
    }
}
