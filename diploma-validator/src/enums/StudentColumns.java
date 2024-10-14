package enums;

public enum StudentColumns {
    ID("id"),
    NAME("name"),
    AGE("age"),
    UNIVERSITY("institute_name"),
    SPECIALTY_CODE("specialty_number"),
    DIPLOMA_NUMBER("diploma_number");

    private final String column;

    StudentColumns(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }
}
