import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RaceResults {
    private final List<String> results = new ArrayList<>();

    public synchronized void addResult(String name) {
        results.add(name);
    }

    public List<String> getResults() {
        return Collections.unmodifiableList(results);
    }
}
