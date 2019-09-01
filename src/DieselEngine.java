public class DieselEngine extends Engine {
    private static final String TYPE = "Diesel";
    //качество топлива
    private boolean fuel_quality;

    public DieselEngine(String name, int power, int torque, boolean fuel_quality) {
        super(name, power * 5, torque);
        this.fuel_quality = fuel_quality;
    }

    public void setFuel_quality(boolean fuel_quality) {
        this.fuel_quality = fuel_quality;
    }

    public boolean getFuel_quality() {
        return fuel_quality;
    }

    @Override
    public void setPower(int power) {
        super.setPower(power * 5);
    }

    @Override
    String getType() {
        return TYPE;
    }
}
