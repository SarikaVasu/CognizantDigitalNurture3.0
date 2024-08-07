public class Feedback {
    private int feedbackId;
    private String customerName;
    private float rating;
    private String comments;
    public Feedback(int feedbackId, String customerName, float rating, String comments) {
        this.feedbackId = feedbackId;
        this.customerName = customerName;
        this.rating = rating;
        this.comments = comments;
    }
    public float getRating() {
        return this.rating;
    }
    public String getCustomerName() {
        return this.customerName;
    }
    public String getComments() {
        return this.comments;
    }
    @Override
    public String toString() {
        return "Feedback Id: " + feedbackId + ", Customer Name: " + customerName + ", Rating: " + rating + ", Comments: " + comments;
    }
}
