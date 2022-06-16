package LeetCode;

import java.util.PriorityQueue;
import java.util.Random;

//83%, 25%
public class KthLargestElement {
    int num_arr[];

    public void run() {
        int[] nums = new int[]{2, 1, 6, 4, 5, 7, 3, 9};
        int result = findKthLargestFormal(nums, 5);
        System.out.println(result);
    }

    public int partition(int left, int right, int pivot_index) {
        int pivot_number = this.num_arr[pivot_index];
        swapWithCheck(pivot_index, right);
        int stored_index = left;
        for (int i = left; i <= right; i++) {
            if (this.num_arr[i] < pivot_number) {
                swap(i, stored_index);
                stored_index++;

            }
        }
        swapWithCheck(right, stored_index);
        return stored_index;
    }

    public int quick_select(int left, int right, int kth_smallest) {
        if (left == right) return num_arr[left];
        Random random_num = new Random();
        int pivot_index = random_num.nextInt(right - left) + left;
        pivot_index = partition(left, right, pivot_index);
        if (pivot_index == kth_smallest) {
            return this.num_arr[kth_smallest];
        } else if (pivot_index < kth_smallest) {
            return quick_select(pivot_index + 1, right, kth_smallest);
        } else {
            return quick_select(left, pivot_index - 1, kth_smallest);
        }
    }

    // 83%, 44%
    public int findKthLargestFormal(int[] nums, int k) {
        this.num_arr = nums;
        int size = nums.length;
        return quick_select(0, size - 1, size - k);

    }

    public void swapWithCheck(int i, int j) {
        if (i != j) {
            int temp = this.num_arr[i];
            this.num_arr[i] = this.num_arr[j];
            this.num_arr[j] = temp;
        }
    }

    public void swap(int i, int j) {
        int temp = this.num_arr[i];
        this.num_arr[i] = this.num_arr[j];
        this.num_arr[j] = temp;
    }

    public int findKthLargestByHeap(int[] nums, int k) {
        int heapSize = nums.length;
        buildMaxHeap(nums, heapSize);
        for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
            swap(nums, 0, i);
            --heapSize;
            maxHeapify(nums, 0, heapSize);
        }
        return nums[0];
    }

    public void buildMaxHeap(int[] a, int heapSize) {
        for (int i = heapSize / 2; i >= 0; --i) {
            maxHeapify(a, i, heapSize);
        }
    }

    public void maxHeapify(int[] a, int i, int heapSize) {
        int l = i * 2 + 1, r = i * 2 + 2, largest = i;
        if (l < heapSize && a[l] > a[largest]) {
            largest = l;
        }
        if (r < heapSize && a[r] > a[largest]) {
            largest = r;
        }
        if (largest != i) {
            swap(a, i, largest);
            maxHeapify(a, largest, heapSize);
        }
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

// 作者：LeetCode-Solution
// 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/shu-zu-zhong-de-di-kge-zui-da-yuan-su-by-leetcode-/
// 来源：力扣（LeetCode）
// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


    //98%, 50%
//    Random random = new Random();
//
//    public int findKthLargestByQuickSort(int[] nums, int k) {
//        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
//    }
//
//    public int quickSelect(int[] a, int l, int r, int index) {
//        int q = randomPartition(a, l, r);
//        if (q == index) {
//            return a[q];
//        } else {
//            return q < index ? quickSelect(a, q + 1, r, index) : quickSelect(a, l, q - 1, index);
//        }
//    }
//
//    public int randomPartition(int[] a, int l, int r) {
//        int i = random.nextInt(r - l + 1) + l;
//        swap(a, i, r);
//        return partition(a, l, r);
//    }
//
//    public int partition(int[] a, int l, int r) {
//        int x = a[r], i = l - 1;
//        for (int j = l; j < r; ++j) {
//            if (a[j] <= x) {
//                swap(a, ++i, j);
//            }
//        }
//        swap(a, i + 1, r);
//        return i + 1;
//    }

//        public void swap(int[] a, int i, int j) {
//            int temp = a[i];
//            a[i] = a[j];
//            a[j] = temp;
//        }
//
//    作者：LeetCode-Solution
//    链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/shu-zu-zhong-de-di-kge-zui-da-yuan-su-by-leetcode-/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    //58%,48%
    public int findKthLargestByQueue(int[] nums, int k) {
        int len = nums.length;
        // 使用一个含有 k 个元素的最小堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, (a, b) -> a - b);
        for (int i = 0; i < k; i++) {
            minHeap.add(nums[i]);
        }
        for (int i = k; i < len; i++) {
            // 看一眼，不拿出，因为有可能没有必要替换
            Integer topEle = minHeap.peek();
            // 只要当前遍历的元素比堆顶元素大，堆顶弹出，遍历的元素进去
            if (nums[i] > topEle) {
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }
        return minHeap.peek();
    }
//
//    作者：liweiwei1419
//    链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/partitionfen-er-zhi-zhi-you-xian-dui-lie-java-dai-/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
