package com.kvlt.structure.tree.recursion;

import com.kvlt.structure.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Solution4BinaryTree： 前中后序遍历（风格不统一）
 *
 * @author KVLT
 * @date 2022-04-12.
 */
public class Solution4BinaryTree {

    List<Integer> preOrderReverse(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preOrder(root, result);
        return result;
    }

    /**
     * 前序遍历  中 -> 左 -> 右
     * @param root
     * @param result
     */
    void preOrder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }

        result.add(root.getVal());

        preOrder(root.getLeft(), result);
        preOrder(root.getRight(), result);
    }

    /**
     * 中序遍历  左 -> 中 -> 右
     * @param root
     * @param result
     */
    void inOrder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inOrder(root.getLeft(), result);

        result.add(root.getVal());

        inOrder(root.getRight(), result);
    }

    /**
     * 后序遍历  左 -> 右 -> 中
     * @param root
     * @param result
     */
    void postOrder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inOrder(root.getLeft(), result);
        inOrder(root.getRight(), result);

        result.add(root.getVal());
    }

}
