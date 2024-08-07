import java.time.LocalDate;

public class SalesRecord {
    private int recordId;
    private String salesPerson;
    private String region;
    private int amount;
    private LocalDate date;
    private String productCategory;
    private int quantity;
    public SalesRecord(int recordId, String salesPerson, String region, int amount, LocalDate date, String productCategory, int quantity) {
        this.recordId = recordId;
        this.salesPerson = salesPerson;
        this.region = region;
        this.amount = amount;
        this.date = date;
        this.productCategory = productCategory;
        this.quantity = quantity;
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
    public String getCategory() {
        return this.productCategory;
    }
    public LocalDate getDate() {
        return this.date;
    }
    @Override
    public String toString() {
        return "RecordId: " + recordId + ", Sales Person: " + salesPerson + ", Region: " + region + ", Amount: " + amount + ", Date: " + date + ", Product Category: " + productCategory + ", Quantity: "  + quantity;
    }
}
