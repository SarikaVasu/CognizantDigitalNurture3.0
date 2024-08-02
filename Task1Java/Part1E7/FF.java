
public class FF {
    public static double predictFunction(double principle, double rate, int period) {
        if(period == 0) {
            return principle;
        }
        return predictFunction(principle*(1+rate), rate, period - 1);
    }
    public static void main(String[] args) {
        System.out.printf("%.2f", predictFunction(1000.0, 0.05, 12));
    }
}