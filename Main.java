public class Main {
    public static void main(String[] args) {
        int numCars = 5;

        RaceMonitor monitor = new RaceMonitor();
        RaceResults results = new RaceResults();
        CarThread[] cars = new CarThread[numCars];

        for (int i = 0; i < numCars; i++) {
            cars[i] = new CarThread(i + 1, monitor, results);
            cars[i].start();
        }

        while (results.getResults().size() < numCars) {
            monitor.allowNextMove();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            monitor.stopMove();
        }

        for (CarThread car : cars) {
            try {
                car.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\nRACE RESULTS:");
        int rank = 1;
        for (String car : results.getResults()) {
            System.out.println(rank++ + ". " + car);
        }
    }
}
