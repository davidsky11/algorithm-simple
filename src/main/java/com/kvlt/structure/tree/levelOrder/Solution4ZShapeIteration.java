package com.kvlt.structure.tree.levelOrder;

import com.kvlt.structure.tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Solution4ZShapeIteration：二叉树的Z形遍历
 *
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 此题是「102. 二叉树的层序遍历」的变种，最后输出的要求有所变化，要求我们按层数的奇偶来决定每一层的输出顺序。规定二叉树的根节点为第 00 层，如果当前层数是偶数，从左至右输出当前层的节点值，否则，从右至左输出当前层的节点值。
 *
 * @author KVLT
 * @date 2022-04-13.
 */
public class Solution4ZShapeIteration {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        boolean isOrderLeft = true;

        while (!nodeQueue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode curNode = nodeQueue.poll();
                if (null == curNode) {
                    continue;
                }
                if (isOrderLeft) {
                    levelList.offerLast(curNode.getVal());
                } else {
                    levelList.offerFirst(curNode.getVal());
                }
                if (curNode.getLeft() != null) {
                    nodeQueue.offer(curNode.getLeft());
                }
                if (curNode.getRight() != null) {
                    nodeQueue.offer(curNode.getRight());
                }
            }
            ans.add(new LinkedList<Integer>(levelList));
            isOrderLeft = !isOrderLeft;
        }

        return ans;
    }

}
