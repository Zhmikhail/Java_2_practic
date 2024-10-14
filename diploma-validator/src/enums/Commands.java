package enums;

public enum Commands {

    HELP("Enter 'help' to see all commands"),
    ADD("Add a new diploma"),
    READ("Read all diplomas"),
    UPDATE("Update student information"),
    DELETE("Delete a student"),
    EXIT("Exit from application");

    private final String description;

    Commands(String description) {
        this.description = description;
    }

    public void printHelp() {
        for (Commands command : Commands.values()) {
            System.out.println(command.name() + ": " + command.description);
        }
    }
}
