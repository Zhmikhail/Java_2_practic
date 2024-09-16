package repository.entity;

public class LogEntity {
    private String header;  // Заголовок записи: [created, mistake, success] - 7 байт
    private String message;  // Текст логируемой информации

    public LogEntity(String header, String message) {
        this.header = header;
        this.message = message;
    }

    // Преобразуем запись в байты для записи в файл

    @Override
    public String toString() {
        return
                header + '\'' +
                ", message='" + message + '\'' + '\n';
    }
}

