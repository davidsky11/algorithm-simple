package com.kvlt.problem.leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Stack;
import java.util.stream.Stream;

/**
 * @author Administrator
 * 力扣 42. 接雨水
 * <a href="https://leetcode.cn/problems/trapping-rain-water/">力扣 42. 接雨水</a>
 *
 * 难度： 困难
 */
public class L42TrappingRainWater {

    /**
     * 构建测试参数
     * @return
     */
    static Stream<Arguments> intArrayAndIntProvider() {
        return Stream.of(
                Arguments.arguments(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}, 6),
                Arguments.arguments(new int[]{4,2,0,3,2,5}, 9)
        );
    }

    @ParameterizedTest
    @MethodSource("intArrayAndIntProvider")
    public void trappingRainWaterByRow(int[] heightArray, int waterCount) {
        ITrappingRainWater trappingRainWater = new TrappingRainWaterByRow();
        Assertions.assertEquals(waterCount, trappingRainWater.trap(heightArray));
    }

    @ParameterizedTest
    @MethodSource("intArrayAndIntProvider")
    public void trappingRainWaterByCol(int[] heightArray, int waterCount) {
        ITrappingRainWater trappingRainWater = new TrappingRainWaterByCol();
        Assertions.assertEquals(waterCount, trappingRainWater.trap(heightArray));
    }

    @ParameterizedTest
    @MethodSource("intArrayAndIntProvider")
    public void trappingRainWaterByDynamicProgramming(int[] heightArray, int waterCount) {
        ITrappingRainWater trappingRainWater = new TrappingRainWaterByDynamicProgramming();
        Assertions.assertEquals(waterCount, trappingRainWater.trap(heightArray));
    }

    @ParameterizedTest
    @MethodSource("intArrayAndIntProvider")
    public void trappingRainWaterByDoublePointer(int[] heightArray, int waterCount) {
        ITrappingRainWater trappingRainWater = new TrappingRainWaterByDoublePointer();
        Assertions.assertEquals(waterCount, trappingRainWater.trap(heightArray));
    }

    @ParameterizedTest
    @MethodSource("intArrayAndIntProvider")
    public void trappingRainWaterByStack(int[] heightArray, int waterCount) {
        ITrappingRainWater trappingRainWater = new TrappingRainWaterByStack();
        Assertions.assertEquals(waterCount, trappingRainWater.trap(heightArray));
    }

}

/**
 * 解法二到解法三，利用动态规划，空间换时间，解法三到解法四，优化动态规划的空间，这一系列下来，让人心旷神怡。
 */
interface ITrappingRainWater {
    int trap(int[] heightArray);
}

/**
 * 解法一：按行求
 *
 * 时间复杂度：如果最大的数是 mm，个数是 nn，那么就是 O(m*n)。
 * 空间复杂度：O(1)。
 *
 * 这个解法现在 AC 不了了，会报超时，但还是放在这里吧
 */
class TrappingRainWaterByRow implements ITrappingRainWater {
    // 解法一：按行求
    private int getMax(int[] heightArray) {
        int max = 0;
        for (int i = 0; i < heightArray.length; i++) {
            if (heightArray[i] > max) {
                max = heightArray[i];
            }
        }
        return max;
    }

    public int trap(int[] heightArray) {
        int sum = 0;
        // 找到最大的高度，以便遍历。
        int max = getMax(heightArray);
        for (int i = 1; i <= max; i++) {
            // 标记是否开始更新 temp
            boolean isStart = false;
            int tempSum = 0;
            for (int j = 0; j < heightArray.length; j++) {
                if (isStart && heightArray[j] < i) {
                    tempSum ++;
                }
                if (heightArray[j] >= i) {
                    sum += tempSum;
                    tempSum = 0;
                    isStart = true;
                }
            }
        }
        return sum;
    }
}

/**
 * 解法二：按列求
 *
 * 时间复杂度：O(n²），遍历每一列需要 nn，找出左边最高和右边最高的墙加起来刚好又是一个 n，所以是 n²。
 * 空间复杂度：O(1）。
 */
class TrappingRainWaterByCol implements ITrappingRainWater {

    @Override
    public int trap(int[] heightArray) {
        int sum = 0;
        // 最两端的列不用考虑，因为一定不会有水。所以下标从 1 到 length - 2
        for (int i = 1; i < heightArray.length - 1; i++) {
            int max_left = 0;
            //找出左边最高
            for (int j = i - 1; j >= 0; j--) {
                if (heightArray[j] > max_left) {
                    max_left = heightArray[j];
                }
            }
            int max_right = 0;
            //找出右边最高
            for (int j = i + 1; j < heightArray.length; j++) {
                if (heightArray[j] > max_right) {
                    max_right = heightArray[j];
                }
            }
            //找出两端较小的
            int min = Math.min(max_left, max_right);
            //只有较小的一段大于当前列的高度才会有水，其他情况不会有水
            if (min > heightArray[i]) {
                sum = sum + (min - heightArray[i]);
            }
        }
        return sum;
    }
}

/**
 * 解法三：动态规划
 *
 * 时间复杂度：O(n)。
 * 空间复杂度：O(n)，用来保存每一列左边最高的墙和右边最高的墙。
 */
class TrappingRainWaterByDynamicProgramming implements ITrappingRainWater {

    @Override
    public int trap(int[] heightArray) {
        int sum = 0;
        int[] max_left = new int[heightArray.length];
        int[] max_right = new int[heightArray.length];

        for (int i = 1; i < heightArray.length - 1; i++) {
            max_left[i] = Math.max(max_left[i - 1], heightArray[i - 1]);
        }
        for (int i = heightArray.length - 2; i >= 0; i--) {
            max_right[i] = Math.max(max_right[i + 1], heightArray[i + 1]);
        }
        for (int i = 1; i < heightArray.length - 1; i++) {
            int min = Math.min(max_left[i], max_right[i]);
            if (min > heightArray[i]) {
                sum = sum + (min - heightArray[i]);
            }
        }
        return sum;
    }
}

/**
 * 解法四：双指针
 * 动态规划中，我们常常可以对空间复杂度进行进一步的优化。
 *
 * 时间复杂度： O(n)。
 * 空间复杂度： O(1)。
 */
class TrappingRainWaterByDoublePointer implements ITrappingRainWater {

    @Override
    public int trap(int[] heightArray) {
        // 先优化 max_left
//            return trapModifyMaxLeft(heightArray);

        // 再使用 left right 双指针优化
        return trapLeftRrightPointer(heightArray);
    }

    private int trapModifyMaxLeft(int[] heightArray) {
        int sum = 0;
        int max_left = 0;
        int[] max_right = new int[heightArray.length];
        for (int i = heightArray.length - 2; i >= 0; i--) {
            max_right[i] = Math.max(max_right[i + 1], heightArray[i + 1]);
        }
        for (int i = 1; i < heightArray.length - 1; i++) {
            max_left = Math.max(max_left, heightArray[i - 1]);
            int min = Math.min(max_left, max_right[i]);
            if (min > heightArray[i]) {
                sum = sum + (min - heightArray[i]);
            }
        }
        return sum;
    }

    private int trapLeftRrightPointer(int[] heightArray) {
        int sum = 0;
        int max_left = 0;
        int max_right = 0;
        int left = 1;
        int right = heightArray.length - 2; // 加右指针进去
        for (int i = 1; i < heightArray.length - 1; i++) {
            //从左到右更
            if (heightArray[left - 1] < heightArray[right + 1]) {
                max_left = Math.max(max_left, heightArray[left - 1]);
                int min = max_left;
                if (min > heightArray[left]) {
                    sum = sum + (min - heightArray[left]);
                }
                left++;
                //从右到左更
            } else {
                max_right = Math.max(max_right, heightArray[right + 1]);
                int min = max_right;
                if (min > heightArray[right]) {
                    sum = sum + (min - heightArray[right]);
                }
                right--;
            }
        }
        return sum;
    }

}

/**
 * 解法五：栈
 * 用栈保存每堵墙。
 *      当遍历墙的高度的时候，如果当前高度小于栈顶的墙高度，说明这里会有积水，我们将墙的高度的下标入栈。
 *      如果当前高度大于栈顶的墙的高度，说明之前的积水到这里停下，我们可以计算下有多少积水了。计算完，就把当前的墙继续入栈，作为新的积水的墙。
 *
 * 总体的原则就是，
 *      1. 当前高度小于等于栈顶高度，入栈，指针后移。
 *      2. 当前高度大于栈顶高度，出栈，计算出当前墙和栈顶的墙之间水的多少，然后计算当前的高度和新栈的高度的关系，重复第 2 步。
 *  直到当前墙的高度不大于栈顶高度或者栈空，然后把当前墙入栈，指针后移。
 *
 *  时间复杂度：虽然 while 循环里套了一个 while 循环，但是考虑到每个元素最多访问两次，
 *          入栈一次和出栈一次，所以时间复杂度是 O(n)O(n)。
 *  空间复杂度：O(n)。栈的空间。
 */
class TrappingRainWaterByStack implements ITrappingRainWater {

    @Override
    public int trap(int[] heightArray) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        int current = 0;
        while (current < heightArray.length) {
            //如果栈不空并且当前指向的高度大于栈顶高度就一直循环
            while (!stack.empty() && heightArray[current] > heightArray[stack.peek()]) {
                int h = heightArray[stack.peek()]; //取出要出栈的元素
                stack.pop(); //出栈
                if (stack.empty()) { // 栈空就出去
                    break;
                }
                int distance = current - stack.peek() - 1; //两堵墙之前的距离。
                int min = Math.min(heightArray[stack.peek()], heightArray[current]);
                sum = sum + distance * (min - h);
            }
            stack.push(current); //当前指向的墙入栈
            current++; //指针后移
        }
        return sum;
    }

}