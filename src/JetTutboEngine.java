public class JetTutboEngine extends Engine {
    private static final String TYPE = "JetTurbo";
    //плотность топлива
    private int fuel_density;

    public JetTutboEngine(String name, int power, int torque, int fuel_density) {
        super(name, power * 10, torque);
        this.fuel_density = fuel_density;
    }

    public void setFuel_density(int fuel_density) {
        this.fuel_density = fuel_density;
    }

    public int getFuel_density() {
        return fuel_density;
    }

    @Override
    public void setPower(int power) {
        super.setPower(power * 10);
    }

    @Override
    String getType() {
        return TYPE;
    }

}
