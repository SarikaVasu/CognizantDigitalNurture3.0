import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class SalesAnalysis {
    private ArrayList<SalesRecord> salesRecords = new ArrayList<>();
    public void addSalesRecord(SalesRecord salesRecord) {
        salesRecords.add(salesRecord);
    }
    public List<SalesRecord> filterBasedOnCategorySeq(String cat) {
        long startSeq = System.nanoTime();
        List<SalesRecord> filteredRecord = salesRecords.stream().filter(record -> record.getCategory().equals(cat)).sorted(Comparator.comparing(SalesRecord::getDate)).toList();
        //(record1, record2) -> record1.getDate().compareTo(record2.getDate()) => comparing
        long endSeq = System.nanoTime();
        filteredRecord.forEach(System.out::println);
        System.out.println((endSeq - startSeq) + "ns");
        return filteredRecord;
    }
    public List<SalesRecord> filterBasedOnCategoryParallel(String cat) {
        long startParallel = System.nanoTime();
        List<SalesRecord> filteredRecord = salesRecords.parallelStream().filter(record -> record.getCategory().equals(cat)).sorted(Comparator.comparing(SalesRecord::getDate)).toList();
        long endParallel = System.nanoTime();
        filteredRecord.forEach(System.out::println);
        System.out.println((endParallel - startParallel) + "ns");
        return filteredRecord;
    }
    public OptionalDouble avgForRegion(String region) {
        OptionalDouble avg = salesRecords.stream().filter(salesRecord -> salesRecord.getRegion()==region).mapToInt(SalesRecord::getSalesAmount).average();
        return avg;
    }
    public Optional<SalesRecord> highestSalesRecord() {
        Optional<SalesRecord> highest = salesRecords.stream().max(Comparator.comparing(SalesRecord::getSalesAmount));
        return highest;
    }
    public static void main(String[] args) {
        SalesAnalysis salesAnalysis = new SalesAnalysis();
        salesAnalysis.addSalesRecord(new SalesRecord(1, "A", "North", 100, LocalDate.of(2023, 1, 10), "Clothes", 2));
        salesAnalysis.addSalesRecord(new SalesRecord(2, "A", "South", 750, LocalDate.of(2023, 2, 15), "Clothes", 5));
        salesAnalysis.addSalesRecord(new SalesRecord(3, "C", "North", 50, LocalDate.of(2023, 3, 20), "Electronics", 1));
        salesAnalysis.addSalesRecord(new SalesRecord(4, "D", "East", 100, LocalDate.of(2023, 4, 25), "Clothes", 10));
        salesAnalysis.addSalesRecord(new SalesRecord(5, "E", "North", 200, LocalDate.of(2023, 5, 30), "Food", 12));
        salesAnalysis.filterBasedOnCategorySeq("Clothes");
        salesAnalysis.filterBasedOnCategoryParallel("Clothes");
        salesAnalysis.avgForRegion("North");
        salesAnalysis.highestSalesRecord().ifPresent(System.out::println);
    }
}
