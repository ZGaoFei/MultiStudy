package com.zgf.javalib;

import com.zgf.javalib.utlis.Node;

import java.util.HashMap;
import java.util.Map;

class Algorithm_0_9 {

    public static void main(String[] args) {
        int[] nums = new int[] {2, 7, 11, 15};
        int[] ints = twoSum(nums, 9);
        System.out.println(ints);
        int[] ints1 = twoSum1(nums, 9);
        System.out.println(ints1);


    }

    /**
     * 力扣 01
     *
     * 两数之和
     */
    private static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    // Hash表法
    private static int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(nums[0], 0);
        for (int i = 1; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i])};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{};
    }

    /**
     * 力扣 02
     *
     * 两数相加
     * 两个逆序的链表
     */
    private static Node<Integer> addTwoNum(Node<Integer> node1, Node<Integer> node2) {
        Node<Integer> node = new Node<>(-1);
        // TODO: 7/19/22
        return node;
    }
}
