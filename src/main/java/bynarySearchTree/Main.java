package bynarySearchTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int limit = 8000;

        BinarySearchTree randomTree = new BinarySearchTree();
        BinarySearchTree orderedTree = new BinarySearchTree();

        AVLTree randomAvl = new AVLTree();
        AVLTree orderedAvl = new AVLTree();

        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < limit; i++) {
            numbers.add(i);
        }

        BiConsumer<List<Integer>, BinaryTree> insertConsumer = (List<Integer> array, BinaryTree tree) -> {
            array.forEach(tree::insert);
        };

        count(insertConsumer, numbers, orderedTree, "orderedTree::insert");
        count(insertConsumer, numbers, orderedAvl, "orderedAvl::insert");
        Collections.shuffle(numbers);
        count(insertConsumer, numbers, randomTree, "randomTree::insert");
        count(insertConsumer, numbers, randomAvl, "randomAvl::insert");

        BiConsumer<List<Integer>, BinaryTree> searchConsumer = (List<Integer> array, BinaryTree tree) -> {
            array.forEach(i -> tree.search(tree.getRoot(), i));
        };

        List<Integer> random = numbers.stream().limit(limit / 10).collect(Collectors.toList());
        count(searchConsumer, random, orderedTree, "orderedTree::search");
        count(searchConsumer, random, orderedAvl, "orderedAvl::search");
        count(searchConsumer, random, randomTree, "randomTree::search");
        count(searchConsumer, random, randomAvl, "randomAvl::search");

        BiConsumer<List<Integer>, BinaryTree> deleteConsumer = (List<Integer> array, BinaryTree tree) -> {
            array.forEach(tree::delete);
        };

        count(deleteConsumer, random, orderedTree, "orderedTree::delete");
        count(deleteConsumer, random, orderedAvl, "orderedAvl::delete");
        count(deleteConsumer, random, randomTree, "randomTree::delete");
        count(deleteConsumer, random, randomAvl, "randomAvl::delete");
    }

    public static void count(BiConsumer<List<Integer>, BinaryTree> consumer, List<Integer> array, BinaryTree tree, String type) {
        long start = System.currentTimeMillis();
        consumer.accept(array, tree);
        System.out.println("testOperation: " + type + " " + (System.currentTimeMillis() - start));
        //tree.print();
        //System.out.println();
    }
}
