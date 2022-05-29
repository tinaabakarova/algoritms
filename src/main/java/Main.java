public class Main {
    public static void main(String[] args) {
        Lucky lucky = new Lucky();

        for (int n = 0; n <= 10; n++) {
                long res = lucky.run(n, 9*n);
                System.out.println("n = " + n + ", result = " + res);
        }

    }

    public static class Lucky {
        long all = 0;
        long count = 0;

        public long run(int n, int l) {
            all = 0;
            calculateCountOfAmountsHaveValue(n, l, 0);
            return all;
        }

        public void calculateSumByDigitCount(int digitCount, int l, int sum) {
            if (digitCount == 0) {
                if (sum == l) {
                    count++;
                }
                return;
            }

            for (int i = 0; i <= 9; i++) {
                calculateSumByDigitCount(digitCount - 1, l, sum + i);
            }
        }

        public void calculateCountOfAmountsHaveValue(int digitCount, int l, int sum){

            calculateSumByDigitCount(digitCount, l, 0);

            long square = count * count;

            all = all + square;
            count = 0;

            // выход
            if (l==0)
                return;

            calculateCountOfAmountsHaveValue(digitCount, l-1, sum);
        }
    }
}
