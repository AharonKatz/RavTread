import java.util.Random;

public class CarThread extends Thread {
    private String name;
    private RaceMonitor monitor;
    private RaceResults results;
    private int trackLength = 100;
    private int progress = 0;
    private Random random = new Random();

    public CarThread(int id, RaceMonitor monitor, RaceResults results) {
        this.name = "Car " + id;
        this.monitor = monitor;
        this.results = results;
    }

    @Override
    public void run() {
        while (progress < trackLength) {
            synchronized (monitor) {
                while (!monitor.allowMove) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }

            progress += 10;
            System.out.println(name + " progressed to " + progress + "/" + trackLength + " km");

            try {
                Thread.sleep(500 + random.nextInt(1000)); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            if (progress >= trackLength) {
                results.addResult(name);
            }
        }
    }
}
