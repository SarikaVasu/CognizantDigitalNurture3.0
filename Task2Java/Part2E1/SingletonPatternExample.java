class Logger {
    private static Logger log;
    private Logger() {

    }
    public static Logger getLog() {
        if(log == null) {
            log = new Logger();
        }
        return log;
    }
}
public class SingletonPatternExample {
    public static void main(String[] args) {
        Logger log1 = Logger.getLog();
        System.out.println(log1);
        Logger log2 = Logger.getLog();
        System.out.println(log2);
        Logger log3 = Logger.getLog();
        System.out.println(log3);
        //all print statement prints the same logger object
    }
}