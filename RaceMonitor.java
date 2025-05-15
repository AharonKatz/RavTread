public class RaceMonitor {
    public volatile boolean allowMove = false;

    public synchronized void allowNextMove() {
        allowMove = true;
        notifyAll();
    }

    public synchronized void stopMove() {
        allowMove = false;
    }
}
