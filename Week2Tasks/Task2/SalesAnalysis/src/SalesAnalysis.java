import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class SalesAnalysis {
    private ArrayList<SalesRecord> salesRecords = new ArrayList<>();
    public void addSalesRecord(SalesRecord salesRecord) {
        salesRecords.add(salesRecord);
    }
    public List<SalesRecord> filterBasedOnRegion(String region) {
        List<SalesRecord> filteredRecord = salesRecords.stream().filter(record -> record.getRegion() .equals(region)).toList();
        filteredRecord.forEach(System.out::println);
        return filteredRecord;
    }
    public List<Integer> filterSalesAmount(List<SalesRecord> filteredRecord) {
        List<Integer> salesAmount = filteredRecord.stream().map((record) -> record.getSalesAmount()).toList();
        //List<Integer> salesAmount = filteredRecord.stream().map(SalesRecord::getSalesAmount).toList();
        salesAmount.forEach(System.out::println);
        return salesAmount;
    }
    public int totalSalesAmount(List<Integer> salesAmount) {
        //mapToInt(Integer : value)
        return salesAmount.stream().mapToInt((amount) -> amount).sum();
    }
    public Map<String, List<SalesRecord>> groupBySalesperson() {
        Map<String, List<SalesRecord>> salesPersonList = salesRecords.stream().collect(Collectors.groupingBy(SalesRecord::getSalesPerson));
        salesPersonList.forEach((salesPerson, records)-> System.out.println(salesPerson + " " + records));
        return salesPersonList;
    }
    public Map<String, Integer> totalSalesBySalesPerson() {
        Map<String, Integer> salesPersonReport = salesRecords.stream().collect(Collectors.groupingBy(SalesRecord::getSalesPerson, Collectors.summingInt(SalesRecord::getSalesAmount)));
        salesPersonReport.forEach((salesPerson, totalSales)-> System.out.println(salesPerson + " " + totalSales));
        return salesPersonReport;
    }
    public static void main(String[] args) {
        SalesAnalysis salesAnalysis = new SalesAnalysis();
        salesAnalysis.addSalesRecord(new SalesRecord(1, "A", "North", 100, LocalDate.of(2023, 1, 10)));
        salesAnalysis.addSalesRecord(new SalesRecord(2, "A", "South", 750, LocalDate.of(2023, 2, 15)));
        salesAnalysis.addSalesRecord(new SalesRecord(3, "C", "North", 50, LocalDate.of(2023, 3, 20)));
        salesAnalysis.addSalesRecord(new SalesRecord(4, "D", "East", 100, LocalDate.of(2023, 4, 25)));
        salesAnalysis.addSalesRecord(new SalesRecord(5, "E", "North", 200, LocalDate.of(2023, 5, 30)));
        List<SalesRecord> filteredList = salesAnalysis.filterBasedOnRegion("North");
        List<Integer> filteredSalesAmount = salesAnalysis.filterSalesAmount(filteredList);
        System.out.println(salesAnalysis.totalSalesAmount(filteredSalesAmount));
        salesAnalysis.groupBySalesperson();
        salesAnalysis.totalSalesBySalesPerson();
    }
}
