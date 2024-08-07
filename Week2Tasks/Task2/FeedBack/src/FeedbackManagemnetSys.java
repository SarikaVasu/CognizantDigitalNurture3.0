import java.util.*;

public class FeedbackManagemnetSys {
    ArrayList<Feedback> feedbacks = new ArrayList<>();
    public void addFeedback(Feedback fb) {
        feedbacks.add(fb);
    }
    public List<Feedback> sortedFeedback(float rating) {
        List<Feedback> sortedFeedBacks = feedbacks.stream().filter(fb->fb.getRating()>=rating).toList();
        sortedFeedBacks.forEach(System.out::println);
        return sortedFeedBacks;
    }
    public List<String> comments() {
        List<String> comments = feedbacks.stream().map(fb -> fb.getCustomerName() + " " + fb.getComments()).toList();
        comments.forEach(System.out::println);
        return comments;
    }
    public long countPosFb() {
        List<Feedback> sortedFeedBacks = feedbacks.stream().filter(fb->fb.getRating()>=4).toList();
        return sortedFeedBacks.size();
    }
    public long countNegFb() {
        return feedbacks.stream().filter(fb->fb.getRating() < 3).count();
    }
    public static List<Feedback> filterFeedback(List<Feedback> list, FeedbackFilter filter) {
        return list.stream().filter(filter::filter).toList();
    }
    public static void processFeedback(List<Feedback> list, FeedbackProcessor processor) {
        list.forEach(processor::process);
    }
    public void filterAndProcess(int rating) {
        FeedbackFilter ratingFilter = feedback -> feedback.getRating() >= rating;
        FeedbackProcessor processor = System.out::println;
//        filterFeedback(feedbacks, ratingFilter).forEach(processor::process);
        processFeedback((filterFeedback(feedbacks, ratingFilter)), processor);
    }
    public static void main(String[] args) {
        FeedbackManagemnetSys fb = new FeedbackManagemnetSys();
        fb.addFeedback(new Feedback(1, "A", 5, "Great service!"));
        fb.addFeedback(new Feedback(2, "B", 3, "Average."));
        fb.addFeedback(new Feedback(3, "C", 1, "Terrible!"));
        fb.addFeedback(new Feedback(4, "D", 4, "Great"));
        fb.addFeedback(new Feedback(5, "E", 2, "Not satisfied."));
        fb.sortedFeedback(2);
        fb.comments();
        System.out.println(fb.countPosFb());
        System.out.println(fb.countNegFb());
        fb.filterAndProcess(2);
    }
}
