import java.util.*;

public class week01and02 {

    public static void main(String[] args) {

        AnalyticsDashboard dashboard = new AnalyticsDashboard();

        dashboard.processEvent("/news", "user1", "google");
        dashboard.processEvent("/news", "user2", "facebook");
        dashboard.processEvent("/sports", "user3", "google");

        System.out.println(dashboard.topPages(2));
    }
}

class AnalyticsDashboard {

    Map<String, Integer> views = new HashMap<>();

    public void processEvent(String url, String user, String source) {
        views.put(url, views.getOrDefault(url, 0) + 1);
    }

    public List<String> topPages(int k) {

        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        pq.addAll(views.entrySet());

        List<String> result = new ArrayList<>();

        while (k-- > 0 && !pq.isEmpty())
            result.add(pq.poll().getKey());

        return result;
    }
}