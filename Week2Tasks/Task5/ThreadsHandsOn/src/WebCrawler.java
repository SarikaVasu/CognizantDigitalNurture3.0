import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class CrawlerTask extends Thread  {
    private final String url;
    private final ConcurrentHashMap<String, String > crawledData;
    public CrawlerTask(String url, ConcurrentHashMap<String, String > crawleddata) {
        this.url = url;
        this.crawledData = crawleddata;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(500);
            String page = "Content of: " + url;
            crawledData.put(url, page);
            System.out.println("Crawled: " + url);
        } catch (InterruptedException err) {
            err.printStackTrace();
        }
    }
}
public class WebCrawler {
    private static final int MAX_THREADS = 10;
    private static final int NO_OF_TASKS = 15;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREADS);
        ConcurrentHashMap<String, String> crawledData =  new ConcurrentHashMap<>();
        for (int i=0; i<NO_OF_TASKS; i++) {
            executorService.submit(new CrawlerTask("https://google.com" + i, crawledData));
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException err) {
            err.printStackTrace();
        }
        System.out.println("Crawling " + crawledData.size() + " pages completed");
    }
}
