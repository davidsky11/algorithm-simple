package com.kvlt.structure.tree.iteration;

import com.kvlt.structure.tree.TreeNode;

import java.util.*;

/**
 * Solution4BinaryTree： 二叉树的统一迭代法
 * 问题：使用栈的话，无法同时解决访问节点（遍历节点）和处理节点（将元素放进结果集）不一致的情况。
 *
 * Sol：那我们就将访问的节点放入栈中，把要处理的节点也放入栈中但是要做标记。
 *
 * 如何标记呢，就是要处理的节点放入栈之后，紧接着放入一个空指针作为标记。 这种方法也可以叫做标记法。
 *
 * @author KVLT
 * @date 2022-04-12.
 */
public class SolutionNew4BinaryTree {

    /**
     * 前序遍历顺序：中-左-右，入栈顺序：中-右-左
     * @param root
     * @return
     */
    List<Integer> preOrderReverse(TreeNode root) {
        List<Integer> result = new LinkedList<>();

        Stack<TreeNode> stack = new Stack<>();
        if (null != root) {
            stack.push(root);
        }

        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (null != node) {
                // 将该节点弹出，避免重复操作，下面再将右中左节点添加到栈中
                stack.pop();

                if (null != node.getRight()) {
                    stack.push(node.getRight());
                }
                if (null != node.getLeft()) {
                    stack.push(node.getLeft());
                }

                stack.push(node);
                // 中节点访问过，但是还没有处理，加入空节点做为标记。
                stack.push(null);
            } else {
                // 将空节点弹出
                stack.pop();
                // 重新取出栈中元素
                node = stack.peek();
                stack.pop();
                result.add(node.getVal());
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
        List<Integer> result = new LinkedList<>();

        Stack<TreeNode> stack = new Stack<>();
        if (null != root) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (null != node) {
                stack.pop();
                if (null != node.getRight()) {
                    stack.push(node.getRight());
                }
                stack.push(node);
                // 中节点访问过，但是还没有处理，加入空节点做为标记。
                stack.push(null);

                if (null != node.getLeft()) {
                    stack.push(node.getLeft());
                }
            } else {
                // 将空节点弹出
                stack.pop();
                // 重新取出栈中元素
                node = stack.peek();
                stack.pop();
                result.add(node.getVal());
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
        List<Integer> result = new LinkedList<>();

        Stack<TreeNode> stack = new Stack<>();
        if (null != root) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (null != node) {
                stack.pop();
                stack.push(node);
                // 中节点访问过，但是还没有处理，加入空节点做为标记。
                stack.push(null);

                if (null != node.getRight()) {
                    stack.push(node.getRight());
                }
                if (null != node.getLeft()) {
                    stack.push(node.getLeft());
                }
            } else {
                // 将空节点弹出
                stack.pop();
                // 重新取出栈中元素
                node = stack.peek();
                stack.pop();
                result.add(node.getVal());
            }
        }

        return result;
    }
}
