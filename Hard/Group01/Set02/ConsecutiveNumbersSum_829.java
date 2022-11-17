package LeetCode.Hard.Group01.Set02;

public class ConsecutiveNumbersSum_829 {

    public void run() {

        int n = 9;
        int res = consecutiveNumbersSum(n);
        System.out.println(res);
    }

    public int consecutiveNumbersSum(int N) {
        int count = 0;
        // x > 0 --> N/k - (k + 1)/2 > 0
        int upper_limit = (int)(Math.sqrt(2 * N + 0.25) - 0.5);
        for (int k = 1; k <= upper_limit; ++k) {
            // x should be an integer
            if ((N - k * (k + 1) / 2) % k == 0)
                count++;
        }
        return count;
    }
}
