public class Base {
    private int health;
    private int healingRate;

    private Base() {
        // TODO set variables to start values
    }

    private static Base INSTANCE = null;

    public static Base get_Base() {
        if (INSTANCE == null) {
            INSTANCE = new Base();
        }
        return INSTANCE;
    }
}
