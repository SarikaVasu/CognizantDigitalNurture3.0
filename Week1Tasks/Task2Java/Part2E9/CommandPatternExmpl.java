interface Command {
    void execute();
}
class LightOnCommand implements Command {
    private Light light;
    public LightOnCommand(Light light) {
        this.light = light;
    }
    public void execute() {
        light.turnLightsOn();
    }
}
class LightOffCommand implements Command {
    private Light light;
    public LightOffCommand(Light light) {
        this.light = light;
    }
    public void execute() {
        light.turnLightsOff();
    }
}
class RemoteControl {
    private Command command;
    public void setCommand(Command command) {
        this.command = command;
    }
    public void executeCommand() {
        command.execute();
    }
}
class Light {
    public void turnLightsOn() {
        System.out.println("The light is turned on");
    }
    public void turnLightsOff() {
        System.out.println("The light is turned off");
    }
}
public class CommandPatternExmpl {
    public static void main(String[] args) {
        Light light1 = new Light();
        Command lightOn = new LightOnCommand(light1);
        Command lightOff = new LightOffCommand(light1);
        RemoteControl remote = new RemoteControl();
        remote.setCommand(lightOn);
        remote.executeCommand();
        remote.setCommand(lightOff);
        remote.executeCommand();
    }
}