package console;


public enum Commands {

    HELP("Enter the 'help' to see all commands"),
    ADD("Add new diploma"),
    READ("Read all diplomas"),
    EXIT ("Exit from application")
    ;

    private final String description;

    Commands(String description) {
        this.description = description;
    }


    public void printHelp() {
        for (Commands command : Commands.values()) {
            System.out.println(command.name() + "," + command.description);
        }
    }




}