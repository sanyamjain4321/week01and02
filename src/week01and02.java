import java.util.*;

public class week01and02 {

    public static void main(String[] args) {

        AutocompleteSystem auto = new AutocompleteSystem();

        auto.insert("java");
        auto.insert("javascript");
        auto.insert("java tutorial");

        System.out.println(auto.searchPrefix("jav"));
    }
}

class TrieNode {

    Map<Character, TrieNode> children = new HashMap<>();
    boolean end;
}

class AutocompleteSystem {

    TrieNode root = new TrieNode();

    public void insert(String word) {

        TrieNode node = root;

        for (char c : word.toCharArray()) {

            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
        }

        node.end = true;
    }

    public List<String> searchPrefix(String prefix) {

        TrieNode node = root;

        for (char c : prefix.toCharArray()) {

            if (!node.children.containsKey(c))
                return new ArrayList<>();

            node = node.children.get(c);
        }

        List<String> result = new ArrayList<>();

        dfs(node, prefix, result);

        return result;
    }

    private void dfs(TrieNode node, String word, List<String> result) {

        if (node.end)
            result.add(word);

        for (char c : node.children.keySet())
            dfs(node.children.get(c), word + c, result);
    }
}