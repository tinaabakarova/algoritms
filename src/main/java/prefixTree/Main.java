package prefixTree;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args)
    {
        Map<String, String> values = new HashMap<>() {{
            put("the", "3");
            put("a", "1");
            put("there", "5");
            put("answer", "6");
            put("any", "3");
            put("by", "2");
            put("bye", "3");
            put("their", "5");
        }};

        String[] output = {"Not present in trie", "Present in trie"};


        Trie root = new Trie();

        for (Map.Entry<String, String> entry: values.entrySet()) {
            root.insert(entry.getKey(), entry.getValue());
        }


        System.out.println("Searching the keys:");
        if(root.search("the"))
            System.out.println("the --- " + output[1]);
        else System.out.println("the --- " + output[0]);

        if(root.search("these"))
            System.out.println("these --- " + output[1]);
        else System.out.println("these --- " + output[0]);

        if(root.search("their"))
            System.out.println("their --- " + output[1]);
        else System.out.println("their --- " + output[0]);

        if(root.search("thaw"))
            System.out.println("thaw --- " + output[1]);
        else System.out.println("thaw --- " + output[0]);


        System.out.println("Getting the values:");
        System.out.println("bye: " + root.get("bye"));
        System.out.println("any: " + root.get("any"));
        System.out.println("answer: " + root.get("answer"));

        System.out.println("Removing the keys:");
        System.out.println("bye: " + root.remove(root.root, "bye", 0));
        System.out.println("any: " + root.remove(root.root, "any", 0));

        System.out.println("Getting the values:");
        System.out.println("bye: " + root.get("bye"));
        System.out.println("any: " + root.get("any"));

    }
}
