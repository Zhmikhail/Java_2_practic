package enums;

import static enums.StudentColumns.*;

public enum StudentSqlQuery {
    INSERT_STUDENT(String.format("INSERT INTO students (%s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?)",
            NAME.getColumn(), AGE.getColumn(), UNIVERSITY.getColumn(), SPECIALTY_CODE.getColumn(), DIPLOMA_NUMBER.getColumn())),
    SELECT_ALL("SELECT * FROM students"),
    UPDATE_STUDENT(String.format("UPDATE students SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?",
            NAME.getColumn(), AGE.getColumn(), UNIVERSITY.getColumn(), SPECIALTY_CODE.getColumn(), DIPLOMA_NUMBER.getColumn(), ID.getColumn())),
    DELETE_STUDENT(String.format("DELETE FROM students WHERE %s = ?", ID.getColumn())),
    DELETE_STUDENT_BY_DETAILS(String.format("DELETE FROM students WHERE %s = ? AND %s = ? AND %s = ? AND %s = ? AND %s = ?",
            NAME.getColumn(), AGE.getColumn(), UNIVERSITY.getColumn(), SPECIALTY_CODE.getColumn(), DIPLOMA_NUMBER.getColumn())),
    FIND_BY_DETAILS(String.format("SELECT * FROM students WHERE %s = ? AND %s = ? AND %s = ? AND %s = ? AND %s = ?",
            NAME.getColumn(), AGE.getColumn(), UNIVERSITY.getColumn(), SPECIALTY_CODE.getColumn(), DIPLOMA_NUMBER.getColumn())),
    FIND_BY_ID(String.format("SELECT * FROM students WHERE %s = ?", ID.getColumn()));

    private final String query;

    StudentSqlQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
