import java.time.LocalDate;

public class SalesRecord {
    private int recordId;
    private String salesPerson;
    private String region;
    private int amount;
    private LocalDate date;
    public SalesRecord(int recordId, String salesPerson, String region, int amount, LocalDate date) {
        this.recordId = recordId;
        this.salesPerson = salesPerson;
        this.region = region;
        this.amount = amount;
        this.date = date;
    }
    public String getRegion() {
        return this.region;
    }
    public int getSalesAmount() {
        return this.amount;
    }
    public String getSalesPerson() {
        return this.salesPerson;
    }
    @Override
    public String toString() {
        return "RecordId: " + recordId + ", Sales Person: " + salesPerson + ", Region: " + region + ", Amount: " + amount + ", Date: " + date;
    }
}
