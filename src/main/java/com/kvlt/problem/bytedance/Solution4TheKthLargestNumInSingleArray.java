package com.kvlt.problem.bytedance;

import java.util.Random;

/**
 * Solution4TheKthLargestNumInSingleArray
 *
 * @author KVLT
 * @date 2022-04-14.
 */
public class Solution4TheKthLargestNumInSingleArray {

    /**
     * 快速排序
     *
     * 我们可以改进快速排序算法来解决这个问题：在分解的过程当中，我们会对子数组进行划分，如果划分得到的 q 正好就是我们需要的下标，就直接返回 a[q]；否则，如果 q 比目标下标小，就递归右子区间，否则递归左子区间。这样就可以把原来递归两个区间变成只递归一个区间，提高了时间效率。这就是「快速选择」算法。
     *
     * 复杂度分析
     *
     * 时间复杂度：O(n)。
     * 空间复杂度：O(logn)，递归使用栈空间的空间代价的期望为 O(logn)。
     *
     */
    static class FastSort {

        public static void main(String[] args) {
            int[] nums = new int[]{1,2,3,4,5,6,7,7,8};
            int k = 2;
            System.out.println(new FastSort().findKthLargest(nums, k));
        }

        Random random = new Random();

        public int findKthLargest(int[] nums, int k) {
            return quickSelect(nums, 0, nums.length - 1, nums.length - k);
        }

        public int quickSelect(int[] nums, int l, int r, int index) {
            int q = randomPartition(nums, l, r);
            if (q == index) {
                return nums[q];
            } else {
                return q < index ? quickSelect(nums, q + 1, r, index) : quickSelect(nums, l, q - 1, index);
            }
        }

        public int randomPartition(int[] nums, int l, int r) {
            int i = random.nextInt(r - l + 1) + l;
            swap(nums, i, r);
            return partition(nums, l, r);
        }

        public int partition(int[] nums, int l, int r) {
            int x = nums[r], i = l - 1;
            for (int j = l; j < r; ++j) {
                if (nums[j] <= x) {
                    swap(nums, ++i, j);
                }
            }
            swap(nums, i + 1, r);
            return i + 1;
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

    }

}
