public class Stats {
    private final long start = System.currentTimeMillis();

    public static Stats start() {
        return new Stats();
    }

    private Stats() {
    }

    public void show() {
        System.out.println("time: " + (System.currentTimeMillis() - start) / 1000.0);
    }
}
