package com.kvlt.structure.tree;

/**
 * TreeNode
 *
 * @author KVLT
 * @date 2022-04-12.
 */
public class TreeNode {

    Integer val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(Integer val) {
        this.val = val;
    }

    TreeNode(Integer val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public Integer getVal() {
        return this.val;
    }

    public TreeNode getLeft() {
        return this.left;
    }

    public TreeNode getRight() {
        return this.right;
    }

}
