public class CombustionEngine extends Engine {
    private static final String TYPE = "Combustion";
    //наличие присадок
    private boolean additives;

    public CombustionEngine(String name, int power, int torque, boolean additives) {
        super(name, power * 2, torque);
        this.additives = additives;
    }

    public void setAdditives(boolean additives) {
        this.additives = additives;
    }

    public boolean getAdditivies() {
        return additives;
    }

    @Override
    public void setPower(int power) {
        super.setPower(power * 2);
    }

    @Override
    String getType() {
        return TYPE;
    }
}
