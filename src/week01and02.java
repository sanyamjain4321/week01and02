import java.util.*;

public class week01and02 {

    public static void main(String[] args) {

        PlagiarismDetector detector = new PlagiarismDetector();

        detector.addDocument("doc1", "This is a plagiarism detection test example", 3);
        detector.addDocument("doc2", "This is another plagiarism example", 3);

        System.out.println(detector.checkDocument("this is plagiarism example", 3));
    }
}

class PlagiarismDetector {

    Map<String, Set<String>> index = new HashMap<>();

    public void addDocument(String id, String text, int n) {

        String[] words = text.split(" ");

        for (int i = 0; i <= words.length - n; i++) {

            String gram = "";

            for (int j = i; j < i + n; j++)
                gram += words[j] + " ";

            index.computeIfAbsent(gram, k -> new HashSet<>()).add(id);
        }
    }

    public Map<String, Integer> checkDocument(String text, int n) {

        Map<String, Integer> result = new HashMap<>();

        String[] words = text.split(" ");

        for (int i = 0; i <= words.length - n; i++) {

            String gram = "";

            for (int j = i; j < i + n; j++)
                gram += words[j] + " ";

            if (index.containsKey(gram)) {

                for (String doc : index.get(gram))
                    result.put(doc, result.getOrDefault(doc, 0) + 1);
            }
        }

        return result;
    }
}