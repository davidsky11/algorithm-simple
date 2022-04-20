package com.kvlt.structure.tree.levelOrder;

import com.kvlt.structure.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Solution：层序遍历
 * 需要借用一个辅助数据结构即队列来实现，队列先进先出，符合一层一层遍历的逻辑，而是用栈先进后出适合模拟深度优先遍历也就是递归的逻辑。
 *
 * 而这种层序遍历方式就是图论中的广度优先遍历，只不过我们应用在二叉树上。
 *
 * @author KVLT
 * @date 2022-04-12.
 */
public class Solution {

    public List<List<Integer>> resList = new ArrayList<List<Integer>>();

    List<List<Integer>> levelOrder(TreeNode root) {
        // 递归
        levelOrderRecursion(root, 0);
        // 迭代
//        levelOrderInteration(root);

        return resList;
    }

    /**
     * DFS - 递归方式
     * @param node
     * @param deep
     */
    void levelOrderRecursion(TreeNode node, Integer deep) {
        if (null == node) {
            return;
        }
        deep++;

        if (resList.size() < deep) {
            //当层级增加时，list的Item也增加，利用list的索引值进行层级界定
            List<Integer> item = new ArrayList<Integer>();
            resList.add(item);
        }
        resList.get(deep - 1).add(node.getVal());

        levelOrderRecursion(node.getLeft(), deep);
        levelOrderRecursion(node.getRight(), deep);
    }

    /**
     * 迭代方式 - 借助队列
     * @param node
     */
    void levelOrderInteration(TreeNode node) {
        if (null == node) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            int size = queue.size();

            List<Integer> subList = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode tmpNode = queue.poll();
                if (null == tmpNode) {
                    continue;
                }

                subList.add(node.getVal());

                if (null != tmpNode.getLeft()) {
                    queue.offer(tmpNode.getLeft());
                }
                if (null != tmpNode.getRight()) {
                    queue.offer(tmpNode.getRight());
                }
            }

            resList.add(subList);
        }
    }
}
