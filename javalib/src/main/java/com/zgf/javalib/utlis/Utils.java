package com.zgf.javalib.utlis;

public final class Utils {

    private Utils() {
    }

    public static void printArray(int[] nums) {
        for (int i : nums) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    public static void printArray(String[] strings) {
        for (String s : strings) {
            System.out.print(s + ", ");
        }
        System.out.println();
    }

    public static <T> void printArray(T[] array) {
        for (T t : array) {
            System.out.print(t + ", ");
        }
        System.out.println();
    }

    public static <T> void printNode(Node<T> node) {
        Node<T> first = node;
        while (first != null) {
            System.out.print(first.value);
            first = first.next;
        }
        System.out.println();
    }
}
