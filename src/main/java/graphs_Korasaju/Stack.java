package graphs_Korasaju;

import java.util.EmptyStackException;

public class Stack {
    private static final int MAX_SIZE = 1000;
    private int top;
    private int[] stackArray;

    public Stack() {
        top = -1;
        stackArray = new int[MAX_SIZE];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == MAX_SIZE - 1;
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("Stack is full. Cannot push element: " + value);
            return;
        }
        stackArray[++top] = value;
        System.out.println("Pushed element: " + value);
    }

    public int pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        int poppedValue = stackArray[top--];
        System.out.println("Popped element: " + poppedValue);
        return poppedValue;
    }

    public int peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stackArray[top];
    }

    public static void main(String[] args) {
        Stack stack = new Stack();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Top element: " + stack.peek());

        stack.pop();
        stack.pop();

        System.out.println("Is the stack empty? " + stack.isEmpty());
    }
}
