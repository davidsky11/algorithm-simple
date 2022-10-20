package com.kvlt.structure.tree.iteration;

import com.kvlt.structure.tree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Solution4BinaryTree： 二叉树的迭代遍历
 *
 * @author KVLT
 * @date 2022-04-12.
 */
public class Solution4BinaryTree {

    /**
     * 前序遍历顺序：中-左-右，入栈顺序：中-右-左
     * @param root
     * @return
     */
    List<Integer> preOrderReverse(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (null == root) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.getVal());
            if (null != node.getRight()) {
                stack.push(node.getRight());
            }
            if (null != node.getLeft()) {
                stack.push(node.getLeft());
            }
        }
        return result;
    }

    /**
     * 中序遍历顺序: 左-中-右 入栈顺序： 左-右
     * @param root
     * @return
     */
    List<Integer> inOrderReverse(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (null == root) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (null != cur || !stack.isEmpty()) {
            if (null != cur) {
                stack.push(cur);
                // 改变指向
                cur = cur.getLeft();
            } else {
                cur = stack.pop();
                result.add(cur.getVal());
                // 改变指向
                cur = cur.getRight();
            }
        }

        return result;
    }

    /**
     * 后序遍历顺序 左-右-中 入栈顺序：中-左-右 出栈顺序：中-右-左， 最后翻转结果
     * @param root
     * @return
     */
    List<Integer> postOrderReverse(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (null == root) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.getVal());
            if (null != node.getLeft()) {
                stack.push(node.getLeft());
            }
            if (null != node.getRight()) {
                stack.push(node.getRight());
            }
        }
        Collections.reverse(stack);

        return result;
    }
}
