import java.util.*;

public class week01and02 {

    public static void main(String[] args) {

        List<Transaction> list = new ArrayList<>();

        list.add(new Transaction(1, 500));
        list.add(new Transaction(2, 300));
        list.add(new Transaction(3, 200));

        FraudDetector detector = new FraudDetector();

        List<int[]> result = detector.twoSum(list, 500);

        for (int[] pair : result) {
            System.out.println(pair[0] + " , " + pair[1]);
        }
    }
}

class Transaction {

    int id;
    int amount;

    Transaction(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }
}

class FraudDetector {

    public List<int[]> twoSum(List<Transaction> list, int target) {

        Map<Integer, Transaction> map = new HashMap<>();
        List<int[]> result = new ArrayList<>();

        for (Transaction t : list) {

            int complement = target - t.amount;

            if (map.containsKey(complement))
                result.add(new int[]{map.get(complement).id, t.id});

            map.put(t.amount, t);
        }

        return result;
    }
}