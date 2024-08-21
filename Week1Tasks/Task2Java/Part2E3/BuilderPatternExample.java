class Computer {
    private final String cpu;
    private final int ram;
    private final int storage;
    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
    }
    public void printComputerDetails() {
        System.out.println("CPU: " + cpu + "\tRAM: " + ram + "\tStorage: " + storage);
    }
    public static class Builder {
        private String cpu;
        private int ram;
        private int storage;
        public void setCPU(String cpu) {
            this.cpu = cpu;
        }
        public void setRAM(int ram) {
            this.ram = ram;
        }
        public void setStorage(int storage) {
            this.storage = storage;
        }
        public Computer build() {
            return new Computer(this);
        }
    }
}
public class BuilderPatternExample {
    public static void main(String[] args) {
        Computer.Builder build1 = new Computer.Builder();
        build1.setCPU("Intel");
        build1.setRAM(64);
        build1.setStorage(256);
        Computer comp1 = build1.build();
        comp1.printComputerDetails();
    }
}