package algebraicAlgorithms;

import java.math.BigInteger;
import java.util.Arrays;

public class Main {
    static long[] primes;
    static int count;

    public static void main(String[] args) {
        long base = 2;
        long power = 30;

        System.out.println(binaryPower(base, power));
        System.out.println(multiplyPower(base, power));

        System.out.println(fibo(20));
        System.out.println(matrix(4));
        System.out.println(countPrimes(1000000));
        System.out.println(eratosphene(1000000));
    }

    public static long binaryPower(long base, long power) {
        long res = 1;

        while (power > 1) {
            if (power % 2 == 1) {
                res *= base;
            }
            base *= base;
            power /= 2;
        }

        if (power > 0) {
            res = res *base;
        }
        return res;
    }

    public static double binaryPower(double base, long power) {
        double res = 1;

        while (power > 1) {
            if (power % 2 == 1) {
                res *= base;
            }
            base *= base;
            power /= 2;
        }

        if (power > 0) {
            res = res *base;
        }
        return res;
    }

    public static long multiplyPower(long base, long power) {
        long base1 = base;

        int j = 1;

        for (; j <= power/2; j *= 2) {
            base *= base;
        }

        for (; j < power; j++) {
            base *= base1;
        }
        return base;
    }

    public static double fibo(long n) {
        double f = (1 + Math.sqrt(5))/2;
        double sqrtFive = Math.sqrt(5);

        return binaryPower(f, n)  / sqrtFive + 1/2;

    }

    public static BigInteger matrix(int input) {
        Matrix res = Matrix.IDENTITY;
        Matrix base = new Matrix(new BigInteger("1"),
                new BigInteger("1"),
                new BigInteger("1"),
                new BigInteger("0"));

        while (input > 1) {
            if ((input & 1) == 1){
                res = res.multiply(base);
            }
            base = base.multiply(base);
            input >>= 1;
        }
        return res.multiply(base).x21;
    }

    private static long countPrimes(int n) {
        count = 0;
        primes = new long[n / 2];
        primes[count++] = 2;

        for (int p = 3; p <= n ; p++) {
            if (isPrime(p))
                primes[count++] = p;
        }
        return count;
    }

    private static boolean isPrime(int n) {
        long sqrtN = (long) Math.sqrt(n);
        for (int i = 0; primes[i] <= sqrtN; i++) {
            if (n % primes[i] == 0)
                return false;
        }
        return true;
    }


    private static int eratosphene(int n) {
        long[] arr = new long[n+1];

        for (int i = 0; i <= n; i++) {
            arr[i] = i;
        }

        arr[0] = 0;
        arr[1] = 0;

        long sqrtN = (long) Math.sqrt(n);

        for (int i = 2; i <=sqrtN ; ++i) {
            if (arr[i] != 0)
                for (int j = i*i; j <= n; j+=i) {
                    arr[j] = 0;
                }
        }
        return (int) Arrays.stream(arr).filter(a -> a != 0).count();
    }


}
