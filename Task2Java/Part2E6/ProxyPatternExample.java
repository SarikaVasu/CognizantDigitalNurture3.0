interface Image {
    void display();
}
class RealImage implements Image {
    private String file;
    public RealImage(String file) {
        this.file = file;
        getImageFromServer();
    }
    private void getImageFromServer() {
        System.out.println("Getting image from server");
    }
    public void display() {
        System.out.println("Image displayed: " + file);
    }
}
class ProxyImage implements Image {
    private String file;
    private RealImage realImage;
    public ProxyImage(String file) {
        this.file = file;
    }
    public void display() {
        if(realImage == null) {
            realImage = new RealImage(file);
        }
        realImage.display();
    }
}
public class ProxyPatternExample {
    public static void main(String[] args) {
        Image img1 = new ProxyImage("img1.png");
        img1.display();
    }
}