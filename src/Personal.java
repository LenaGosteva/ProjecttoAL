public class Personal {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Logger getLog() {
        return log;
    }

    public void setLog(Logger log) {
        this.log = log;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    int id;
    String role;
    String password;
    String login;
    Logger log = new Logger("C:\\Supermarket\\log.txt");
    public Personal(int id,String role,String name, String password, String login) {
        this.role = role;
        this.name = name;
        this.id = id;
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return this.role + " -> " +this.name;
    }

    String name;

}
