import java.util.*;

public class week01and02 {

    public static void main(String[] args) {

        InventoryManager manager = new InventoryManager();

        manager.addProduct("IPHONE15_256GB", 3);

        System.out.println(manager.checkStock("IPHONE15_256GB"));

        System.out.println(manager.purchaseItem("IPHONE15_256GB", 101));
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 102));
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 103));
        System.out.println(manager.purchaseItem("IPHONE15_256GB", 104));
    }
}

class InventoryManager {

    private Map<String, Integer> stock = new HashMap<>();
    private Map<String, Queue<Integer>> waitingList = new HashMap<>();

    public void addProduct(String productId, int quantity) {
        stock.put(productId, quantity);
        waitingList.put(productId, new LinkedList<>());
    }

    public synchronized String purchaseItem(String productId, int userId) {

        int current = stock.getOrDefault(productId, 0);

        if (current > 0) {
            stock.put(productId, current - 1);
            return "Purchase successful. Remaining: " + (current - 1);
        } else {
            Queue<Integer> queue = waitingList.get(productId);
            queue.add(userId);
            return "Added to waiting list. Position: " + queue.size();
        }
    }

    public int checkStock(String productId) {
        return stock.getOrDefault(productId, 0);
    }
}