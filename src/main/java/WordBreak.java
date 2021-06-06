import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class WordBreak {

    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        Set<String> words = getWords("C:\\Users\\mohammad\\Desktop\\words_dictionary.json");
        wordBreak(input.next().toLowerCase(), words);
    }

    private static void wordBreak(String input, Set<String> dictionary) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(input));

        while (!queue.isEmpty()) {
            Node node = queue.remove();
            if (node.val.length() == 0) {
                System.out.println(node.parsed);
                return;
            } else
                for (String word : dictionary)
                    if (node.val.startsWith(word)) {
                        String valNew = node.val.substring(word.length());
                        String parsedNew = node.parsed;
                        parsedNew = parsedNew + " " + word;
                        queue.add(new Node(valNew, parsedNew));
                    }
        }
    }

    private static class Node {
        private String val;
        private String parsed;

        public Node(String initial) {
            val = initial;
            parsed = "";
        }

        public Node(String s, String p) {
            val = s;
            parsed = p;
        }
    }

    private static Set<String> getWords(String filePath) {
        try {
            JsonObject jsonObject = JsonParser.parseReader(new FileReader(filePath)).getAsJsonObject();
            return jsonObject.keySet();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}