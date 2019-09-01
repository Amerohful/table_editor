public abstract class Engine {
    private String name;
    //мощность
    private int power;
    //крутящий момент
    private int torque;

    private Engine(){}

    public Engine(String name, int power, int torque) {
        this.name = name;
        this.power  = power;
        this.torque = torque;
    }

    public Engine(Engine engine) {
        this.name = engine.name;
        this.power  = engine.power;
        this.torque = engine.torque;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setTorque(int torque) {
        this.torque = torque;
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public int getTorque() {
        return torque;
    }

    abstract String getType();
}
