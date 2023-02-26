import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {





    private static final String INFO = "info";
    private static final String ERROR = "err";
    private static final String WARNING = "war";
    private static final String NOT_TYPE = "not_type";
    String pathToFile;
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private final LocalDateTime now = LocalDateTime.now();
    String date = now.format(format);
    private String msg;
    private String type;

    public Logger(String file) {
        this.pathToFile = file;
    }

    public void setMsg(String msg, String type) {
        if (type.equals(NOT_TYPE)) {
            System.out.println(this.date + " " + msg);
        } else {
            if (!type.equals(INFO) && !type.equals(ERROR) && !type.equals(WARNING)) {
                System.out.println("Not found type!" + type);
                return;
            }
            this.msg = msg;
            this.type = type;
            switch (type){
                case INFO:
                    System.out.printf("%s [%s] %s", AllConstants.ANSI_CYAN + this.date, this.type, this.msg + AllConstants.ANSI_RESET);
                    break;
                case ERROR:System.out.printf("%s [%s] %s", AllConstants.ANSI_RED + this.date, this.type, this.msg + AllConstants.ANSI_RESET);
                    break;
                case WARNING:System.out.printf("%s [%s] %s", AllConstants.ANSI_YELLOW + this.date, this.type, this.msg + AllConstants.ANSI_RESET);
                    break;
                case NOT_TYPE:System.out.printf("%s [%s] %s", AllConstants.ANSI_BLACK + this.date, this.type, this.msg + AllConstants.ANSI_RESET);
                    break;

            }

        }

    }
}